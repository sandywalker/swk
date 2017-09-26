package com.wenku.doc.manager;

import com.wenku.define.Result;

import java.util.concurrent.Callable;

/**
 * Created by sandy on 11/09/2017.
 */
public class ConvertTask implements Callable<Result> {

    private String filePath;
    private String imagePath;
    private String pdfPath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    @Override
    public Result call() throws Exception {




        return null;
    }
}
