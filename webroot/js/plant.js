var eb = new vertx.EventBus('http://localhost:8080/eventbus');

eb.onopen = function() {
	$("#status_info").text("Connected");
	eb.registerHandler('CLIENT_LOG_HANDLER', function(message) {
		$('#logcontent').append(message.message);

	});
	eb.send('some-address', {
		name : 'tim',
		age : 587
	});
}

eb.onclose = function() {
	$("#status_info").text("Not connected");
	eb = null;
};
