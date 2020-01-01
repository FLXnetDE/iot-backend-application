package de.flxnet.iot_backend.mqtt;

import java.util.Date;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class DataSet {

    private int id;
    private String clientId;
    private String topicName;
    private String messagePayload;
    private Date dateReceived;
    
    public DataSet(int id, String clientId, String topicName, String messagePayload, Date dateReceived) {
		this.id = id;
		this.clientId = clientId;
		this.topicName = topicName;
		this.messagePayload = messagePayload;
		this.dateReceived = dateReceived;
    }
    
    /**
     * @return the id
     */
    public int getId() {
	return id;
    }
    
    /**
     * @return the clientId
     */
    public String getClientId() {
	return clientId;
    }
    
    /**
     * @return the topicName
     */
    public String getTopicName() {
	return topicName;
    }
    
    /**
     * @return the messagePayload
     */
    public String getMessagePayload() {
	return messagePayload;
    }
    
    /**
     * @return the dateReceived
     */
    public Date getDateReceived() {
	return dateReceived;
    }
    
}