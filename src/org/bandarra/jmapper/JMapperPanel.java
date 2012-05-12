/*
 * JMapperPanel.java
 *
 * Created on 22 de Fevereiro de 2006, 15:36
 */

package org.bandarra.jmapper;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static java.lang.Math.*;
/**
 *
 * @author  andre.bandarra
 */
public class JMapperPanel extends javax.swing.JPanel {
    MapDrawer mapDrawer;
    Image mapImage;
    BufferedImage colorImage;
    Point mousePressPoint;
    Point mouseReleasePoint;
    Rectangle rect;
    Line2D line;
    SequenceColorChooser sqColorChooses = new SequenceColorChooser();
    boolean zoomMode = false;
    boolean panMode = false;
    boolean isZoomed = false;
    private boolean repaintMap = true;
    
    public void repaintMap(){
        repaintMap = true;
    }
    
    public boolean getZoomMode(){
        return zoomMode;
    }
    
    public boolean getPanMode(){
        return panMode;
    }
    public void reset(){
        mapDrawer.reset();
        repaintMap();
        repaint();
    }
    public void toggleZoomMode(){
        panMode = false;
        zoomMode = !zoomMode;
//        setCursor();
    }

    public void togglePanMode(){
        zoomMode = false;
        if (!panMode && isZoomed){
            panMode = true;
        } else {
            panMode = false;
        }
//        setCursor();       
    }    
    public JMapperPanel() {
        initComponents();
        addMouseMotionListener(
                new MouseMotionListener()
                {
                    public void mouseMoved(MouseEvent e){

                    }
                    public void mouseDragged(MouseEvent e){
                        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK)
                                == MouseEvent.BUTTON1_MASK){
                            if (zoomMode){
                                Point p = e.getPoint();
                                int startx = min (mousePressPoint.x,p.x);
                                int starty = min (mousePressPoint.y,p.y);
                            
                                int width = max(mousePressPoint.x,p.x) - startx;
                                int heigth = max(mousePressPoint.y,p.y) - starty;                            
                            
                                rect = new Rectangle(startx,starty,
                                        width,
                                        heigth);
                                repaint();
                            } else if (isZoomed && panMode){
                                Point p1 = new Point();
                                Point p2 = new Point();
                                p1.setLocation(mousePressPoint.x,mousePressPoint.y);
                                p2.setLocation(e.getPoint().x,e.getPoint().y);
                                line = new Line2D.Double();
                                line.setLine(p1,p2);
                                repaint();
                                
                            }
                            
                        }
                        

                    }
            
                });
        addMouseListener
            (
                new MouseListener()
                {
                    public void mousePressed(MouseEvent e){                        
                        if (e.getButton() == MouseEvent.BUTTON1){
                            mousePressPoint = e.getPoint(); 
                            //drawColorMap = false;
                        }
                    }
                    public void mouseReleased(MouseEvent e){
                        if (e.getButton() == MouseEvent.BUTTON1){                        
                            mouseReleasePoint = e.getPoint();   
                            if (!mouseReleasePoint.equals(mousePressPoint) && zoomMode){ 
                                isZoomed = true;      
                                mapDrawer.zoom(rect);
                                repaintMap = true;
                          //      setZoomCoordenadas();
                                repaint();
                            } else if (isZoomed && panMode){
                              //  setPanCoordenadas();
                                mapDrawer.pan(mouseReleasePoint.x - mousePressPoint.x,
                                        mouseReleasePoint.y - mousePressPoint.y);
                                repaintMap = true;
                                repaint();
                            }
                            
                     //       drawColorMap = true;
                            rect = null;
                            line = null;
                        }
                    }
                    public void mouseExited(MouseEvent e){}
                    public void mouseEntered(MouseEvent e){}
                    public void mouseClicked(MouseEvent e){
                            if ((e.getButton() & MouseEvent.BUTTON2) ==MouseEvent.BUTTON2) {
               //                 reset();
                                repaint();
                                                            
                        }                        
                    }
                }                                             
             );        
    }
    public void setMapDrawer(MapDrawer mapDrawer){
        this.mapDrawer = mapDrawer;
    }
    
    public String getNomeAreaAt(Point p){
        if (colorImage != null){
            Color cl = new Color(colorImage.getRGB(p.x,p.y));
            return sqColorChooses.getArea(cl);
           // return tmColorName.get(cl.getRGB()) != null ? tmColorName.get(cl.getRGB()).getNome():"";
        }
        return "";
    }
        
    public void paintComponent(Graphics g){
        if (mapDrawer != null){
            if (mapImage ==  null  || 
                    this.getWidth() != mapImage.getWidth(this) ||
                    this.getHeight() != mapImage.getHeight(this) ||
                    this.repaintMap){
                mapImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
                colorImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
                Graphics gImage = mapImage.getGraphics();
                mapDrawer.setSize(getWidth(), getHeight());
                mapDrawer.drawMap(gImage);                
                Graphics cImage = colorImage.getGraphics();
                mapDrawer.drawMap(cImage,sqColorChooses);
                repaintMap = false;
            }
            g.drawImage(mapImage,0,0,Color.WHITE,this);
            Graphics2D g2d = (Graphics2D) g;
            if (rect  != null){
                g2d.draw(rect);
            }
            
            if (line != null){
                g2d.draw(line);
            }
            
        }        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
