package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import static io.UnixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter;

public class ResultSetToJson {

    public String   writeToJsonFilter(ServletOutputStream out, String result) throws IOException {
        JsonWriter writer=null;
        StringBuilder sb=new StringBuilder();
        try {
       writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("    ");
        writer.beginObject();
        writer.name("accounts");


            writer.jsonValue(result);
        }catch ( IOException e ) {

            e.printStackTrace();
        }

        writer.endObject();
        writer.close();
        return sb.toString();
    }

    public String   writeToJsonGroup(ServletOutputStream out, String result) throws IOException {
        String s="";
        JsonWriter writer=null;
try{
       writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("    ");
        writer.beginObject();
        writer.name("groups");

    writer.jsonValue(result);
}catch ( IOException e ) {
    e.printStackTrace();
}
        writer.endObject();
        writer.close();
        return s;

    }

    public void writeResultSetArray(JsonWriter writer, ResultSet result) throws IOException{
        writer.beginArray();
        try{
            while (result.next()) {
                    writeRsObject(writer, result);
                }

        } catch (SQLException e ) {
           e.printStackTrace();
      }
        writer.endArray();
    }

     public void writeRsObject(JsonWriter writer, ResultSet result) throws IOException, SQLException {
        ResultSetMetaData rsmd=result.getMetaData();
        int count=rsmd.getColumnCount();
        writer.beginObject();
for(int i=1; i<=count; i++){
    String name_col=rsmd.getColumnName(i);
String rs_type=rsmd.getColumnTypeName(i);

      switch (rs_type){
          // если в таблицу добавится еще один инт то тут сломается логика
          case "INT":  writer.name("id").value(result.getInt(i));
          break;
          case "VARCHAR": writer.name(name_col).value(result.getString(i));
          break;
          case "DATETIME": writer.name(name_col).value(((result.getTimestamp(name_col)).getTime())/1000);
          break;

      }
}
     writer.endObject();

    }
}
