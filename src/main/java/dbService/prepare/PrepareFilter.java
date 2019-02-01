package dbService.prepare;

import dbService.Dictionary;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import static java.sql.Types.NULL;

public class PrepareFilter {

    public String prepareRequest(Map reqMap){

        String fields="acc_f.id_acc, email";
        String select="select";
        String from_tables=" from acc_f";
        String where="where ";
String str_limit="";
String and="AND";
String or="OR";
PreparedStatement ps=null;

int count_without=reqMap.size()-3;
int count_city=0;
    //    try {
        Set s=reqMap.entrySet();
        Iterator it=s.iterator();
        while (it.hasNext()){

            Map.Entry<String,String[]> entry = (Map.Entry<String,String[]>)it.next();
            String key=entry.getKey();
            String [] value=entry.getValue();
            if(value.length>1){
                for(int i=0; i<value.length; i++){
                    String value_next=value[i];
                }
          //       val= value[0];
            }
            switch (key){
                case "sex_eq": if(count_without>0){
                    fields=fields+" ,sex";
                    where =where+" sex=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where +=" sex=" +  "'" +value[0]+"'";
                }
                    break;

                case "email": if(count_without>0){
                    fields=fields+" ,email";
                    where =where+" email=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" email=" +  "'" +value[0]+"'";
                }
                    break;

                case "email_gt": if(count_without>0){
                    fields=fields+" ,email";
                    where =where+" email<" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" email<" +  "'" +value[0]+"'";
                }
                    break;

                case "email_lt": if(count_without>0){
                    fields=fields+" ,email";
                    where =where+" email>" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" email>" +  "'" +value[0]+"'";
                }
                    break;





                case "status_neq": if(count_without>0){
                    fields=fields+" ,status";
                    where =where+" status<>" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" status<>" +  "'" +value[0]+"'";
                }
                    break;

                case "status_eq": if(count_without>0){
                    fields=fields+" ,status";
                    where =where+" status=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" status=" +  "'" +value[0]+"'";
                }
                    break;

                case "fname": if(count_without>0){
                    fields=fields+" ,fname";
                    where =where+" fname=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" fname=" +  "'" +value[0]+"'";
                }
                    break;

                case "sname": if(count_without>0){
                    fields=fields+" ,sname";
                    where =where+" sname=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" sname=" +  "'" +value[0]+"'";
                }
                    break;

                case "sname_null": if(count_without>0){
                    if(Integer.parseInt(value[0])==0){
                        fields =fields+" ,sname";
                        where =where+" sname " +"IS NOT NULL"+" "+and;
                        count_without--;

                    }else if(Integer.parseInt(value[0])==1) {
                        fields =fields+" ,sname";
                        where =where+" sname "+"IS NULL"+" "+and;
                        count_without--;
                    }

                }else{
                    if(Integer.parseInt(value[0])==0){

                        where =where+" sname "+"IS NOT NULL ";
                        count_without--;

                    }else if(Integer.parseInt(value[0])==1) {
                        where =where+" sname "+"IS NULL ";
                        count_without--;
                    }
                }
                    break;

                case "phone": if(count_without>0){
                    fields=fields+" ,phone";
                    where =where+" phone=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" phone=" +  "'" +value[0]+"'";
                }
                    break;

            //    case "country_eq": str_qu =str_qu+"  country=" +  "'" +value[0]+"'"+" AND ";
                case "country_eq": if(count_without>0){
                    fields=fields+" ,country";
                    where =where+" country=" +  "'" +value[0]+"'"+" "+and;
                        count_without--;
                    }else{
                    fields=fields+" ,country";
                    where =where+"  country=" +  "'" +value[0]+"'";
                    }
                    break;

                case "country": if(count_without>0){
                    fields =fields+" ,country";
                    where =where+" country=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else{
                    where =where+"  country=" +  "'" +value[0]+"'";
                }
                    break;

                case "city_eq": if(count_without>0){
                    fields =fields+" ,city";
                    where =where+" city=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else{
                    where =where+"  city=" +  "'" +value[0]+"'";
                }
                    break;
                case "city": if(count_without>0){
                    fields =fields+" ,city";
                    where =where+" city=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else{
                    where =where+"  city=" +  "'" +value[0]+"'";
                }
                    break;

                case "city_null": if(count_without>0){
                    if(Integer.parseInt(value[0])==0){
                        fields =fields+" ,city";
                        where =where+" city " +"IS NOT NULL"+" "+and;
                        count_without--;

                    }else if(Integer.parseInt(value[0])==1) {
                        fields =fields+" ,city";
                        where =where+" city "+"IS NULL"+" "+and;
                        count_without--;
                    }

                }else{
                    if(Integer.parseInt(value[0])==0){

                        where =where+" city<>" +  "'" +NULL+"'";
                        count_without--;

                    }else if(Integer.parseInt(value[0])==1) {
                        where =where+" city=" +  "'" +NULL+"'";
                        count_without--;
                    }


                }
                    break;


                case "city_any":  if (count_without > 0) {
                    fields =fields+" ,city";
                  String [] cit=value[0].split(",");
                    if(cit.length>1) {
                        for (int i = 0; i < cit.length; i++) {
                                where = where + " city=" + "'" + cit[i] + "'" + " " + or;
                                if(i==cit.length-1)where = where + " city=" + "'" + cit[i] + "'" + " ";
                            }

                        } else if(cit.length==1)   where = where + " city=" + "'" + cit[0] + "'" + " ";
                    count_without--;
                    where=where+" "+and;
                } else {
                    where=where+" ";
                    }
                    break;

                case "birth_lt": if(count_without>0){
                    fields=fields+" ,birth";
                    where =where+" birth<" +  "'" +new Date(Long.parseLong(value[0]))+"'"+" "+and;
                        count_without--;
                    }else {
                    fields=fields+" ,birth";
                    where =where+"  birth<" +  "'" +new Date(Long.parseLong(value[0]))+"'";
                    }
                    break;

                case "interests_contains": if(count_without>0){
                   fields +=" ,interes";
                   from_tables +=" ,interests";
                    where =where +  "  " +PrepareInterests.prepareRequest(value[0])+" "+and;
                    count_without--;

                }else {
                    where =where+  "  " +PrepareInterests.prepareRequest(value[0])+" ";
                }
                    break;

                case "interests_any": if(count_without>0){
                       fields +=" ,interes";
                      from_tables +=" ,interests";
                    where =where +" interes in  " +PrepareInterests.prepareRequest_Any(value[0])+" "+and;
                    count_without--;

                }else {
                    where =where+" interes in " +PrepareInterests.prepareRequest_Any(value[0])+" ";
                }
                    break;

                case "premium_null": if(count_without>0){
                    if(Integer.parseInt(value[0])==0){
                        fields =fields+" ,premium";
                        where =where+" premium_start " +"IS NOT NULL"+" "+and;
                        count_without--;
//sem don`t do
                    }else if(Integer.parseInt(value[0])==1) {
                        fields =fields+" ,premium";
                        where =where+" premium_start "+"IS NULL"+" "+and;
                        count_without--;
                    }

                }else{
                    if(Integer.parseInt(value[0])==0){

                        where =where+" premium<>" +  "'" +NULL+"'";
                        count_without--;

                    }else if(Integer.parseInt(value[0])==1) {
                        where =where+" premium=" +  "'" +NULL+"'";
                        count_without--;
                    }


                }
                    break;


                case "limit": str_limit =" limit "  +value[0];
                    break;

            }


        }
        String sql=select+" "+fields+" "+from_tables+" "+where+ " order by id_acc desc "+str_limit;



        return sql;




    }
}
