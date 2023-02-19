package iut2.brunetqu_projet_mi4.DAO;

import iut2.brunetqu_projet_mi4.data.Absence;
import iut2.brunetqu_projet_mi4.data.Etudiant;
import iut2.brunetqu_projet_mi4.data.GestionFactory;
import iut2.brunetqu_projet_mi4.data.Groupe;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EtudiantDAO {

    private static int NB_ABSENCES_MAX = 8;

    public static Etudiant retrieveById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        Etudiant etu = em.find(Etudiant.class, id);
        // etu est maintenant un objet de la classe Etudiant
        // ou NULL si l'étudiant n'existe pas

        // Close the entity manager
        em.close();

        return etu;
    }


    public static Etudiant create(String prenom, String nom, Groupe groupe) {

        System.out.println(prenom);
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // create new etudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom(prenom);
        etudiant.setNom(nom);
        etudiant.setGroupe(groupe);
        em.persist(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return etudiant;
    }

    public static void addEtudiant(Etudiant etudiant){
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(etudiant);
        em.getTransaction().commit();
        em.close();
    }

    public static Etudiant update(Etudiant etudiant) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (etudiant) à l’EntityManager courant  pour réaliser la modification
        em.merge(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return etudiant;
    }

    public static Etudiant addAbsences(int id, Absence absence) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Find
        Etudiant etudiant = em.find(Etudiant.class, id);

        //
        em.getTransaction().begin();

        //
        etudiant.addAbsence(absence);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return etudiant;
    }


    public static void remove(Etudiant etudiant) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // L'étudiant passé en paramètre doit être associé à l'EM
        if (!em.contains(etudiant)) {
            etudiant = em.merge(etudiant);
        }

        // Remove
        em.remove(etudiant);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();
    }

    public static void remove(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        //
        em.createQuery("DELETE FROM Etudiant AS e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();
    }

    public static int removeAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // RemoveAll
        int deletedCount = em.createQuery("DELETE FROM Etudiant").executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return deletedCount;
    }

    // Retourne l'ensemble des etudiants
    public static List<Etudiant> getAll() {
        EntityManager em = GestionFactory.factory.createEntityManager();

        Query q = em.createQuery("SELECT e FROM Etudiant e");

        @SuppressWarnings("unchecked")
        List<Etudiant> listEtudiant = q.getResultList();

        return listEtudiant;
    }

    // Retourne l'ensemble des etudiants d'un groupe donné
    public static List<Etudiant> getAllByAbsences() {

        // Creation de l'entity manager

        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT e FROM Etudiant e WHERE e.nbAbsences > :nbAbsenceMAx")
                .setParameter("nbAbsenceMAx", NB_ABSENCES_MAX);

        @SuppressWarnings("unchecked")
        List<Etudiant> listEtudiant = q.getResultList();

        return listEtudiant;
    }

    public static void editFormEtudiant(Map<String, String[]> form, int id){

        Etudiant etudiant;

        if(id == -1){
            etudiant = new Etudiant();
        }else {
            etudiant = retrieveById(id);
        }

        for (Map.Entry<String, String[]> entry : form.entrySet()) {
            String[] values = entry.getValue();
            if(values.length == 0) continue;
            String value = values[0];

            switch (entry.getKey()){
                case "nom":
                    etudiant.setNom(value);
                    break;
                case "prenom":
                    etudiant.setPrenom(value);
                    break;
                case "groupe":
                    if(value.isEmpty()){
                        etudiant.setGroupe(null);
                        break;
                    }
                    Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(value));
                    etudiant.setGroupe(groupe);
                    break;
            }
        }
        if(id == -1){
            addEtudiant(etudiant);
        }else {
            update(etudiant);
        }
    }

}