<%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 01/02/2023
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajout etudiant</title>
</head>
<body>
  <jsp:include page='<%=application.getInitParameter("header")%>' />
  <form method="post">
      <label for="nom">Nom :</label>
      <input name="nom" id="nom">

      <label for="prenom">Prenom :</label>
      <input name="prenom" id="prenom">

      <button type="submit">Valider</button>
  </form>
</body>
</html>
