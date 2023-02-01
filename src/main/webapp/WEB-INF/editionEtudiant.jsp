<%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 18/01/2023
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="etudiant" class="iut2.brunetqu_projet_mi4.data.Etudiant" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page='<%=application.getInitParameter("header")%>' />
    <form method="post">
        <label for="nom">Nom :</label>
        <input name="nom" id="nom" value="<%= etudiant.getNom() %>">

        <label for="prenom">Prenom :</label>
        <input name="prenom" id="prenom" value="<%= etudiant.getPrenom() %>">

        <button type="submit">Valider</button>
    </form>
</body>
</html>
