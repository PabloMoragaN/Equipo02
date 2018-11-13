<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1" import="com.uclm.equipo02.FichajeController"%>

<!DOCTYPE html>
<html>
<head>
<script src=" https://code.jquery.com/jquery.js ">
	
</script>
<script
	src=" https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js">
	
</script>
<link rel="stylesheet" href="css/estilos.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<title>Home</title>
</head>

<style>
.fichaje-container {
  margin-top: 5%;
	margin-bottom: 5%;
}

.historial-container {
	margin-top: 10%;
}

.gestion-form {
	padding: 5%;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.gestion-form h3 {
	text-align: center;
	color: #333;
}

.historial-form {
	padding: 5%;
	background: #0062cc;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.historial-form h3 {
	text-align: center;
	color: #fff;
}

.historial-form h4 {
	color: #fff;
}

.btnfichaje {
	width: 50%;
	border-radius: 2rem;
	padding: 0%;
	border: solid;
	cursor: pointer;
}

.gestion-form .btnfichaje {
	font-weight: 600;
	color: #fff;
	background-color: #0062cc;
}

</style>


<body>
<!-- 
<script>
function myFunction() {
    var x = document.getElementById("listaFichajes");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
</script>
-->

	<h1 align="center">EQUIPO 02</h1>
	</br>
	<form action="abrirFichaje" method="post">
		<input type="submit" name="abrirFichaje" value="Abrir Fichaje" />
	</form>

	<form action="cerrarFichaje" method="post">
		<input type="submit" name="cerrarFichaje" value="Cerrar Fichaje" />
	</form>
  

	 <form action="listarFichajesEmpleado"  method="post">
		<table align="center" border="1" width: 100%>
			<thead class="thead">
				<tr>
					<th scope="col">Fecha</th>
					<th scope="col">Entrada</th>
					<th scope="col">Salida</th>
					<th scope="col">Estado</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="fich" items="${listaFichajes}">
					<td>${fich.fechaFichaje}</td>
					<td>${fich.horaFichaje}</td>
					<td>${fich.horaCierre}</td>
					<td>${fich.estado}</td>
				</c:forEach>
			</tbody>
		</table>
	</form>

  




</body>
</html>
</body>
</html>
  
  
<!--  <button onclick="myFunction()">Mostrar Fichajes</button>

   <div id="listaFichajes"class="container historial-container col-md-6 col-md-offset-3">
  <form class"historial-form" action="listarFichajesEmpleado"  method="post">
		<table class="table table-bordered table-dark ">
			<thead class="thead">
				<tr>
					<th scope="col">Fecha</th>
					<th scope="col">Entrada</th>
					<th scope="col">Salida</th>
					<th scope="col">Estado</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="fich" items="${fichajes}">
					<td>${fich.fechaFichaje}</td>
					<td>${fich.horaFichaje}</td>
					<td>${fich.horaCierre}</td>
					<td>${fich.estado}</td>
				</c:forEach>
			</tbody>
		</table>
	</form>
  </div> -->
  
  
 


<!--  
<!DOCTYPE html>

<html>
<head>
<title>Fichajes | InTime</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style>
.fichaje-container {
	margin-top: 5%;
	margin-bottom: 5%;
}

.historial-container {
	margin-top: 10%;
}

.gestion-form {
	padding: 5%;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.gestion-form h3 {
	text-align: center;
	color: #333;
}

.historial-form {
	padding: 5%;
	background: #0062cc;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.historial-form h3 {
	text-align: center;
	color: #fff;
}

.historial-form h4 {
	color: #fff;
}

.btnfichaje {
	width: 50%;
	border-radius: 2rem;
	padding: 0%;
	border: solid;
	cursor: pointer;
}

.gestion-form .btnfichaje {
	font-weight: 600;
	color: #fff;
	background-color: #0062cc;
}
</style>


</head>

<body>

	<nav class="navbar navbar-inverse" style="background-color: #337ab7;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"></a>
			</div>
			<ul class="nav navbar-nav">
				<li style="color: white; font-size: 15px;"><a href="#">Inicio</a></li>
				<li class="active" style="color: red; font-size: 15px;"><a
					href="#">Fichajes</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
						Cerrar Sesión</a></li>
			</ul>
		</div>
	</nav>

	 <center>
		<br /> <img src="http://i65.tinypic.com/s5ybty.png" style="width: %;">
		<br />
	</center>



	<div class="container fichaje-container col-md-4 col-md-offset-4 ">
		<div class="row">
			<h2>
				<span class=" glyphicon glyphicon-sort"></span> Fichajes
			</h2>



			<div class="gestion-form">
				<h3>Gestión y Validación de Fichajes</h3>
				<form>

					<div class="form-inline text-center" style="margin: auto;">


						<form action="abrirFichaje" method="post">
							<input type="submit" class="btnfichaje" value="Abrir Fichaje" />
						</form>

						<form action="cerrarFichaje" method="post">
							<input type="submit" class="btnfichaje" value="Cerrar Fichaje" />
						</form>

					</div>
				</form>
			</div>

		</div>

	</div>

	<div class="container historial-container col-md-6 col-md-offset-3">

		<div class="row">
			<div class="historial-form">

				<h3>Historial de Fichajes</h3>
				<form action=" " class="form-inline">
					<h4>Usuario:</h4>
					<input type="text" class="form-control-plaintext" value="Usuario">

					<h4>Hora Actual:</h4>
					<input type="text" class="form-control-plaintext" value="HH:MM:SS">


			</div>
		</div>

	</div>

	<div class="col-md-6" style="position: fixed; bottom: 0;">
		<div class="panel-footer">&copy; Copyright 2018 InTime -
			Equipo02. Todos los derechos reservados.</div>
	</div>
</body>
</html>-->



