var savedId = "";
var savedName = "";
var savedColor = "";

$(window).load(function() {
	var resdiv = document.getElementById("resultDiv");
	var query = window.location.search.substring(1);
	var variables = query.split("&");
	var id = variables[0].split("=")[1];

	$.ajax({
	        url: "rest/retosresthome/getsinglecategory",
	        type: "POST",
	        contentType:"text/plain",
    		dataType:"json",
    		data: id,
	        success: function(category) {
	        	var cat = category;
	        	savedId = cat.id;
	        	savedName = cat.name;
	        	savedColor = cat.color;

	        	$('#categoryname').val(cat.name);
	        	$('#categorycolor').val(cat.color);
	        	$('#deleteCategoryButton').prop('disabled', false);
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	resdiv.innerHTML="<font color='red'>Failed to get server data.</font>";
            }
		});
});


// enable - disable button based on name field contents.
function disableOrEnableUpdateButton(){
	var name = $.trim($('#categoryname').val());
	var color = $.trim($('#categorycolor').val());

	if(name === savedName && color === savedColor){
		$('#updateCategoryButton').prop('disabled', true);
	}else{
		$('#updateCategoryButton').prop('disabled', false);
	}
}