package dbService.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dbService.executor.Executor;
import dbService.prepare.PrepareIdRecomend;
import io.Likes;
import io.Premium;
import io.Account;
import io.ResultSetToJson;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io.IOException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AccountDAO {

    private Executor executor;
    private Connection connection;
    private Integer response_status;

    public AccountDAO(Connection connection) {
        this.executor = new Executor(connection);
        this.connection=connection;

    }


    public String  getRSData(String query) throws SQLException {

        Statement stmt = null;


        JsonArray ar= new JsonArray();

        try {
              stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);
            JsonObject ob;
            JsonObject ob1=new JsonObject();

            if (result.next() == false) {
                System.out.println("ResultSet in empty in Java");
            }else{

               do{
                 ob=new JsonObject();
                   ResultSetMetaData rsmd=result.getMetaData();
                   int count=rsmd.getColumnCount();
                   out:
                   for(int i=1; i<=count; i++) {
                       String name_col = rsmd.getColumnName(i);
                       String rs_type = rsmd.getColumnTypeName(i);

                       switch (rs_type) {
                           // если в таблицу добавится еще один инт то тут сломается логика
                           case "INT":
                               ob.addProperty("id", result.getInt(i)); // writer.name("id").value(result.getInt(i));
                               break;

                           case "BIGINT":
                               ob.addProperty(name_col, result.getInt(i)); // writer.name("id").value(result.getInt(i));
                               break;

                           case "VARCHAR":
                               ob.addProperty(name_col, result.getString(i)); //writer.name(name_col).value(result.getString(i));
                               break;

                           case "DATETIME":

                               if(name_col.equals("birth")  ){
                                   ob.addProperty(name_col, (result.getTimestamp(i)).getTime() / 1000);
                                   break;
                               }

                               if (result.getDate("premiumfinish")==null | result.getDate("premiumstart")==null) {
                                   break out;
                               }
                               if(name_col.equals("premiumstart")  ){
                                   ob1.addProperty("start",(result.getTimestamp(name_col)).getTime() / 1000);
                           }
                               if(name_col.equals("premiumfinish") ) {
                                   ob1.addProperty("finish", (result.getTimestamp(name_col)).getTime() / 1000);
                               }

                               if(result.getDate("premiumfinish")!=null | result.getDate("premiumstart")!=null)  {
                                   ob.add("premium", ob1);
                                   break;
                               }

                               ob.addProperty(name_col, (result.getTimestamp(i)).getTime() / 1000);
                               break;
                       }
                   }
                   ar.add(ob);

               }while (result.next());

            }

          //  if (stmt != null) { stmt.close(); }


              } catch (SQLException  e ) {
           e.printStackTrace();
        }finally {
            if (stmt != null) { stmt.close(); }
        }
return ar.toString();

    }

public void setResponseStatus(Integer status){
        this.response_status=status;
 }

public Integer getResponseStatus(){
        return response_status;
}


            public String getUserDataById(String query) throws SQLException {

String s=null;
                ResultSet result=null;
                try ( Statement stmt = connection.createStatement()){
                     result = stmt.executeQuery(query);

                        if (result.next() == false) {
                        System.out.println("ResultSet in empty in Java");
                        setResponseStatus(404);
                    }


                } catch (SQLException e ) {
                    e.printStackTrace();
                }

                return s;
            }


    public String getUserById(String query,  PrepareIdRecomend pg , String where) throws SQLException {

        String s=null;
        ResultSet result=null;
        String ss=null;
        try ( Statement stmt = connection.createStatement()){
            result = stmt.executeQuery(query);

            if (result.next() == false) {
                System.out.println("ResultSet in empty in Java");
                setResponseStatus(404);
            }
           ss=pg.prepareUsersDataById(result, where  );



        } catch (SQLException e ) {
            e.printStackTrace();
        }

        return ss;

    }




    public void insertAccountData(Integer id_acc, String email, String fname, String sname, String phone, String sex, Date  birth, String country, String city, Date joined, String status, Premium premium) throws SQLException {
if(premium!=null){
    executor.execUpdate("insert into acc_f (id_acc, email, fname, sname, phone, sex, birth, country, city, joined, status, premium_start, premium_finish) values ('" + id_acc + "', '" + email + "', '" + fname + "', '" + sname + "', '" + phone + "', '" + sex + "', '" + birth + "', '" + country + "', '" + city + "', '" + joined + "', '" + status + "', '" + premium.getStart() + "', '" + premium.getFinish() + "')");
}
        executor.execUpdate("insert into acc_f (id_acc, email, fname, sname, phone, sex, birth, country, city, joined, status) values ('" + id_acc + "', '" + email + "', '" + fname + "', '" + sname + "', '" + phone + "', '" + sex + "', '" + birth + "', '" + country + "', '" + city + "', '" + joined + "', '" + status  + "')");
    }


    public void insertLikesData(Integer id_acc, List<Likes> likes) throws SQLException {
        for ( Likes lai: likes) {
            executor.execUpdate("insert into likes (id_acc, id_likes, ts) values ('" + id_acc + "',  '" + lai.getId() + "', '" + lai.getTs() + "')");
        }
    }

    public void insertInterestsData(Integer id_acc, List<String> interests) throws SQLException {

        for ( String str: interests) {
            executor.execUpdate("insert into interests (id_acc, interes) values ('" + id_acc + "',  '" + str  + "')");
        }
    }


    public void createDB() throws SQLException {
        executor.execUpdate("create database  dbe CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");

    }

    public void createTableLikes() throws SQLException {
        executor.execUpdate("create table if not exists likes ( id INT NOT NULL AUTO_INCREMENT  PRIMARY KEY, id_acc INT NULL DEFAULT NULL, id_likes INT NULL DEFAULT NULL, ts DATETIME NULL DEFAULT NULL)CHARACTER SET utf8 COLLATE utf8_bin");

    }

    public void createTableInterests() throws SQLException {
        executor.execUpdate("create table if not exists interests (id INT NOT NULL AUTO_INCREMENT  PRIMARY KEY, id_acc INT NOT NULL, interes VARCHAR(100) NULL DEFAULT NULL )CHARACTER SET utf8 COLLATE utf8_bin");

    }

    public void createTableAcc_f() throws SQLException {
        executor.execUpdate("create table if not exists acc_f (id int auto_increment PRIMARY KEY , id_acc  int  not null, email  varchar(100) not null, fname varchar(50)  null, sname varchar(50)  null, phone  varchar(16)  null, sex  varchar(50)  null, country  varchar(50)  null, city  varchar(50)  null, joined  datetime     null, status  varchar(50)  null, premium_start  datetime     null, birth datetime     null, premium_finish datetime     null  )CHARACTER SET utf8 COLLATE utf8_bin");

    }

    public void truncateAccountTables() throws SQLException {
        executor.execUpdate("truncate table acc_f");
        executor.execUpdate("truncate table interests");
        executor.execUpdate("truncate table likes");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }


}
