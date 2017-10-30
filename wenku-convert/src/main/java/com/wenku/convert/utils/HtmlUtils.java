package com.wenku.convert.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.html.CSS;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sandy on 22/09/2017.
 */
public class HtmlUtils {

    private static final String SVG_HEAD = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\t<head>\n" +
            "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
            "\t\t<title>\n" +
            "\t\t</title>\n" +
            "\t\t<style type=\"text/css\">\n" +
            "\t\t\t\n" +
            "\t\t\tbody{\n" +
            "\t\t\t\tmargin:0;\n" +
            "\t\t\t\tpadding:0;\n" +
            "\t\t\t\toverflow: hidden;\n" +
            "\t\t\t}\n" +
            "\t\t\t#content{\n" +
            "\t\t\t\tmargin:0 auto;\n" +
            "\t\t\t}\n" +
            "\t\t</style>\n" +
            "\t</head>\n" +
            "<body>\t";

    private static final String SVG_FOOT = "<script type=\"text/javascript\">\n" +
            "\n" +
            "\t\t(function(){\n" +
            "\t\t\t\tvar hash = window.location.hash;\n" +
            "\t\t\t\tif (hash){\n" +
            "\t\t\t\t\thash = hash.substring(1);\n" +
            "\t\t\t\t\tvar width = hash.split('=')[1];\n" +
            "\t\t\t\t\tvar content = document.getElementById('awpage');\n" +
            "\t\t\t\t\tvar scale = 1;\n" +
            "\t\t\t\t\tvar rect = content.getBoundingClientRect(); // get the bounding rectangle\n" +
            "\t\t\t\t\tvar elW = rect.width;\n" +
            "\t\t\t\t\tscale = width / elW ;\n" +
            "\t\t\t\t\tif (scale<1){\n" +
            "\t\t\t\t\t\tvar html = document.getElementsByTagName('html');\n" +
            "\t\t\t\t\t\thtml[0].style.transformOrigin = '0 0';\n" +
            "\t\t\t\t\t\thtml[0].style.transform = 'scale('+ scale+')';\n" +
            "\t\t\t\t\t\thtml[0].style.msTransformOrigin = '0 0';\n" +
            "\t\t\t\t\t\thtml[0].style.msTransform = 'scale('+ scale+')';\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t})();\n" +
            "\t\t</script>\n" +
            "\n" +
            "</body>\n" +
            "</html>";

    private static final String CSS_FIX = "<style>body{marign:0;padding:0;overflow: hidden;} .awpage{margin:0 auto;}</style>";

    private static final String JS_FIX = "<script type=\"text/javascript\">\n" +
            "\t\t\t(function(){\n" +
            "\t\t\t\t\tvar hash = window.location.hash;\n" +
            "\t\t\t\t\tif (hash){\n" +
            "\t\t\t\t\t\thash = hash.substring(1);\n" +
            "\t\t\t\t\t\tvar width = hash.split('=')[1];\n" +
            "\t\t\t\t\t\tvar nodes = document.querySelectorAll('.awpage');\n" +
            "\t\t\t\t\t\tvar scale = 1;\n" +
            "\t\t\t\t\t\tfor(var i=0;i < nodes.length;i++){\n" +
            "\t\t\t\t\t\t\tvar node = nodes[i];\n" +
            "\t\t\t\t\t\t\tvar elW = node.offsetWidth;\n" +
            "\t\t\t\t\t\t\tvar scale = width /elW ;\n" +
            "\t\t\t\t\t\t}\n" +
            "\n" +
            "\t\t\t\t\t\tif (scale<1){\n" +
            "\t\t\t\t\t\t\tvar html = document.getElementsByTagName('html');\n" +
            "\t\t\t\t\t\t\thtml[0].style.transformOrigin = '0 0';\n" +
            "\t\t\t\t\t\t\thtml[0].style.transform = 'scale('+ scale+')';\n" +
            "\t\t\t\t\t\t\thtml[0].style.msTransformOrigin = '0 0';\n" +
            "\t\t\t\t\t\t\thtml[0].style.msTransform = 'scale('+ scale+')';\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t})();\n" +
            "\t\t</script>";


    public static String getBodyContent(String htmlPath){
        File htmlFile = new File(htmlPath);
        try {
            String html = FileUtils.readFileToString(htmlFile);
            Pattern pattern = Pattern.compile("<body>(.*)</body>");
            Matcher matcher =  pattern.matcher(html);
            if (matcher.find()){
                return matcher.group(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void simplifyHtml(String htmlPath){
        File htmlFile = new File(htmlPath);
        String content = getBodyContent(htmlPath);
        if (content!=null){
            try {
                FileUtils.write(htmlFile,content,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String fixSvg(String svg){
        String result = SVG_HEAD;
        if (svg.startsWith("<svg")){
            String s1 = svg.substring(0,4);
            String s2 = svg.substring(6);
            result = SVG_HEAD + s1 + " id=\"awpage\" " + s2 + SVG_FOOT;
            return result;
        }
        return svg;
    }

    public static String optimizeHtml(String html){
        int idx1 = html.indexOf("</head>");
        int idx2 = html.indexOf("</body>");

        if (idx1>0&&idx2>idx1) {
            String s1 = html.substring(0, idx1);
            String s2 = html.substring(idx1, idx2);
            s2 = s2.replaceFirst("stl_02","stl_02 awpage");
            String s3 = html.substring(idx2);
            return s1 + CSS_FIX + s2 + JS_FIX + s3;
        }else{
            return html;
        }
    }

    public static void fixHtml(String htmlPath){
        File htmlFile = new File(htmlPath);
        StringBuilder builder=new StringBuilder();
        try {
            String html = FileUtils.readFileToString(htmlFile);
            char[] chars = html.toCharArray();
            for(int i = 0; i < chars.length; i ++) {
                if (chars[i]<= 57343){
                    builder.append(chars[i]);
                }
//                if((chars[i] >= 19968 && chars[i] <= 40869) || (chars[i] >= 97 && chars[i] <= 122) || (chars[i] >= 65 && chars[i] <= 90)
//                        ) {
//                    builder.append(chars[i]);
//                }
            }
            String content = builder.toString();
            if (content.startsWith("<svg")){
                content = fixSvg(content);
            }else{
                content = optimizeHtml(content);
            }

            FileUtils.writeStringToFile(htmlFile,content,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       // simplifyHtml("/Users/sandy/test-data/html-1.html");
//        for(int i=1;i<17;i++) {
//            fixHtml("/Users/sandy/test-data/tt/html-" + i + ".html");
//            System.out.println(i);
//        }
        //System.out.println(getBodyContent("/Users/sandy/test-data/html-1.html"));

        String s = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "\t\t<title>\n" +
                "\t\t</title>\n" +
                "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"page2/fontFaces.css\" media=\"all\" />\n" +
                "\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"page2/styles.css\" media=\"all\" />\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<div class=\"awdiv awpage\" style=\"width:595.3pt; height:841.9pt;\">\n" +
                "\t\t\t\n" +
                "\t\t</div>\n" +
                "\t</body>\n" +
                "</html>";

        int idx1 = s.indexOf("</head>");
        int idx2 = s.indexOf("</body>");

        String s1 = s.substring(0,idx1);
        String s2 = s.substring(idx1,idx2);
        String s3 = s.substring(idx2);
        System.out.println(s1 + CSS_FIX + s2 + JS_FIX + s3);

    }
}
