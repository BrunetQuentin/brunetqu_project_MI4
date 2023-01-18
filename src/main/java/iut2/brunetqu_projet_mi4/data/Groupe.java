package iut2.brunetqu_projet_mi4.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Groupe implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true, nullable = false)
    private String nom;
    @ManyToMany(mappedBy = "groupes", fetch = FetchType.LAZY)    // LAZY = fetch when needed, EAGER = fetch immediately
    private List<Etudiant> etudiants;
    public Groupe() {
        super();
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }

    public List<Etudiant> getEtudiants() {
        return this.etudiants;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Groupe)) return false;
        return id != null && id.equals(((Groupe) o).id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
