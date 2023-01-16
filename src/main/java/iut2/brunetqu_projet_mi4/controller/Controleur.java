package iut2.brunetqu_projet_mi4.controller;

import iut2.brunetqu_projet_mi4.DAO.EtudiantDAO;
import iut2.brunetqu_projet_mi4.DAO.GroupeDAO;
import iut2.brunetqu_projet_mi4.data.Etudiant;
import iut2.brunetqu_projet_mi4.data.GestionFactory;
import iut2.brunetqu_projet_mi4.data.Groupe;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author hb
 */

@SuppressWarnings("serial")
public class Controleur extends HttpServlet {

    private String urlEtudiants;
    private String urlGroupes;
    private String urlFicheEtudiant;
    private String urlEditionEtudiant;

    // INIT
    @Override
    public void init() throws ServletException {
        // Récupération des URLs en paramètre du web.xml
        urlEtudiants = getServletConfig().getInitParameter("urlEtudiants");
        urlGroupes = getServletConfig().getInitParameter("urlGroupes");
        urlFicheEtudiant = getServletConfig().getInitParameter("urlFicheEtudiant");
        urlEditionEtudiant = getServletConfig().getInitParameter("urlEditionEtudiant");

        // Création de la factory permettant la création d'EntityManager
        // (gestion des transactions)
        GestionFactory.open();

        ///// INITIALISATION DE LA BD
        // Normalement l'initialisation se fait directement dans la base de données
        if ((GroupeDAO.getAll().size() == 0) && (EtudiantDAO.getAll().size() == 0)) {

            // Creation des groupes
            Groupe MIAM = GroupeDAO.create("miam");
            Groupe SIMO = GroupeDAO.create("SIMO");
            Groupe MESSI = GroupeDAO.create("MESSI");

            // Creation des étudiants
            EtudiantDAO.create("Francis", "Brunet-Manquat", MIAM);
            EtudiantDAO.create("Philippe", "Martin", MIAM);
            EtudiantDAO.create("Mario", "Cortes-Cornax", MIAM);
            EtudiantDAO.create("Françoise", "Coat", SIMO);
            EtudiantDAO.create("Laurent", "Bonnaud", MESSI);
            EtudiantDAO.create("Sébastien", "Bourdon", MESSI);
            EtudiantDAO.create("Mathieu", "Gatumel", SIMO);
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        // Fermeture de la factory
        GestionFactory.close();
    }

    // POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // on passe la main au GET
        doGet(request, response);
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();
        if (action == null) {
            action = "/etudiants";
        }

        switch(action){
            case "/etudiants":
                doEtudiants(request, response);
                break;
            case "/groupes":
                doGroupes(request, response);
                break;
            case "/ficheetudiant":
                doFicheEtudiant(request, response);
                break;
            case "/editionetudiant":
                doEditionEtudiant(request, response);
                break;
            case "/modifetudiant":
                doModifEtudiant(request, response);
                break;
            default:
                doEtudiants(request, response);
                break;
        }
    }

    // ///////////////////////
    //
    private void doEtudiants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les étudiants
        List<Etudiant> etudiants = EtudiantDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("etudiants", etudiants);

        //
        loadJSP(urlEtudiants, request, response);
    }

    // ///////////////////////
    //
    private void doGroupes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les étudiants
        List<Groupe> groupes = GroupeDAO.getAll();

        // Ajouter les étudiants à la requête pour affichage
        request.setAttribute("groupes", groupes);

        //
        loadJSP(urlGroupes, request, response);
    }

    // ///////////////////////
    //
    private void doFicheEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer l'id de l'étudiant
        int idEtudiant = Integer.valueOf(request.getParameter("id"));

        // Récupérer l'étudiant
        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);

        // Ajouter l'étudiant à la requête pour affichage
        request.setAttribute("etudiant", etudiant);

        //
        loadJSP(urlFicheEtudiant, request, response);
    }

    // ///////////////////////
    //
    private void doEditionEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer l'id de l'étudiant
        int idEtudiant = Integer.valueOf(request.getParameter("id"));

        // Récupérer l'étudiant
        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);

        // Ajouter l'étudiant à la requête pour affichage
        request.setAttribute("etudiant", etudiant);

        //
        loadJSP(urlEditionEtudiant, request, response);
    }


    // ///////////////////////
    //
    private void doModifEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer l'id de l'étudiant
        int idEtudiant = Integer.valueOf(request.getParameter("id"));
        int moyenneEtudiant = Integer.valueOf(request.getParameter("moyenne"));

        // Récupérer l'étudiant
        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);

        EtudiantDAO.update(etudiant);

        // Ajouter l'étudiant à la requête pour affichage
        request.setAttribute("etudiant", etudiant);

        //
        loadJSP(urlFicheEtudiant, request, response);
    }

    /**
     * Charge la JSP indiquée en paramètre
     *
     * @param url
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void loadJSP(String url, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // L'interface RequestDispatcher permet de transférer le contrôle à une
        // autre servlet
        // Deux méthodes possibles :
        // - forward() : donne le contrôle à une autre servlet. Annule le flux
        // de sortie de la servlet courante
        // - include() : inclus dynamiquement une autre servlet
        // + le contrôle est donné à une autre servlet puis revient à la servlet
        // courante (sorte d'appel de fonction).
        // + Le flux de sortie n'est pas supprimé et les deux se cumulent

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
    }

}
