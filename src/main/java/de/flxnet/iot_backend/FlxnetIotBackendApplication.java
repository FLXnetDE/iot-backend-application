package de.flxnet.iot_backend;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import de.flxnet.iot_backend.http.DefaultBeforeFilter;
import de.flxnet.iot_backend.http.DefaultRoute;
import de.flxnet.iot_backend.http.GetConnectedClientsRoute;
import de.flxnet.iot_backend.http.PostMqttOverHttpRoute;
import de.flxnet.iot_backend.mqtt.InterceptListener;
import de.flxnet.iot_backend.persistence.DatabaseConnection;
import de.flxnet.iot_backend.util.ConsoleHelper;
import io.moquette.broker.Server;
import spark.Spark;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class FlxnetIotBackendApplication {
    
    private static FlxnetIotBackendApplication instance;
    private DatabaseConnection databaseConnection;
    private final Server mqttBroker;
    
    private FlxnetIotBackendApplication() {
		instance = this;
		
		String[] config = readConfigFile();
		databaseConnection = new DatabaseConnection(config[0], config[1], config[2], config[3], config[4]);
		databaseConnection.connect();
		
		// HTTP REST - Spark
		Spark.port(new Integer(config[5]));
		Spark.before(new DefaultBeforeFilter(this));
		Spark.get("/", new DefaultRoute());
		Spark.get("/clients", new GetConnectedClientsRoute(this));
		Spark.post("/mqttOverHttp", new PostMqttOverHttpRoute(this));
		
		// MQTT - Moquette
		this.mqttBroker = new Server();
		this.startServer();
    }
    
    public void startServer() {
        ConsoleHelper.ok("Starting MQTT broker...");

        try {
            mqttBroker.startServer(new File("moquette.conf"));
            mqttBroker.addInterceptHandler(new InterceptListener(this));
        } catch (IOException e) {
        	ConsoleHelper.error("Failed to start MQTT broker!");
        }
    }
    
    public void stopServer() {
    	this.mqttBroker.stopServer();
    }
    
    private String[] readConfigFile() {
		String[] config = new String[6];
		Properties p = new Properties();
		
		try {
		    p.load(new FileReader(new File("config.properties")));
		    
		    config[0] = p.getProperty("MYSQL_HOST");
		    config[1] = p.getProperty("MYSQL_PORT");
		    config[2] = p.getProperty("MYSQL_USER");
		    config[3] = p.getProperty("MYSQL_PASSWORD");
		    config[4] = p.getProperty("MYSQL_DATABASE");
		    config[5] = p.getProperty("HTTP_API_PORT");
		    
		    return config;
		    
		} catch (IOException e) {
		    ConsoleHelper.error("File 'config.properties' could not be found, or is malformed - Please check!");
		    System.exit(1);
		}
		
		return null;
    }
    
    public static void main(String[] args) {
    	new FlxnetIotBackendApplication();
    }
    
    /**
     * @return the instance
     */
    public static FlxnetIotBackendApplication getInstance() {
    	return instance;
    }
    
    /**
     * @return the databaseConnection
     */
    public DatabaseConnection getDatabaseConnection() {
    	return databaseConnection;
    }
    
    /**
     * @return the mqttBroker
     */
    public Server getMqttBroker() {
    	return mqttBroker;
    }
    
}
