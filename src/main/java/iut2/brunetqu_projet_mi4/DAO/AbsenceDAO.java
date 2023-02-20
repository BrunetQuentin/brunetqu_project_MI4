package iut2.brunetqu_projet_mi4.DAO;

import iut2.brunetqu_projet_mi4.data.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class AbsenceDAO {
    public static Absence create(Date startAt, Date endAt, Etudiant etudiant, boolean isJustify) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        // create new absence
        Absence absence = new Absence();
        absence.setStartAt(startAt);
        absence.setEndAt(endAt);
        absence.setEtudiant(etudiant);
        absence.setJustify(isJustify);

        em.getTransaction().commit();

        em.close();

        return absence;
    }

    public static Absence update(Absence absence) {
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(absence);

        em.getTransaction().commit();
        em.close();

        return absence;
    }

    public static List<Absence> getAbsenceByUserId(int userId){
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select a from Absence a where a.etudiant.id=" + userId);
        List<Absence> absences = query.getResultList();

        em.close();

        return absences;
    }

    public static void remove(int idAbsence) {
        Absence absence = retrieveById(idAbsence);
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        if (!em.contains(absence)) {
            absence = em.merge(absence);
        }
        em.remove(absence);
        em.getTransaction().commit();
        em.close();
    }

    public static Absence addAbsence(Absence absence) {

        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        em.persist(absence);

        em.getTransaction().commit();

        em.close();

        return absence;
    }

    public static List<Absence> getAll(){
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select a from Absence a");
        List<Absence> absences = query.getResultList();

        em.close();

        return absences;
    }

    public static Absence retrieveById(int id) {

        EntityManager em = GestionFactory.factory.createEntityManager();

        Absence absence = em.find(Absence.class, id);

        em.close();

        return absence;
    }

    public static void editFormAbsence(Map<String, String[]> form, int id){

        Absence absence;

        if(id == -1){
            absence = new Absence();
        }else {
            absence = retrieveById(id);
        }

        for (Map.Entry<String, String[]> entry : form.entrySet()) {
            String[] values = entry.getValue();
            if(values.length == 0) continue;
            String value = values[0];

            switch (entry.getKey()){
                case "startAt":
                    LocalDateTime timeStartAt = LocalDateTime.parse(value);
                    absence.setStartAt(Date.from(timeStartAt.atZone(ZoneId.systemDefault()).toInstant()));
                    break;
                case "endAt":
                    if(value.isEmpty()){
                        absence.setEndAt(null);
                        break;
                    }
                    LocalDateTime timeEndAt = LocalDateTime.parse(value);
                    absence.setEndAt(Date.from(timeEndAt.atZone(ZoneId.systemDefault()).toInstant()));
                    absence.setEndAt(Date.from(timeEndAt.atZone(ZoneId.systemDefault()).toInstant()));
                    break;
                case "justified":
                    if(value.equals("oui")){
                        absence.setJustify(true);
                    }else if(value.equals("non")) {
                        absence.setJustify(false);
                    }
                    break;
                case "etudiant":
                    Etudiant etudiant = EtudiantDAO.retrieveById(parseInt(value));
                    absence.setEtudiant(etudiant);
                    etudiant.addAbsence(absence);
                    break;
            }
        }
        if(id == -1){
            addAbsence(absence);
        }else {
            update(absence);
        }
    }
}
