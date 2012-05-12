/*
 * JRangesPanel.java
 *
 * Created on 14 de Setembro de 2005, 10:29
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.bandarra.jmapper;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.*;
import java.awt.*;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.text.DecimalFormat;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author andre.bandarra
 */
public class JRangesPanel extends JPanel {
    private class JColorValueLabel extends JPanel{
        private JPanel colorPanel = new JPanel();
        private JLabel dataLabel = new JLabel("teste");
        private EmptyBorder empBorder = new EmptyBorder(1,1,1,1);
        private EtchedBorder etchBorder = new EtchedBorder();            
        public JColorValueLabel(){
            setBackground(Color.WHITE);
            setBorder(new EmptyBorder(7,2,7,2));
            setLayout(new BorderLayout());
            dataLabel.setBackground(Color.WHITE);
            colorPanel.setBorder(new LineBorder(Color.BLACK,1, true));
            colorPanel.setPreferredSize(new Dimension(30,10));
            dataLabel.setPreferredSize(new Dimension(80,10));
            dataLabel.setBorder(new EmptyBorder(0, 2,0,2));    
            dataLabel.setFont(new Font("Arial",Font.PLAIN, 10));
            dataLabel.setSize(80,5);
            dataLabel.setHorizontalAlignment(JLabel.RIGHT);
            dataLabel.setHorizontalTextPosition(JLabel.RIGHT);  
            setMaximumSize(new Dimension(330, 30));
            add(colorPanel, BorderLayout.WEST);
            add(dataLabel, BorderLayout.CENTER);
        }
        public void setColor(Color newColor){
            colorPanel.setBackground(newColor);
        }
        public void setText(String newText){
            dataLabel.setText(newText);
        }
        public void setEmptyBorder(){
            colorPanel.setBorder(empBorder);   
        }
        public void setEtchedBorder(){
            colorPanel.setBorder(etchBorder);
        }
    }
    private ArrayList<ColorValue> listaCores = null;
    private JColorValueLabel[] arrayColorLabel = null;
    private DecimalFormat dc = new DecimalFormat("#,###.####");
    public JRangesPanel(){  
        setBackground(Color.WHITE);
        setBorder(new EtchedBorder());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));      
        arrayColorLabel = new JColorValueLabel[10];   
        setPreferredSize(new Dimension(135,300));
        for(int i = 0 ; i < arrayColorLabel.length ; i++){
            arrayColorLabel[i] = new JColorValueLabel();

            arrayColorLabel[i].setEmptyBorder();            
            arrayColorLabel[i].setText("");
            arrayColorLabel[i].setColor(Color.WHITE);                                                
            
//            arrayColorLabel[i].setColor(Color.BLUE);
  //          arrayColorLabel[i].setText("azul");
            add(arrayColorLabel[i]);
        }

    }
    public void setListaCores(ArrayList<ColorValue> newListaCores){
        listaCores = newListaCores;
        String valor;
        
        int i =0;
        for (i = 0 ; i < listaCores.size() ; i++){
            arrayColorLabel[i].setEtchedBorder();
            arrayColorLabel[i].setVisible(true);
            arrayColorLabel[i].setColor(listaCores.get(i).color);                        
            try{
                valor = dc.format(listaCores.get(i).minValue)+" - "+
                        dc.format(listaCores.get(i).maxValue);
            } catch (Exception e){
                valor = "0.0";
            }
            arrayColorLabel[i].setText(valor);                        
            arrayColorLabel[i].repaint();
        }
        for (i = listaCores.size() ; i < arrayColorLabel.length ; i++){
            arrayColorLabel[i].setEmptyBorder();            
            arrayColorLabel[i].setText("");
            arrayColorLabel[i].setColor(Color.WHITE);                                    
            //arrayColorLabel[i].setVisible(false);            
        }                
    }       
}

