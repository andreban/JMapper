/*
 * ColorValue.java
 *
 * Created on 22 de Fevereiro de 2006, 16:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.jmapper;
import java.awt.Color;
/**
 *
 * @author andre.bandarra
 */
public class ColorValue {
    
    float minValue;
    float maxValue;
    Color color;
    public String toString(){
        return "min: "+minValue+" max: "+maxValue+" Color: "+color; 
    }    
    /** Creates a new instance of ColorValue */
    public ColorValue() {
    }
    
}
