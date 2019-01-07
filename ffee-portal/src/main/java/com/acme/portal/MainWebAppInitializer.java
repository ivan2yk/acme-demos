package com.acme.portal;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Ivan on 6/01/2019.
 */
public class MainWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("com.acme");

        servletContext.addListener(new ContextLoaderListener(root));
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");

        ServletRegistration.Dynamic appServlet = servletContext.addServlet("mvc", new DispatcherServlet(root));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }

}
