$(document).ready(function(){
	var select = function(value){
		let selectOption = '<select name="status-estado">'+
						'<option disabled="disabled" selected="selected" seled>Status</option>';
		if(value === 'ativo'){
			selectOption += '<option selected="selected" value="ativo">Ativo</option>'+
			'<option value="ativo">Inativo</option></select>';
		}else if(value == 'inativo'){
			selectOption += '<option value="ativo">Ativo</option>'+
			'<option selected="selected" value="ativo">Inativo</option></select>';
		}else{
			selectOption += '<option value="ativo">Ativo</option>'+
			'<option value="ativo">Inativo</option></select>';
		}
	
		return selectOption;
	}

	var form = function(buttons){
		return '<div class="row form">'+
					'<form class="col s12">'+
					'<input type="hidden" name="id-estado" id="id-estado" value="">'+
					'<div class="input-field col s12 m4" required>'+
						'<input type="text" name="nome-estado" id="nome-estado" placeholder="Nome do estado">'+
					'</div>'+
					'<div class="input-field col s12 m4">'+
						'<input type="text" name="sigla-estado" id="sigla-estado" placeholder="Sigla do estado	">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="status-estado">'+
					'</div>'+
					'<div class="input-field col s12 m12" >'+
						buttons+
					'</div>'+
				'</form>'+
			'</div>';
	}
	

	var telaListar = function(){
		$('main')[0].innerHTML = '<div class="row data-table">'+
			'<a class="waves-effect waves-light btn green" id="btn-adicionar">Adicionar</a>'+
			'<div class="header">'+
				'<ul>'+
					'<li>ID</li>'+
					'<li>Nome</li>'+
					'<li>Sigla</li>'+
					'<li>Status</li>'+
					'<li>Ações</li>'+
				'</ul>'+
			'</div>'+
			'<div class="row body">'+					
			'</div>'+
			'<div class="paginator">'+
				'<ul class="pagination">'+
					'<li class="disabled">'+
						'<a href="#!"><i class="material-icons">chevron_left</i></a>'+
					'</li>'+
					'<li class="active"><a href="#!">1</a></li>'+
					'<li class="waves-effect"><a href="#!">2</a></li>'+
					'<li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>'+
				'</ul>'+
			'</div>'+
		'</div>';
		
		$.ajax({
			url: '../EstadoServlet',
			type: 'GET',
			data: {'id-estado':'0'},
			success: function(response){
				console.log(response);	
				var divSet;
				$.each(response, function(index, value) {
					divSet = $('<ul>'+
					'<li class="col-id">' + value.idEstado + '</li>'+
					'<li class="col-nome">' + value.nomeEstado + '</li>'+
					'<li class="col-sigla">' + value.siglaEstado + '</li>'+
					'<li class="col-status">' + value.status + '</li>'+
					'<li class="col-acoes">'+
					'<a class="waves-effect waves-light btn btn-editar blue">editar</a>'+
					'<a class="waves-effect waves-light btn btn-excluir red">excluir</a>' +
					'</ul>');
					$(".btn-editar", divSet).bind("click", telaEditar);  
					$(".btn-excluir", divSet).bind("click", deletar);
					$('.body').append(divSet);
					M.AutoInit();
				});
			}
		});
		
		$('#btn-excluir').on('click', deletar );
		$('#btn-adicionar').on('click', telaCriar );
		$('#btn-editar').on('click', telaEditar );
		M.AutoInit();
		//garrote bonitao
		
		
	}
	
	var telaEditar = function(){
		let id = $(this).parent().parent().children('.col-id')[0].innerText;
		let nome = $(this).parent().parent().children('.col-nome')[0].innerText;
		let sigla = $(this).parent().parent().children('.col-sigla')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		
		$('main')[0].innerHTML = '<h2>Editar</h2>'+
				form('<a class="waves-effect waves-light btn green" id="btn-update">Atualizar</a>'+
						'<a class="waves-effect waves-light btn blue" id="btn-voltar">Voltar</a>');
				

		$('#id-estado').val(id);
		$('#nome-estado').val(nome);
		$('#sigla-estado').val(sigla);
		$('#status-estado').append(select(status));
		$('#btn-voltar').on('click', telaListar );
		$('#btn-update').on('click', update );
		M.AutoInit();
	}
	
	var telaCriar = function(){
		$('main')[0].innerHTML = '<h2>Editar</h2>'+
		form('<a class="waves-effect waves-light btn green" id="btn-salvar">Salvar</a>'+
					'<a class="waves-effect waves-light btn blue" id="btn-voltar">Voltar</a>');
		$('#status-estado').append(select(status));
		$('#btn-salvar').on('click', inserir );
		$('#btn-voltar').on('click', telaListar );
		
		M.AutoInit();
	}

	var editar = function (){		
		let id = $(this).parent().parent().children('.col-id')[0].innerText;
		let nome = $(this).parent().parent().children('.col-nome')[0].innerText;
		let sigla = $(this).parent().parent().children('.col-sigla')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		let estado = {
			'id-estado': id,
			'nome-estado': nome,
			'sigla-estado': sigla,
			'status-estado': status
		};
		
		$.ajax({
			url: '../EstadoServlet',
			type: 'GET',
			data: estado,
			success: function(response) {
								
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados do Estado');
            }
		});
	}
	
	var inserir = function (){
		var estado = {
			'id-estado':0,
			'nome-estado': $('#nome-estado').val(),
			'sigla-estado': $('#sigla-estado').val(),
			'status-estado': $('#status-estado').val()
		}
		console.log(`inserir estado\n ${JSON.stringify(estado)}`)
		$.ajax({
			url: '../EstadoServlet',
			type: 'POST',
			data: estado,
			success: function(response){
				alert("Estado Salvo");
				telaListar();
			}
		});
	}
	
	var update = function (){
		var estado = {
			'id-estado':$('#id-estado').val(),
			'nome-estado':$('#nome-estado').val(),
			'sigla-estado':$('#sigla-estado').val(),
			'status-estado':$('#status-estado')[0].childNodes[0].childNodes[0].value.toLowerCase()
		}
		console.log(`atualizar estado\n ${JSON.stringify(estado)}`)
		$.ajax({
			url: '../EstadoServlet',
			type: 'POST',
			data: estado,
			success: function(response){
				telaListar();
			}
		});
	}
	
	var deletar = function (){
		let id = $(this).parent().parent().children('.col-id')[0].innerText;
		let nome = $(this).parent().parent().children('.col-nome')[0].innerText;
		let sigla = $(this).parent().parent().children('.col-sigla')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		alert('Deletando banco!');
	}
	
	telaListar();
	
});