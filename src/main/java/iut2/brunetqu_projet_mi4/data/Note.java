package iut2.brunetqu_projet_mi4.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Note implements Serializable {

    private int note;
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Etudiant etudiant;


    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Note() {
        super();
    }

    public Note(int note) {
        super();
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    public int getId() {
        return id;
    }
}
