package de.flxnet.iot_backend.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class JsonUtil {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    /**
     * @return the gson
     */
    public static Gson getGson() {
    	return gson;
    }
}