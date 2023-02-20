package iut2.brunetqu_projet_mi4.DAO;

import iut2.brunetqu_projet_mi4.data.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class NoteDAO {
    public static Note create(int nbNote, Etudiant etudiant) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        Note note = new Note();
        note.setNote(nbNote);
        note.setEtudiant(etudiant);

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

    public static Note update(Note note) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(note);

        em.getTransaction().commit();
        em.close();

        return note;
    }

    public static Note addNote(Note note) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        em.persist(note);

        em.getTransaction().commit();

        em.close();

        return note;
    }

    public static Note retrieveById(int id) {

        EntityManager em = GestionFactory.factory.createEntityManager();

        Note note = em.find(Note.class, id);

        em.close();

        return note;
    }

    public static void remove(int idNote) {
        Note note = retrieveById(idNote);
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        if (!em.contains(note)) {
            note = em.merge(note);
        }
        em.remove(note);
        em.getTransaction().commit();
        em.close();
    }

    public static void editFormNote(Map<String, String[]> form, int id){

        Note note;

        if(id == -1){
            note = new Note();
        }else {
            note = retrieveById(id);
        }

        for (Map.Entry<String, String[]> entry : form.entrySet()) {
            String[] values = entry.getValue();
            if(values.length == 0) continue;
            String value = values[0];

            switch (entry.getKey()){
                case "note":
                    note.setNote(parseFloat(value));
                    break;
                case "etudiant":
                    Etudiant etudiant = EtudiantDAO.retrieveById(parseInt(value));
                    note.setEtudiant(etudiant);
                    break;
            }
        }
        if(id == -1){
            addNote(note);
        }else {
            update(note);
        }
    }
}
