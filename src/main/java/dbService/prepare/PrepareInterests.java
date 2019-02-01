package dbService.prepare;

import dbService.Dictionary;

import java.sql.Connection;
import java.sql.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import static dbService.DBService.getMysqlConnection;
import static java.sql.Types.NULL;

public  class PrepareInterests {

    Connection connection=getMysqlConnection();

    public static String  prepareRequest(String ss){

        String [] arr_ss=ss.split(",");
        String select="(select interes from interests";

        String where="where ";
        String inter="interes=";
        String and=" AND ";
int count=arr_ss.length;
                for(int i=0, j=count-1; i<count; i++, j--){

                    if(j==0){
                        inter =inter +  "'" +arr_ss[i]+"'";
                        break;
                    }

                    inter =inter +  "'" +arr_ss[i]+"'"+" "+and;

                }

        return select+"  "+where+" "+inter+")";
    }

    public static String  prepareRequest_Any(String ss){

        String [] arr_ss=ss.split(",");
        String select="(select interes from interests";

        String where="where ";
        String inter="interes=";
        String and=" OR ";
        int count=arr_ss.length;
        for(int i=0, j=count-1; i<count; i++, j--){

            if(j==0){
                inter =inter +  "'" +arr_ss[i]+"'";
                break;
            }

            inter =inter +  "'" +arr_ss[i]+"'"+" "+and;

        }

        return select+"  "+where+" "+inter+") "+"and acc_f.id_acc=interests.id_acc";
    }
            }


