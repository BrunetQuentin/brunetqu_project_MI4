package iut2.brunetqu_projet_mi4.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Absence implements Serializable {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    private Date startAt;

    @Column(nullable = true)
    private Date endAt;

    @Column(nullable = true)
    private boolean justify;
    @Column(nullable = false)
    private Etudiant etudiant;

    public Absence(){
        super();
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public boolean isJustify() {
        return justify;
    }

    public void setJustify(boolean justify) {
        this.justify = justify;
    }
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
}
