package iut2.brunetqu_projet_mi4.DAO;

import iut2.brunetqu_projet_mi4.data.Etudiant;
import iut2.brunetqu_projet_mi4.data.GestionFactory;
import iut2.brunetqu_projet_mi4.data.Groupe;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class GroupeDAO {

    public static Groupe create(String nom) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // create
        em.getTransaction().begin();

        // create new groupe
        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        em.persist(groupe);

        // On peut maintenant accéder au champ id de l'objet créé
        // (champ autoincrémenté)
        int id = groupe.getId();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return groupe;
    }

    public static void remove(Groupe groupe) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Le groupe passé en paramètre doit être associé à l'EM
        if (!em.contains(groupe)) {
            groupe = em.merge(groupe);
        }

        // Supprime l'entité courante mais aussi les entités (étudiants) liées
        // grâce à l'annotation cascade = {CascadeType.REMOVE} dans la classe Groupe
        em.remove(groupe);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();
    }

    public static void remove(int idGroupe) {
        Groupe groupe = retrieveById(idGroupe);

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Le groupe passé en paramètre doit être associé à l'EM
        if (!em.contains(groupe)) {
            groupe = em.merge(groupe);
        }

        // Supprime l'entité courante mais aussi les entités (étudiants) liées
        // grâce à l'annotation cascade = {CascadeType.REMOVE} dans la classe Groupe
        em.remove(groupe);

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
        int deletedCount = em.createQuery("DELETE FROM Groupe").executeUpdate();

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return deletedCount;
    }


    public static List<Groupe> getAll() {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT g FROM Groupe g");

        @SuppressWarnings("unchecked")
        List<Groupe> listGroupe = q.getResultList();

        return listGroupe;
    }

    public static Groupe update(Groupe groupe) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (etudiant) à l’EntityManager courant  pour réaliser la modification
        em.merge(groupe);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return groupe;
    }

    public static Groupe retrieveById(int id) {

        EntityManager em = GestionFactory.factory.createEntityManager();

        Groupe groupe = em.find(Groupe.class, id);

        em.close();

        return groupe;
    }

    public static Groupe addGroupe(Groupe groupe) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        em.persist(groupe);

        int id = groupe.getId();
        em.getTransaction().commit();

        em.close();
        return groupe;
    }
    public static void editFormGroupe(Map<String, String[]> form, int id){

        Groupe groupe;

        if(id == -1){
            groupe = new Groupe();
        }else {
            groupe = retrieveById(id);
        }

        for (Map.Entry<String, String[]> entry : form.entrySet()) {
            String[] values = entry.getValue();
            if(values.length == 0) continue;
            String value = values[0];

            switch (entry.getKey()){
                case "nom":
                    groupe.setNom(value);
            }
        }
        if(id == -1){
            addGroupe(groupe);
        }else {
            update(groupe);
        }
    }

}
