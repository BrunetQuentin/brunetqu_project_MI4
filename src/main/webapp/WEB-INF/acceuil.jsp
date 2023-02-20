<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Groupe" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Absence" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Note" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="etudiants" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Etudiant>" scope="request"/>
<jsp:useBean id="groupes" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Groupe>" scope="request"/>
<jsp:useBean id="absences" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Absence>" scope="request"/>
<jsp:useBean id="notes" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Note>" scope="request"/>

<%
  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH'H' mm'm'");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Accueil</title>
  <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%= application.getInitParameter("header")%>' />

<main>
  <h2>Tableau de bord</h2>

  <div class="multiple-table">

    <div>
      <h3>Etudiants</h3>
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
        </tr>
        </thead>
        <tbody>
        <%for(Etudiant etudiant : etudiants) {%>
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
        </tr>
        <%}%>
        </tbody>
      </Table>
    </div>

    <div>
      <h3>Groupes</h3>
      <Table>
        <thead>
        <tr>
          <th>
            N° Groupe
          </th>
          <th>
            Intitulé
          </th>
        </tr>
        </thead>
        <tbody>
        <%for(Groupe groupe : groupes) {%>
        <tr>
          <td>
            <%=groupe.getId() %>
          </td>
          <td>
            <%=groupe.getNom() %>
          </td>
        </tr>
        <%}%>
        </tbody>
      </Table>
    </div>

    <div>
      <h3>Notes</h3>
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
        </tr>
        <%}%>
        </tbody>
      </Table>
    </div>

    <div>
      <h3>Absences</h3>
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
            Nom de l'etudiant
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
            <%= absence.getEtudiant().getPrenom() + " " + absence.getEtudiant().getNom() %>
          </td>
        </tr>
        <%}%>
        </tbody>
      </Table>
    </div>


  </div>

</main>

</body>
</html>