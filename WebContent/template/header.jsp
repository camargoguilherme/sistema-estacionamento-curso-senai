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
		<link type="text/css" rel="stylesheet" href="../css/materialize.min.css"  media="screen,projection"/>

		<!--  <link rel="stylesheet" href="../css/reset.css" >-->
		<link rel="stylesheet" href="../css/style.css" >
		
		<script type="text/javascript" src="../js/materialize.min.js"></script>
		<script type="text/javascript" src="../js/jquery.js"></script>
		<script type="text/javascript" src="../js/index.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
		<!-- <script type="text/javascript" src="js/editar.js"></script>  -->
		
	</head>
	<body>
		<header>
		<!-- Dropdown Structure -->
		<ul id="menu" class="dropdown-content">
			<li><a href="../estado/index.jsp">Estados</a></li>
			<li class="divider"></li>
			<li><a href="../cidade/index.jsp">Cidades</a></li>
			<li class="divider"></li>
			<li><a href="../endereco/index.jsp">Enderecos</a></li>
			<li class="divider"></li>
			<li><a href="../mensalista/index.jsp">Mensalistas</a></li>
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
							<li><a href="../index.jsp">Dashboard</a></li>
							<!-- Dropdown Trigger -->
      						<li><a class="dropdown-trigger" href="#" data-target="menu">Cadastro<i class="material-icons right">arrow_drop_down</i></a></li>
						</ul>
					</div>
				</nav>
			</div>	
		</header>