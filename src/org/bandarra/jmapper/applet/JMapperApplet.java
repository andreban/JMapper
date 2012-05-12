package org.bandarra.jmapper.applet;
import java.applet.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import org.bandarra.jmapper.*;
import org.bandarra.tabulacao.*;
import org.bandarra.map.*;
/**
 *
 * @author andre.bandarra
 */
public class JMapperApplet extends Applet implements ClipboardOwner {
    private String nomeMap;
    private String nomeTab;
    private MapDrawer map;
    private TabData td;    
    private JPanel jPanelRodape;
    private JLabel jLabelRodape;
    private JPanel jPanelControles;
    private JPanel jPanelButtons;    
    private JMapperPanel jMapperPanel;
    private JRangesPanel jRangesPanel;
    private TabDataColorChooser tabDataColorChooser;
    private JToggleButton jToggleButtonZoom = new JToggleButton("Z");    
    private JToggleButton jToggleButtonPan = new JToggleButton("P");       
    private JButton jButtonReset = new JButton("R");
    private JComboBox jComboBoxColuna;
    private JComboBox jComboBoxPaleta;
    private Clipboard clipboard;

    public void lostOwnership(Clipboard clipboard, Transferable contents) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private class MouseMotionEvent implements MouseMotionListener{
        public void mouseMoved(MouseEvent evt){
            if (td != null){
                int i = td.getIdxArea(jMapperPanel.getNomeAreaAt(evt.getPoint()));
                if (i > 0){
                    jLabelRodape.setText(td.getNomesAreas()[i]+ " - "+td.getValorArea(i,tabDataColorChooser.getIdxColunaMapa()));
                } else {
                    jLabelRodape.setText("");
                }
            }                        
        }

        public void mouseDragged(MouseEvent e) {
            
        }

        
    }
    private class NumClassesChangeListener implements ChangeListener{
        public void stateChanged(ChangeEvent e){            
            int numClasses = (Integer)((JSpinner)e.getSource()).getValue();
            System.out.println(numClasses);
            tabDataColorChooser.setNumeroClasses(numClasses);
            jRangesPanel.setListaCores(tabDataColorChooser.getArrayValues());            
            jMapperPanel.repaintMap();            
            repaint();            
        }        
    }
    private class PaletaChangeActionListener implements ActionListener{
        public void actionPerformed(java.awt.event.ActionEvent event) {        
            Object o = ((JComboBox)event.getSource()).getSelectedItem();
            Paletas.Cores p = (Paletas.Cores)o;
            if (tabDataColorChooser != null){
                tabDataColorChooser.setPaleta(p.getCores());
                jRangesPanel.setListaCores(tabDataColorChooser.getArrayValues());
                jMapperPanel.repaintMap();
                repaint();
            }            
        }        
    }
    private class ColunaMapaActionListener implements ActionListener{
        public void actionPerformed(java.awt.event.ActionEvent event) {        
            tabDataColorChooser.setIdxColunaMapa(((JComboBox)event.getSource()).getSelectedIndex());
            jRangesPanel.setListaCores(tabDataColorChooser.getArrayValues());               
            jMapperPanel.repaintMap();
            repaint();
        }
    }
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
        jMapperPanel.addMouseMotionListener(new MouseMotionEvent());
//        jMapperPanel.setTransferHandler(new ImageS);

    }
    private void createPanelControles(){
        jPanelControles = new JPanel();
        jPanelControles.setLayout(new FlowLayout());
        jPanelControles.setBorder(new EtchedBorder());
        jPanelControles.setSize(50, 200);
        jPanelControles.setMinimumSize(new Dimension(145,400));
        jPanelControles.setMaximumSize(new Dimension(145,400));
        jPanelControles.setPreferredSize(new Dimension(145,400));
                
        jComboBoxColuna = new JComboBox();
        jComboBoxColuna.setPreferredSize(new Dimension(135,25));
//        jComboBoxColuna.setFont(fonte);
        jComboBoxColuna.setToolTipText("Coluna Dados"); 
        for (String s: td.getNomeColunas())
            jComboBoxColuna.addItem(s);
        jComboBoxColuna.addActionListener(new ColunaMapaActionListener());        
        jPanelControles.add(jComboBoxColuna);
        
        jRangesPanel = new JRangesPanel();
        jRangesPanel.setSize(new Dimension(135,200));            
        jRangesPanel.setBorder(new EtchedBorder());                
        jRangesPanel.setListaCores(tabDataColorChooser.getArrayValues());
        jPanelControles.add(jRangesPanel);        
        
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
        
        jComboBoxPaleta = new JComboBox();   
        jComboBoxPaleta.setPreferredSize(new Dimension(135,25));
        jComboBoxPaleta.addItem(Paletas.Cores.VERDE_ROXO_1);
        jComboBoxPaleta.addItem(Paletas.Cores.VERDE_ROXO_2);            
        jComboBoxPaleta.addItem(Paletas.Cores.VERMELHA_1);            
        jComboBoxPaleta.addItem(Paletas.Cores.VERMELHA_2);                        
        jComboBoxPaleta.addItem(Paletas.Cores.VERDE_AMARELA_1);                                    
        jComboBoxPaleta.addItem(Paletas.Cores.VERDE_AMARELA_2);
        jComboBoxPaleta.addItem(Paletas.Cores.AMARELA_1);                                                            
        jComboBoxPaleta.addItem(Paletas.Cores.AMARELA_2);                                                                        
        jComboBoxPaleta.addItem(Paletas.Cores.ROSA_1); 
        jComboBoxPaleta.addItem(Paletas.Cores.ROSA_2);            
        jComboBoxPaleta.addItem(Paletas.Cores.AZUL_1); 
        jComboBoxPaleta.addItem(Paletas.Cores.AZUL_2);                        
        jComboBoxPaleta.addItem(Paletas.Cores.VERDE_1);                                    
        jComboBoxPaleta.addItem(Paletas.Cores.VERDE_2);                                                
        jComboBoxPaleta.addItem(Paletas.Cores.PB_1);
        jComboBoxPaleta.addItem(Paletas.Cores.PB_2);            
        jComboBoxPaleta.addItem(Paletas.Cores.COLORIDA);    
        jComboBoxPaleta.setEditable(false);
        jComboBoxPaleta.setRenderer(new PaletaCellRenderer());
        jComboBoxPaleta.addActionListener(new PaletaChangeActionListener());
        jPanelControles.add(jComboBoxPaleta);        
        
        SpinnerNumberModel spinModel = new SpinnerNumberModel(5,2,10,1);
        JSpinner jSpinnerClasses = new JSpinner(spinModel);
        jSpinnerClasses.setPreferredSize(new Dimension(135,25));            
        jSpinnerClasses.setToolTipText("N�mero de Classes");
        jSpinnerClasses.addChangeListener(new NumClassesChangeListener());
        jPanelControles.add(jSpinnerClasses);
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
    private void createData() throws Exception{
            nomeMap = getParameter("nomeMap");
            nomeTab = getParameter("nomeTab");

            URL urlMap = new URL(getCodeBase(),nomeMap);
            URL urlTab = new URL(getCodeBase(),nomeTab);

            URLConnection urlCon = urlMap.openConnection();
            urlCon.setUseCaches(false);           

            DataInputStream bufInMap = new DataInputStream(urlCon.getInputStream());   

            byte[] buffer = new byte[urlCon.getContentLength()];

            bufInMap.readFully(buffer);

            ByteArrayInputStream bInput = new ByteArrayInputStream(buffer);


            InputStream bufInTab = new BufferedInputStream(urlTab.openStream());
            
            td = new TabWinReader(new BufferedReader(new InputStreamReader(bufInTab))).getTabData();
            tabDataColorChooser = new TabDataColorChooser(td);
            
            map = new SimpleMapDrawer();            
            map.setMapData(new MapFileReader(new DataInputStream(bInput)).getData());        
            map.setColorChooser(tabDataColorChooser);
        
    }
    public void init(){
        try{
            this.setLayout(new BorderLayout());            
            createData();
            createPanelRodape();
            createPanelControles();
            createJMapperPanel();
            this.add(jMapperPanel, BorderLayout.CENTER);  
            this.add(jPanelControles,BorderLayout.EAST);     
            this.add(jPanelRodape,BorderLayout.SOUTH);  
            clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();            
            clipboard.setContents(null, this);
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void start(){
        setVisible(true);                    
    }
    public void stop(){
        
    }
    public void destroy(){
        
    }
    
}
