package com.wenku.convert.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sandy on 22/09/2017.
 */
public class CssUtils {

    public static List<String> getSpecialFonts(String cssFile){
        File file = new File(cssFile);
        if (!file.exists()){
            return null;
        }
        List<String> fontNames = new ArrayList<>();
        try {
            String css = FileUtils.readFileToString(file);
            Pattern pattern = Pattern.compile("font-family:'(\\w+)';");
            Matcher matcher = pattern.matcher(css);
            while (matcher.find()){
                fontNames.add(matcher.group(1));
                //System.out.println(matcher.group(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fontNames;
    }

    public static void fixCss(String cssFile,List<String> fonts, String suffix){
        if (CollectionUtils.isEmpty(fonts)){
            return;
        }
        try {
            File file = new File(cssFile);
            String css = FileUtils.readFileToString(file);
            for(String font:fonts){
                css = css.replaceAll(font,font + suffix);
            }
            System.out.println("--------------");
            System.out.println(css);
            //FileUtils.writeStringToFile(file,css);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergeCssFiles(String out,boolean removeSrc,String... css){
        File outFile = new File(out);
        if (outFile.exists()){
            outFile.delete();
        }
        try {
            for (String s : css) {
                File f = new File(s);
                String data = FileUtils.readFileToString(f);
                FileUtils.writeStringToFile(outFile,data,true);
                if (removeSrc){
                    f.delete();
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception{
        String css1 = "/Users/sandy/test-data/html-0/fontFaces.css";
        String css2 = "/Users/sandy/test-data/html-0/styles.css";
        List<String> fonts = getSpecialFonts(css1);
        String suffix =  RandomStringUtils.randomAlphanumeric(8);
        fixCss(css1,fonts,suffix);
        fixCss(css2,fonts,suffix);
        mergeCssFiles("/Users/sandy/test-data/html-0/wkstyles.css",false,css1,css2);
//        String s = FileUtils.readFileToString(new File("/Users/sandy/test-data/html-0/fontFaces.css"));
//

//        System.out.println(fixCss(s,suffix));

    }
}
