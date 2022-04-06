package com.kobeshow.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootTomcatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTomcatApplication.class, args);

//        new SpringApplicationBuilder(SpringbootTomcatApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);

//        SpringApplication application = new SpringApplication(SpringbootTomcatApplication.class);
//        application.setWebApplicationType(WebApplicationType.NONE);
//        application.run(args);
//
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8080);
//
//        Context context = tomcat.addContext("/", "/");
//        HttpServlet servlet = new HttpServlet() {
//            @Override
//            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                PrintWriter writer = resp.getWriter();
//                writer.println("<html><head><title>");
//                writer.println("Hello World");
//                writer.println("</title></head>");
//                writer.println("<body><h1><Hello Tomcat</h1><body>");
//                writer.println("</html>");
//            }
//        };
//        String servletName = "helloServlet";
//        tomcat.addServlet("/", servletName, servlet);
//        context.addServletMappingDecoded("/hello", servletName);
//
//        tomcat.start();
//        tomcat.getServer().await();
    }
}
