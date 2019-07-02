/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

/**
 *
 * @author gabyc
 */
public class Util {

    public static boolean isNumeric(String tipo) {
        
    	if (tipo.equals("inteiro")) {
            return true;
        }
    	
        return false;
        
    }

    public static String getNumericType(String tipo1, String tipo2) {
        if (tipo1.equals("real") || tipo2.equals("real")) {
            return "real";
        }
        return "inteiro";
    }
    
    public static boolean canAssign(String tipoe, String tipod) {
        
    	if (isNumeric(tipoe) && isNumeric(tipod)) {
            
            if (tipoe.equals("inteiro") || tipod.equals("inteiro")) {
                return true;
            }
        
    	} else {
           
    		if (tipoe.equals(tipod)) {
                return true;
            }
        }
    	
        return false;
        
    }
    
}
