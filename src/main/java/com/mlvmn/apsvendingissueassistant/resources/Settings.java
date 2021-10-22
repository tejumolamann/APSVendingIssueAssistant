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
}
