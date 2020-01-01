package de.flxnet.iot_backend.http;

import java.util.Collection;

import de.flxnet.iot_backend.FlxnetIotBackendApplication;
import io.moquette.broker.ClientDescriptor;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class GetConnectedClientsRoute implements Route {

	private FlxnetIotBackendApplication main;
	
	public GetConnectedClientsRoute(FlxnetIotBackendApplication main) {
		this.main = main;
	}
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		
		Collection<ClientDescriptor> clients = main.getMqttBroker().listConnectedClients();
		
		return new HttpResponse(HttpResponseCode.OK, clients).asJsonString();
	}
	
	/**
	 * 
	 * @return FlxnetIotBackendApplication
	 */
	public FlxnetIotBackendApplication getMain() {
		return main;
	}

}
