<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" http-equiv="Content-Type" content="text/html,width=device-width, initial-scale=1, shrink-to-fit=no ">

<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



<title>Fichajes</title>
</head>

<style>



</style>

<script>
jQuery(document).ready(function(){
    jQuery('#btnListar').on('click', function(event) {        
         jQuery('#panelFichajes').toggle('show');
    });
});

</script>




<body>

	<nav class="navbar navbar-inverse" style="background-color: #337ab7;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"></a>
			</div>
			<div class="nav-item pull-left col-md-1 fixed-top"
				style="position: relative; top: 8px">
				<form action="inicio" method="GET">
					<button class="btn" type="submit">
						<strong><span class="glyphicon glyphicon-sort"></span>
							Fichajes</strong>
					</button>
				</form>
			</div>
			<div class="nav-item pull-left col-md-1 fixed-top"
				style="position: relative; top: 8px">
				<form action="fichajes" method="GET">
					<button class="btn" type="submit">
						<strong><span class="glyphicon glyphicon-copy"></span>
							Gestionar Incidencias</strong>
					</button>
				</form>
			</div>
			<div class="nav-item pull-left col-md-1 fixed-top"
				style="position: relative; top: 8px">
				<form action="ncidencias" method="GET">
					<button class="btn" type="submit">
						<strong><span class="glyphicon glyphicon-cog"></span>
							Gestionar Contraseña</strong>
					</button>
				</form>
			</div>
			<div class="col-md-1 pull-right fixed-top"
				style="position: relative; top: 8px">
				<form action="logout" method="GET">
					<button class="btn btn-danger" type="submit">
						<strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong>
					</button>
				</form>
			</div>
		</div>
	</nav>


	<form action="abrirFichaje" method="post">
		<input type="submit" name="abrirFichaje" value="Abrir Fichaje" />
	</form>

	<form action="cerrarFichaje" method="post">
		<input type="submit" name="cerrarFichaje" value="Cerrar Fichaje" />
	</form>

	<form id="formListar" action="listarFichajesEmpleado" method="get">
		<button id="btnListar" class="btn btn-primary" type="button" >Listar Fichajes</button>
	</form>





	<div class="collapse.in" id="panelFichajes">
		<table class="table table-bordered" align="center">
			<thead class="thead">
				<tr>
					<th scope="col">Fecha</th>
					<th scope="col">Entrada</th>
					<th scope="col">Salida</th>
					<th scope="col">Estado</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listafichajes}" var="fichaje">
					<tr>
						<td>${fichaje.fechaFichaje}</td>
						<td>${fichaje.horaEntrada}</td>
						<td>${fichaje.horaSalida}</td>
						<td>${fichaje.estado}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>
