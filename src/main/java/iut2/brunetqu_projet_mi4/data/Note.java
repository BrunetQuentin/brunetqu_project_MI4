package iut2.brunetqu_projet_mi4.data;

public class Note {

    private Integer note;

    public Integer getId() {
        return id;
    }

    private Integer id;

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    private Integer idEtudiant;
    public Note(Integer id, Integer idEtudiant, Integer note) {
        this.note = note;
        this.id = id;
        this.idEtudiant = idEtudiant;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }
}
