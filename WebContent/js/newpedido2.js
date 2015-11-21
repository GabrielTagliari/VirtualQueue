//Criando o objeto de pedido e a array produtolist
t = new Object();
t.produto;
t.quantidade;

p = new Object();
p.valor = 100;
p.senha = Math.floor((Math .random() * 100)+1);
p.itempedido = [];

var produtolist = [];
var pedidolist = [];
var table;
//Chama as funções
lista();
populaCombo();
updateClock();

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
}

function carregaProduto(data) { // gerar Combo dos produtolist
	produtolist = data;
	table = $('#example').DataTable( {
	    scrollY:        '30vh',
        scrollCollapse: true,
        paging:         false,
	    "aaData": pedidolist,
	    "aoColumns": [
	      { "sTitle": "Código",   "mData": "id" },
	      { "sTitle": "Nome",  "mData": "nome" },
	      { "sTitle": "Preco", "mData": "preco" }
	    ]
	});
	console.log(JSON.stringify(produtolist[0]));//testando
	
}

function addProduto() { //Adiciona um produto da combobox na tabela de pedido
	var selecionado = document.getElementById("idCombox2").value;
	var verifica;
	p.itempedido.forEach(function(value) {
  		if (value.produto == produtolist[selecionado]) {
  			verifica = true;
		}
	});
	if(verifica != true){
		if (selecionado == -1) {
			swal("","Por favor, selecione um produto...")
		} else {
			produtolist[selecionado].quantidade = 1;
			ps = new Object();
			ps.produto = produtolist[selecionado];
			ps.quantidade = 1;
			p.itempedido.push(ps);
			pedidolist.push(produtolist[selecionado]);
			table.rows.add([produtolist[selecionado]]).draw();
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
	var val = 0;
	p.itempedido.forEach(function(value) {
  		if (value.produto.id === remove) {
  			p.itempedido.splice(val,1);
		}else{
			val++;
		}
	});
}

 function valor_pedido(){ //Realiza a soma dos pedidos da tabela e atualiza o valor valor do pedido
	p.valor = 0;
	p.itempedido.forEach(function(value) {
  		p.valor += value.produto.preco * value.quantidade;
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
		p.itempedido.forEach(function(value) {
			if (value.produto.id == selecionado) {
				value.quantidade = qntd;
			}
		});
	}
}

function fechar_pedido() { //Finaliza e envia o Pedido
	if (p.itempedido.length == 0){
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
		    		confirmButtonColor: "#8ebebc",   
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
					    		confirmButtonColor: "#8ebebc",   
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