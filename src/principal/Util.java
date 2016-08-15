/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;


import java.text.DecimalFormat;

/**
 *
 * @author Vanderlei
 */
public class Util {
    
    private String monetario;
    
    public String getDecimal(String valor){
        if (valor.equals("")) {
            monetario = "0.00";
        }else{
            DecimalFormat decimal = new DecimalFormat("0.00");            
            Float numero = Float.parseFloat(valor);
            monetario = decimal.format(numero);
            
        }
        return monetario;
    }
    
    
}
