/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mlvmn.apsvendingissueassistant.printing;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.output.PrinterOutputStream;
import java.io.IOException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 * This class has static methods for querying the computer's printing services.
 * 
 * @author tejum
 */
public class PrintingService {
    
    /**
     * This method obtains an array of print services offered by all printers 
     * installed on the computer, and this will allow a user to choose the 
     * appropriate printer for the job from the list of services.
     * 
     * @return An array of PrintService.
     */
    public static PrintService[] getAllPrintServices(){
        return PrintServiceLookup.lookupPrintServices(null, null);
    }
    
    /**
     * This method returns the names of all the printers installed on this 
     * local computer.
     * @return String[] an array of the names of the printers that are installed 
     * on this local computer.
     */
    public static String[] getAllInstalledPrinterNames(){
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        
        String[] printerNames = new String[printServices.length];
        
        for(int a = 0; a < printServices.length; a++){
            printerNames[a] = printServices[a].getName();
        }
        
        return printerNames;
    }
    
    public static void printToThermalPrinter(String printerName, String payLoad) throws IOException
    {
        PrintService thermalPrinterService = PrinterOutputStream.getPrintServiceByName(printerName);
        
        PrinterOutputStream printerOutputStream = new PrinterOutputStream(thermalPrinterService);
        
        EscPos escPos = new EscPos(printerOutputStream);
        escPos.write(payLoad);
        escPos.close();
    }
}
