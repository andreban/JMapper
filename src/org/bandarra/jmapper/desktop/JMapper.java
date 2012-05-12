/*
 * JMapper.java
 *
 * Created on 22 de Fevereiro de 2006, 15:41
 */

package org.bandarra.jmapper.desktop;

import org.bandarra.jmapper.*;
import java.io.*;
import org.bandarra.tabulacao.*;
import org.bandarra.map.*;
import javax.swing.*;
import org.bandarra.jmapper.Paletas.Cores;
/**
 *
 * @author  andre.bandarra
 */
public class JMapper extends javax.swing.JFrame {
    
    /** Creates new form JMapper */
    TabDataColorChooser tdcc;
    MapDrawer map;
    TabData td;
    public JMapper() {
        initComponents();
        
        jComboBox1.setRenderer(new PaletaCellRenderer());
        jComboBox1.addItem(Cores.AZUL_1);
        jComboBox1.addItem(Cores.VERDE_ROXO_1);        
        jComboBox1.addItem(Cores.VERMELHA_1);                
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jRangesPanel1 = new org.bandarra.jmapper.JRangesPanel();
        jButton1 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jComboBox1 = new javax.swing.JComboBox();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jMapperPanel1 = new org.bandarra.jmapper.JMapperPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 150));
        jPanel2.add(jRangesPanel1);

        jButton1.setText("jButton1");
        jButton1.setPreferredSize(new java.awt.Dimension(135, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton1);

        jSpinner1.setPreferredSize(new java.awt.Dimension(135, 25));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jPanel2.add(jSpinner1);

        jComboBox1.setPreferredSize(new java.awt.Dimension(135, 25));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jPanel2.add(jComboBox1);

        jToggleButton1.setText("jToggleButton1");
        jToggleButton1.setPreferredSize(new java.awt.Dimension(135, 25));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jPanel2.add(jToggleButton1);

        jToggleButton2.setText("jToggleButton2");
        jToggleButton2.setPreferredSize(new java.awt.Dimension(135, 25));
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jPanel2.add(jToggleButton2);

        jButton2.setText("jButton2");
        jButton2.setPreferredSize(new java.awt.Dimension(135, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.add(jButton2);

        jComboBox2.setPreferredSize(new java.awt.Dimension(135, 25));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jPanel2.add(jComboBox2);

        getContentPane().add(jPanel2, java.awt.BorderLayout.EAST);

        jMapperPanel1.setLayout(new javax.swing.BoxLayout(jMapperPanel1, javax.swing.BoxLayout.X_AXIS));

        jMapperPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jMapperPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        jMapperPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMapperPanel1MouseMoved(evt);
            }
        });

        getContentPane().add(jMapperPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMapperPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMapperPanel1MouseMoved
       if (td != null){
           int i = td.getIdxArea(jMapperPanel1.getNomeAreaAt(evt.getPoint()));
           if (i >= 0){
               jLabel1.setText(td.getNomesAreas()[i]+ " - "+td.getValorArea(i,tdcc.getIdxColunaMapa()));
           }
       }
              
    }//GEN-LAST:event_jMapperPanel1MouseMoved

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
       tdcc.setIdxColunaMapa(((JComboBox)evt.getSource()).getSelectedIndex());
       jRangesPanel1.setListaCores(tdcc.getArrayValues());               
       jMapperPanel1.repaintMap();
       repaint();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        map.reset();
        jMapperPanel1.repaintMap();
        repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        jMapperPanel1.togglePanMode();
        jToggleButton1.setSelected(jMapperPanel1.getZoomMode());
        jToggleButton2.setSelected(jMapperPanel1.getPanMode());
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        jMapperPanel1.toggleZoomMode();
        jToggleButton1.setSelected(jMapperPanel1.getZoomMode());
        jToggleButton2.setSelected(jMapperPanel1.getPanMode());
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        Object o = ((JComboBox)evt.getSource()).getSelectedItem();
        Paletas.Cores p = (Paletas.Cores)o;
        if (tdcc != null){
            tdcc.setPaleta(p.getCores());
            jRangesPanel1.setListaCores(tdcc.getArrayValues());
            jMapperPanel1.repaintMap();
            repaint();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
            int numClasses = (Integer)((JSpinner)evt.getSource()).getValue();
            System.out.println(numClasses);
            tdcc.setNumeroClasses(numClasses);
            jRangesPanel1.setListaCores(tdcc.getArrayValues());            
            jMapperPanel1.repaintMap();            
            repaint();
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           try{
            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(this);
            String mapFile = jfc.getSelectedFile().getAbsolutePath();
            jfc.showOpenDialog(this);            
            String tabFile = jfc.getSelectedFile().getAbsolutePath(); 
//            FileInputStream fisMap = new FileInputStream("C:\\Andre\\JMapper\\rn.map");
            FileInputStream fisMap = new FileInputStream(mapFile);            
            DataInputStream dis = new DataInputStream(fisMap);
            MapFileReader mfr = new MapFileReader(dis);

            FileInputStream fisTab = new FileInputStream(tabFile);
            BufferedReader bfTab = new BufferedReader(new InputStreamReader(fisTab));
            TabWinReader twr = new TabWinReader(bfTab);
            td = twr.getTabData();
            tdcc = new TabDataColorChooser(td);
            map = new SimpleMapDrawer();
            jComboBox2.removeAllItems();
            for (String s: td.getNomeColunas()){
                jComboBox2.addItem(s);
            }
            map.setColorChooser(tdcc);
            map.setMapData(mfr.getData());
            jMapperPanel1.setMapDrawer(map);
            jRangesPanel1.setListaCores(tdcc.getArrayValues());
            jMapperPanel1.repaintMap();            
            repaint();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JMapper().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private org.bandarra.jmapper.JMapperPanel jMapperPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.bandarra.jmapper.JRangesPanel jRangesPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
    
}