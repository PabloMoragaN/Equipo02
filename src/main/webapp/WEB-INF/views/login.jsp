<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Login | InTime</title>

<style>
.login-form {
	padding: 5%;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.login-container h2 {
	text-align: left;
	background-color: #337ab7;
	color: #333;
}
</style>

</head>
<body>
	<center>
<img src="http://i65.tinypic.com/s5ybty.png" style="width:%;">
   <br/>
  	</div>
  </center>

	<div class="container login-container col-md-6 col-md-offset-3 ">
		<div class="row">
			<h2>
				<span class=" glyphicon glyphicon-pencil "></span> Login
			</h2>

			<div class="login-form center-div col-md-4">



				<form action="login" method="post" id="formlogin">
					<div class="form-group">
						<input name="txtUsuarioNombre" type="text" class="form-control" placeholder="Usuario *" value="" />
					</div>
					<div class="form-group">
						<input name="txtUsuarioPassword" type="password" class="form-control" placeholder="Contrase&ntilde;a *" value="" />
					</div>
					<div class="form-inline text-center" style="margin: auto;">
						<div class="form-group">
							<input type="submit" class="btnSubmit" value="Acceso" />
							<span style="color:red"><em>${alerta}</em></span>
						</div>
					</div>
					<div class="checkbox">
						<label><input type="checkbox">Recordar Usuario</label>
					</div>
					<div class="form-group">
						<a href="#" class="ForgetPwd">Recuperar Contrase&ntilde;a</a>
					</div>
				</form>
			</div>
			
		</div>

	</div>

</body>
</html>

