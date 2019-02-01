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

public class PrepareGroup {



    public String  prepareRequestGroup (Map reqMap){
        String fields="";
        String select="select";
        String from_tables=" from acc_f";
        String where_without_where_="";
        String where="where ";
        String str_limit="";
        String and="AND";
        String order="order by";
      String desc="desc";
      String group_by="group by";
      String count="count(";
      String braket=")";
        String key_count="";
        String key_enum="";
        int order_group=0;
        String order_direction="";
        String as=" AS  ";
        String counting="";
        String counting_order_by="";
        PreparedStatement ps=null;
        int limit=0;
StringBuilder sb=new StringBuilder(15);
        int count_without=reqMap.size()-4;


   sb.append("SELECT");
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

            }
            switch (key){
                case "sex": if(count_without>0){
                //    fields=fields+" sex ";
                    sb.append("sex");
                    where =where+" sex=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where +=" sex=" +  "'" +value[0]+"'";
                }
                    break;

                case "email": if(count_without>1){
                    fields=fields+" email";
                    where =where+" email=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" email=" +  "'" +value[0]+"'";
                }
                    break;

                case "status": if(count_without>1){
                    fields=fields+" status";
                    where =where+" status<>" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" status<>" +  "'" +value[0]+"'";
                }
                    break;

                case "fname": if(count_without>1){
                    fields=fields+" fname";
                    where =where+" fname=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" fname=" +  "'" +value[0]+"'";
                }
                    break;

                case "sname": if(count_without>1){
                    fields=fields+" sname";
                    where =where+" sname=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" sname=" +  "'" +value[0]+"'";
                }
                    break;

                case "phone": if(count_without>1){
                    fields=fields+" phone";
                    where =where+" phone=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else {
                    where =where+" phone=" +  "'" +value[0]+"'";
                }
                    break;



                case "country": if(count_without>1){
                    fields =fields+" country";
                    sb.append("country");
                    where =where+" country=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else{
                    where =where+"  country=" +  "'" +value[0]+"'";
                }
                    break;

                case "city": if(count_without>1){
                    fields =fields+" city,";
                    where =where+" city=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;
                }else{
                    fields =fields+" city,";
                    where =where+"  city=" +  "'" +value[0]+"'";
                }
                    break;


                case "birth": if(count_without>1){
                 //   fields +=" birth,";
                    where =where+" YEAR(birth)=" +value[0]+" "+and;
                    count_without--;
                }else if (count_without==1){
                //    fields +=" birth,";
                    where =where+"  YEAR(birth)=" +value[0];
                }
                    break;

                case "joined": if(count_without>1){
                 //   fields +=" joined";
                    where =where+" YEAR(joined)=" +value[0]+" "+and;
                    count_without--;
                }else if (count_without==1){
                 //   fields +=" ,joined";
                    where =where+"  YEAR(joined)=" +value[0];
                }
                    break;

                case "interests": if(count_without>1){
                //    fields +=" interests";
                    from_tables=from_tables+" ,interests";
                    where =where+" interests.interes=" +  "'" +value[0]+"'"+" "+and;
                    where_without_where_ =where_without_where_+" interests.interes=" +  "'" +value[0]+"'"+" "+and;
                    count_without--;




                }else if (count_without==1){
             //       fields +=" interests";
                    from_tables=from_tables+" ,interests";
                    where =where+" interests.interes=" +  "'" +value[0]+"'";
                    where_without_where_ =where_without_where_+" interests.interes=" +  "'" +value[0]+"'";
                }
                    break;

                case "limit": str_limit =" limit "  +value[0];
                limit=Integer.parseInt(value[0]);
                    break;

                case "order": order_group =Integer.parseInt(value[0]);
                if(order_group==-1){
                    order_direction=desc;
                }else{
                    order_direction="asc";
                }
                    break;

                case "keys":  key_count=value[0];
                String [] count_key=key_count.split(",");
                for(int i=0, j=count_key.length-1; i<count_key.length; i++, j--){
                    if(j==0){
                       counting=counting+" count("+count_key[i]+") "+as+" "+count_key[i];
                        counting_order_by=counting_order_by+" count("+count_key[i]+") ";
                        fields=fields+" "+value[i]+",";
                       break;
                   } else {
                        counting=counting+" count("+count_key[i]+") "+as+" "+count_key[i]+", ";
                        sb.append(counting);
                        counting_order_by=counting_order_by+" count("+count_key[i]+") ,";
                    }
                    fields=fields+" "+value[i]+",";
                }

               key_enum=  prepareKeysGroupBy(key_count);
                    break;

            }


        }
        String sql=select+" "+fields+" "+counting_order_by+" "+from_tables+" "+where+" "+group_by+" "+key_enum +
                " "+order+" "+counting_order_by+" "+order_direction+" "+str_limit;

        return sql;
    }


    public enum Keys{
        city, status, sex, interests, country;
     }

     public String prepareKeysGroupBy(String ss){

StringBuilder sb=new StringBuilder(20);
         String [] arr_ss=ss.split(",");
              String key="";
         int count=arr_ss.length;
         for(int i=0, j=count-1; i<count; i++, j--){
             if(j==0){
               //  sb.append("'");
                 sb.append(arr_ss[i]);
              //   sb.append("'");
                 break;
             }

             sb.append(arr_ss[i]);
          //   sb.append("'");
             sb.append(" ,");
         }
         return sb.toString();
     }
}

