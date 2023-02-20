<%@ page import="iut2.brunetqu_projet_mi4.data.Groupe" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %><%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 01/02/2023
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="etudiants" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Etudiant>" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ajout absence</title>
  <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%=application.getInitParameter("header")%>' />
<main>
  <h2>Ajouter une absence</h2>
  <form method="post">
    <fieldset>
      <legend>Informations de l'absence</legend>
      <div>
        <label for="startAt">Commence le :</label>
        <input type="datetime-local" id="startAt" name="startAt" required>
      </div>

      <div>
        <label for="endAt">Finis le :</label>
        <input type="datetime-local" id="endAt" name="endAt">
      </div>

      <div>
        <label>Est justifié ? :</label>
        <label for="oui">Oui</label>
        <input type="radio" id="oui" name="justified" value="oui">
        <label for="non">Non</label>
        <input type="radio" id="non" name="justified" value="non" checked>
      </div>

      <div>
        <label for="etudiant">Etudiant :</label>
        <select id="etudiant" name="etudiant" required>
          <%for(Etudiant etudiant : etudiants) {%>
            <option value="<%=etudiant.getId()%>"><%=etudiant.getPrenom() + " " + etudiant.getNom()%></option>
          <%}%>
        </select>
      </div>

      <button type="submit">Valider</button>
    </fieldset>
  </form>
</main>
</body>
</html>
