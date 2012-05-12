/*
 * Coordenada.java
 *
 * Created on May 3, 2005, 11:41 PM
 */

package org.bandarra.map;

/**
 *
 * @author Andre Cipriani Bandarra
 */
public class MapCoordenada implements Coordenada{
    private float pontoX = 0.0f;
    private float pontoY = 0.0f;
    public String toString(){
        return "br.nom.bandarra.mapreader.MapCoordenada=[x="+pontoX+",y="+pontoY+"]";
    }
    public MapCoordenada(){
        pontoX = 0.0f;
        pontoY = 0.0f;
    }
    public MapCoordenada(float x, float y){
        pontoX = x;
        pontoY = y;
    }
    public boolean equals(Object other){  
        if (other != null){            
            if (other instanceof MapCoordenada){ 
                MapCoordenada o = (MapCoordenada) other;
                if (pontoX == o.getPontoX() && pontoY == o.getPontoY()){              
                    return true;
                }
            }                        
        }            
        return false;
    }
    
    public void setPontos(float x, float y)
    {
        pontoX = x;
        pontoY = y;
    }
    public void setPontoX(float x)
    {    
        pontoX = x;
    }
    public void setPontoY(float y)
    {
        pontoY = y;
    }
    public float getPontoX()
    {
        return pontoX;
    }
    public float getPontoY()
    {
        return pontoY;
    }
}
    

