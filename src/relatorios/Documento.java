/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

/**
 *Esta classe é responsável por receber uma lista de dados
 * de clientes e gerar um documento html formatado com os dados
 * do relatório
 * @author user
 */
public class Documento {
    private PrintWriter writer;
    private String htmlEstatico;
    private String htmlDinamico;
    private String htmlFinal;
    
    public Documento(){
        htmlEstatico = getHtmlEstatico();//1 pega html estatico
        htmlDinamico = getHtmlDinamico();//2 pega html dinamico        
    }
    
    /***
     * Este método constrói o documento html com base em uma
     * lista de clientes
     * @param cliente - Esta é uma lista de clientes enviada pela 
     * classe relatório 
     */
    protected void constroiHtml(List<Cliente> cliente){
        String pageBreak = "<p class=\"pbreak\">";
        StringBuilder htmlIntermediario = new  StringBuilder();        
        
        int i = 0;
        for(Cliente cli : cliente){
            
            //Coloca o page-break no lugar
            if (i%8 == 0 || i == 0) {
                htmlIntermediario.append(pageBreak);                
            }
            i++;
            //=============================
            
            String html;
            html = htmlDinamico.replace("$numero", cli.getCodigo());
            html = html.replace("$nome", cli.getNome());
            html = html.replace("$url", cli.getDdns());
            html = html.replace("$fabricante", cli.getModelo());
            html = html.replace("$total", cli.getTotal());
            html = html.replace("$ativos", cli.getAtivo());
            html = html.replace("$dias", cli.getPeriodo());
            html = html.replace("$canais", cli.getCameras());
            html = html.replace("$valor", cli.getValor());
            html = html.replace("$data", cli.getData());            
            
            htmlIntermediario.append(html);
        }      
        
        htmlFinal = htmlEstatico.replace("$dinamic", htmlIntermediario);       
        
        escreveNoHtml();        
    }
    
    /***
     * Este método escreve o html finalizado depois que já foi
     * dinamicamente modificado. O index.html fica localizado 
     * na pasta raiz do programa e pode ser chamada à partir daí.
     * Caso o método seja chamado mais de uma vez, o documento será
     * sobrescrito. Se não existir, será criado.
     */
    private void escreveNoHtml(){        
        try {
            writer = new PrintWriter("index.html", "UTF-8");
            writer.print(htmlFinal);
            writer.close();            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /***
     * 
     * @return String contendo o html estatico
     */
    private String getHtmlEstatico(){
        StringBuilder _htmlEstatico = new StringBuilder();
        try {
            //este trecho obtém o html como um recurso da aplicação
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream htmlStream = classLoader.getResourceAsStream("resources/estatico.html");            
            //============          
            
            //código antigo
            //BufferedReader doc = new BufferedReader(new FileReader("estatico.html"));
            BufferedReader doc = new BufferedReader(new InputStreamReader(htmlStream));
            String str;
            while ((str = doc.readLine()) != null) {
                _htmlEstatico.append(str);                
            }
            htmlStream.close();
            doc.close();
            
        } catch (IOException e) {
        }
        return _htmlEstatico.toString();
    }
    
    /***
     * 
     * @return String contendo o html dinamico
     */
    private String getHtmlDinamico(){
        StringBuilder _htmlDinamico = new StringBuilder();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream htmlStream = classLoader.getResourceAsStream("resources/dinamico.html");  
            
            BufferedReader doc = new BufferedReader(new InputStreamReader(htmlStream));
            String str;
            while ((str = doc.readLine()) != null) {
                _htmlDinamico.append(str);
            }
            htmlStream.close();
            doc.close();
        } catch (IOException e) {
        }
        return _htmlDinamico.toString();
    }
    
}
