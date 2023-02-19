<%@ page import="iut2.brunetqu_projet_mi4.data.Etudiant" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="etudiants" type="java.util.Collection<iut2.brunetqu_projet_mi4.data.Etudiant>" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Etudiants</title>
        <jsp:include page='<%= application.getInitParameter("head")%>' />
    </head>
    <body>
        <jsp:include page='<%= application.getInitParameter("header")%>' />

        <main>
            <h2>Liste des étudiants</h2>

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
                        Groupe
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
                            <td>
                                <%= etudiant.getGroupe() != null ? etudiant.getGroupe().getNom() : "-" %>
                            </td>
                            <td>
                                <%=etudiant.getNbrAbsences() %>
                            </td>
                            <td>
                                <%= etudiant.getMoyenne() == -1 ? "-" : etudiant.getMoyenne()  %>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/do/editionetudiant/<%=etudiant.getId() %>">
                                    <i class="fas fa-pen-to-square" title="Editer"></i>
                                </a>
                                <a href="${pageContext.request.contextPath}/do/suppressionetudiant/<%=etudiant.getId() %>">
                                    <i class="fas fa-trash-can" title="Supprimer"></i>
                                </a>
                            </td>
                        </tr>
                    <%}%>
                </tbody>
            </Table>

            <div class="plus-button"><a href="${pageContext.request.contextPath}/do/ajouteretudiant/"><i class="fas fa-plus" title="Ajouter un étudiant"></i></a></div>
        </main>

    </body>
</html>