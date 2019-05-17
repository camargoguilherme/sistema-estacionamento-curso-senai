$(document).ready(function(){

	var selectStatus = function(value){
		let selectOption = '<select name="status-endereco">'+
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
	
	var selectCidade = function(cidades, cidadeAtual = null){
		let selectOption = '<select name="cidade-endereco">'+
						'<option disabled="disabled" selected="selected">Cidades</option>';
		$.each(cidades, function(key, value){
			if(value.nomeCidade === cidadeAtual){
				selectOption += `<option selected="selected" value="${value.idCidade}">${value.nomeCidade}</option>`;
			}else{
				selectOption += `<option value="${value.idCidade}">${value.nomeCidade}</option>`;
			}
			
		});
		selectOption += '</select>';
		return selectOption;
	}
	
	var selectMensalista = function(mensalistas, mensalistaAtual = null){
		let selectOption = '<select name="mensalista-endereco">'+
						'<option disabled="disabled" selected="selected">Mensalista</option>';
		$.each(mensalistas, function(key, value){
			if(value.nomeMensalista === mensalistaAtual){
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
					'<input type="hidden" name="id-endereco" id="id-endereco" value="">'+
					'<div class="input-field col s12 m4" required>'+
						'<input type="text" name="rua-endereco" id="rua-endereco" placeholder="Rua">'+
					'</div>'+
					'<div class="input-field col s12 m4" required>'+
						'<input type="text" name="numero-endereco" id="numero-endereco" placeholder="Número">'+
					'</div>'+
					'<div class="input-field col s12 m4" required>'+
						'<input type="text" name="cep-endereco" id="cep-endereco" placeholder="CEP">'+
					'</div>'+
					'<div class="input-field col s12 m4" required>'+
						'<input type="text" name="comp-endereco" id="comp-endereco" placeholder="Complemento">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="cidade-endereco">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="mensalista-endereco">'+
					'</div>'+
					'<div class="input-field col s12 m4" id="status-endereco">'+
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
					'<li>Rua</li>'+
					'<li>Número</li>'+
					'<li>CEP</li>'+
					'<li>Complemento</li>'+
					'<li>Cidade</li>'+
					'<li>Mensalista</li>'+
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
			url: '../EnderecoServlet',
			type: 'GET',
			data: {'id-endereco':'0'},
			success: function(response){
				console.log(response);	
				var divSet;
				$.each(response, function(index, value) {
					divSet = $('<ul>'+
					'<li class="col-id">' + value.idEndereco + '</li>'+
					'<li class="col-rua">' + value.logradouroEndereco + '</li>'+
					'<li class="col-numero">' + value.numEndereco +'</li>'+
					'<li class="col-cep">' + value.cepEndereco + '</li>'+
					'<li class="col-complemento">' + value.compEndereco + '</li>'+
					'<li class="col-cidade">' + value.cidadeEndereco.nomeCidade + '</li>'+
					'<li class="col-mensalista">' + value.mensalistaEndereco.nomeMensalista + '</li>'+
					'<li class="col-status">' + value.statusEndereco + '</li>'+
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
		let rua = $(this).parent().parent().children('.col-rua')[0].innerText;
		let numero = $(this).parent().parent().children('.col-numero')[0].innerText;
		let cep = $(this).parent().parent().children('.col-cep')[0].innerText;
		let complemento = $(this).parent().parent().children('.col-complemento')[0].innerText;
		let cidade = $(this).parent().parent().children('.col-cidade')[0].innerText;
		let mensalista = $(this).parent().parent().children('.col-mensalista')[0].innerText;
		let status = $(this).parent().parent().children('.col-status')[0].innerText;
		
		$('main')[0].innerHTML = '<h2>Editar</h2>'+
				form('<a class="waves-effect waves-light btn green" id="btn-update">Atualizar</a>'+
						'<a class="waves-effect waves-light btn blue" id="btn-voltar">Voltar</a>');
		$.ajax({
			url: '../MensalistaServlet',
			type: 'GET',
			data: {'id-mensalista':0},
			success: function(response) {
				$('#mensalista-endereco').append(selectMensalista(response, mensalista));
				M.AutoInit();
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados da endereco');
            }
		});
		
		$.ajax({
			url: '../CidadeServlet',
			type: 'GET',
			data: {'id-cidade':0},
			success: function(response) {
				$('#cidade-endereco').append(selectCidade(response, cidade));
				M.AutoInit();
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados da endereco');
            }
		});
		$('#id-endereco').val(id);
		$('#rua-endereco').val(rua);
		$('#num-endereco').val(numero);
		$('#cep-endereco').val(cep);
		$('#comp-endereco').val(complemento);
		$('#status-endereco').append(selectStatus(status));
		
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
			url: '../CidadeServlet',
			type: 'GET',
			data: {'id-cidade':0},
			success: function(response) {
				$('#cidade-endereco').append(selectCidade(response));
				M.AutoInit();
            },
            error: function(xhr, ajaxOptions, thrownError) {
                console.log(xhr, ajaxOptions, thrownError);
                alert('Erro ao recuperar dados da endereco');
            }
		});
		$('#status-endereco').append(selectStatus(status));
		$('#btn-voltar').on('click', telaListar );
		$('#btn-salvar').on('click', inserir );
		
	}


	var inserir = function (){
		let id = 0;
		let rua = $('#rua-endereco').val();
		let numero = $('#numero-endereco').val();
		let cep = $('#cep-endereco').val();
		let complemento = $('#comp-endereco').val();
		let cidade = $('#cidade-endereco')[0].childNodes[0].childNodes[0].value;
		let mensalista = $('#mensalista-endereco')[0].childNodes[0].childNodes[0].value
		let status = $('#status-endereco')[0].childNodes[0].childNodes[0].value;
		
		let endereco = {
			'id-endereco': id,
			'rua-endereco': rua,
			'numero-endereco': numero,
			'cep-endereco': cep,
			'complemento-endereco':complemento,
			'cidade-endereco':cidade,
			'mensalista-endereco':mensalista,
			'statu-endereco':status
		};
		
		console.log(`inserir endereco\n ${JSON.stringify(endereco)}`)
		$.ajax({
			url: '../EnderecoServlet',
			type: 'POST',
			data: endereco,
			success: function(response){
				alert("endereco Salvo");
				telaListar();
			}
		});
	}
	
	var update = function (){
		let id = $('#id-endereco').val();
		let rua = $('#rua-endereco').val();
		let numero = $('#numero-endereco').val();
		let cep = $('#cep-endereco').val();
		let complemento = $('#comp-endereco').val();
		let cidade = $('#cidade-endereco')[0].childNodes[0].childNodes[0].value;
		let mensalista = $('#mensalista-endereco')[0].childNodes[0].childNodes[0].value
		let status = $('#status-endereco')[0].childNodes[0].childNodes[0].value;
		
		let endereco = {
			'id-endereco': id,
			'rua-endereco': rua,
			'numero-endereco': numero,
			'cep-endereco': cep,
			'complemento-endereco':complemento,
			'cidade-endereco':cidade,
			'mensalista-endereco':mensalista,
			'statu-endereco':status
		};
		
		console.log(`atualizar endereco\n ${JSON.stringify(endereco)}`)
		
		$.ajax({
			url: '../EnderecoServlet',
			type: 'POST',
			data: endereco,
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