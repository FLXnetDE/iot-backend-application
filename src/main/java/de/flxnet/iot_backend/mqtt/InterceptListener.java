package de.flxnet.iot_backend.mqtt;

import de.flxnet.iot_backend.FlxnetIotBackendApplication;
import de.flxnet.iot_backend.util.ConsoleHelper;
import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptConnectMessage;
import io.moquette.interception.messages.InterceptDisconnectMessage;
import io.moquette.interception.messages.InterceptPublishMessage;
import io.moquette.interception.messages.InterceptSubscribeMessage;
import io.moquette.interception.messages.InterceptUnsubscribeMessage;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class InterceptListener extends AbstractInterceptHandler {

    private FlxnetIotBackendApplication app;

    public InterceptListener(FlxnetIotBackendApplication app) {
    	this.app = app;
    }
    
    @Override
    public void onConnect(InterceptConnectMessage message) {
    	ConsoleHelper.ok("New client connected, id: " + message.getClientID() + ", protocol: " + message.getProtocolName() + " " + message.getProtocolVersion());
    }
    
    @Override
    public void onDisconnect(InterceptDisconnectMessage message) {
    	ConsoleHelper.error("Client disconnected, id: " + message.getClientID());
    }
    
    @Override
    public void onPublish(InterceptPublishMessage message) {
		String clientId = message.getClientID();	
		String topicName = message.getTopicName();
		
        int readableBytes = message.getPayload().readableBytes();
        byte[] payload = new byte[readableBytes];

        for (int i = 0; i < readableBytes; i++) {
            payload[i] = message.getPayload().readByte();
        }

        String decodedPayload = new String(payload);
	
		app.getDatabaseConnection().storeData(clientId, topicName, decodedPayload);
		
		ConsoleHelper.mqttPublish(clientId, topicName, decodedPayload);
    }
    
    @Override
    public void onSubscribe(InterceptSubscribeMessage message) {
    	ConsoleHelper.mqttSubscribe(message.getClientID(), message.getTopicFilter());
    }
    
    @Override
    public void onUnsubscribe(InterceptUnsubscribeMessage message) {

    }
    
    /**
     * @return the app
     */
    public FlxnetIotBackendApplication getApp() {
    	return app;
    }

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}
}