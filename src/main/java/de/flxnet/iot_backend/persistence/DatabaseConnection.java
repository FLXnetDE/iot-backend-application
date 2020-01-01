package de.flxnet.iot_backend.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.flxnet.iot_backend.util.ConsoleHelper;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class DatabaseConnection {

    private String username;
    private String password;
    private String database;
    private String host;
    private String port;
    private Connection con;

    public DatabaseConnection(String host, String port, String username, String password, String database) {
	this.host = host;
	this.port = port;
	this.username = username;
	this.password = password;
	this.database = database;
    }

    public void connect() {
	if (!isConnected()) {
	    try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(
			"jdbc:mysql://" + host + ":" + port + "/" + database
				+ "?autoReconnect=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			username, password);
		ConsoleHelper.ok("Database connection established! (" + database + ")");
	    } catch (SQLException e) {
		e.printStackTrace();
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
	    }
	}
    }
    
    // Database READ / WRITE functions
    
    /**
     * @param clientId
     * @param topicName
     * @param messagePayload
     */
    public void storeData(String clientId, String topicName, String messagePayload) {
	this.update("INSERT INTO iot_data (client_id, topic_name, message_payload) "
		+ "VALUES ('" + clientId + "', '" + topicName + "', '" + messagePayload + "')");
    }
    
    /**
     * @param displayName
     * @param idName
     * @return boolean
     */
    public boolean validateDevice(String displayName, String idName) {
	ResultSet rs = this.getResult("SELECT * FROM iot_devices WHERE device_displayname='" + displayName + "' AND device_idname='" + idName + "'");
	try {
	    while(rs.next()) {
	        if(rs.getInt("is_locked") == 1) return false;
	        return true;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }
    
    public void close() {
	if (isConnected()) {
	    try {
		con.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    public boolean isConnected() {
	return con != null;
    }

    public void update(String qry) {
	if (isConnected()) {
	    try {
		con.createStatement().executeUpdate(qry);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    public ResultSet getResult(String qry) {
	if (isConnected()) {
	    try {
		return con.createStatement().executeQuery(qry);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return null;
    }
}