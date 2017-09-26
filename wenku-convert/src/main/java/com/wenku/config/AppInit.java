package com.wenku.config;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by sandy on 02/06/2017.
 */
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
        return super.registerServletFilter(servletContext, filter);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        com.aspose.words.License wordLicense = new com.aspose.words.License();
        com.aspose.cells.License cellsLicense = new com.aspose.cells.License();
        com.aspose.slides.License slidesLicense = new com.aspose.slides.License();
        com.aspose.pdf.License pdfLicense = new com.aspose.pdf.License();
        ResourceLoader loader = new DefaultResourceLoader();
        String path = "classpath:aspose/license.xml";
        try {
            wordLicense.setLicense(loader.getResource(path).getInputStream());
            cellsLicense.setLicense(loader.getResource(path).getInputStream());
            slidesLicense.setLicense(loader.getResource(path).getInputStream());
            pdfLicense.setLicense(loader.getResource(path).getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8",true);

        return new Filter[]{characterEncodingFilter};
    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ConvertConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {MVCConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
