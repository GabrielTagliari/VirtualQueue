//Criando o objeto de pedido e a array produtolist
p = new Object();
p.valor;
p.senha = Math.floor((Math.random() * 100)+1);
p.produtos = [];
var produtolist = [];
		
//Chama as funções
lista();
populaCombo();
				
function lista() {
	var list = "http://localhost:8080/VirtualQueue/VQ/produto/list";
	$.ajax({
		type : 'GET',
		url : list,
		crossDomain : true,
		dataType : "json",
		success : carregaProduto, // função de retorno dos produtolist
		error : function(xhr, status, error) {
			var err = eval("(" + xhr.responseText + ")");
			alert(err.Message);
		}
	});
}

function populaCombo() { //Popula a combobox com produtolist do banco de dados
	$(document).ready(function() {
		$.ajax({
			dataType : 'json',
			url : "http://localhost:8080/VirtualQueue/VQ/produto/list",
			data : produtolist, //array produtolist
			success : function(data) {
				var q = '<select name="idCombox2" id="idCombox2" class="form-control"> <option value="-1"><-- Selecione um Produto --></option>';
				for (i = 0; i < data.length; i++) {
						if (data[i].data_exclusao == null) {
							q += '<option value=' + i + '>'
							+ data[i].nome
							+ '</option>';
						} else {}							
					}
					q += '</select>';
					$('#combo').html(q);
				}
			});
	});
}

function carregaProduto(data) { // gerar Combo dos produtolist
	produtolist = data;		
}

function addProduto() { //Adiciona um produto da combobox na tabela de pedido
	var selecionado = document.getElementById("idCombox2").value;
	
	if(p.produtos.indexOf(produtolist[selecionado])==-1){
		if (selecionado == -1) {
			swal("","Por favor, selecione um produto...")
		} else {
			var table = document.getElementById("myTable");
			var row = table.insertRow(1);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			
			produtolist[selecionado].quantidade = 1;
			
			cell1.innerHTML = produtolist[selecionado].id;
			cell2.innerHTML = produtolist[selecionado].nome;
			cell3.innerHTML = "R$ "+produtolist[selecionado].preco;
			cell4.innerHTML = "<input type='text' name='quantidade' id='"+produtolist[selecionado].id+"' value='1' onchange='altera_quantidade("+produtolist[selecionado].id+","+produtolist[selecionado].preco+");valor_pedido();' style='width:50px;'><span id='qntd"+produtolist[selecionado].id+"' hidden>"+produtolist[selecionado].quantidade+"</span>";
			cell5.innerHTML = "<a href='#' class='delete' value='"+produtolist[selecionado].id+"'><img src='../../img/x.png' onclick='removeProduto("+produtolist[selecionado].id+");valor_pedido("+selecionado+");' height='15' width='15'></a><span id='totaldoproduto"+produtolist[selecionado].id+"' hidden>R$ "+produtolist[selecionado].preco+"</span>";
			p.produtos.push(produtolist[selecionado]);
		}
	}else{
		swal("","Produto já adicionado, altere a quantidade.")
	}			
}

function removeProduto(remove){ //Remove o produto da lista
	$("#myTable .delete").on("click",function() {
	     var td = $(this).parent();
	     var tr = td.parent();
	   	 tr.remove();
	});
	for (var i =0; i < p.produtos.length; i++)
		if (p.produtos[i].id === remove) {
	    p.produtos.splice(i,1);
	break;
	}
}

 function valor_pedido(){ //Realiza a soma dos pedidos da tabela e atualiza o valor valor do pedido
	p.valor = 0;
	p.produtos.forEach(function(value) {
  		p.valor += value.preco * value.quantidade;
	});
	$("#valor").html("R$ "+p.valor);
}

