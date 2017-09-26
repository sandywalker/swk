package com.wenku.convert.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sandy on 22/09/2017.
 */
public class HtmlUtils {

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
            FileUtils.writeStringToFile(htmlFile,builder.toString(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       // simplifyHtml("/Users/sandy/test-data/html-1.html");
        for(int i=1;i<17;i++) {
            fixHtml("/Users/sandy/test-data/tt/html-" + i + ".html");
            System.out.println(i);
        }
        //System.out.println(getBodyContent("/Users/sandy/test-data/html-1.html"));
    }
}
