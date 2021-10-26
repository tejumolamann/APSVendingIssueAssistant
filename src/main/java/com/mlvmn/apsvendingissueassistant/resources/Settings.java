/*
 * This is the class the controls the persistence of the settings for program.
 * It ntakes advantage of java.util.Properties class to accomplish this.
 */
package com.mlvmn.apsvendingissueassistant.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tejum
 */
public class Settings {
    
    //Path to where the properties file will be saved
    private static final String SETTINGS_FOLDER_PATH = System.getProperty("user.dir") 
            + "\\resources\\settings.properties";
    
    /**
     * Properties object
     */
    private Properties prop;
    
    /**
     * Instance of the Settings class
     */
    private static Settings settings;
    
    //Keys to be used to store and retrieve values in the Properties object and 
    //ultimately the properties file.
    private static final String IS_LIVE = "isLive";
    private static final String AUTHENTICATION_CODE = "authenticationCode";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final String SERVICE_CHARGE = "serviceCharge";
    
    /**
     * Constructor
     */
    private Settings() {
        //initialise the Properties object
        this.prop = new Properties();
        
        //Read the properties file and load its values
        try {
            File file = new File(SETTINGS_FOLDER_PATH);
            
            //just in case the file does not exist, it gets created
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }            
            
            FileReader fr = new FileReader(file);
            prop.load(fr);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Ensures a single instance of this class gets created
     * @return a single and the same instance of the Settings class is returned
     */
    public static Settings getSettings(){
        if(settings == null)
            settings = new Settings();
        return settings;
    }
    
    /**
     * This method ensures the key and value pairs are written into the 
     * properties file and should be called each time a value is changed in the 
     * Properties object.
     */
    private void saveSettings(){
        File settingsFile = new File(SETTINGS_FOLDER_PATH);
        
        try {
            FileWriter fw = new FileWriter(settingsFile);
            this.prop.store(fw, null);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method stores the program's vending state, either live or demo
     * @param isLive boolean value for live vends or demo vends, true for live, 
     * and false for demo
     */
    public void storeDemoLiveState(boolean isLive){
        this.prop.setProperty(IS_LIVE, String.valueOf(isLive));
        saveSettings();
    }    
    
    /**
     * This method retrieves the value for demo or live vending
     * @return boolean, true for live vending and false for demo vending
     */
    public boolean retrieveDemoLiveState(){
        return Boolean.valueOf(this.prop.getProperty(IS_LIVE));
    }
    
    /**
     *This method stores the credentials into the Properties object and the file.
     * 
     * @param username Username to access Access Power API
     * @param password Password to access Access Power API
     * @param authCode The authentication code to login
     */
    public void storeCredentials(String username, char[] password, String authCode){
        //TODO Implement standard encryption and descryption here
        
        this.prop.setProperty(USERNAME, username);
        this.prop.setProperty(PASSWORD, new String(password));
        this.prop.setProperty(AUTHENTICATION_CODE, authCode);
        saveSettings();
    }
        
    /**
     * This method retrieves the credentials (username, password and 
     * authentication code) from the Properties object.
     * 
     * @return String array containing the credentials.
     */
    public String[] retrieveCredentials(){
        //TODO Implement standard encryption and descryption here
        
        String[] creds = {
            this.prop.getProperty(USERNAME, ""), 
            this.prop.getProperty(AUTHENTICATION_CODE, "")
        };
        
        return creds;
    }

    /**
     * This method retrieve the amount to be charged to customers for using the 
     * vending service and this amount will be deducted from what the customer 
     * paid.
     * @return String value of the amount of service charge.
     */
    public String retrieveServiceCharge() {
        return this.prop.getProperty(SERVICE_CHARGE, "0.0");
    }

    /**
     * This method handles the storing of the amount charged to customers for 
     * using the service.
     * @param amount Service charge amount.
     */
    public void storeServiceCharge(double amount) {
        this.prop.setProperty(SERVICE_CHARGE, String.valueOf(amount));
        saveSettings();
    }
    
}
