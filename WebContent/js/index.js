$(document).ready(function(){

	M.AutoInit();
	M.updateTextFields();
	
	$('#btn-estados').on('click', function(){
		window.location.href = 'estado/listar.jsp';
	});
	
	
	$('#btn-cidades').on('click', function(){
		window.location.href = 'cidade/listar.jsp';
	});
	
	$('#btn-usuarios').on('click', function(){
		//window.location.href = 'usario/listar.jsp';
	});

});