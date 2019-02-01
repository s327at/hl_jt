package dbService;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
  private   Map dict_type;
private String sex;
    private String sex_eq;




    public void set(){
      dict_type =new HashMap<String, String>();
        dict_type.put("id", "Integer");
        dict_type.put("email", "String");
        dict_type.put("fname", "String");
        dict_type.put("sname", "String");
        dict_type.put("phone", "String");
        dict_type.put("sex", "String");
        dict_type.put("sex_eq", "String");
        dict_type.put("birth", "Date");
        dict_type.put("country", "String");
        dict_type.put("city", "String");
        dict_type.put("jouned", "Date");
        dict_type.put("status", "String");
        dict_type.put("interests", "String");
        dict_type.put("premium", "Date");
        dict_type.put("likes", "String");
    }

    public Object get(Object kei){
        return dict_type.get(kei);
    }
}
