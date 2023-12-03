<%@page import="entities.Hotel"%>
<%@page import="entities.Ville"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Liste des Hôtels</title>
    
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
        $(document).ready(function() {
            $('#hotelsTable').DataTable();
        });
    </script>
</head>
<body>

    <div id="navbar">
        <ul>
            <li><a href="villeController">Villes</a></li>
            <li><a href="Hotel.jsp">Hotels</a></li>
        </ul>
    </div>

    <div class="container">
    <form action="hotelController" method="post" class="form">
        <!-- Champ caché pour l'ID lors de la mise à jour -->
        <input type="hidden" name="id" value="${hotel.id}" />

        <input type="text" name="nom" value="${hotel.nom}" placeholder="Nom de l'hôtel" required />
        <input type="text" name="adresse" value="${hotel.adresse}" placeholder="Adresse" required />
        <input type="text" name="telephone" value="${hotel.telephone}" placeholder="Téléphone" required />

        <select name="villeId">
		    <c:forEach items="${villes}" var="v">
		        <c:set var="selected" value="${v.id eq hotel.ville.id ? 'selected' : ''}" />
		        <option value="${v.id}" ${selected}>${v.nom}</option>
		    </c:forEach>
		</select>
        <button class="btn btn-primary">save</button>
    </form>
</div>

    <h1>Liste des hôtels : </h1>
    
    <div class="offset-4 mx-auto">
        <table id="hotelsTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Adresse</th>
                    <th>Téléphone</th>
                    <th>Ville</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${hotels}" var="hotel">
                    <tr>
                        <td>${hotel.id}</td>
                        <td>${hotel.nom}</td>
                        <td>${hotel.adresse}</td>
                        <td>${hotel.telephone}</td>
                        <td>${hotel.ville.nom}</td>
                        <td>
                        	<a href="hotelController?action=formEdit&id=${hotel.id}" class="btn btn-primary">Editer</a>
                            <a href="hotelController?action=delete&id=${hotel.id}" class="btn btn-danger" onclick="return confirm('Voulez-vous vraiment supprimer cet hôtel ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
