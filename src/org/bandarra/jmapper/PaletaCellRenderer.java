package org.bandarra.jmapper;
/*
 * PaletaCellRenderer.java
 *
 * Created on December 18, 2005, 11:25 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author andreban
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EtchedBorder;


public class PaletaCellRenderer extends JPanel implements ListCellRenderer{ 
    private JPanel[] jpList = new JPanel[10];
    public PaletaCellRenderer() {
        setOpaque(true);
        BoxLayout cl = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(cl);
        for (int i = 0 ; i < jpList.length ; i++){
            jpList[i] = new JPanel();
//            jpList[i].setBorder(new EtchedBorder());
            jpList[i].setBorder(null);            
            jpList[i].setPreferredSize(new Dimension(5,20));

            add(jpList[i]);
        }
    }

    public Component getListCellRendererComponent(
         JList list,
         Object value,
         int index,
         boolean isSelected,
         boolean cellHasFocus)
     {
        Paletas.Cores p = (Paletas.Cores)value;
        Color[] paleta = p.getCores();
        for (int i = 0 ; i< 10 ; i++){
            jpList[i].setBackground(p.getCores()[i]);
//            if (isSelected){
//               jpList[i].setBorder(null);                               
//            } else {
//               jpList[i].setBorder(new EtchedBorder());                
//            }
        }     
        setBackground(Color.LIGHT_GRAY);        
        return this;
    }    
}
