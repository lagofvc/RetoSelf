$(window).load(function() {
	var resdiv = document.getElementById("resultDiv");

	$.ajax({
	        url: "rest/retosresthome/getchallenges",
	        type: "GET",
    		dataType:"json",
	        success: function(challenges) {
	        	for(var i = 0; i < challenges.length; i++) {
 					var challenge = challenges[i];
 					
    				$('.challengesResult').append("<tr>");
					$('.challengesResult').append("<td align='center'><a href='viewchallenge.html?id=" + challenge.id + "'>" + challenge.name + "</a></td>");
    				$('.challengesResult').append("</tr>");
				}
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	resdiv.innerHTML="<font color='red'>Failed to get server data.</font>";
            }
		});
});

function paintCanvas (categories) {
	for(var i = 0; i < categories.length; i++) {
		var cat = categories[i];
		var canvas = document.getElementById(cat.id);
		var context = canvas.getContext('2d');
		var centerX = canvas.width / 2;
	    var centerY = canvas.height / 2;
	    var radius = 50;

	    context.beginPath();
	    context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
	    context.fillStyle = cat.color;
	    context.fill();
	    // context.lineWidth = 5;
	    // context.strokeStyle = '#003300';
	    context.stroke();
	}
}