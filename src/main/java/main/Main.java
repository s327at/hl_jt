package main;

import dbService.DBService;
import io.Jso;
import io.U_DService;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AccountDispatchServlet;
import servlets.FilterServlet;
import servlets.GroupServlet;
import servlets.IdRecomendServlet;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception{
        U_DService udService =new U_DService();
        DBService dbService = new DBService();
        dbService.createStartDbTables();
        dbService.printConnectInfo();

      //  Jso jso=new Jso();
     //   jso.readJsonStream();
      //     dbService.truncateAccountTables();
           MyThread mt=new MyThread();
           mt.start();



        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/*");
        context.addServlet(new ServletHolder(new FilterServlet(udService)), "/accounts/filter/");
        context.addServlet(new ServletHolder(new GroupServlet(udService)), "/accounts/group/");
         context.addServlet(new ServletHolder(new AccountDispatchServlet(udService)), "/accounts/*");
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();

    }
}



class MyThread extends Thread{

    public void run() {

        try {
            Jso jso=new Jso();
            jso.readJsonStream();
            //   dbService.truncateAccountTables();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
