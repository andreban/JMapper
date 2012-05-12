/*
 * Area.java
 *
 * Created on 17 de Fevereiro de 2006, 15:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.map;
import java.util.Iterator;
/**
 *
 * @author andre.bandarra
 */
public interface Area {
    public enum TipoPoligono {POLIGONO, POLIGONOSEDE, POLIGONAL, PONTO, POLIGONOINDEFINIDO};
    public enum Projecao {PRIGN, PRLL, PRUTM};
    public String getCodigo();
    public String getNome();
    public Iterator<Coordenada> getCoordenadaIterator();
    public Coordenada getCoordenadaCentral();
    public TipoPoligono getTipoObj();    
}
