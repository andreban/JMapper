/*
 * RandomColorChooser.java
 *
 * Created on 20 de Fevereiro de 2006, 15:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.jmapper;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author andre.bandarra
 */
public class RandomColorChooser implements AreaColorChooser{    
    public Color getColor(String nomeArea) {
        Random r = new Random();
        Color c = new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256));
        return c;
    }    
}
