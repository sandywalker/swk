package com.wenku.convert;

import com.aspose.cells.*;
import com.aspose.cells.HtmlSaveOptions;
import com.aspose.cells.PdfSaveOptions;
import com.aspose.pdf.Image;
import com.aspose.pdf.Page;
import com.aspose.pdf.Rectangle;
import com.aspose.slides.*;
import com.aspose.slides.SaveFormat;
import com.aspose.words.*;
import com.wenku.convert.utils.HtmlUtils;
import com.wenku.define.Result;
import com.wenku.queue.ProcState;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by sandy on 11/09/2017.
 */
public class ConvertTask implements Callable<Result> {

    private static final Logger logger = LoggerFactory.getLogger(ConvertTask.class);

    private String filePath;
    private String htmlPath;
    private String pdfPath;
    private String fontPath;
    private Long qid;
    private Long did;

    private ConvertDao convertDao;

    public ConvertTask(ConvertDao convertDao, Long qid,Long did, String filePath, String pdfPath, String htmlPath,String fontPath) {
        Assert.hasText(filePath,"filePath must not empty!");
        Assert.hasText(htmlPath,"htmlPath must not empty!");
        Assert.hasText(pdfPath,"pdfPath must not empty!");
        Assert.hasText(pdfPath,"fontPath must not empty!");
        this.filePath = filePath;
        this.pdfPath = pdfPath;
        this.htmlPath = htmlPath;
        this.fontPath = fontPath;
        File htmlDir = new File(htmlPath);
        if (!htmlDir.exists()){
            htmlDir.mkdir();
        }

        this.qid  = qid;
        this.did = did;
        this.convertDao = convertDao;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getImagePath() {
        return htmlPath;
    }

    public void setImagePath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    @Override
    public Result call() throws Exception {

        File file = new File(filePath);
        if (!file.exists()){
            logger.debug("file not exists, filepath: " + filePath);
            convertDao.updateQueueState(qid, ProcState.failed,"file: " + filePath + " not exists!");
            return Result.fail("file not exists");
        }
        String ext = FilenameUtils.getExtension(filePath);
        this.convertDao.updateQueueState(qid, ProcState.processing,null);
        logger.debug("begin convert file: " + filePath);
        Result result;

        FontSettings fs = FontSettings.getDefaultInstance();

        fs.setFontsFolder(fontPath,false);
        fs.setDefaultFontName("STSong");
        fs.setFontSubstitutes("楷体_GB2312","STKaiti");
        fs.setFontSubstitutes("仿宋_GB2312","STFangsong");
        fs.setFontSubstitutes("宋体_GB2312","STSong");

        if ("docx".equalsIgnoreCase(ext)||"doc".equalsIgnoreCase(ext)){
            result = convertWord();
        }else if ("xls".equalsIgnoreCase(ext)||"xlsx".equalsIgnoreCase(ext)){
            result = convertExcel();
        }else if ("ppt".equalsIgnoreCase(ext)||"pptx".equalsIgnoreCase(ext)){
            result = convertPPT();
        }else if ("pdf".equalsIgnoreCase(ext)){
            result = convertPDF();
        }else if ("jpg".equalsIgnoreCase(ext)||"jpeg".equalsIgnoreCase(ext)
                ||"bmp".equalsIgnoreCase(ext)||"png".equalsIgnoreCase(ext)||"gif".equalsIgnoreCase(ext)){
            result = convertImage();
        }else{
            logger.warn("file type not supported yet! filepath:" + filePath);
            convertDao.updateQueueState(qid, ProcState.failed,"file: " + filePath + " not support!");
            result = Result.fail("file type not support yet");
        }
        if (result.isSuccess()){
            convertDao.updateDocPageCount(did,(Integer) result.getData());
            convertDao.updateQueueState(qid,ProcState.success,null);
        }else{
            convertDao.updateQueueState(qid, ProcState.failed,result.getMessage());
        }

        return result;
    }

    private void pdfToHtml(){
        com.aspose.pdf.Document pdfDoc = new com.aspose.pdf.Document(pdfPath);


        logger.debug("converting html for ie...");
        com.aspose.pdf.HtmlSaveOptions pdfOptions = new com.aspose.pdf.HtmlSaveOptions();
//            pdfOptions.setFixedLayout(true);
        pdfOptions.setSplitCssIntoPages(true);
        pdfOptions.RasterImagesSavingMode =  com.aspose.pdf.HtmlSaveOptions.RasterImagesSavingModes.AsEmbeddedPartsOfPngPageBackground;
        //pdfOptions.FontSavingMode = com.aspose.pdf.HtmlSaveOptions.FontSavingModes.SaveInAllFormats;
        pdfOptions.setSplitIntoPages(true);

        pdfOptions.setConvertMarkedContentToLayers(true);
        pdfOptions.setUseZOrder(true);

        String dirPath = htmlPath + "/main";
        File dir = new File(dirPath);
        if (dir.exists()){
            try {
                FileUtils.deleteDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        pdfDoc.save(String.format("%s/main/page.html",htmlPath),pdfOptions);

        int pageCount = pdfDoc.getPages().size();
        for(int i=1;i<=pageCount;i++){
            String html = String.format("%s/main/page%d.html",htmlPath,i);
            HtmlUtils.fixHtml(html);
        }
    }

    public Result convertWord(){
        Integer pageCount = 0;
        try {
            Document word = new Document(filePath);

            HtmlFixedSaveOptions fixedSaveOptions = new HtmlFixedSaveOptions();
            fixedSaveOptions.setPrettyFormat(true);
            fixedSaveOptions.setShowPageBorder(false);
            fixedSaveOptions.setExportEmbeddedCss(false);
            fixedSaveOptions.setExportEmbeddedFonts(true);

            pageCount = word.getPageCount();
            logger.debug("page count: " + pageCount);
            for (int i = 0; i < pageCount; i++) {
                fixedSaveOptions.setPageIndex(i);
                fixedSaveOptions.setPageCount(1);
                String html = String.format("%s/modern/page%d.html",htmlPath,i+1);
                word.save( html, fixedSaveOptions);
            }
            logger.debug("html converted, htmlPath: " + htmlPath);

            word.save(pdfPath);
            logger.debug("pdf converted, pdfPath: " + pdfPath);

            pdfToHtml();


            logger.debug("convert complete");

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
        return Result.success("",pageCount);

    }

    public Result convertExcel(){
        Integer pageCount = 0;
        try {


            Workbook workbook = new Workbook(filePath);

            HtmlSaveOptions htmlSaveOptions = new HtmlSaveOptions();

            workbook.getWorksheets().getCount();
            logger.debug("page count: " + pageCount);

            logger.debug("html converted, htmlPath: " + htmlPath);

            com.aspose.cells.PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
            pdfSaveOptions.setDefaultFont("STSong");
            pdfSaveOptions.setFontSubstitutionCharGranularity(true);


            workbook.save(pdfPath,pdfSaveOptions);
            logger.debug("pdf converted, pdfPath: " + pdfPath);

            pdfToHtml();

            logger.debug("convert complete");

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
        return Result.success("",pageCount);

    }

//    private void replacePPTFont(IFontSubstRuleCollection fontSubstRuleCollection,String sourceName,String targetName){
//        IFontData sourceFont = new FontData(sourceName);
//        IFontData destFont = new FontData(targetName);
//        IFontSubstRule fontSubstRule = new FontSubstRule(sourceFont, destFont, FontSubstCondition.WhenInaccessible);
//        fontSubstRuleCollection.add(fontSubstRule);
//    }
    private void replacePPTFont(Presentation pres,String sourceName,String targetName){
        IFontData sourceFont = new FontData(sourceName);
        IFontData destFont = new FontData(targetName);
        pres.getFontsManager().replaceFont(sourceFont,destFont);
    }

    public Result convertPPT(){
        Integer pageCount = 0;
        try{


            Presentation pres = new Presentation(filePath);
//
//            for(IFontData iFontData: pres.getFontsManager().getFonts()){
//                System.out.println(iFontData.getFontName());
//            };

            //FontsLoader.loadExternalFonts(new String[]{fontPath+"/STKAITI.TTF",fontPath+"/STSONG.TTF",fontPath+"/STFANGSO.TTF"});

            pageCount = pres.getSlides().size();
            logger.debug("page count: " + pageCount);

            IFontSubstRuleCollection fontSubstRules = new FontSubstRuleCollection();
            this.replacePPTFont(pres,"楷体_GB2312","STKaiti");
            this.replacePPTFont(pres,"仿宋_GB2312","STFangsong");
            this.replacePPTFont(pres,"宋体_GB2312","STSong");
            this.replacePPTFont(pres,"宋体","STSong");

            PdfOptions pdfOptions = new PdfOptions();
//            pdfOptions.setAdditionalCommonFontFamilies(new String[]{"STKaiti","STKaiti","STKaiti"});

            //pres.getFontsManager().setFontSubstRuleList(fontSubstRules);
//            for(int i=0;i<pageCount;i++){
//                ISlide slide = pres.getSlides().get_Item(i);
//                BufferedImage html = slide.getThumbnail(1f, 1f);
//                String htmlName = htmlPath + File.separator + (i) + ".png";
//                ImageIO.write(html,"png",new File(htmlName));
//                logger.debug("convert to html: " + htmlName);
//            }
//            logger.debug("html converted, htmlPath: " + htmlPath);


            pres.save(pdfPath, com.aspose.slides.SaveFormat.PdfNotes,pdfOptions);
            logger.debug("pdf converted, pdfPath: " + pdfPath);


            HtmlOptions htmlOptions = new HtmlOptions();
            htmlOptions.setHtmlFormatter(HtmlFormatter.createCustomFormatter(new CustomFormattingController()));

            // Saving File
            logger.debug("converting html...");
            File path = new File(htmlPath+"/modern");
            if (!path.exists()){
                path.mkdir();
            }
            for (int i = 0; i < pres.getSlides().size(); i++)
            {
                pres.save(htmlPath + "/modern/page" + (i + 1) + ".html", new int[] { i + 1 }, SaveFormat.Html, htmlOptions);
            }

            //for ie
            logger.debug("converting html for old ie...");
            pdfToHtml();


            logger.debug("convert complete");

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
        return Result.success("",pageCount);
    }

    public Result convertPDF(){
        Integer pageCount = 0;
        try {
            com.aspose.pdf.Document pdfDocument = new com.aspose.pdf.Document(filePath);
            pageCount = pdfDocument.getPages().size();
            logger.debug("page count: " + pageCount);
//            Resolution resolution = new Resolution(120);
//            PngDevice pngDevice = new PngDevice(resolution);
//            for (int i = 0; i < pageCount; i++) {
//                String htmlName = htmlPath + File.separator + (i) + ".png";
//                java.io.OutputStream htmlStream = new java.io.FileOutputStream(htmlName);
//                pngDevice.process(pdfDocument.getPages().get_Item(i+1), htmlStream);
//                logger.debug("convert to html: " + htmlName);
//            }
//            logger.debug("html converted, htmlPath: " + htmlPath);


            FileUtils.copyFile(new File(filePath),new File(pdfPath));
            logger.debug("pdf copied, pdfPath: " + pdfPath);

            pdfToHtml();

            logger.debug("convert complete");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
        return Result.success("",pageCount);

    }

    public Result convertImage(){
        try{

            logger.debug("page count: 1");
            FileUtils.copyFile(new File(filePath),new File(htmlPath));
            logger.debug("html copied, htmlPath: " + htmlPath);

            com.aspose.pdf.Document doc = new com.aspose.pdf.Document();
            Page page = doc.getPages().add();
            java.io.FileInputStream fs = new java.io.FileInputStream(filePath);
            page.getPageInfo().getMargin().setBottom(0);
            page.getPageInfo().getMargin().setTop(0);
            page.getPageInfo().getMargin().setLeft(0);
            page.getPageInfo().getMargin().setRight(0);

            page.setCropBox(new Rectangle(0, 0, 400, 400));
            Image html1 = new Image();
            page.getParagraphs().add(html1);
            html1.setImageStream(fs);
            doc.save(pdfPath);
            logger.debug("pdf converted, pdfPath: " + pdfPath);

            pdfToHtml();

            logger.debug("convert complete");

        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
        return Result.success("",1);
    }

    private class CustomFormattingController implements IHtmlFormattingController{

        @Override
        public void writeDocumentStart(IHtmlGenerator iHtmlGenerator, IPresentation iPresentation) {

        }

        @Override
        public void writeDocumentEnd(IHtmlGenerator iHtmlGenerator, IPresentation iPresentation) {

        }

        @Override
        public void writeSlideStart(IHtmlGenerator iHtmlGenerator, ISlide iSlide) {
        }

        @Override
        public void writeSlideEnd(IHtmlGenerator iHtmlGenerator, ISlide iSlide) {

        }

        @Override
        public void writeShapeStart(IHtmlGenerator iHtmlGenerator, IShape iShape) {

        }

        @Override
        public void writeShapeEnd(IHtmlGenerator iHtmlGenerator, IShape iShape) {

        }
    }


}
