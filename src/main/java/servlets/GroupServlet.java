package servlets;

import io.U_DService;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class GroupServlet extends HttpServlet{
    private U_DService udService;
    public GroupServlet(U_DService udService) {this.udService =udService;}

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map  reqMap =request.getParameterMap();
             response.setContentType("text/html;charset=utf-8");
      //  response.setStatus(HttpServletResponse.SC_OK);
        ServletOutputStream out = response.getOutputStream();
      //  out.println(200);
        //sem code response server
        out.print(udService.getResultSetDataGroup(out, reqMap));



    }

}
