$(window).load(function() {
		var canvas = document.getElementById("canvas1");
		var context = canvas.getContext('2d');
		var centerX = canvas.width / 2;
	    var centerY = canvas.height / 2;
	    var radius = 100;

	    context.beginPath();
	    context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
	    context.fillStyle = "red";
	    context.fill();
	    // context.lineWidth = 5;
	    // context.strokeStyle = '#003300';
	    context.stroke();
});


/* SEE THIS AND TRY IT: 
//http://jsfiddle.net/glee/3ZJz3/
*/