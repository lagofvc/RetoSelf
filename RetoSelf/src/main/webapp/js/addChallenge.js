$(window).load(function() {
	var resdiv = document.getElementById("resultDiv");

	$.ajax({
	        url: "rest/retosresthome/getchallengetypes",
	        type: "GET",
    		dataType:"text",
	        success: function(allTypes) {
	            var types = allTypes.split(",");
	        	for(var i = 0; i < types.length; i++) {
 					var type = types[i];
    				$('.challengetypes').append("<option value=" + type + ">" + type + "</option>");
	            }
            },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	resdiv.innerHTML="<font color='red'>Failed to get server data.</font>";
            }
		});
});

$(document).ready(function(){

	$('#addChallengeButton').click(function(){
		var resdiv = document.getElementById("resultDiv");

//      var type = document.getElementById('type');
		var name = document.getElementById('name');
//		var catid = document.getElementById('categoryid');
//		var desc = document.getElementById('description');
//		var challenge = {type: type.value, name: name.value, categoryid: catid.value, description: desc.value, completed: completed.value};
		var challenge = {name: name.value, completed: false};

		$.ajax({
	        url: "rest/retosresthome/addchallenge",
	        type: "POST",
	        data: JSON.stringify(challenge),
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
		var name = $.trim($('#name').val());
		if(name.length > 0){
			$('#addChallengeButton').prop('disabled', false);
		}else{
			$('#addChallengeButton').prop('disabled', true);
		}
	});
});