<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Fichajes</title>
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
		<br /> <img src="https://i.imgur.com/bwlSMSI.png" style="width: %;">
		<br />
	</center>



	<div class="container fichaje-container col-md-4 col-md-offset-4 ">
		<div class="row">
			<h2>
				<span class=" glyphicon glyphicon-sort"></span> Fichajes
			</h2>



			<div class="gestion-form center-div">
				<h3>Gestión y Validación de Fichajes</h3>
				<form>

					<div class="form-inline text-center" style="margin: auto;">


						<form action="abrirFichaje" method="post">
							<input type="submit" class="btnfichaje input-lg"
								name="abrirFichaje" value="Abrir Fichaje" />
						</form>

						<form action="cerrarFichaje" method="post">
							<input type="submit" class="btnfichaje input-lg"
								name="cerrarFichaje" value="Cerrar Fichaje" />
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
					<input type="text" readonly class="form-control-plaintext"
						id="staticEmail" value="Usuario">

					<h4>Hora Actual:</h4>
					<input type="text" readonly class="form-control-plaintext"
						id="staticEmail" value="HH:MM:SS">





				</form>
				<!--DatePicker-->

				<h4>Elige Fecha Actual:</h4>
				<div class="container">
					<div class="row">
						<div class='col-sm-6'>
							<div class="form-group">
								<div class='input-group date' id='datetimepicker3'>
									<input type='text' class="form-control" /> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<script type="text/javascript">
                      $(function () {
                          $('#datetimepicker3').datetimepicker({
                              format: 'LT'
                          });
                      });
                  </script>
					</div>
				</div>

			</div>

			<table class="table table-bordered  table-dark ">
				<thead class="thead">
					<tr>
						<th scope="col">Fichajes</th>
						<th scope="col">Tipo</th>
						<th scope="col">Hora Fichaje</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">1</th>
						<td>Entrada</td>
						<td>8:45</td>
					</tr>
					<tr>
						<th scope="row">2</th>
						<td>Rodrigo</td>
						<td>8:30</td>
					</tr>
					<tr>
						<th scope="row">3</th>
						<td>Salida</td>
						<td>15:30</td>
					</tr>
				</tbody>
			</table>




		</div>

	</div>

	<div class="col-md-6" style="position: fixed; bottom: 0;">
		<div class="panel-footer">&copy; Copyright 2018 InTime -
			Equipo02. Todos los derechos reservados.</div>
	</div>
</body>
</html>
