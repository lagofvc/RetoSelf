$(document).ready(function(){

	$('#addCategoryButton').click(function(){
		var resdiv = document.getElementById("resultDiv");
		var name = document.getElementById('categoryname');
		var color = document.getElementById('categorycolor');
		var category = {name: name.value, color: color.value};

		$.ajax({
	        url: "rest/retosresthome/addcategory",
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


// enable - disable button based on name field contents.
$(document).ready(function(){
	$('input').on('keyup', function () {
		var name = $.trim($('#categoryname').val());
		if(name.length > 0){
			$('#addCategoryButton').prop('disabled', false);
		}else{
			$('#addCategoryButton').prop('disabled', true);
		}
	});
});