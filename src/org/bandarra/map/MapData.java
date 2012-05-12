/*
 * MapData.java
 *
 * Created on 17 de Fevereiro de 2006, 15:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.map;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author andre.bandarra
 */
public class MapData implements Data {
    
    /** Creates a new instance of MapData */
    private short versao = 0;
    private float limiteLeste = 0.0f;
    private float limiteOeste = 0.0f;
    private float limiteNorte = 0.0f;
    private float limiteSul = 0.0f;
    private ArrayList<Area> listaAreas = new ArrayList<Area>();
    public MapData() {
    }

    void setLimiteLeste(float novoLimite){
        limiteLeste = novoLimite;
    }
    void setLimiteNorte(float novoLimite){
        limiteNorte = novoLimite;
    }
    
    void setLimiteOeste(float limiteOeste) {
        this.limiteOeste = limiteOeste;
    }

    void setLimiteSul(float limiteSul) {
        this.limiteSul = limiteSul;
    }
    
    void setVersao(short novaVersao){
        versao = novaVersao;
    }
    void addArea(Area newArea){
        listaAreas.add(newArea);
    }
    
    public Iterator<Area> getAreaIterator() {
        return listaAreas.iterator();
    }

    public float getLimiteLeste() {
        return limiteLeste;
    }

    public float getLimiteOeste() {
        return limiteOeste;
    }

    public float getLimiteNorte() {
        return limiteNorte;
    }

    public float getLimiteSul() {
        return limiteSul;
    }

    public float getHorizontalMapSize() {
        return Math.abs(limiteLeste - limiteOeste);
    }

    public float getVerticalMapSize() {
        return Math.abs(limiteNorte - limiteSul);
    }

    public Coordenada getCentro() {
        float centroX = limiteOeste + getHorizontalMapSize() / 2;
        float centroY = limiteSul + getVerticalMapSize() / 2;
        Coordenada centro = new MapCoordenada(centroX, centroY);
        return centro;
    }
          
}
