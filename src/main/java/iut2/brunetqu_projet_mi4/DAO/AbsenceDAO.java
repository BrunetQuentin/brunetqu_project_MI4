package iut2.brunetqu_projet_mi4.DAO;

import iut2.brunetqu_projet_mi4.data.Absence;
import iut2.brunetqu_projet_mi4.data.Etudiant;
import iut2.brunetqu_projet_mi4.data.GestionFactory;
import iut2.brunetqu_projet_mi4.data.Groupe;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

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

    public static List<Absence> getAll(){
        EntityManager em = GestionFactory.factory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select a from Absence a");
        List<Absence> absences = query.getResultList();

        em.close();

        return absences;
    }
}
