/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.util.List;

/**
 *
 * @author user
 */
public class Cliente {
    //ordem do BD
    private String id, codigo, nome, ddns, modelo,
            total, ativo, periodo, valor, data, cameras;

    public Cliente(List clList) {
        id = clList.get(0).toString();
        codigo = clList.get(1).toString();
        nome = clList.get(2).toString();
        ddns = clList.get(3).toString();
        modelo = clList.get(4).toString();
        total = clList.get(5).toString();
        ativo = clList.get(6).toString();
        cameras = clList.get(7).toString();
        periodo = clList.get(8).toString();
        valor = clList.get(9).toString();
        data = clList.get(10).toString();      
        
    }    

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }    

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }    

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    
    /**
     * @return the ddns
     */
    public String getDdns() {
        return ddns;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @return the ativo
     */
    public String getAtivo() {
        return ativo;
    }

    /**
     * @return the periodo String
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @return a data do cliente
     */
    public String getData() {
        return data;
    }

    /**
     * @return the cameras
     */
    public String getCameras() {
        return cameras;
    }    
}
