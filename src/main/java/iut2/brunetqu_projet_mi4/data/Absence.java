package iut2.brunetqu_projet_mi4.data;

import javax.persistence.*;
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

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @OneToOne(mappedBy = "Etudiant", fetch = FetchType.LAZY)
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
