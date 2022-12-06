package iut2.brunetqu_projet_mi4;

public class TestAbsences {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Afficher les étudiants et leurs absences
		for (Etudiant etu : GestionFactory.getEtudiants()) {
			System.out.print("Etudiant : " + etu.getPrenom() + " " + etu.getNom());
			System.out.println(" -> nombre d'absences : " + GestionFactory.getAbsencesByEtudiantId(etu.getId()));
		}

	}

}
