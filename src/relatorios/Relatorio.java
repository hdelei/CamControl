/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import principal.Sql;

/**
 *
 * @author user
 */
public class Relatorio {
    private final List<Cliente> listaClientes;    
    
    /***
     * O construtor se encarrega de gerar uma lista de clientes do banco de dados
     * para ser utilizada em geraRelatorio()
     */
    public Relatorio() {        
        Sql sql = new Sql();
        listaClientes = sql.Select();
    }
    
    /***
     * Este método gera o relatório chamando a classe Documento
     * e após ter construído o index.html, abre o arquivo no 
     * navegador padrão
     */
    public void geraRelatorio(){ 
        Documento doc = new Documento();
        doc.constroiHtml(listaClientes);        
        abreHtml();
    }
    
    /***
     * Abre o arquivo index.html caso exista na raiz
     */
    private void abreHtml(){
        File html = new File("index.html");
        if(Desktop.isDesktopSupported() && 
            html.exists() && 
            !html.isDirectory()){
            
            try {
                Desktop.getDesktop().open(html);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                //Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
}
