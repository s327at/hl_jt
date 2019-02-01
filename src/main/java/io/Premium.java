package io;
import java.sql.Date;

public class Premium {
    Date start = null;
    Date finish=null;
    public Premium (Date start, Date finish){
       this.start=start;
       this.finish=finish;

    }

    @Override
    public String toString() {
        return "start=" + start +"finish=" + finish;

    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }
}
