package iut2.brunetqu_projet_mi4.controller;

import iut2.brunetqu_projet_mi4.DAO.AbsenceDAO;
import iut2.brunetqu_projet_mi4.DAO.EtudiantDAO;
import iut2.brunetqu_projet_mi4.DAO.GroupeDAO;
import iut2.brunetqu_projet_mi4.DAO.NoteDAO;
import iut2.brunetqu_projet_mi4.data.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

/**
 * @author hb
 */

@SuppressWarnings("serial")
public class Controleur extends HttpServlet {

    //// ETUDIANT ////
    private String urlEtudiants;
    private String urlFicheEtudiant;
    private String urlEditionEtudiant;
    private String urlAjoutEtudiant;

    //// GROUPE ////
    private String urlGroupes;
    private  String urlEditionGroupe;
    private String urlAjoutGroupe;

    //// ABSENCE ////
    private String urlAbsences;

    //// NOTE ////
    private String urlNotes;

    // INIT
    @Override
    public void init() throws ServletException {
        // Récupération des URLs en paramètre du web.xml

        //// ETUDIANT ////
        urlEtudiants = getServletConfig().getInitParameter("urlEtudiants");
        urlFicheEtudiant = getServletConfig().getInitParameter("urlFicheEtudiant");
        urlEditionEtudiant = getServletConfig().getInitParameter("urlEditionEtudiant");
        urlAjoutEtudiant = getServletConfig().getInitParameter("urlAjoutEtudiant");
        //// GROUPE ////
        urlGroupes = getServletConfig().getInitParameter("urlGroupes");
        urlEditionGroupe = getServletConfig().getInitParameter("urlEditionGroupes");
        urlAjoutGroupe = getServletConfig().getInitParameter("urlAjoutGroupes");
        //// ABSENCE ////
        urlAbsences = getServletConfig().getInitParameter("urlAbsences");
        //// NOTE ////
        urlNotes = getServletConfig().getInitParameter("urlNotes");

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
        String action = request.getPathInfo();

        if (action == null) {
            action = "/etudiants";
        }

        ArrayList<String> actions = new ArrayList<String>();
        Collections.addAll(actions, action.split("/"));
        actions.remove(0);
        String firstAction = actions.get(0);
        actions.remove(0);

        Map<String, String[]> params = request.getParameterMap();
        System.out.println("post: " + firstAction);
        switch(firstAction){
            case "editionetudiant":
                editionEtudiant(actions, params);
                response.sendRedirect("etudiants");
                break;
            case "ajoutEtudiant":
                ajoutEtudiant(params);
                response.sendRedirect("etudiants");
                break;
            case "editionGroupe":
                ajoutEtudiant(params);
                response.sendRedirect("groupes");
                break;
            case "AjoutGroupe":
                ajoutEtudiant(params);
                response.sendRedirect("groupe");
                break;
        }
    }

    // GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // On récupère le path
        String action = request.getPathInfo();

        if (action == null) {
            action = "/etudiants";
        }

        ArrayList<String> actions = new ArrayList<String>();
        Collections.addAll(actions, action.split("/"));
        actions.remove(0);
        String firstAction = actions.get(0);
        actions.remove(0);

