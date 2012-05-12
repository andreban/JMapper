/*
 * TabWinData.java
 *
 * Created on 17 de Fevereiro de 2006, 16:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.tabulacao;

import java.util.*;

/**
 *
 * @author andre.bandarra
 */

public class TabWinData implements TabData{
    
    /** Creates a new instance of TabWinData */

    ArrayList<String> codigoAreas = new ArrayList<String>();
    ArrayList<String> nomesAreas = new ArrayList<String>();;
    private String[] nomeColunas;
    private String nomeMapa;
    private String rodape;
    private String titulo1;
    private String titulo2;
    ArrayList<float[]> valores = new ArrayList<float[]>();

    public void setTitulo1(String titulo){
        titulo1 = titulo;
    }
    public void setTitulo2(String titulo){
        titulo2 = titulo;
    }
    public TabWinData(){
        
    }

    public int getIdxArea(String nomeArea) {
        int i = nomesAreas.indexOf(nomeArea);
        return (i>=0)?i:codigoAreas.indexOf(nomeArea);
    }

    void setNomesColunas(String[] nomeColunas){
        this.nomeColunas = nomeColunas;
    }

    private int findNomeColuna(String coluna){
        for (int i=0; i<nomeColunas.length;i++){
            if (nomeColunas[i].equalsIgnoreCase(coluna)){
                return i;
            }
        }
        return -1;
    }
    public float[] getValoresColuna(String coluna) {
        int idxColuna = findNomeColuna(coluna);
        return getValoresColuna(idxColuna);
    }

    public float[] getValoresColuna(int idxColuna) {
        float[] tmp;
        tmp = new float[valores.size()];
        for (int i=0; i<valores.size(); i++){
            tmp[i] = valores.get(i)[idxColuna];
        }
        return tmp;
        
    }

    public String[] getCodigoAreas() {
        String[] tmp = new String[0];
        return codigoAreas.toArray(tmp);
    }

    public String[] getNomeColunas() {
        return nomeColunas; 
    }

    public String getNomeMapa() {
        return nomeMapa; 
    }

    public String[] getNomesAreas() {
        String[] tmp = new String[0];
        return nomesAreas.toArray(tmp);
    }

    public String getRodape() {
        return rodape;  
    }

    public String getTitulo1() {
        return titulo1;
    }

    public String getTitulo2() {
        return titulo2;
    }        

    public float getValorArea(String nomeArea, String nomeColuna) {
        int i = nomesAreas.indexOf(nomeArea);        
        if (i < 0){
            i = codigoAreas.indexOf(nomeArea); 
        }
        
        int j = findNomeColuna(nomeColuna);
        if (i >=0 && j >= 0){
            return getValorArea(i,j);
        } else {
            return 0.0f;
        }
    }
    
    public float getValorArea(int idxArea, int idxColuna) {
        if (idxArea >= 0 && idxArea < nomesAreas.size()
                && idxColuna >= 0 && idxColuna < nomeColunas.length){
            return valores.get(idxArea)[idxColuna];
        } else {
            return 0.0f;
        }
    }
    

}
