package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.sql.Date;
import static io.UnixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter;

public class JsoToJson {

 public String   writeToJsonFilter(ServletOutputStream out, List<Account> acc_req) throws IOException {
     StringBuilder sb=new StringBuilder();
     JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
     writer.setIndent("    ");
     writer.beginObject();
     writer.name("accounts");
     writeAccountArray(writer, acc_req);
     writer.endObject();
     writer.close();
    return sb.toString();
 }

    public String   writeToJsonGroup(ServletOutputStream out, List<Account> acc_req) throws IOException {
       String s="";

        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        if(acc_req.isEmpty()) {
            out.println("");
        }
        writer.setIndent("    ");
        writer.beginObject();
        writer.name("groups");
        writeAccountArray(writer, acc_req);
        writer.endObject();
        writer.close();
        return s;
    }


    public void writeAccountArray(JsonWriter writer, List<Account> acc_req) throws IOException {
        writer.beginArray();

        for (Account account : acc_req) {
            writeAccount(writer, account);
        }
        writer.endArray();
    }

    public void writeAccount(JsonWriter writer, Account account) throws IOException {
        writer.beginObject();
        writer.name("id").value(account.getId());
        writer.name("email").value(account.getEmail());
               if (account.getFname() != null) {
            writer.name("fname").value(account.getFname());

        }

        if (account.getSname() != null) {
            writer.name("sname").value(account.getSname());
        }

        if (account.getBirth() != null) {
            writer.name("birth").value(account.getBirthLong());
        }

        if (account.getPhone() != null) {
            writer.name("phone").value(account.getPhone());
        }

        if (account.getCountry() != null) {
            writer.name("country").value(account.getCountry());
        }

        if (account.getCity() != null) {
            writer.name("city").value(account.getCity());
        }

        if (account.getJoined() != null) {
            writer.name("joined").value(account.getJoinedLong());
        }

        if (account.getStatus() != null) {
            writer.name("status").value(account.getStatus());
        }

        if (account.getSex() != null) {
            writer.name("sex").value(account.getSex());
        }


        writer.endObject();
    }

}
