package iut2.brunetqu_projet_mi4.data;

import java.util.Date;

public class Absence {

    int id;
    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    private Date startAt;

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    private Date endAt;

    public boolean isJustify() {
        return justify;
    }

    public void setJustify(boolean justify) {
        this.justify = justify;
    }

    private boolean justify;
    public Absence(int id, Date startAt, Date endAt, boolean justify){
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.justify = justify;
    }

}
