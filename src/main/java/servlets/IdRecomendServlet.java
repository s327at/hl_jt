package servlets;

import io.U_DService;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class IdRecomendServlet extends HttpServlet{
    private U_DService udService;
    public IdRecomendServlet(U_DService udService) {this.udService =udService;}

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

         Map<String, String []> reqMap =new HashMap<>();
        String [] req=request.getPathInfo().split("/");


        reqMap =request.getParameterMap();
          response.setContentType("text/html;charset=utf-8");
        //  response.setStatus(HttpServletResponse.SC_OK);
        response.setStatus(udService.getResponseStatus());
        ServletOutputStream out = response.getOutputStream();
        //  out.println(200);
        //sem code response server
        out.print(udService.getUsersByReccomend(out, reqMap, req[1]));



    }
}
