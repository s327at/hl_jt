package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.*;
import java.io.IOException;
import java.util.*;
import java.sql.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static io.UnixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter;

public class Jso {


    U_DService uploadService = new U_DService();

          public void readJsonStream() throws IOException {
            JsonReader reader=null;
            ZipFile zipFile=null;
            InputStream stream=null;

                 try {

                          zipFile = new ZipFile("/tmp/data/data.zip");
                          Enumeration<? extends ZipEntry> entries = zipFile.entries();

   while(entries.hasMoreElements()) {
                         ZipEntry entry = entries.nextElement();
                          stream = zipFile.getInputStream(entry);
                         reader = new JsonReader(new InputStreamReader(stream));
                     }


                             Gson gson = new GsonBuilder()
                            .registerTypeAdapter(Date.class, getUnixEpochDateTypeAdapter()).setPrettyPrinting()
                            .create();

                    reader.beginObject();    //start well code
                    while (reader.hasNext()) {
                        if (reader.nextName() == "accounts") {
                            reader.skipValue();
                        }
                        break;
                    }
                    reader.beginArray();
                    while (reader.hasNext()) {

                        readAccount(reader);

                    }
                    reader.endArray();
                    reader.endObject();
                    reader.close();


                 } catch (IOException e){
                     e.printStackTrace();
                 }finally {

                     zipFile.close();
                     stream.close();
                     reader.close();

                 }
                }



                public void readAccount (JsonReader reader) throws IOException {
                Integer id = null;
                String email = null;
                String fname = null;
                String sname = null;
                String phone = null;
                String sex = null;
                Date birth = null;
                String country = null;
                String city = null;
                Date joined = null;
                String status = null;
                List<String> interests = null;
                Premium premium = null;
                List<Likes> likes = null;

                reader.beginObject();
                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("birth") && reader.peek() != JsonToken.NULL) {
                        birth = new Date(reader.nextLong());
                    } else if (name.equals("country") && reader.peek() != JsonToken.NULL) {
                        country = reader.nextString();
                    } else if (name.equals("city") && reader.peek() != JsonToken.NULL) {
                        city = reader.nextString();
                    } else if (name.equals("status") && reader.peek() != JsonToken.NULL) {
                        status = reader.nextString();
                    } else if (name.equals("id") && reader.peek() != JsonToken.NULL) {
                        id = reader.nextInt();
                    } else if (name.equals("joined") && reader.peek() != JsonToken.NULL) {
                        joined = new Date(reader.nextLong());
                    } else if (name.equals("fname") && reader.peek() != JsonToken.NULL) {
                        fname = reader.nextString();
                    } else if (name.equals("sname") && reader.peek() != JsonToken.NULL) {
                        sname = reader.nextString();
                    } else if (name.equals("phone") && reader.peek() != JsonToken.NULL) {
                        phone = reader.nextString();
                    } else if (name.equals("interests") && reader.peek() != JsonToken.NULL) {
                        interests = readInterests(reader, id);
                    } else if (name.equals("premium") && reader.peek() != JsonToken.NULL) {
                        premium = readPremium(reader);
                    } else if (name.equals("likes") && reader.peek() != JsonToken.NULL) {
                        likes = readLikesList(reader);
                    } else if (name.equals("email") && reader.peek() != JsonToken.NULL) {
                        email = reader.nextString();
                    } else if (name.equals("sex") && reader.peek() != JsonToken.NULL) {
                        sex = reader.nextString();
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                if (likes != null) uploadService.addNewLikes(id, likes);
                if (interests != null) uploadService.addNewInterests(id, interests);
                uploadService.addNewAccount(id, email, fname, sname, phone, sex, birth, country, city, joined, status, premium);
                //   return new Account(id, email, fname, sname, phone, sex, birth, country,  city, joined,  status, interests,  premium, likes );
            }


            public List<String> readInterests (JsonReader reader, Integer id) throws IOException {
                List<String> interests = new ArrayList<String>();
                reader.beginArray();
                while (reader.hasNext()) {
                    interests.add(reader.nextString());
                }
                reader.endArray();
                return interests;
            }

            public Premium readPremium (JsonReader reader) throws IOException {
                Date start = null;
                Date finish = null;

                reader.beginObject();
                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("start") && reader.peek() != JsonToken.NULL) {
                        start = new Date(reader.nextLong());
                    } else if (name.equals("finish") && reader.peek() != JsonToken.NULL) {
                        finish = new Date(reader.nextLong());
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                return new Premium(start, finish);
            }

            public List<Likes> readLikesList (JsonReader reader) throws IOException {
                List<Likes> laikes = new ArrayList<>();
                reader.beginArray();
                while (reader.hasNext()) {
                    laikes.add(readLikes(reader));
                }
                reader.endArray();
                return laikes;

            }


            public Likes readLikes (JsonReader reader) throws IOException {
                Integer id = null;
                Date ts = null;
                reader.beginObject();
                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("id") && reader.peek() != JsonToken.NULL) {
                        id = reader.nextInt();
                    } else if (name.equals("ts") && reader.peek() != JsonToken.NULL) {
                        ts = new Date(reader.nextLong());
                    } else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                return new Likes(id, ts);
            }


        }
