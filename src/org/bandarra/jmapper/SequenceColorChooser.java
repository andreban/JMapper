/*
 * SequenceColorChooser.java
 *
 * Created on 24 de Fevereiro de 2006, 16:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.jmapper;
import java.awt.Color;
import java.util.*;
/**
 *
 * @author andre.bandarra
 */
public class SequenceColorChooser implements AreaColorChooser{ 
    private TreeMap<String,Color> treeMapCores = new TreeMap<String,Color>();
    private TreeMap<Integer,String> treeMapStrings = new TreeMap<Integer,String>();
    private int lastColor = 1;
    public Color getColor(String nomeArea){  
        Color cor = treeMapCores.get(nomeArea);
        if (cor != null){
            return cor;
        }
        cor = new Color(lastColor++);
        treeMapCores.put(nomeArea,cor);
        treeMapStrings.put(cor.getRGB(), nomeArea);
        return cor;
    }  
    public String getArea(Color color){
        return treeMapStrings.get(color.getRGB());
    }
}
