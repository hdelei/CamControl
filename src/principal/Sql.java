/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
        
        query = "SELECT * FROM Geral" +
                    " WHERE id " + botao + " " + id +
                    " ORDER BY id " + descendo + " LIMIT 1;" ;
        try {
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
    
    public List Incluir(List dados){
        
        String[] texto;// = new String[dados.size()];
        texto = conversorStrings(dados);
        
        query = "INSERT INTO Geral(id,codigo,nome,ddns,modelo,cam_total,"
                + "cam_ativ,cam_grava,periodo,valor,data)";
                
        query += " VALUES(";        
        query += "null, ";
        query += "'" + texto[0] + "', ";
        query += "'" + texto[1] + "', ";
        query += "'" + texto[2] + "', ";
        query += "'" + texto[3] + "', ";
        query += "'" + texto[4] + "', ";
        query += "'" + texto[5] + "', ";
        query += "'" + texto[6] + "', ";
        query += "'" + texto[7] + "', ";
        query += "'" + texto[8] + "', ";
        query += "'" + texto[9] + "');";        
        
        try {
            Connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            stmt.executeUpdate(query);            
            stmt.close();
            con.commit();
            con.close();            
            
        } catch (Exception e) {      
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );                      
        }
        
        return cliente;
    }
    
    private String[] conversorStrings(List dados){
        String[] texto = new String[dados.size()];
        int index = 0;
        for (Object objetos : dados) {
            texto[index] = objetos.toString();
            index++;
        }
        return texto;
    }
    
    public void Deletar(String id){
        query = "DELETE FROM Geral WHERE id = " + id + ";";
        
        try{
            Connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
            stmt.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );      
        }
    }

    void Atualiza(List campos, String id) {
        String[] campoStrings;
        campoStrings = conversorStrings(campos);
        
        query = "UPDATE Geral SET" + 
                "   codigo ='" + campoStrings[0] +
                "', nome ='" + campoStrings[1] +
                "', ddns ='" + campoStrings[2] +
                "', modelo ='" + campoStrings[3] +
                "', cam_total ='" + campoStrings[4] +
                "', cam_ativ ='" + campoStrings[5] +
                "', cam_grava ='" + campoStrings[6] +
                "', valor ='" + campoStrings[7] +
                "', periodo ='" + campoStrings[8] +
                "', data ='" + campoStrings[9] +
                "' WHERE id = '" + id + "';";        
        try {
            Connect();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
            stmt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso.");
            
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            JOptionPane.showMessageDialog(null, "Ocorreu um erro. Corrija o problema.");
        }        
        
    }
}