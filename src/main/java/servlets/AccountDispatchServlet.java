package servlets;

import io.U_DService;
import org.eclipse.jetty.http.pathmap.PathSpec;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class AccountDispatchServlet extends HttpServlet {

    private U_DService udService;

    public AccountDispatchServlet(U_DService udService) {
        this.udService = udService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map<String, String []> reqMap =new HashMap<>();
        String [] req=request.getPathInfo().split("/");
        int count=req.length;
        reqMap =request.getParameterMap();

        if(count==3) {
            switch (req[2]) {
                case "recommend":
                    response.setContentType("text/html;charset=utf-8");
                    //  response.setStatus(HttpServletResponse.SC_OK);
                //    response.setStatus(udService.getResponseStatus());
                    ServletOutputStream out = response.getOutputStream();
                    //  out.println(200);
                    //sem code response server
                    out.print(udService.getUsersByReccomend(out, reqMap, req[1]));
                    break;


            }

        }else if(count==2){
            ServletOutputStream out = response.getOutputStream();
            //sem code response server
            out.print("ggggggggggggggggggggggggg");

        }

    }
}