function altera_quantidade(selecionado, preco){ //Altera a quantidade do item do pedido
	var qntd = $("#"+selecionado+"").val();
	$("#qntd"+selecionado+"").text(qntd);//Quantidade do produto no PDF
	if (qntd == 0) {
		swal("","A quantidade deve ser maior ou igual a 1")
	} else {
		$("#"+selecionado+"").attr("value", qntd);
		$("#totaldoproduto"+selecionado+"").text("R$ "+preco*qntd); // Total do produto no PDF
		for (var i =0; i < p.produtos.length; i++)
			   if (p.produtos[i].id === selecionado) {
			      p.produtos[i].quantidade = qntd;
			      break;
		}
	}
}

function fechar_pedido() { //Finaliza e envia o Pedido
	if (p.produtos == 0){
		swal("Seu pedido está vazio!","Adicione ao menos um produto...");
	} else {
		$.ajax({
		     type: "POST",
		     url: "http://localhost:8080/VirtualQueue/VQ/pedido/pedidocreate",
		     data: JSON.stringify(p),
		     contentType: "application/json; charset=utf-8",
		     dataType: "json",
		     processData: true,
		     success: function (vdata, status, jqXHR) {
		    	 swal({   
		    		title: "Deseja imprimir o pedido?",   
		    		text: "Será gerado um PDF",   
		    		type: "",   
		    		showCancelButton: true,   
		    		confirmButtonColor: "#141E5F",   
		    		confirmButtonText: "Sim",   
		    		cancelButtonText: "No",   
		    		closeOnConfirm: true,   
		    		closeOnCancel: false }, 
		    		function(isConfirm){   
		    			if (isConfirm) {     
		    				geraPdf();
		    			} else {
		    				swal({   
					    		title: "",   
					    		text: "Pedido Gerado!",   
					    		type: "",   
					    		showCancelButton: false,   
					    		confirmButtonColor: "#141E5F",   
					    		confirmButtonText: "Sim",   
					    		cancelButtonText: "No",   
					    		closeOnConfirm: false,   
					    		closeOnCancel: false }, 
					    		function(isConfirm){   
					    			if (isConfirm) {     
					    				window.location = "newpedido.html";
					    	}});
		    	 	} });
		     },
		     error: function (xhr) {
		         alert("Erro" + xhr.responseText);
		     }
		 });
	}
}

function updateClock(){ //Relógio
 	var currentTime = new Date ( );
  	var currentHours = currentTime.getHours ( );
  	var currentMinutes = currentTime.getMinutes ( );
  	var currentSeconds = currentTime.getSeconds ( );
  	currentHours = ( currentHours < 10 ? "0" : "" ) + currentHours;
  	currentMinutes = ( currentMinutes < 10 ? "0" : "" ) + currentMinutes;
  	currentSeconds = ( currentSeconds < 10 ? "0" : "" ) + currentSeconds;
  	var currentTimeString = currentHours + ":" + currentMinutes + ":" + currentSeconds;
	$("#clock").html(currentTimeString);
}

function verifica_pedido(){	//Botão temporário somente para checar o json que será mandado ao PedidoWebService
alert(JSON.stringify(p));
}

$(document).ready(function(){
	setInterval('updateClock()', 1000); //Update do relógio a cada segundo
	var mes = new Date().getMonth() + 1;
	$("#data").text(new Date().getDate()+"/"+mes+"/"+new Date().getFullYear()); //Mostra a data atual
	$("#senha").text("Senha: "+p.senha); // Mostra a senha aleatória
});

function geraPdf() { //Gera o PDF do pedido
	$("#remove").text("Total");
	var pdf = new jsPDF('p', 'pt', 'letter');
	pdf.setFontSize(22);
	source = $('#container')[0];
	specialElementHandlers = {
			'#bypassme': function (element, renderer) {
				return true
			}
	};
	margins = {
			top: 80,
			bottom: 60,
			left: 40,
			width: 522
	};
	pdf.fromHTML(
			source,
			margins.left,
			margins.top, {
				'width': margins.width,
				'elementHandlers': specialElementHandlers
			},
			function (dispose) {
				window.location = "newpedido.html";
				pdf.output('dataurlnewwindow');
			}, margins);
}