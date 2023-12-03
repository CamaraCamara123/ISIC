<%@page import="entities.Ville"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Liste des Villes</title>
    
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
        $(document).ready(function() {
            $('#villesTable').DataTable();
        });
    </script>
</head>
<body>

    <div id="navbar">
        <ul>
            <li><a href="ville.jsp">Villes</a></li>
            <li><a href="hotelController?action=accueil">Hotels</a></li>
        </ul>
    </div>
    <div class="container">
        <form action="VilleController" method="post" class="form">
            <input type="hidden" name="id" value="${ville.id}" />
            Nom : <input type="text" name="ville" value="${ville.nom}" />
            <button class="btn btn-primary">Enregistrer</button>
        </form>
    </div>

    <h1>Liste des villes : </h1>
    
    <div class="offset-4 mx-auto">
        <table id="villesTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${villes}" var="v">
                    <tr>
                        <td>${v.id}</td>
                        <td>${v.nom}</td>
                        <td>
                            <a href="villeController?action=formEdit&id=${v.id}" class="btn btn-primary">Modifier</a>
                            <a href="villeController?action=delete&id=${v.id}" class="btn btn-danger" onclick="return confirm('Voulez-vous vraiment supprimer cette ville ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
