<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" http-equiv="Content-Type"
	content="text/html,width=device-width, initial-scale=1, shrink-to-fit=no ">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



<style>


.historial-container {
	margin-top: 5%;
}

.gestion-div{
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
	padding-bottom: 5px;
}


.historial-div {
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.historial-div h2 {
	color: #333;
}


.btnUsuario {
	width: 50%;
	border-radius: 2rem;
	padding: 0%;
	border: solid;
	cursor: pointer;
	margin-bottom: 5px;
	font-weight: 600;
	color: #fff;
	background-color: #0062cc;
}

.btnFichajes {
	border-radius: 2rem;
	border: solid;
	cursor: pointer;
	font-weight: 600;
	color: #fff;
	background-color: #0062cc;
}

.btn-xl {
    padding: 10px 20px;
    font-size: 20px;
    border-radius: 10px;
}


.btn-space {
    margin-right: 5px;
}

.btn-verticalspace{
	margin-bottom:30px;
}

.centerdiv {
  display: flex;
  justify-content: center;
}


.inlinediv{
float: left;
}

.btnAbrir{
    font-size: 20px;
    border-radius: 10px;
margin-bottom:30px;
margin-right: 10px;

}

.btnCerrar{
    font-size: 20px;
    border-radius: 10px;
margin-bottom:30px;
}

.btnListar{
 width: 150px;
 padding: 10px 10px;
    font-size: 15px;
    border-radius: 15px;
margin-bottom:10px;	
}

.logo{

width:180px;
height:180px
}

</style>


</head>

<body>

	<nav class="navbar navbar-inverse" style="background-color: #337ab7;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"></a>
			</div>
			<div class="nav-item pull-left fixed-top"
				style="position: relative; top: 8px">
				<form action="REfichajesAdminNav" method="GET">
					<button class="btn btn-space" type="submit">
						<strong><span class="glyphicon glyphicon-sort"></span>
							Fichajes</strong>
					</button>
				</form>
			</div>
			<div class="nav-item pull-left fixed-top"
				style="position: relative; top: 8px">
				<form action="interfazCrearIncidencia" method="GET">
					<button class="btn btn-space" type="submit">
						<strong><span class="glyphicon glyphicon-sort"></span>
							Incidencias</strong>
					</button>
				</form>
			</div>
			<div class="nav-item pull-right fixed-top"
				style="position: relative; top: 8px">
				<form action="logout" method="GET">
					<button class="btn btn-danger" type="submit">
						<strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong>
					</button>
				</form>
			</div>
		</div>
	</nav>

	<center>
		<br /> <img src="https://i.imgur.com/bwlSMSI.png" style="width: %;">
		<br />
	</center>



	<div class="container">
		<div class="row">
			<div class="col-md-5 col-md-offset-4">
				<div class="inlinediv" >
					<form action="abrirFichajeAdmin" method="post">
						<button type="submit" id="btnAbrir" class="btn btn-primary btnAbrir btnFichajes" value="Abrir Fichaje">Abrir Fichaje</button>
					</form>
				</div>

				<div >
					<form action="cerrarFichajeAdmin" method="post">
						<button type="submit" id="btnCerrar" class="btn btn-primary btnCerrar btnFichajes" value="Cerrar Fichaje">Cerrar Fichaje</button>
					</form>
				</div>
			</div>
			
		</div>
	</div>

	<div class="container centerdiv col-md-6 col-md-offset-3">
			<div class="row">
				<span style="color:red"><em>${errorMessageCerrar}</em></span>
				<span style="color:red"><em>${errorMessageAbrir}</em></span>
				<span style="color: green"><em>${alertaFichaje}</em></span>
			</div>
	</div>
	

	
	<div class="container col-md-12" style="margin-top: 5%;">
			<div class="row">

						<div class="historial-div collapse.in col-md-5 col-md-offset-1" id="panelFichajes">
												<h2>
															<span class="glyphicon glyphicon-edit"></span> Listar Fichajes
												</h2>
						
									<form id="formListar" action="consultaFichajesFechaAdmin" method="get">
												
												<fieldset class="form-group">
															
															<label>Introduzca primera fecha del periodo de fichajes:<br></label> 
															<input placeholder="yyyy-MM-dd" type="text" name="fecha1" id="fecha1">
															<label>Introduzca segunda fecha del periodo de fichajes:<br></label> 
															<input placeholder="yyyy-MM-dd" type="text" name="fecha2" id="fecha2">
															<button id="btnListar" class="btn btn-primary btnListar btnUsuario" type="submit" data-toggle="collapse.in" data-target="#panelFichajes" aria-expanded="false" aria-controls="panelFichajes">Listar Fichajes</button>
												</fieldset>
														<span style="color: red"><em>${nullFecha}</em></span>
									</form>
											
										<table class="table table-bordered">
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
														<td onload="switchState();" id="tdState">${fichaje.estado}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>

						</div>

					

						<div class="gestion-div col-md-4 col-md-offset-1">
								<h2>
									<span class=" glyphicon glyphicon-asterisk"></span> Gesti&oacuten de usuarios
								</h2>
									<form method = "get">
										<div class="text-center"">
										
												<input type="submit" class="btnUsuario input-lg"
													name="crearUsuario" value="Crear usuario" formaction = "interfazCrearUsuario" />
												
												<input type="submit" class="btnUsuario input-lg"
													name="modificarUsuario" value="Modificar usuario" formaction = "modificarUsuario"/>
											
												<input type="submit" class="btnUsuario input-lg"
													name="eliminarUsuario" value="Eliminar usuario" formaction = "interfazEliminarUsuario"/>
													
												<input type="submit" class="btnUsuario input-lg"
													name="modificarPwd" value="Modificar contrase&ntilde;a usuario" formaction = "adminUpdatePwd"/>
										
										</div>
									</form>
						</div>

							

						</div>
			
			</div>
	
	
</body>
</html>
