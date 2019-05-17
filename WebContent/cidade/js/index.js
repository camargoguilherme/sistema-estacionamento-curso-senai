$(document).ready(function(){

	var selectStatus = function(value){
		let selectOption = '<select name="status-cidade">'+
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
	
	var selectEstados = function(estados, estadoCidade = null){
		let selectOption = '<select name="estado-cidade">'+
						'<option disabled="disabled" selected="selected">Estado</option>';
		$.each(estados, function(key, value){
			if((value.nomeEstado + ' - ' + value.siglaEstado ) === estadoCidade){
				selectOption += `<option selected="selected" value="${value.idEstado}">${value.nomeEstado} - ${value.siglaEstado }</option>`;
			}else{
				selectOption += `<option value="${value.idEstado}">${value.nomeEstado} - ${value.siglaEstado }</option>`;
			}
			
		});
		selectOption += '</select>';
		return selectOption;
	}

	var form = function(buttons){
		return '<div class="row form">'+
					'<form class="col s12">'+
					'<input type="hidden" name="id-cidade" id="id-cidade" value="">'+
					'<div class="input-field col s12 m4" required>'+
						'<input type="text" name="nome-cidade" id="nome-cidade" placeholder="Nome da cidade">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="estado-cidade">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="status-cidade">'+
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
					'<li>Estado</li>'+
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
			url: '../CidadeServlet',
			type: 'GET',
			data: {'id-cidade':'0'},
			success: function(response){
				console.log(response);	
				var divSet;
				$.each(response, function(index, value) {
					divSet = $('<ul>'+
					'<li class="col-id">' + value.idCidade + '</li>'+
					'<li class="col-nome">' + value.nomeCidade + '</li>'+
					'<li class="col-estado">' + value.estadoCidade.nomeEstado + " - " +value.estadoCidade.siglaEstado + '</li>'+
					'<li class="col-status">' + value.statusCidade + '</li>'+
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
		let estado = $(this).parent().parent().children('.col-estado')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		
		$('main')[0].innerHTML = '<h2>Editar</h2>'+
				form('<a class="waves-effect waves-light btn green" id="btn-update">Atualizar</a>'+
						'<a class="waves-effect waves-light btn blue" id="btn-voltar">Voltar</a>');
		$.ajax({
			url: '../EstadoServlet',
			type: 'GET',
			data: {'id-estado':0},
			success: function(response) {
				$('#id-cidade').val(id);
				$('#nome-cidade').val(nome);
				$('#estado-cidade').append(selectEstados(response, estado));
				$('#status-cidade').append(selectStatus(status));
				$('#btn-voltar').on('click', telaListar );
				$('#btn-update').on('click', update );
				M.AutoInit();
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados da cidade');
            }
		});
		
		
	}
	
	var telaCriar = function(){
		$('main')[0].innerHTML = 
		form('<a class="waves-effect waves-light btn green" id="btn-salvar">Salvar</a>'+
					'<a class="waves-effect waves-light btn blue" id="btn-voltar">Voltar</a>');
		
		$.ajax({
			url: '../EstadoServlet',
			type: 'GET',
			data: {'id-estado':0},
			success: function(response) {
				$('#estado-cidade').append(selectEstados(response));
				$('#status-cidade').append(selectStatus(status));
				$('#btn-salvar').on('click', inserir );
				$('#btn-voltar').on('click', telaListar );
				M.AutoInit();
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados da cidade');
            }
		});
		
	}

//	var editar = function (){		
//		let id = $(this).parent().parent().children('.col-id')[0].innerText;
//		let nome = $(this).parent().parent().children('.col-nome')[0].innerText;
//		let estado = $(this).parent().parent().children('.col-estado')[0].innerText.split(' - ')[1];
//		let status = $(this).parent().parent().children('.col-status')[0].innerText;
//		let cidade = {
//			'id-cidade': id,
//			'nome-cidade': nome,
//			'estado-cidade': estado,
//			'status-cidade': status
//		};
//		
//		$.ajax({
//			url: '../CidadeServlet',
//			type: 'GET',
//			data: cidade,
//			success: function(response) {
//								
//            },
//            error: function(xhr, ajaxOptions, thrownError) {
//                console.log(xhr, ajaxOptions, thrownError);
//                alert('Erro ao recuperar dados da cidade');
//            }
//		});
//	}
	
	var inserir = function (){
		var cidade = {
			'id-cidade':0,
			'nome-cidade':$('#nome-cidade').val(),
			'estado-cidade':$('#estado-cidade')[0].childNodes[0].childNodes[0].value.split(' - ')[1],
			'status-cidade':$('#status-cidade')[0].childNodes[0].childNodes[0].value.toLowerCase()
		};
		console.log(`inserir cidade\n ${JSON.stringify(cidade)}`)
		$.ajax({
			url: '../CidadeServlet',
			type: 'POST',
			data: cidade,
			success: function(response){
				alert("cidade Salvo");
				telaListar();
			}
		});
	}
	
	var update = function (){
		var cidade = {
			'id-cidade':$('#id-cidade').val(),
			'nome-cidade':$('#nome-cidade').val(),
			'estado-cidade':$('#estado-cidade')[0].childNodes[0].childNodes[0].value.split(' - ')[1],
			'status-cidade':$('#status-cidade')[0].childNodes[0].childNodes[0].value.toLowerCase()
		}
		
		console.log(`atualizar cidade\n ${JSON.stringify(cidade)}`)
		
		$.ajax({
			url: '../CidadeServlet',
			type: 'POST',
			data: cidade,
			success: function(response){
				telaListar();
			}
		});
	}
	
	var deletar = function (){
		let id = $(this).parent().parent().children('.col-id')[0].innerText;
		let nome = $(this).parent().parent().children('.col-nome')[0].innerText;
		let sigla = $(this).parent().parent().children('.col-estado	')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		alert('Deletando banco!');
	}
	
	telaListar();
	
});