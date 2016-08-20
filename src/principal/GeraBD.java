/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class GeraBD {
    
    Connection c = null;
    Statement stmt = null;
    
    public void geraBD(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:CamDB.db");            

            stmt = c.createStatement();
            String sql = "CREATE TABLE Geral" +
                " (id  INTEGER PRIMARY KEY AUTOINCREMENT" +
                "           UNIQUE," +
                " codigo    INTEGER NOT NULL" +
                "           DEFAULT (3000)," +
                " nome      TEXT," +
                " ddns      TEXT," +
                " modelo    TEXT," + 
                " cam_total INTEGER," +
                " cam_ativ  INTEGER DEFAULT (8)," +
                " cam_grava INTEGER, " +
                " periodo   INTEGER," +
                " valor     DECIMAL," + 
                " data      DATE);"; 
            
            stmt.executeUpdate(sql);            
            
            // Insere os dados da tabela
            sql = "INSERT INTO Geral" +
                  " VALUES (null,'0001', 'Cliente Padrão','padrao.no-ip.org:1200'," +
                  " 'Intelbras VD3016', '16', '12', '5', '15', '200.00', '20/08/2016');";
            stmt.executeUpdate(sql);
            
            
            stmt.close();
            c.close();
            System.out.println("Tabela criada com sucesso");
        } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                //System.exit(0);
            }
    
    }
    
}

