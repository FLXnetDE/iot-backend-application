package de.flxnet.iot_backend.util;

import java.util.Base64;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class BackendUtil {

    /**
     * @param base64EncodedString
     * @return String[]
     */
    public static String[] extractCredentialsData(String base64EncodedString) {
	String[] result = new String[2];
    	String decodedString = new String(Base64.getDecoder().decode(base64EncodedString.split(" ")[1]));
    	result[0] = decodedString.split(":")[0];
    	result[1] = decodedString.split(":")[1];
    	return result;
    }
    
}