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
	@ManyToOne
	private Groupe groupe;
	@OneToMany(mappedBy = "etudiant", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.REMOVE})
	private List<Note> notes;
	@OneToMany(mappedBy = "etudiant", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.REMOVE})
	private List<Absence> absences;

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public Etudiant() {
		super();
		this.absences = new ArrayList<>();
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

	public List<Absence> getAbsences(){
		return this.absences;
	}

	public float getMoyenne(){
		float moyenne = 0;
		for (Note note : notes) {
			moyenne += note.getNote();
		}
		if(notes.size() == 0){
			return -1;
		}
		return moyenne / notes.size();
	}

	public List<Note> getNotes() {
		return this.notes;
	}
}
