<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Absence" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="absences" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Absence>" scope="request"/>
<%
  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH'H' mm'm'");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Absences</title>
  <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%= application.getInitParameter("header")%>' />
<main>
  <h2>Liste des Absences</h2>

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
          <%= absence.getEtudiant() != null ? (absence.getEtudiant().getPrenom() + " " + absence.getEtudiant().getNom()) : "Pb suppression cascade" %>
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

  <div class="plus-button"><a href="${pageContext.request.contextPath}/do/ajouterabsence/"><i class="fas fa-plus" title="Ajouter une absence"></i></a></div>
</main>

</body>
</html>