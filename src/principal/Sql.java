/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Vanderlei
 */
public class Sql {
    
    private Connection con = null;
    private Statement stmt;
    private ResultSet rs;
    private String connectionString = "jdbc:sqlite:CamDB.db";    
    private String query;
    private List dados = new ArrayList();
    
    
    public boolean Connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(connectionString);
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
     System.out.println("Opened database successfully");
        
        return true;
    }
    
    public List AbreTabela(){       
        try {
            Connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Geral;");
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                dados.add(rs.getString(i+1));                
            }            
//            while (rs.next()) {                
//                for (int i = 0; i < rsmd.getColumnCount(); i++) {
//                dados.add(rs.getString(i+1));                
//                }                
//            }            
            
            rs.close();
            stmt.close();
            con.close();   
            
        } catch (Exception e) {      
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );                      
        }
        return dados;
    }
    
    public void AtualizaCampos(ResultSetMetaData colunas) {
    
        try{
            for (int i = 0; i < colunas.getColumnCount(); i++) {
                dados.add(rs.getString(i+1));
                System.out.println(dados.get(i));
            }
        } catch(Exception e){
            
        }
        
        
    }
}


