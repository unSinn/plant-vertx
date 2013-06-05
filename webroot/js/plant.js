var eb = new vertx.EventBus('http://localhost:8080/eventbus');

eb.onopen = function() {
	$("#status_info").text("Connected");
	eb.registerHandler('CLIENT', function(response) {

		var graph = new Rickshaw.Graph({
			element : document.querySelector("#chart"),
			renderer : 'line',
			width : 580,
			height : 250,
			series : response.series
		});

		var x_axis = new Rickshaw.Graph.Axis.Time({
			graph : graph
		});

		var y_axis = new Rickshaw.Graph.Axis.Y({
			graph : graph,
			orientation : 'left',
			tickFormat : Rickshaw.Fixtures.Number.formatKMBT,
			element : document.getElementById('y_axis'),
		});

		var legend = new Rickshaw.Graph.Legend({
			element : document.querySelector('#legend'),
			graph : graph
		});

		var shelving = new Rickshaw.Graph.Behavior.Series.Toggle({
			graph : graph,
			legend : legend
		});

		new Rickshaw.Graph.HoverDetail({
			graph : graph
		});

		graph.render();
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
