/*
 * JMapperApplet.java
 *
 * Created on 24 de Fevereiro de 2006, 14:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.jmapper.desktop;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import org.bandarra.jmapper.*;
import org.bandarra.tabulacao.*;
import org.bandarra.map.*;
/**
 *
 * @author andre.bandarra
 */
public class JMapperDesk extends JFrame{
    private String nomeMap;
    private String nomeTab;
    private MapDrawer map;
    private TabData td;    
    private JPanel jPanelRodape;
    private JLabel jLabelRodape;
    private JPanel jPanelControles;
    private JPanel jPanelButtons;    
    private JMapperPanel jMapperPanel;
    private JToggleButton jToggleButtonZoom = new JToggleButton("Z");    
    private JToggleButton jToggleButtonPan = new JToggleButton("P");       
    private JButton jButtonReset = new JButton("R");



    private class ZoomActionListener implements ActionListener{
        public void actionPerformed(java.awt.event.ActionEvent event) {
            jMapperPanel.toggleZoomMode();
            jToggleButtonPan.setSelected(jMapperPanel.getPanMode());
            jToggleButtonZoom.setSelected(jMapperPanel.getZoomMode());                   
        }
    }
    
    private class PanActionListener implements ActionListener{
        public void actionPerformed(java.awt.event.ActionEvent event) {
            jMapperPanel.togglePanMode();
            jToggleButtonPan.setSelected(jMapperPanel.getPanMode());
            jToggleButtonZoom.setSelected(jMapperPanel.getZoomMode());                   
        }        
    }
    
    private class ResetActionListener implements ActionListener{
        public void actionPerformed(java.awt.event.ActionEvent event) {
            jMapperPanel.reset();
            jToggleButtonPan.setSelected(jMapperPanel.getPanMode());
            jToggleButtonZoom.setSelected(jMapperPanel.getZoomMode());                   
        }        
    }    
    private void createJMapperPanel(){
        jMapperPanel = new JMapperPanel();
        jMapperPanel.setMapDrawer(map);
        jMapperPanel.setBorder(new EtchedBorder());   

    }
    private void createPanelControles(){
        jPanelControles = new JPanel();
        jPanelControles.setLayout(new FlowLayout());
        jPanelControles.setBorder(new EtchedBorder());
        jPanelControles.setSize(50, 200);
        jPanelControles.setMinimumSize(new Dimension(145,400));
        jPanelControles.setMaximumSize(new Dimension(145,400));
        jPanelControles.setPreferredSize(new Dimension(145,400));
                
//        jRangesPanel = new JRangesPanel();
//        jRangesPanel.setSize(new Dimension(135,200));            
//        jRangesPanel.setBorder(new EtchedBorder());                
////        jRangesPanel.setListaCores(tabDataColorChooser.getArrayValues());
//        jPanelControles.add(jRangesPanel);        
        
        jPanelButtons = new JPanel();
        jPanelButtons.setPreferredSize(new Dimension(135,30));
        Font buttonsFont = new Font("Arial",Font.PLAIN, 8);
        
        jToggleButtonZoom.setPreferredSize(new Dimension(40,25));
        jToggleButtonZoom.setFont(buttonsFont);
        jToggleButtonZoom.addActionListener(new ZoomActionListener());
        jPanelButtons.add(jToggleButtonZoom);
        
        jToggleButtonPan.setPreferredSize(new Dimension(40,25));
        jToggleButtonPan.setFont(buttonsFont);  
        jToggleButtonPan.addActionListener(new PanActionListener());
        jPanelButtons.add(jToggleButtonPan);
        
        jButtonReset.setPreferredSize(new Dimension(40,25));
        jButtonReset.setFont(buttonsFont);
        jButtonReset.addActionListener(new ResetActionListener());
        jPanelButtons.add(jButtonReset);
                
        jPanelControles.add(jPanelButtons);
        
    }
    private void createPanelRodape(){
        jPanelRodape = new JPanel();
        jPanelRodape.setSize(200, 15);
        jPanelRodape.setMinimumSize(new Dimension(200,15));            
        jPanelRodape.setMaximumSize(new Dimension(200,15)); 
        jPanelRodape.setPreferredSize(new Dimension(200,15));
        jPanelRodape.setBorder(new EtchedBorder());
        jPanelRodape.setLayout(new BorderLayout());   
        jLabelRodape = new JLabel("");
        jLabelRodape.setPreferredSize(new Dimension(200,15));
        //jLabelRodape.setFont(fonteRodape);
        jPanelRodape.add(jLabelRodape,BorderLayout.WEST);        
    }
    
    private ColorPointGeoMapLayer buildMapLayer() {
        Map<Integer, Color> colorMap = new HashMap<>();
        Random rand = new Random(System.currentTimeMillis());
        ColorPointGeoMapLayer colorPointLayer = new ColorPointGeoMapLayer();          
        try (BufferedReader reader = new BufferedReader(new FileReader("/home/andreban/Desktop/coords.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                float lat = Float.parseFloat(parts[0]);
                float lng = Float.parseFloat(parts[1]);
                int cluster = Integer.parseInt(parts[2]);
                Color c = colorMap.get(cluster);
                if (c == null) {
                    c = new Color(rand.nextInt());
                    colorMap.put(cluster, c);
                }
                colorPointLayer.addCoordinate(c, new MapCoordenada(lng, lat));                
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return colorPointLayer;
    }
    private void createData() throws Exception{          
        FileInputStream fin = new FileInputStream("/home/andreban/Desktop/TM_WORLD_BORDERS-0.3.MAP");
        map = new SimpleMapDrawer();            
        map.setMapData(new MapFileReader(new DataInputStream(fin)).getData());        
        map.setColorChooser(new PlainColorChooser());          
        map.addMapLayer(buildMapLayer());
    }
    public void init(){
        try{
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);            
            this.setLayout(new BorderLayout());            
            createData();
            createPanelRodape();
            createPanelControles();
            createJMapperPanel();
            this.add(jMapperPanel, BorderLayout.CENTER);  
            this.add(jPanelControles,BorderLayout.EAST);     
            this.add(jPanelRodape,BorderLayout.SOUTH);            

        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JMapperDesk jframe = new JMapperDesk();
                jframe.setSize(1024, 768);
                jframe.init();
                jframe.setVisible(true);
            }
        });        
    }
    
}
