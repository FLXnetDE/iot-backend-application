package de.flxnet.iot_backend.http;

import de.flxnet.iot_backend.util.JsonUtil;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class DefaultRoute implements Route {

    @Override
    public Object handle(Request arg0, Response arg1) throws Exception {
	return JsonUtil.getGson().toJson(new HttpResponse(HttpResponseCode.OK, "Welcome to the FLXnet.de IOT Backend!"));
    }

}