/*
 * TabDataColorChooser.java
 *
 * Created on 22 de Fevereiro de 2006, 11:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.jmapper;

import java.awt.Color;
import java.util.*;
import org.bandarra.tabulacao.*;

/**
 *
 * @author andre.bandarra
 */
public class TabDataColorChooser implements AreaColorChooser {
/*    class ColorValue{

    }*/
    private ArrayList<ColorValue> arrayColorValue = new ArrayList<ColorValue>();
    private Color[] paleta;
    private TabData tabData;
    private int numeroClasses = 10;
    private Color[] paletaClasses;
    private int idxColunaMapa = 0;
    public ArrayList<ColorValue> getArrayValues(){
        return arrayColorValue;
    }
    public int getIdxColunaMapa(){
        return this.idxColunaMapa;
    }
    public void setIdxColunaMapa(int idxColunaMapa){
        this.idxColunaMapa = idxColunaMapa;
        setNumeroClasses(numeroClasses);
        calculaCorValor();        
    }
    private void calculaCorValor(){
        float[] listaValores = tabData.getValoresColuna(idxColunaMapa);
        Arrays.sort(listaValores);
        
        float step = (float)listaValores.length / (float)paletaClasses.length;
//        System.out.println(step);
        arrayColorValue.clear();
//        for (float f:listaValores)
  //          System.out.println(f);
        
        for (int i=0; i< paletaClasses.length; i++){
            ColorValue cv = new ColorValue();

           // System.out.println(i*step);
            cv.minValue = listaValores[(int)Math.floor(i*step)];
            int idxMax = 0;           
            if (i == paletaClasses.length -1){
                idxMax = listaValores.length-1;
            } else {
                idxMax = (int)(Math.ceil(i+1)*step);
            }
            cv.maxValue = listaValores[idxMax];
            cv.color = paletaClasses[i];
//            System.out.println(cv);
            arrayColorValue.add(cv);            
        }                
    }
    public void setNumeroClasses(int numeroClasses){
        if (numeroClasses > 1 && numeroClasses <= 10){
            this.numeroClasses = numeroClasses;
            paletaClasses = new Color[numeroClasses];
            float step = 10/numeroClasses;
            
            for (int i = 0; i < numeroClasses; i++){
                paletaClasses[i] = paleta[Math.round(i*step)];
            }
            calculaCorValor();            
            //System.arraycopy(paleta,0,paletaClasses,0,numeroClasses);
        }
    }
    public TabDataColorChooser(TabData tabData) {
        this(tabData,Paletas.Cores.VERDE_ROXO_1.getCores());
    }
    public TabDataColorChooser(TabData tabData, Color[] paleta){
        this.tabData = tabData;
        this.paleta = paleta;    
        setNumeroClasses(5);
        calculaCorValor();
    }
    public Color getColor(String nomeArea){
        int idxArea = tabData.getIdxArea(nomeArea);
        float valor = tabData.getValorArea(idxArea, idxColunaMapa);
        //System.out.println(valor);
        for (ColorValue vc: arrayColorValue){
            if (valor >= vc.minValue && valor <= vc.maxValue){
                return vc.color;
            }
        }
        return Color.WHITE;        
    }        
    public void setPaleta(Color[] paleta){
        this.paleta = paleta;
        setNumeroClasses(numeroClasses);
        
    }
}
