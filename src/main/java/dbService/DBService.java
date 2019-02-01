package dbService;

import com.google.gson.JsonArray;
import dbService.dao.AccountDAO;
import dbService.prepare.PrepareFilter;
import dbService.prepare.PrepareGroup;
import dbService.prepare.PrepareIdRecomend;
import io.Account;
import io.Likes;
import io.Premium;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DBService {

    private  Connection connection;

    public DBService() {

        this.connection = getMysqlConnection();

    }

public void createStartDbTables() {
    AccountDAO dao = new AccountDAO(connection);
    try {
      //  dao.createDB();
        dao.createTableAcc_f();
        dao.createTableLikes();
        dao.createTableInterests();
    } catch (SQLException e) {
e.printStackTrace();

    }
}

    public void addAccount(Integer id_acc, String email, String fname, String sname, String phone, String sex, Date birth, String country, String city, Date joined, String status, Premium premium) throws DBException {
        try {
            connection.setAutoCommit(false);
            AccountDAO dao = new AccountDAO(connection);
            dao.createTableAcc_f();
            dao.insertAccountData(id_acc, email, fname, sname, phone, sex, birth, country, city, joined, status, premium);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void addLikes(Integer id_acc, List<Likes> likes) throws DBException {
        try {
            connection.setAutoCommit(false);
            AccountDAO dao = new AccountDAO(connection);
            dao.createTableLikes();
            dao.insertLikesData(id_acc, likes);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void addInterests(Integer id_acc, List<String> interests) throws DBException {
        try {
            connection.setAutoCommit(false);
            AccountDAO dao = new AccountDAO(connection);
            dao.createTableInterests();
            dao.insertInterestsData(id_acc, interests);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }




    public String  getResultSetDataFilter(Map reqMap)  {
        String a= null;
        try {
            AccountDAO dao = new AccountDAO(connection);
            PrepareFilter rr=new PrepareFilter();
            String query=rr.prepareRequest( reqMap);
            a=  dao.getRSData( query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public String  getResultSetDataGroup(Map reqMap)  {
        String  a=null;
        try {
            AccountDAO dao = new AccountDAO(connection);
            PrepareGroup pg=new PrepareGroup();
            String query=pg.prepareRequestGroup( reqMap);
            a=  dao.getRSData( query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public String  getPrepareUserById(Map reqMap, String idd)  {
        String  a=null;

        try {
            AccountDAO dao = new AccountDAO(connection);
            PrepareIdRecomend pg=new PrepareIdRecomend();
       String s=pg.prepareUserById(idd);
        // dao.getUserDataById(pg.prepareUserById(idd));
            String where=pg.prepareCompatibleUsers( reqMap);
            String rs=dao.getUserById(s, pg, where);

            a=  dao.getRSData(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

public Integer getResponseStatus(){
        AccountDAO dao = new AccountDAO(connection);
       return  dao.getResponseStatus();
}


    public void cleanUp() throws DBException {
        AccountDAO dao = new AccountDAO(connection);
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void truncateAccountTables() throws DBException {
        AccountDAO dao = new AccountDAO(connection);
        try {
            dao.truncateAccountTables();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                  //  append("172.17.0.2:").           //host name
                    //    append("127.0.0.2:").
                     //      append("db:").
                  //  append("app_network:").
                 //   append("dbContainer:").
                    append("localhost:").
                   append("3306/").                //port
                    append("dbe?").          //db name
                    append("user=usr&").          //login
                    append("password=11111&").       //password
                   // append("charset=utf8&").
                    append("characterEncoding=utf8&");
              //      append("useSSL=false");    //for mysql 8 only

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
           // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_example?useSSL=false", "usr", "11111");
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
