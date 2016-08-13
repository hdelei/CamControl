/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.sql.*;
import java.time.temporal.IsoFields;
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
    private List cliente = new ArrayList();
    private List nomes = new ArrayList();
    private List ids = new ArrayList();
    
    public List getNomes(){
        return nomes;
    }    
    
    public List getIDs(){
        return ids;
    }
    
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
    
    public List SelectPorIndice(String id){
        try {
            query = "SELECT * FROM Geral" +
                    " WHERE id = " + id + ";";
            Connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                cliente.add(rs.getString(i+1));                
            }            
            
//            while (rs.next()) {                
//                todosOsNomes.add(rs.getString(3));
//                todosOsIndices.add(rs.getInt(1));                
//            }            
            
            rs.close();
            stmt.close();
            con.close();   
            
        } catch (Exception e) {      
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );                      
        }
        
        return cliente;
    }
    
    public List AbreTabela(){       
        try {
            Connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Geral;");
            ResultSetMetaData rsmd = rs.getMetaData();
            
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                cliente.add(rs.getString(i+1));                
            }            
            
            while (rs.next()) {                
                nomes.add(rs.getString(3));
                ids.add(rs.getInt(1));                
            }            
            
            rs.close();
            stmt.close();
            con.close();   
            
        } catch (Exception e) {      
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );                      
        }
        return cliente;
    }
    
    public List SelectPrimeiroUltimo(String posicao){
        try {            
            if (posicao.equals("Primeiro")) {
               query = "SELECT * FROM Geral" +
                       " WHERE id = " +
                       " (SELECT MIN(id) FROM Geral);"; 
            }
            else{
                query = "SELECT * FROM Geral" +
                        " WHERE id = " +
                        " (SELECT MAX(id) FROM Geral);";
            }            
            
            Connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                cliente.add(rs.getString(i+1));                
            }            
            rs.close();
            stmt.close();
            con.close();
            
        } catch (Exception e) {      
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );                      
        }
        return cliente;
    }
    
    public List SelectProxAnt(String botao, String id){
        String descendo = "";
        if (botao.equals("Anterior")){
            botao = "<";
            descendo = "DESC";
        }else
            botao = ">";
        
        try {            
            query = "SELECT * FROM Geral" +
                    " WHERE id " + botao + " " + id +
                    " ORDER BY id " + descendo + " LIMIT 1;" ; 
            
            Connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                cliente.add(rs.getString(i+1));                
            }            
            rs.close();
            stmt.close();
            con.close();
            
        } catch (Exception e) {      
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );                      
        }
        return cliente;
    }    
}