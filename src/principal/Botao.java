/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Vanderlei
 */
public class Botao {
    private ArrayList numero;
    
    
    public void Preenche(JTextField txt, String numeroBotao){
        
        if (txt.getText().equals("")){
        txt.setText(numeroBotao);    
        }
        else{
            String text = txt.getText();            
            txt.setText(text + ", " + numeroBotao);
        }        
        
    }
}
