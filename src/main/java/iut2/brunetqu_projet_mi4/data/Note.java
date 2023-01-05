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

    @OneToMany(mappedBy = "note", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Etudiant> etudiant;

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
