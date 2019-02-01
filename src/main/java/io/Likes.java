package io;
import java.sql.Date;

public class Likes {
    private Integer  id;
    private Date ts;

    public Likes(Integer  id, Date ts){
        this.id=id;
        this.ts=ts;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
