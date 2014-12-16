var catname="";
var catcolor="";
var catid="";

$(window).load(function() {
    var resdiv = document.getElementById("resultDiv");
    var titlediv = document.getElementById("retoTitleDiv");
    var transboxdiv = document.getElementById("transbox");

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
            catname = category.name;
            catcolor = category.color;
            catid = category.id;
            titlediv.innerHTML="<h1>&nbsp;Retos : " + catname + "</h1>";
            //setdivcolor(transboxdiv);

        },
        error: function (xhr, ajaxOptions, thrownError) {
            resdiv.innerHTML="<font color='red'>Failed to get server data.</font>";
        }
    });

	$.ajax({
	        url: "rest/retosresthome/getchallenges",
	        type: "POST",
	        contentType: "text/plain",
    		dataType: "json",
    		data: id,
	        success: function(challenges) {
	        	for(var i = 0; i < challenges.length; i++) {
 					var challenge = challenges[i];
 					var challengecompleted = "";
 					if(challenge.completed){
 					    challengecompleted = "checked";
 					}
 					
    				$('.challengesResult').append("<tr>");
					$('.challengesResult').append("<td><a href='viewchallenge.html?id=" + challenge.id + "'>" + challenge.name + "</a></td>");
					$('.challengesResult').append("<td><input id='completed' type='checkbox'" + challengecompleted + "/></td>");
    				$('.challengesResult').append("</tr>");
				}
	        },
	        error: function (xhr, ajaxOptions, thrownError) {
	        	resdiv.innerHTML="<font color='red'>Failed to get server data.</font>";
            }
		});
});

function setdivcolor(elem){
    elem.style.backgroundColor = catcolor;
    elem.style.opacity= 0.5;
}

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