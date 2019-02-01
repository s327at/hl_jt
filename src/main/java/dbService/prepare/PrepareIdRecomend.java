package dbService.prepare;

import dbService.Dictionary;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import static java.sql.Types.NULL;

public  class PrepareIdRecomend {


    public String prepareCompatibleUsers(Map reqMap) {

        String where = "";
        String str_limit = "";
        String and = "AND";

        int count_without = reqMap.size() - 3;

             //    try {
        Set s = reqMap.entrySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {

            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) it.next();
            String key = entry.getKey();
            String[] value = entry.getValue();

            if (value.length > 1) {
                for (int i = 0; i < value.length; i++) {
                    String value_next = value[i];
                }

            }
            switch (key) {

              case "country":
                    if (count_without > 0) {
                    //    fields = fields + " ,country";
                        where = where + " where country=" + "'" + value[0] + "'" + " " + and;
                        count_without--;
                    } else {
                        where = where + " where  country=" + "'" + value[0] + "'";
                    }
                    break;


                case "city":
                    if (count_without > 0) {
                   //     fields = fields + " ,city";
                        where = where + " where city=" + "'" + value[0] + "'" + " " + and;
                        count_without--;
                    } else {
                        where = where + " where  city=" + "'" + value[0] + "'";
                    }
                    break;

                case "limit": str_limit =" limit "  +value[0];
                    break;



            }


        }


        return where+"/"+str_limit;

    }

    public static String prepareUserById(String id) {

        StringBuilder sb=new StringBuilder();
     sb.append("SELECT acc_f.id_acc, sex, birth, interes, @birth_user:=birth FROM acc_f, interests WHERE acc_f.id_acc=");
     sb.append(id);
     sb.append(" AND interests.id_acc=");
        sb.append(id);

              return sb.toString();
    }





    public static String prepareUsersDataById(ResultSet result, String where_prepare){

        String fields="";
        String select="select acc_f.id_acc, email, sex, status, fname, sname, birth, premiumfinish , premiumstart from acc_f, interests where ";
        String [] ss=where_prepare.split("/");
        String where=ss[0];
           String and="AND";
            String interes_sum="";
        int limit=0;
        int count_where=where.length();
        StringBuilder sb=new StringBuilder(15);
try {
 // do {

/*int status=result.getInt("status");
if(status==404) break;*/
    ResultSetMetaData rsmd=result.getMetaData();
    int count=rsmd.getColumnCount();



      String sex=result.getString("sex");
        if(count_where>1){
          if(sex.equals("f")){

              where =where+" AND sex='m' ";
                }else if(sex.equals("m")){
                where +=" AND sex='f' ";
            }
          } else{
            if(sex.equals("m")){

                where =where+"  sex='f' ";

            }else if(sex.equals("m")){
                where +="  sex='f' ";
            }

        }


           Date birth_user=result.getDate("birth");

        String interes=result.getString("interes");
        if (interes!=null){
            interes_sum=interes_sum+"  interes="+"'"+result.getString("interes")+"'";
            for (int i=0; i<count; i++) {

                result.next();
               // where = where + " AND interes=" + "'" + interes + "'";
                interes_sum=interes_sum+" OR interes="+"'"+result.getString("interes")+"'";
            }

        }
    where = where + " AND "+interes_sum;



} catch (
    SQLException e ) {
        e.printStackTrace();
    }
String sql =  select+" "+where+"  ORDER BY  FIELD(status, 'свободны','всё сложно','заняты'), count(interes) desc, ABS(@birth_user-birth) desc "+ss[1];
return sql;

    }

}
