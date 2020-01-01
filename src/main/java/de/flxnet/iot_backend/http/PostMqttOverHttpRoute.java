package de.flxnet.iot_backend.http;

import de.flxnet.iot_backend.FlxnetIotBackendApplication;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttQoS;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class PostMqttOverHttpRoute implements Route {

	private FlxnetIotBackendApplication app;
	
	public PostMqttOverHttpRoute(FlxnetIotBackendApplication app) {
		this.app = app;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String topic = request.queryParams("topic");
		String payload = request.queryParams("payload");
		
		
		MqttPublishMessage message = MqttMessageBuilders.publish()
				.topicName(topic)
				.retained(true)
				.qos(MqttQoS.EXACTLY_ONCE)
				.payload(Unpooled.copiedBuffer(payload.getBytes()))
				.build();
		
		app.getMqttBroker().internalPublish(message, "iot-backend-web-console");
		
		return new HttpResponse(HttpResponseCode.OK, "Message has been published internally").asJsonString();
	}
	
	public FlxnetIotBackendApplication getApp() {
		return app;
	}
	
}