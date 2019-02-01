package io;

import dbService.DBException;
import dbService.DBService;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class U_DService {

    DBService dbService = new DBService();

    ResultSetToJson rstj=new ResultSetToJson();

    public void addNewAccount(Integer id_acc, String email, String fname, String sname, String phone, String sex, Date birth, String country, String city, Date joined, String status, Premium premium) {
              try {
             dbService.addAccount(id_acc, email,  fname,  sname, phone,  sex,   birth,  country,  city,  joined,  status, premium);
        }catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void addNewLikes(Integer id_acc, List<Likes> likes) {
              try {
            dbService.addLikes(id_acc, likes);
        }catch (DBException e) {
            e.printStackTrace();
        }
    }

    public void addNewInterests(Integer id_acc, List<String> interests) {
        try {
            dbService.addInterests(id_acc, interests);
        }catch (DBException e) {
            e.printStackTrace();
        }
    }


    public String  getUsersByReccomend(ServletOutputStream out, Map reqMap, String idd) throws IOException {
        return    rstj.writeToJsonFilter( out, dbService.getPrepareUserById(reqMap,  idd));
    }


    public String  getResultSetDataFilter(ServletOutputStream out, Map reqMap) throws IOException {

        return   rstj.writeToJsonFilter( out,   dbService.getResultSetDataFilter(reqMap));
    }


    public String  getResultSetDataGroup(ServletOutputStream out, Map reqMap) throws IOException {
        return   rstj.writeToJsonGroup( out,   dbService.getResultSetDataGroup(reqMap));
    }

    public Integer getResponseStatus(){

        return dbService.getResponseStatus();
    }

   }
