package com.seniorgeek.samples.jetty_springmvc_json;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Application entry point. Initializes jetty with spring mvc.
 * 
 * @author jformoso
 */
public class App {
    public static void main(String[] args) {

        Server server = new Server(8082);
        Context root = new Context(server, "/", Context.SESSIONS);
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setContextConfigLocation("classpath:application-context.xml");

        root.addServlet(new ServletHolder(dispatcherServlet), "/*");

        try {
            server.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
