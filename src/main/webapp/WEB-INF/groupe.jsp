<%@ page import="iut2.brunetqu_projet_mi4.data.Groupe" %>
<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %><%--
  Created by IntelliJ IDEA.
  User: quentin
  Date: 18/01/2023
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="groupes" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Groupe>" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page='<%= application.getInitParameter("header")%>' />
    <Table>
        <thead>
            <tr>
                <th>
                    id
                </th>
                <th>
                    nom
                </th>
                <th>
                    etudiants
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
                <td>
                    <%for(Etudiant etudiant : groupe.getEtudiants()) {%>
                        <%=etudiant.getPrenom() + " " + etudiant.getNom() + ", "%>
                    <%}%>
                </td>
            </tr>
        <%}%>
        </tbody>
    </Table>
</body>
</html>
