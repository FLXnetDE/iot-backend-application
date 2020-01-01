package de.flxnet.iot_backend.http;

import de.flxnet.iot_backend.FlxnetIotBackendApplication;
import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class DefaultBeforeFilter implements Filter {

    private FlxnetIotBackendApplication app;

    public DefaultBeforeFilter(FlxnetIotBackendApplication app) {
    	this.app = app;
    }
    
    @Override
    public void handle(Request request, Response response) throws Exception {
    	response.header("Content-Type", "text/json");
    }
    
    /**
     * @return the app
     */
    public FlxnetIotBackendApplication getApp() {
    	return app;
    }

}