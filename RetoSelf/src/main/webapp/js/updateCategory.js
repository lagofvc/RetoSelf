$(document).ready(function(){

	$('#updateCategoryButton').click(function(){
		
		var resdiv = document.getElementById("resultDiv");
		var name = document.getElementById('categoryname');
		var color = document.getElementById('categorycolor');
		var category = {id: savedId, name: name.value, color: color.value};

		$.ajax({
	        url: "rest/retosresthome/updatecategory",
	        type: "POST",
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
	});
});