$(window).load(function() {
	var resdiv = document.getElementById("resultDiv");

	$.ajax({
	        url: "rest/retosresthome/getcategories",
	        type: "GET",
    		dataType:"json",
	        success: function(categories) {
	        	for(var i = 0; i < categories.length; i++) {
 					var cat = categories[i];
 					
    				$('.categoriesResult').append("<tr>");
					$('.categoriesResult').append("<td align='center'><a href='viewcategory.html?id=" + cat.id + "'>" + cat.name + "</a></td>");
					$('.categoriesResult').append("<td align='center'><a href='retos.html?id=" + cat.id + "'><canvas id=" + cat.id + " width='200' height='100'></canvas></a></td>");
    				$('.categoriesResult').append("</tr>");
				}
				paintCanvas(categories);
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