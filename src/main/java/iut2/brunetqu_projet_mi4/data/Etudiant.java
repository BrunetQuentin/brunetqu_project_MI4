package iut2.brunetqu_projet_mi4.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Etudiant implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private String prenom;
	@Column(nullable = false)
	private String nom;
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Groupe> groupes;
	@OneToMany(mappedBy = "etudiant", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Note> notes;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Absence> absences;

	public List<Groupe> getGroupe() {
		return groupes;
	}

	public void setGroupe(List<Groupe> groupes) {
		this.groupes = groupes;
	}
	public Etudiant() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addNote(Note note){
		this.notes.add(note);
	}
	public void addAbsence(Absence absence){
		this.absences.add(absence);
	}
	public void removeNote(Note note){
		this.notes.remove(note);
	}
	public void removeAbsence(Absence absence){
		this.absences.remove(absence);
	}

}
