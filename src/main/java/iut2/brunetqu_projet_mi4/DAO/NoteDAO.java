package iut2.brunetqu_projet_mi4.DAO;

import iut2.brunetqu_projet_mi4.data.Etudiant;
import iut2.brunetqu_projet_mi4.data.GestionFactory;
import iut2.brunetqu_projet_mi4.data.Note;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class NoteDAO {
    public static Note create(int nbNote, Etudiant etudiant) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        Note note = new Note();
        note.setNote(nbNote);
        note.setEtudiant(Arrays.asList(etudiant));

        em.getTransaction().commit();

        em.close();

        return note;
    }

    public static List<Note> getAll(){
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select n from Note n");

        List<Note> listNotes = query.getResultList();
        return listNotes;
    }
}
