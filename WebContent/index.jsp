<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta http-equiv="X-UA-Compatible" content="ie=edge">
		<title>Dashboard</title>
				<!--Import Google Icon Font-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--Import materialize.css-->
		<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

		<link rel="stylesheet" href="css/reset.css" >
		<link rel="stylesheet" href="css/style.css" >
		
		<script type="text/javascript" src="js/materialize.min.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
		
	</head>
	<body>
		<header>
		<!-- Dropdown Structure -->
		<ul id="menu" class="dropdown-content">
			<li><a href="#">Profile</a></li>
			<li class="divider"></li>
			<li><a href="#">Settings</a></li>
			<li class="divider"></li>
			<li><a href="#">Logout</a></li>
		</ul>
			<div class="navbar-fixed">
				<nav>
					<div class="nav-wrapper">
						<a href="#" class="brand-logo">Estacionamento</a>
						<ul id="nav-mobile" class="right hide-on-med-and-down">
							<li><a href="index.jsp">Dashboard</a></li>
							<!-- Dropdown Trigger -->
      						<li><a class="dropdown-trigger" href="#" data-target="menu">Menu<i class="material-icons right">arrow_drop_down</i></a></li>
						</ul>
					</div>
				</nav>
			</div>	
		</header>
		<main>
			<button id="btn-estados">Estados</button>
			<button id="btn-cidades">Cidades</button>
			<button id="btn-usuarios">Usuarios</button>
		</main>
		
		<%@ include file = "template/footer.jsp" %>
	</body>

</html>