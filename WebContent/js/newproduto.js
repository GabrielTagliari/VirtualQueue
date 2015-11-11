function limpa_form(){	
    	$(':input','#form')
    	 .not(':button, :submit, :reset, :hidden')
    	 .val('')
    	 .removeAttr('checked')
    	 .removeAttr('selected');
    }
$(document).ready(function(){
	$('#form').submit(function(){
		swal({   
			title: "Produto Cadastrado!",   
			text: "",   
			type: "",   
			showCancelButton: false,   
			confirmButtonColor: "#8ebebc",   
			confirmButtonText: "Sim",   
			cancelButtonText: "No",   
			closeOnConfirm: true,   
			closeOnCancel: false }, 
			function(isConfirm){   
				window.location = "newproduto.html";
			});
	});
});