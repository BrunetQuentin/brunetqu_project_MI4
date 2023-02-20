<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Groupe" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Absence" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Note" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="etudiant" type="iut2.brunetqu_projet_mi4.data.Etudiant" scope="request"/>
<%
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH'H' mm'm'");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Fiche etudiant</title>
    <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%= application.getInitParameter("header")%>' />

<main>
    <h2>Fiche etudiant</h2>

    <h3>Information sur l'étudiant</h3>
    <Table>
        <thead>
        <tr>
            <th>
                N° Etudiant
            </th>
            <th>
                Nom
            </th>
            <th>
                Prenom
            </th>
            <th>
                Nombre d'absences
            </th>
            <th>
                Moyenne
            </th>
            <th>
                Actions
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <%=etudiant.getId() %>
            </td>
            <td>
                <%=etudiant.getNom() %>
            </td>
            <td>
                <%=etudiant.getPrenom() %>
            </td>
            <td>
                <%=etudiant.getAbsences().toArray().length %>
            </td>
            <td>
                <%= etudiant.getMoyenne() == -1 ? "-" : etudiant.getMoyenne()  %>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/do/editionetudiant/<%=etudiant.getId() %>">
                    <i class="fas fa-pen-to-square" title="Editer"></i>
                </a>
            </td>
        </tr>
        </tbody>
    </Table>

    <h3>Groupe de l'étudiant</h3>
    <% Groupe groupe = etudiant.getGroupe();%>
    <%if(etudiant.getGroupe() != null){%>
        <Table>
        <thead>
        <tr>
            <th>
                N° Groupe
            </th>
            <th>
                Intitulé
            </th>
            <th>
                Camarades de class
            </th>
            <th>
                Actions
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <%=groupe.getId() %>
            </td>
            <td>
                <%=groupe.getNom() %>
            </td>
            <td>
                <%for(Etudiant e : groupe.getEtudiants()) {%>
                <%=e.getPrenom() + " " + e.getNom() + ", "%>
                <%}%>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/do/editiongroupe/<%=groupe.getId() %>">
                    <i class="fas fa-pen-to-square" title="Editer"></i>
                </a>
                <a href="${pageContext.request.contextPath}/do/suppressiongroupe/<%=groupe.getId() %>">
                    <i class="fas fa-trash-can" title="Supprimer"></i>
                </a>
            </td>
        </tr>
        </tbody>
    </Table>
    <%}else{%>
        <p>Pas de groupe pour <%=etudiant.getPrenom()%>, dommage !</p>
    <%}%>

    <h3>Absences de l'étudiant</h3>
    <% List<Absence> absences = etudiant.getAbsences();%>
    <%if(absences.size() > 0){%>
        <Table>
        <thead>
        <tr>
            <th>
                N° Absence
            </th>
            <th>
                Commence le
            </th>
            <th>
                Finis le
            </th>
            <th>
                Est justifié
            </th>
            <th>
                Nom de l'etudiant
            </th>
            <th>
                Actions
            </th>
        </tr>
        </thead>
        <tbody>
        <%for(Absence absence : absences) {%>
        <tr>
            <td>
                <%=absence.getId() %>
            </td>
            <td>
                <%=dateFormat.format(absence.getStartAt()) %>
            </td>
            <td>
                <%=absence.getEndAt() != null ? dateFormat.format(absence.getEndAt()) : "?" %>
            </td>
            <td>
                <%= absence.isJustify() ? "Oui" : "Non" %>
            </td>
            <td>
                <%= absence.getEtudiant().getPrenom() + " " + absence.getEtudiant().getNom() %>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/do/editionabsence/<%=absence.getId() %>">
                    <i class="fas fa-pen-to-square" title="Editer"></i>
                </a>
                <a href="${pageContext.request.contextPath}/do/suppressionabsence/<%=absence.getId() %>">
                    <i class="fas fa-trash-can" title="Supprimer"></i>
                </a>
            </td>
        </tr>
        <%}%>
        </tbody>
    </Table>
    <%}else{%>
        <p>Pas d'absence pour <%=etudiant.getPrenom()%>, c'est un élève exemplaire !</p>
    <%}%>

    <h3>Notes de l'étudiant</h3>
    <% List<Note> notes = etudiant.getNotes();%>
    <%if(notes.size() > 0){%>
        <Table>
        <thead>
        <tr>
            <th>
                N° Note
            </th>
            <th>
                Etudiant
            </th>
            <th>
                Note
            </th>
            <th>
                Actions
            </th>
        </tr>
        </thead>
        <tbody>
        <%for(Note note : notes) {%>
        <tr>
            <td>
                <%=note.getId() %>
            </td>
            <td>
                <%=note.getEtudiant().getPrenom() + " " + note.getEtudiant().getNom()%>
            </td>
            <td>
                <%=note.getNote()%>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/do/editionnote/<%=note.getId() %>">
                    <i class="fas fa-pen-to-square" title="Editer"></i>
                </a>
                <a href="${pageContext.request.contextPath}/do/suppressionnote/<%=note.getId() %>">
                    <i class="fas fa-trash-can" title="Supprimer"></i>
                </a>
            </td>
        </tr>
        <%}%>
        </tbody>
    </Table>
    <%}else{%>
        <p>Pas de note pour <%=etudiant.getPrenom()%>, il doit encore montrer ses preuves !</p>
    <%}%>

</main>

</body>
</html>