var eb = new vertx.EventBus('http://localhost:8080/eventbus');

eb.onopen = function() {
	$("#status_info").text("Connected");
	eb.registerHandler('CLIENT', function(response) {

		// Get context with jQuery - using jQuery's .get() method.
		var ctx = $("#lineChart").get(0).getContext("2d");
		new Chart(ctx).Line(response, {});
	});

	eb.send('SERVER', {
		name : 'tim',
		age : 587
	});
}

eb.onclose = function() {
	$("#status_info").text("Not connected");
	eb = null;
};
