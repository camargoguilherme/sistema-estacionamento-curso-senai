$(document).ready(function(){
	var select = function(value){
		let selectOption = '<select name="status-mensalista">'+
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
					'<input type="hidden" name="id-mensalista" id="id-mensalista" value="">'+
					'<div class="input-field col s12 m4" required>'+
						'<input type="text" name="nome-mensalista" id="nome-mensalista" placeholder="Nome do Mensalista">'+
					'</div>'+
					'<div class="input-field col s12 m4">'+
						'<input type="text" name="cpf-mensalista" id="cpf-mensalista" placeholder="Cpf do Mensalista">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="status-mensalista">'+
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
					'<li>CPF</li>'+
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
			url: '../MensalistaServlet',
			type: 'GET',
			data: {'id-mensalista':'0'},
			success: function(response){
				console.log(response);	
				var divSet;
				$.each(response, function(index, value) {
					divSet = $('<ul>'+
					'<li class="col-id">' + value.idMensalista + '</li>'+
					'<li class="col-nome">' + value.nomeMensalista + '</li>'+
					'<li class="col-cpf">' + value.cpfMensalista + '</li>'+
					'<li class="col-status">' + value.statusMensalista + '</li>'+
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
		let cpf = $(this).parent().parent().children('.col-cpf')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		
		$('main')[0].innerHTML = '<h2>Editar</h2>'+
				form('<a class="waves-effect waves-light btn green" id="btn-update">Atualizar</a>'+
						'<a class="waves-effect waves-light btn blue" id="btn-voltar">Voltar</a>');
				

		$('#id-mensalista').val(id);
		$('#nome-mensalista').val(nome);
		$('#cpf-mensalista').val(cpf);
		$('#status-mensalista').append(select(status));
		$('#btn-voltar').on('click', telaListar );
		$('#btn-update').on('click', update );
		M.AutoInit();
	}
	
	var telaCriar = function(){
		$('main')[0].innerHTML = '<h2>Editar</h2>'+
		form('<a class="waves-effect waves-light btn green" id="btn-salvar">Salvar</a>'+
					'<a class="waves-effect waves-light btn blue" id="btn-voltar">Voltar</a>');
		$('#status-mensalista').append(select(status));
		$('#btn-salvar').on('click', inserir );
		$('#btn-voltar').on('click', telaListar );
		
		M.AutoInit();
	}

	var editar = function (){		
		let id = $(this).parent().parent().children('.col-id')[0].innerText;
		let nome = $(this).parent().parent().children('.col-nome')[0].innerText;
		let cpf = $(this).parent().parent().children('.col-cpf')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		let mensalista = {
			'id-mensalista': id,
			'nome-mensalista': nome,
			'cpf-mensalista': cpf,
			'status-mensalista': status
		};
		
		$.ajax({
			url: '../MensalistaServlet',
			type: 'GET',
			data: mensalista,
			success: function(response) {
								
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados do Estado');
            }
		});
	}
	
	var inserir = function (){
		var mensalista = {
			'id-mensalista':0,
			'nome-mensalista': $('#nome-mensalista').val(),
			'cpf-mensalista': $('#cpf-mensalista').val(),
			'status-mensalista': $('input.select-dropdown').val().toLowerCase()
		}
		console.log(`inserir mensalista\n ${JSON.stringify(mensalista)}`);
		
		$.ajax({
			url: '../MensalistaServlet',
			type: 'POST',
			data: mensalista,
			success: function(response){
				alert("Mensalista Salvo");
				telaListar();
			}
		});
	}
	
	var update = function (){
		var mensalista = {
			'id-mensalista':$('#id-mensalista').val(),
			'nome-mensalista':$('#nome-mensalista').val(),
			'cpf-mensalista':$('#cpf-mensalista').val(),
			'status-mensalista':$('#status-mensalista')[0].childNodes[0].childNodes[0].value.toLowerCase()
		}
		console.log(`atualizar mensalista\n ${JSON.stringify(mensalista)}`)
		$.ajax({
			url: '../MensalistaServlet',
			type: 'POST',
			data: mensalista,
			success: function(response){
				telaListar();
			}
		});
	}
	
	var deletar = function (){
		let id = $(this).parent().parent().children('.col-id')[0].innerText;
		let nome = $(this).parent().parent().children('.col-nome')[0].innerText;
		let cpf = $(this).parent().parent().children('.col-cpf')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		alert('Deletando banco!');
	}
	
	telaListar();
	
});