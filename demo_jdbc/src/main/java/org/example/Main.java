package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080); // Cổng chạy Web

        String webappDirLocation = "src/main/webapp/";
        Context ctx = tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        File additionWebInfClasses = new File("target/classes");
        if (additionWebInfClasses.exists()) {
            org.apache.catalina.WebResourceRoot resources = new org.apache.catalina.webresources.StandardRoot(ctx);
            resources.addPreResources(new org.apache.catalina.webresources.DirResourceSet(
                    resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
            ctx.setResources(resources);
        }

        System.out.println("Đang khởi động Tomcat tại cổng 8080...");
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }
}