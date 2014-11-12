$(document).ready(function(){

	$('#addChallengeButton').click(function(){
		var resdiv = document.getElementById("resultDiv");

//        var type = document.getElementById('type');
		var name = document.getElementById('name');
//		var catid = document.getElementById('categoryid');
//		var desc = document.getElementById('description');
		var completed = document.getElementById('completed');

        alert(completed.checked + " jajajaj!!!!!");

//		var challenge = {type: type.value, name: name.value, categoryid: catid.value, description: desc.value, completed: completed.value};
		var challenge = {name: name.value, completed: completed.checked};

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