        System.out.println("get: " + firstAction);
        switch(firstAction){
                /////// ETUDIANT //////
            case "etudiants":
                doEtudiants(request, response);
                break;
            case "ficheetudiant":
                doFicheEtudiant(request, response, actions);
                break;
            case "editionetudiant":
                doEditionEtudiant(request, response, actions);
                break;
            case "ajoutEtudiant":
                doAjoutEtudiant(request, response);
                break;
                /////// GROUPE //////
            case "groupes":
                doGroupes(request, response);
                break;
            case "editionGroupe":
                doEditionGroupe(request, response, actions);
                break;
            case "ajoutGroupe":
                doAjoutGroupe(request, response, actions);
                break;
                /////// NOTE //////
            case "notes":
                doGroupes(request, response);
                break;
                /////// ABSENCE //////
            case "absences":
                doGroupes(request, response);
                break;
            default:
                doEtudiants(request, response);
                break;
        }
    }
    private void doAbsences(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Absence> absences = AbsenceDAO.getAll();
        request.setAttribute("absences", absences);

        Collection<Etudiant> etudiants = EtudiantDAO.getAll();
        request.setAttribute("etudiants", etudiants);

        loadJSP(urlAbsences, request, response);
    }

    // ///////////////////////
    //
    private void doNotes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Note> notes = NoteDAO.getAll();
        request.setAttribute("notes", notes);

        Collection<Etudiant> etudiants = EtudiantDAO.getAll();
        request.setAttribute("etudiants", etudiants);

        loadJSP(urlNotes, request, response);
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

    /////////////////////////////////////////////////////ETUDIANT METHODE ////////////////////
    private void doEtudiants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Collection<Etudiant> etudiants = EtudiantDAO.getAll();

        request.setAttribute("etudiants", etudiants);

        loadJSP("/WEB-INF/etudiants.jsp", request, response);

        loadJSP(urlEtudiants, request, response);
    }

    private void doFicheEtudiant(HttpServletRequest request, HttpServletResponse response, ArrayList<String> parameter)
            throws ServletException, IOException {

        int idEtudiant = Integer.valueOf(parameter.get(0));
        parameter.remove(0);

        System.out.println("idEtudiant: " + idEtudiant);
        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);
        request.setAttribute("etudiant", etudiant);
        request.setAttribute("nbAbsences", 2);

        loadJSP(urlFicheEtudiant, request, response);
    }
    private void doEditionEtudiant(HttpServletRequest request, HttpServletResponse response, ArrayList<String> actions)
            throws ServletException, IOException {

        int idEtudiant = Integer.valueOf(actions.get(0));
        actions.remove(0);

        System.out.println("idEtudiant: " + idEtudiant);

        Etudiant etudiant = EtudiantDAO.retrieveById(idEtudiant);

        request.setAttribute("etudiant", etudiant);

        loadJSP(urlEditionEtudiant, request, response);
    }
    private void doAjoutEtudiant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadJSP(urlAjoutEtudiant, request, response);
    }

    public void editionEtudiant(ArrayList<String> actions, Map<String,String[]>params){

        int idEtudiant = Integer.valueOf(actions.get(0));

        EtudiantDAO.editFormEtudiant(params, idEtudiant);
    }

    public void ajoutEtudiant(Map<String,String[]>params){
        EtudiantDAO.editFormEtudiant(params, -1);
    }

    /////////////////////////////////////////////GROUPE METHODE//////////////////////////////////
    private void doGroupes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Groupe> groupes = GroupeDAO.getAll();

        request.setAttribute("groupes", groupes);

        loadJSP(urlGroupes, request, response);
    }
    private void doEditionGroupe(HttpServletRequest request, HttpServletResponse response, ArrayList<String> actions)
            throws ServletException, IOException {

        int groupeId = Integer.valueOf(actions.get(0));
        actions.remove(0);

        Groupe groupe = GroupeDAO.retrieveById(groupeId);

        request.setAttribute("groupe", groupe);

        loadJSP(urlEditionGroupe, request, response);
    }
    private void doAjoutGroupe(HttpServletRequest request, HttpServletResponse response, ArrayList<String> actions)
            throws ServletException, IOException {

        int groupeId = Integer.valueOf(actions.get(0));
        actions.remove(0);

        Groupe groupe = GroupeDAO.retrieveById(groupeId);

        request.setAttribute("groupe", groupe);

        loadJSP(urlAjoutGroupe, request, response);
    }

    public void editionGroupe(ArrayList<String> actions, Map<String,String[]>params){

        int idEtudiant = Integer.valueOf(actions.get(0));

        GroupeDAO.editFormEtudiant(params, idEtudiant);
    }

    public void ajoutGroupe(Map<String,String[]>params){
        GroupeDAO.editFormEtudiant(params, -1);
    }
}
