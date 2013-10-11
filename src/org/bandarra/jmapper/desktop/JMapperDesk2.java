/*
 * JMapperApplet.java
 *
 * Created on 24 de Fevereiro de 2006, 14:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.jmapper.desktop;

import org.bandarra.jmapper.*;
import org.bandarra.map.MapCoordenada;
import org.bandarra.map.MapFileReader;
import org.bandarra.tabulacao.TabData;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author andre.bandarra
 */
public class JMapperDesk2 extends JFrame{
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
        public void actionPerformed(ActionEvent event) {
            jMapperPanel.toggleZoomMode();
            jToggleButtonPan.setSelected(jMapperPanel.getPanMode());
            jToggleButtonZoom.setSelected(jMapperPanel.getZoomMode());
        }
    }

    private class PanActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            jMapperPanel.togglePanMode();
            jToggleButtonPan.setSelected(jMapperPanel.getPanMode());
            jToggleButtonZoom.setSelected(jMapperPanel.getZoomMode());
        }
    }

    private class ResetActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
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

    private MapLayer buildMapLayer() {
        Map<Integer, Color> colorMap = new HashMap<>();
        Random rand = new Random(System.currentTimeMillis());
        ColorPointGeoMapLayer2 colorPointLayer = new ColorPointGeoMapLayer2();
        try (BufferedReader reader = new BufferedReader(new FileReader("/home/andreban/Desktop/centroids.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int cluster = Integer.parseInt(parts[0]);
                float lat = Float.parseFloat(parts[1]);
                float lng = Float.parseFloat(parts[2]);

                float lat2 = Float.parseFloat(parts[3]);
                float lng2 = Float.parseFloat(parts[4]);

                Color c = colorMap.get(cluster);
                if (c == null) {
                    c = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255), 200);
                    colorMap.put(cluster, c);
                }
                colorPointLayer.addCoordinate(c, new MapCoordenada(lng, lat), new MapCoordenada(lng2, lat2));
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
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JMapperDesk2 jframe = new JMapperDesk2();
                jframe.setSize(1024, 768);
                jframe.init();
                jframe.setVisible(true);
            }
        });        
    }
    
}
