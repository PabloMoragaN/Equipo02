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



<title>Gestión y Validación de Fichajes</title>
</head>

<style>

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

.historial{
padding: 15px;
}

.inlinediv{
float: left;
}

.h1div{
display: flex;
  justify-content: center;
  margin-bottom:30px;

}

.btnAbrir{
 padding: 10px 20px;
    font-size: 20px;
    border-radius: 10px;
margin-bottom:30px;
margin-right: 10px;

}

.btnCerrar{

 padding: 10px 20px;
    font-size: 20px;
    border-radius: 10px;
margin-bottom:30px;
}

.btnListar{
 padding: 15px 15px;
    font-size: 15px;
    border-radius: 15px;
margin-bottom:10px;	
}

.logo{

width:180px;
height:180px
}


</style>




<script>
	
function switchState() {
	
	var tdElem = document.getElementById ( "tdState" );
	var tdText = tdElem.innerHTML;
    if(tdText="true"){
    	document.getElementById('tdState').innerHTML = "Abierto";
    }else{
    	document.getElementById('tdState').innerHTML = "Cerrado";
    }     
}	
	
	
</script>




<body>

	<nav class="navbar navbar-inverse" style="background-color: #337ab7;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"></a>
			</div>
			<div class="nav-item pull-left fixed-top"
				style="position: relative; top: 8px">
				<form action="inicio" method="GET">
					<button class="btn btn-space" type="submit">
						<strong><span class="glyphicon glyphicon-sort"></span>
							Fichajes</strong>
					</button>
				</form>
			</div>
			<div class="nav-item pull-left  fixed-top"
				style="position: relative; top: 8px">
				<form action="fichajes" method="GET">
					<button class="btn btn-space " type="submit">
						<strong><span class="glyphicon glyphicon-copy"></span>
							Gestionar Incidencias</strong>
					</button>
				</form>
			</div>
			<div class="nav-item pull-left fixed-top"
				style="position: relative; top: 8px">
				<form action="Incidencias" method="GET">
					<button class="btn btn-space" type="submit">
						<strong><span class="glyphicon glyphicon-cog"></span>
							Gestionar Contraseña</strong>
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
	<img src="https://i.imgur.com/bwlSMSI.png" class="logo">
	</center>
	<div class = "page-header h1div">
	
   <h1>
      Gestión y Validación de Fichajes
   </h1>
   
</div>

	<div class="container centerdiv">
		
			<div class="inlinediv">
				<form action="abrirFichaje" method="post">
					<button type="submit" id="btnAbrir" class="btn btn-primary btnAbrir" value="Abrir Fichaje" >Abrir Fichaje</button>
				</form>
			</div>
			
			<div class="inlinediv ">
				<form action="cerrarFichaje" method="post">
					<button type="submit" id="btnCerrar" class="btn btn-primary btnCerrar"value="Cerrar Fichaje" >Cerrar Fichaje</button>
				</form>
			</div>
	
	</div>



	<div class="historial collapse.in col-md-8 col-md-offset-2" id="panelFichajes">
	
	<form id="formListar" action="listarFichajesEmpleado" method="get">
				<button id="btnListar" class="btn btn-primary btnListar  " type="submit" data-toggle="collapse.in" data-target="#panelFichajes" aria-expanded="false" aria-controls="panelFichajes">ListarFichajes</button>
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
						<td id="tdState" onload="switchState();">${fichaje.estado}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>