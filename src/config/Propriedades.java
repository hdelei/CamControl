/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Propriedades {
    private final File configFile = new File("config.properties");
    private Properties configProps;
    private String stringConexao;
    
    
    public String getStringConexao(){
        return stringConexao;
    }
    
    public Propriedades(){
        try {
            loadProperties();
        } catch (IOException ex) {
            System.out.println("Não existe arquivo de configuração."+
                    "\r\nCarregadas as configurações padrão.");
            }
        stringConexao = configProps.getProperty("stringConexao");
    }

    private void loadProperties() throws IOException{
        Properties defaultProps = new Properties();
        defaultProps.setProperty("stringConexao", "CamDB.db");
        //stringConexao = "jdbc:sqlite:CamDB.db";
        configProps = new Properties(defaultProps);
	
        // loads properties from file
	InputStream inputStream = new FileInputStream(configFile);
	configProps.load(inputStream);
	inputStream.close();
    }
    
    public void SaveProperties(String varName, String varValue) throws IOException {
	configProps.setProperty(varName, varValue);
		
	OutputStream outputStream = new FileOutputStream(configFile);
	configProps.store(outputStream, "Arquivo de configuracao");
	outputStream.close();
	}
}
