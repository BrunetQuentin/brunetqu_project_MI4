package iut2.brunetqu_projet_mi4.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class GestionFactory {

	/////// SIMULATION DE LA PERSISTANCE DES ETUDIANTS ET DES ABSENCES
	
	// CHARGER en premier à l'execution du projet (car constante : static final)
	private static final HashMap<Integer, Etudiant> LISTE_ID_ETUDIANTS = intializeListEtudiants();
	private static final HashMap<Integer, Absence[]> LISTE_ID_ABSENCES = intializelistEtudiantAbsence();
	private static final HashMap<Integer, Note[]> LISTE_ID_NOTES = intializelistEtudiantNote();

	// Initialisation des étudiants
	private static HashMap<Integer, Etudiant> intializeListEtudiants() {

		// Création des étudiants
		Etudiant etu1 = new Etudiant(0, "Brunet-Manquat", "Francis");
		Etudiant etu2 = new Etudiant(1, "Martin", "Philippe");

		// Création du hasmap (association clé/valeur)
		// Association id -> etudiant
		HashMap<Integer, Etudiant> listEtudiantsTemp = new HashMap<>();
		listEtudiantsTemp.put(etu1.getId(), etu1);
		listEtudiantsTemp.put(etu2.getId(), etu2);

		//
		return listEtudiantsTemp;
	}

	private static final HashMap<Integer, Note[]> intializelistEtudiantNote() {

		HashMap<Integer, Note[]> listEtudiantNoteTemp = new HashMap<>();

		int id = 0;

		Random rand = new Random();
		for (Etudiant etudiant : LISTE_ID_ETUDIANTS.values()) {
			int nbrNote = rand.nextInt(5) + 5;
			Note[] notes = new Note[nbrNote];
			for (int i = 0; i < nbrNote; i++) {
				notes[i] = new Note(id, etudiant.getId(), rand.nextInt(20));
			}
			listEtudiantNoteTemp.put(etudiant.getId(), notes);
			id++;
		}

		return listEtudiantNoteTemp;
	}


	private static final HashMap<Integer, Absence[]> intializelistEtudiantAbsence() {

		HashMap<Integer, Absence[]> listEtudiantAbsenceTemp = new HashMap<>();

		int id = 0;

		Random rand = new Random();
		for (Etudiant etudiant : LISTE_ID_ETUDIANTS.values()) {
			int nbrAbsences = rand.nextInt(5) + 5;
			Absence[] absences = new Absence[nbrAbsences];
			for (int i = 0; i < nbrAbsences; i++) {
				absences[i] = new Absence(id, new Date(), new Date(), false);
			}
			listEtudiantAbsenceTemp.put(etudiant.getId(), absences);
			id++;
		}

		return listEtudiantAbsenceTemp;
	}

	
	/////// METHODES A UTILISER
	// Retourne l'ensemble des etudiants
	public static Collection<Etudiant> getEtudiants() {
		return LISTE_ID_ETUDIANTS.values();
	}

	// Retourne un étudiant en fonction de son id 
	public static Etudiant getEtudiantById(int id) {
		return LISTE_ID_ETUDIANTS.get(id);
	}

	// Retourne le nombre d'absences d'un etudiant en fonction de son id 
	public static Absence[] getAbsencesByEtudiantId(int id) {
		return LISTE_ID_ABSENCES.get(id);
	}
	public static Note[] GetNotesByEtuId(int id) { return LISTE_ID_NOTES.get(id); }

	public static HashMap<Integer, Note[]> getNotesByEtus(){return  LISTE_ID_NOTES;}

	public static HashMap<Integer, Absence[]> getAbsencesByEtus(){return  LISTE_ID_ABSENCES;}

	//////////////////////////////////////////////////////////////persistence///////////////////////////////////////////////////////////////////////////////
	// Nom de l'unité de persistence
	// Permet le lien avec le fichier persistence.xml présent dans le dossier META-INF
	// Ce fichier contient les propriétés de connexion à la base de données
	private static final String PERSISTENCE_UNIT_NAME = "Projet_JPA_SQLITE";
	//private static final String PERSISTENCE_UNIT_NAME = "Projet_JPA_MYSQL";
	//private static final String PERSISTENCE_UNIT_NAME = "Projet_JPA_MYSQL_DIST";
	//private static final String PERSISTENCE_UNIT_NAME = "Projet_JPA_MYSQL_DIST2";

	// Factory pour la création d'EntityManager (gestion des transactions)
	public static EntityManagerFactory factory;


	// Creation de la factory
	public static void open() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	// Fermeture de la factory
	public static void close() {
		factory.close();
	}

}