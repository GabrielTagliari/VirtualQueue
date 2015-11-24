//Criando o objeto de pedido e a array produtolist
t = new Object();
t.produto;
t.quantidade;

p = new Object();
p.valor;
p.senha = Math.floor((Math .random() * 100)+1);
p.itempedido = [];

var produtolist = [];
var table;

//Chama as funções
lista();
populaCombo();
updateClock();

$(document).ready(function(){
	setInterval('updateClock()', 1000); //Update do relógio a cada segundo
	var mes = new Date().getMonth() + 1;
	$("#data").text(new Date().getDate()+"/"+mes+"/"+new Date().getFullYear()); //Mostra a data atual
	$("#senha").text("Senha: "+p.senha); // Mostra a senha aleatória
});

function lista() {
	var list = "http://localhost:8080/VirtualQueue/VQ/produto/list";
	$.ajax({
		type : 'GET',
		url : list,
		crossDomain : true,
		dataType : "json",
		success : carregaProduto,
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
				var q = '<select name="idCombox2" id="idCombox2" class="form-control"><option value="-1"><-- Selecione um Produto --></option>';
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

function carregaProduto(data) {
	produtolist = data;
	$("#example").append("<tfoot><tr><th></th><th></th><th></th><th colspan='2'><span id='total'>TOTAL: R$ 0</span></th></tr></tfoot>")
	table = $('#example').DataTable( {
	    scrollY:        '30vh',
        scrollCollapse: true,
        paging:         false,
        "bFilter": false,
        "info": false,
        "language": {"emptyTable": "Pedido Vazio"},
	    "aaData": p.itempedido,
	    "aoColumns": [
	      { "sTitle": "Código",   "mData": "id", "width": "10px" },
	      { "sTitle": "Nome",  "mData": "nome" },
	      { "sTitle": "Preco", "mData": "preco", render: $.fn.dataTable.render.number( '.', ',', 2, 'R$ ' )},
	      {
	    	  	"sTitle": "Quantidade",
	    	  	"width": "1%",
	            "targets": -1,
	            "data": null,
	            "defaultContent": "<input type='text' class='quantidade' value='1' style='width:50px;'/><span id='hiddenqntd' hidden>1</span>"
	        },
	        {
	    	  	
	            "targets": -1,
	            "data": null,
	            "defaultContent": "<span class='icon fa-remove''></span>",
	            "width": "5px" 
	        }
	    ]
	});
	
	//Remove item do pedido
	$('#example tbody').on( 'click', '.icon.fa-remove', function () {
		var data = table.row( $(this).parents('tr')).data();
		var val = 0;
		p.itempedido.forEach(function(value) {
			if (value.produto.id === data.id) {
				p.itempedido.splice(val,1);
			}else{
				val++;
			}
		});
	    table.row($(this).parents('tr')).remove().draw();
	    valor_pedido();
	});
	
	//Altera a quantidade
	$('#example tbody').on( 'change', '.quantidade', function () {
		var qntd = $(this).val();
		console.log(qntd);
		var data = table.row($(this).parents('tr')).data();
		console.log(data.id);
		p.itempedido.forEach(function(value) {
			if (value.produto.id === data.id) {
					console.log(value);
					value.quantidade = qntd;
			}
		console.log(JSON.stringify(p.itempedido));
		});
	    valor_pedido();
	});
	
	//Finaliza o pedido
	$("#finaliza").on('click', function(){
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
						    		closeOnConfirm: true,   
						    		closeOnCancel: false }, 
						    		function(isConfirm){   
						    			if (isConfirm) {     
						    				$("#conteudo").load("newpedido2.html");
						    	}});
			    	 	} });
			     },
			     error: function (xhr) {
			         alert("Erro" + xhr.responseText);
			     }
			 });
		}
	});
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
			table.rows.add([produtolist[selecionado]]).draw();
		}
	}else{
		swal("","Produto já adicionado, altere a quantidade.")
	}			
}

function valor_pedido(){ //Realiza a soma dos pedidos da tabela e atualiza o valor valor do pedido
	p.valor = 0;
	p.itempedido.forEach(function(value) {
  		p.valor += value.produto.preco * value.quantidade;
	});
	$("#total").html("TOTAL: R$ "+p.valor);
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

function geraPdf() { //Gera o PDF do pedido
	var doc = new jsPDF('p', 'pt');
    var header = function (data) {
    	doc.setFontSize(20);
        doc.setTextColor(40);
        doc.setFontStyle('normal');
        doc.text("Pedido", data.settings.margin.left + 35, 60);
        doc.fromHTML($('#dados').get(0), 10, 100, {'width': 90});
    };
    
    var footer = function (data) {
    };

   var options = {
        beforePageContent: header,
        afterPageContent: footer,
        margin: {top: 80}
    };
   	var elem = document.getElementById("example");
	var res = doc.autoTableHtmlToJson(elem);
	doc.autoTable(res.columns, res.data, options);
    doc.output("dataurlnewwindow");
	
	/*var printDoc = new jsPDF();
    printDoc.fromHTML($('h2').get(0), 10, 10, {'width': 90});
    var elem = document.getElementById("example");
	var res = printDoc.autoTableHtmlToJson(elem);
	printDoc.autoTable(res.columns, res.data);
    printDoc.fromHTML($('#dados').get(0), 10, 100, {'width': 180});
    printDoc.output("dataurlnewwindow");*/

	/*var doc = new jsPDF('p', 'pt');
	doc.text(250, 20, 'Pedido');
	var elem = document.getElementById("example");
	var res = doc.autoTableHtmlToJson(elem);
	doc.autoTable(res.columns, res.data);
	doc.text(250, 40, 'Senha: ' +p.senha);
	doc.save("table.pdf");*/
}
