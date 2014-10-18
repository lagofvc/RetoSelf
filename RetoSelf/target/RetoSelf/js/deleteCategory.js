$(document).ready(function(){

	$('#deleteCategoryButton').click(function(){

	    if (confirm("Are you sure you want to delete this category?") == true) {
	    	//clicked OK.
	        var resdiv = document.getElementById("resultDiv");
			var	category = {id: savedId};

			$.ajax({
		        url: "rest/retosresthome/deletecategory",
		        type: "DELETE",
		        data: JSON.stringify(category),
	    		contentType:"application/json",
	    		dataType:"text",
		        success: function(response) {
		        	if(response.indexOf("SUCCESS") >= 0){
			            window.location = "index.html";
		        	}else{
		        		resdiv.innerHTML="<font color='red'>Operation Failed: " + response + "</font>";
		        	}
		        },
		        error: function (xhr, ajaxOptions, thrownError) {
		        	resdiv.innerHTML="<font color='red'>Failed to send the request.</font>";
	            }
			});
	    } else {
	    	//Clicked Cancel.
	    	//no - op.
	    }
	});
});
