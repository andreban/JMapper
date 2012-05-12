/*
 * Area.java
 *
 * Created on May 3, 2005, 11:33 PM
 */

package org.bandarra.map;
import java.io.*;
import java.util.*;
/**
 *
 * @author Andre Cipriani Bandarra
 */
public class MapArea implements Area{
    public static final int MAXPONTOS = 10000;
    private byte tipoObj = 0;
    private String codigo = "";
    private String nome = "";    
    private Coordenada coordenadaCentral;
    private short qtdCoordenadas = 0;
    private TipoPoligono aTipoPoligono = TipoPoligono.POLIGONOINDEFINIDO;
    private ArrayList<Coordenada> listaCoordenadas = new ArrayList<Coordenada>();

    void setTipoObj(byte tipoObj){
        this.tipoObj = tipoObj;
    }
    
    void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    void setNome(String nome){
        this.nome = nome;
    }
    
    void addCoordenada(Coordenada c){
        listaCoordenadas.add(c);
    }
    
    void setCoordenadaCentral(Coordenada coordenada){
       coordenadaCentral = coordenada;    
    }
    
    public Iterator<Coordenada> getCoordenadaIterator(){
        return listaCoordenadas.iterator();
    }
    public String getCodigo()
    {
        return codigo;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public Coordenada getCoordenadaCentral()
    {
        return coordenadaCentral;
    }
    public int getQtdCoordenadas()
    {
        return qtdCoordenadas;
    }
    public Coordenada[] getListaCoordenadas()
    {
        return listaCoordenadas.toArray(new Coordenada[listaCoordenadas.size()]);
    }
    public TipoPoligono getTipoObj()
    {
        switch(tipoObj)
        {
            case 0: return TipoPoligono.POLIGONAL;
            case 1: return TipoPoligono.POLIGONO;
            case 2: return TipoPoligono.POLIGONOSEDE;
            case 3: return TipoPoligono.PONTO;
            default: return TipoPoligono.POLIGONOINDEFINIDO;
        }
    }      
}
