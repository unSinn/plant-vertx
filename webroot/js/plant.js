var eb = new vertx.EventBus('http://localhost:8080/eventbus');

eb.onopen = function() {
	$("#status_info").text("Connected");
	eb.registerHandler('CLIENT', function(response) {
		$('#logcontent').append('</br>' + response.message);
		$('#chart').text(JSON.stringify(response.data));

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
