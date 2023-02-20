<%@ page import="iut2.brunetqu_projet_mi4.data.Groupe" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %><%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 01/02/2023
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="note" type="iut2.brunetqu_projet_mi4.data.Note" scope="request"/>
<jsp:useBean id="etudiants" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Etudiant>" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajout note</title>
    <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%=application.getInitParameter("header")%>' />
<main>
    <h2>Ajouter une note</h2>
    <form method="post">
        <fieldset>
            <legend>Informations de la note</legend>
            <div>
                <label for="note">Note/20 :</label>
                <input type="number" id="note" max="20" min="0" value="<%=note.getNote()%>" required name="note">
            </div>

            <div>
                <label for="etudiant">Etudiant :</label>
                <select id="etudiant" name="etudiant" required>
                    <%for(Etudiant etudiant : etudiants) {%>
                    <option value="<%=etudiant.getId()%>" <%= note.getEtudiant().getId().equals(etudiant.getId()) ? "selected" : ""%>><%=etudiant.getPrenom() + " " + etudiant.getNom()%></option>
                    <%}%>
                </select>
            </div>

            <button type="submit">Valider</button>
        </fieldset>
    </form>
</main>
</body>
</html>
