<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
<title>Creacion de Usuario</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style>
.creacion-container {
    margin-left: 5%;
	margin-bottom: 5%;
	box-shadow: 5px 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.creacion-container h2{
	padding: 0% 0% 0% 5%;
}
.gestion-form {
	padding: 0% 5% 0% 5%;
}

.gestion-form h3 {
	text-align: left;
	color: #333;
	text-align: left;
	font-size: 17px;
    
}

.botones-form {
	padding: 5% 0% 5% 0%;
}

.botones-form .btnUsuario {
	font-weight: 600;
	color: #fff;
	background-color: #0062cc;
}

.textoIncidencia{
	resize: none;
}

</style>


<meta charset="UTF-8">
<title>Crear incidencia</title>
</head>

<body>

	<nav class="navbar navbar-inverse" style="background-color: #337ab7;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"></a>
			</div>
      
			<div class="nav-item pull-left col-md-1 fixed-top" style="position: relative; top: 8px">
				<form action="REfichajes" method="GET"> <button class="btn" type="submit"><strong><span class="glyphicon glyphicon-sort"></span> Fichajes</strong></button></form>
			</div>
			<div class="col-md-1 pull-right fixed-top" style="position: relative; top: 8px">
				<form action="logout" method="GET"> <button class="btn btn-danger" type="submit"><strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong></button></form>
			</div>
		</div>
	</nav>


	<div class="container creacion-container col-md-7 offset-md-2">
		<div class="row">
			<h2>
				<span class="glyphicon glyphicon-plus"></span> Crear incidencia
			</h2>

			<div class="gestion-form left-div">
				<form action="crearIncidencia" method="post" id="formCrearIncidencia">
		
					<div class="tipoIncidencia-form">
                            <h3>Tipo de incidencia:</h3>
							<select class="listaDesplegable" name = "listaTiposIncidencia">
						    	<option value = "Vacaciones"> Vacaciones </option>
						    	<option value = "Baja"> Baja médica </option>
                                <option value = "Ausencia"> Justificación de ausencia </option>
                                <option value = "Error"> Error de fichaje </option>
						    </select>
				 	</div>
				 	<div class="form-group">
						    <h3>Descripción de la incidencia: </h3>
                           <textarea class=textoIncidencia name=textoIncidencia rows="10" cols="100" ></textarea>
					</div>
						    <div class="botones-form text-right" style="margin: auto;">
						    	<input type="submit" class="btnUsuario input-lg"
									name="Aceptar" value="Aceptar" />
								
							</div>
					
				</form>
			</div>

		</div>

	</div>

</body>
</html>