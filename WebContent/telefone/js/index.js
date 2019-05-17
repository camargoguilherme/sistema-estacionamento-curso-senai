$(document).ready(function(){

	var selectStatus = function(value){
		let selectOption = '<select name="status-telefone">'+
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
	
	var selectTipoTelefone = function( tipo ){
		let selectOption = '<select name="status-telefone">'+
		'<option disabled="disabled" selected="selected" >Tipo de Telefone</option>';
		if(tipo == 'residencial'){
			selectOption += '<option selected="selected" value="residencial">Residencial</option>'+
				'<option value="comercial">Comercial</option>'+
				'<option value="celular">Celular</option>';
		}else if(tipo == 'comercial'){
			selectOption += '<option value="residencial">Residencial</option>'+
				'<option selected="selected" value="comercial">Comercial</option>'+
				'<option value="celular">Celular</option>';
		}else if(tipo == 'celular' ){
			selectOption += '<option value="residencial">Residencial</option>'+
				'<option value="comercial">Comercial</option>'+
				'<option selected="selected" value="celular">Celular</option>';
		}
		else{
			selectOption += '<option value="residencial">Residencial</option>'+
				'<option value="comercial">Comercial</option>'+
				'<option value="celular">Celular</option>';
		}
		selectOption += '</select>';		
		return selectOption;
	}
	
	var selectMensalista = function(mensalistas, mensalistaAtual = null){
		let selectOption = '<select name="mensalista-endereco">'+
						'<option disabled="disabled" selected="selected">Mensalista</option>';
		$.each(mensalistas, function(key, value){
			if(value.nomeMensalista == mensalistaAtual){
				selectOption += `<option selected="selected" value="${value.idMensalista}">${value.nomeMensalista}</option>`;
			}else{
				selectOption += `<option value="${value.idMensalista}">${value.nomeMensalista}</option>`;
			}
			
		});
		selectOption += '</select>';
		return selectOption;
	}

	var form = function(buttons){
		return '<div class="row form">'+
					'<form class="col s12">'+
					'<input type="hidden" name="id-telefone" id="id-telefone" value="">'+
					'<div class="input-field col s12 m4" required>'+
						'<input type="text" name="numero-telefone" id="numero-telefone" placeholder="Numero de telefone">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="tipo-telefone">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="mensalista-telefone">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="status-telefone">'+
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
					'<li>Numero</li>'+
					'<li>Tipo de Telefone</li>'+
					'<li>Mensalista</li>'+
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
			data: {'id-mensalista':0},
			success: function(response) {
				$('#mensalista-telefone').append(selectMensalista(response));
				M.AutoInit();
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados da endereco');
            }
		});
		
		$.ajax({
			url: '../TelefoneServlet',
			type: 'GET',
			data: {'id-telefone':'0'},
			success: function(response){
				console.log(response);	
				var divSet;
				$.each(response, function(index, value) {
					divSet = $('<ul>'+
					'<li class="col-id">' + value.idTelefone + '</li>'+
					'<li class="col-numero">' + value.numTelefone + '</li>'+
					'<li class="col-tipo">' + value.tipoTelefone+'</li>'+
					'<li class="col-mensalista">' + value.mensalistaTelefone.nomeMensalista+'</li>'+
					'<li class="col-status">' + value.statusTelefone + '</li>'+
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
		let numero = $(this).parent().parent().children('.col-numero')[0].innerText;
		let tipo = $(this).parent().parent().children('.col-tipo')[0].innerText;
		let nome = $(this).parent().parent().children('.col-mensalista')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		
		$('main')[0].innerHTML = '<h2>Editar</h2>'+
				form('<a class="waves-effect waves-light btn green" id="btn-update">Atualizar</a>'+
						'<a class="waves-effect waves-light btn blue" id="btn-voltar">Voltar</a>');
		
		$.ajax({
			url: '../MensalistaServlet',
			type: 'GET',
			data: {'id-mensalista':0},
			success: function(response) {
				$('#mensalista-endereco').innerHTML = selectMensalista(response, nome);
				M.AutoInit();
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados da endereco');
            }
		});
		
		$('#id-telefone').val(id);
		$('#numero-telefone').val(numero);
		$('#tipo-telefone').append(selectTipoTelefone(tipo));
		$('#status-telefone').append(selectStatus(status));
		$('#btn-voltar').on('click', telaListar );
		$('#btn-update').on('click', update );
		M.AutoInit();
		
	}
	
	var telaCriar = function(){
		$('main')[0].innerHTML = 
		form('<a class="waves-effect waves-light btn green" id="btn-salvar">Salvar</a>'+
					'<a class="waves-effect waves-light btn blue" id="btn-voltar">Voltar</a>');
		
		$.ajax({
			url: '../MensalistaServlet',
			type: 'GET',
			data: {'id-mensalista':0},
			success: function(response) {
				$('#mensalista-endereco').append(selectMensalista(response));
				M.AutoInit();
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados da endereco');
            }
		});
		
		$.ajax({
			url: '../TelefoneServlet',
			type: 'GET',
			data: {'id-estado':0},
			success: function(response) {
				$('#tipo-telefone').append(selectTipoTelefone);
				$('#status-telefone').append(selectStatus(status));
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
//		let numero = $(this).parent().parent().children('.col-numero')[0].innerText;
//		let estado = $(this).parent().parent().children('.col-estado')[0].innerText.split(' - ')[1];
//		let status = $(this).parent().parent().children('.col-status')[0].innerText;
//		let cidade = {
//			'id-telefone': id,
//			'numero-telefone': numero,
//			'tipo-telefone': estado,
//			'status-telefone': status
//		};
//		
//		$.ajax({
//			url: '../TelefoneServlet',
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
			'id-telefone':0,
			'numero-telefone':$('#numero-telefone').val(),
			'tipo-telefone':$('#tipo-telefone')[0].childNodes[0].childNodes[0].value.split(' - ')[1],
			'status-telefone':$('#status-telefone')[0].childNodes[0].childNodes[0].value.toLowerCase()
		};
		console.log(`inserir cidade\n ${JSON.stringify(telefone)}`)
		$.ajax({
			url: '../TelefoneServlet',
			type: 'POST',
			data: telefone,
			success: function(response){
				alert("telefone Salvo");
				telaListar();
			}
		});
	}
	
	var update = function (){
		var telefone = {
			'id-telefone':$('#id-telefone').val(),
			'numero-telefone':$('#numero-telefone').val(),
			'tipo-telefone':$('#tipo-telefone')[0].childNodes[0].childNodes[0].value.toLowerCase(),
			'status-telefone':$('#status-telefone')[0].childNodes[0].childNodes[0].value.toLowerCase()
		}
		
		console.log(`atualizar telefone\n ${JSON.stringify(telefone)}`)
		
		$.ajax({
			url: '../TelefoneServlet',
			type: 'POST',
			data: telefone,
			success: function(response){
				telaListar();
			}
		});
	}
	
	var deletar = function (){
		let id = $(this).parent().parent().children('.col-id')[0].innerText;
		let numero = $(this).parent().parent().children('.col-numero')[0].innerText;
		let sigla = $(this).parent().parent().children('.col-estado	')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		alert('Deletando banco!');
	}
	
	telaListar();
	
});