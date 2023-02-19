<%@ page import="iut2.brunetqu_projet_mi4.data.Groupe" %><%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 01/02/2023
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="groupe" type="iut2.brunetqu_projet_mi4.data.Groupe" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajout groupe</title>
    <jsp:include page='<%= application.getInitParameter("head")%>' />
</head>
<body>
<jsp:include page='<%=application.getInitParameter("header")%>' />
<main>
    <h2>Ajouter un groupe</h2>
    <form method="post">
        <fieldset>
            <legend>Informations du groupe</legend>
            <div>
                <label for="nom">Nom :</label>
                <input name="nom" id="nom" value="<%=groupe.getNom()%>">
            </div>

            <button type="submit">Valider</button>
        </fieldset>
    </form>
</main>
</body>
</html>
