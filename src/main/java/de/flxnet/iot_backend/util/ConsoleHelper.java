package de.flxnet.iot_backend.util;

import java.util.Date;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2019 by FLXnet
 * @author Felix
 */
public class ConsoleHelper {
    private static ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false).build();
    
    /**
     * @param level
     * @param message
     */
    public static void send(String level, String message, FColor fColor) {
		coloredPrinter.print("[" + level + "][" + new Date(System.currentTimeMillis()) + "] ", Attribute.NONE, FColor.CYAN, BColor.BLACK);
		coloredPrinter.println(message, Attribute.NONE, fColor, BColor.BLACK);
		coloredPrinter.clear();
    }
    
    /**
     * @param clientId
     * @param topicName
     * @param payload
     */
    public static void mqttPublish(String clientId, String topicName, String payload) {
    	coloredPrinter.print("[PUBLISH] ", Attribute.NONE, FColor.CYAN, BColor.BLACK);
    	coloredPrinter.print(clientId, Attribute.NONE, FColor.RED, BColor.BLACK);
    	coloredPrinter.print(" @ ", Attribute.NONE, FColor.WHITE, BColor.BLACK);
    	coloredPrinter.print(topicName + ": ", Attribute.NONE, FColor.GREEN, BColor.BLACK);
    	coloredPrinter.println(payload, Attribute.NONE, FColor.MAGENTA, BColor.BLACK);
    	coloredPrinter.clear();
    }
    
    /**
     * 
     * @param clientId
     * @param topicName
     */
    public static void mqttSubscribe(String clientId, String topicName) {
    	coloredPrinter.print("[SUBSCRIBE] ", Attribute.NONE, FColor.CYAN, BColor.BLACK);
    	coloredPrinter.print(clientId, Attribute.NONE, FColor.RED, BColor.BLACK);
    	coloredPrinter.print(" @ ", Attribute.NONE, FColor.WHITE, BColor.BLACK);
    	coloredPrinter.println(topicName, Attribute.NONE, FColor.GREEN, BColor.BLACK);
    	coloredPrinter.clear();
    }
    
    /**
     * @param message
     */
    public static void ok(String message) {
    	send("OK", message, FColor.GREEN);
    }
    
    /**
     * @param message
     */
    public static void info(String message) {
    	send("INFO", message, FColor.WHITE);
    }
    
    /**
     * @param message
     */
    public static void warning(String message) {
    	send("WARNING", message, FColor.YELLOW);
    }
    
    /**
     * @param message
     */
    public static void error(String message) {
    	send("ERROR", message, FColor.RED);
    }
    
    public static void print(String message, FColor fColor) {
		coloredPrinter.print(message, Attribute.NONE, fColor, BColor.BLACK);
		coloredPrinter.clear();
    }
    
    /**
     * @param message
     */
    public static void printWhite(String message) {
		coloredPrinter.println(message, Attribute.NONE, FColor.WHITE, BColor.BLACK);
		coloredPrinter.clear();
    }
    
    /**
     * @param message
     */
    public static void printGreen(String message) {
		coloredPrinter.println(message, Attribute.NONE, FColor.GREEN, BColor.BLACK);
		coloredPrinter.clear();
    }
    
    /**
     * @param message
     */
    public static void printCyan(String message) {
		coloredPrinter.println(message, Attribute.NONE, FColor.CYAN, BColor.BLACK);
		coloredPrinter.clear();
    }
    
    /**
     * @param message
     */
    public static void printYellow(String message) {
		coloredPrinter.println(message, Attribute.NONE, FColor.YELLOW, BColor.BLACK);
		coloredPrinter.clear();
    }
    
    /**
     * @param message
     */
    public static void printRed(String message) {
		coloredPrinter.println(message, Attribute.NONE, FColor.RED, BColor.BLACK);
		coloredPrinter.clear();
    }
}