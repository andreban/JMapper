/*
 * SimpleMapDrawer.java
 *
 * Created on 20 de Fevereiro de 2006, 15:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.jmapper;
import java.awt.*;
import java.util.Iterator;
import static java.lang.Math.*;
import org.bandarra.map.*;
/**
 *
 * @author andre.bandarra
 */
public class SimpleMapDrawer implements MapDrawer {
    private class MapInfo{
        float limiteLeste, limiteOeste, limiteNorte, limiteSul;
        float horizontalMapSize, verticalMapSize;        
    }
    private boolean stateChanged = true;
    private MapInfo mapInfo = new MapInfo();
    private int width = 0;
    private int height = 0;
    private AreaColorChooser cm;
    private int borderSize = 10;
    private Data mapData;
    private float mapRatio = 0.0f;
    private Stroke stroke = new BasicStroke(1.5f);    

    public void addMapLayer(MapLayer aMapLayer) {
    }

    public boolean stateChanged() {
        return this.stateChanged;
    }

    public void reset() {
        mapInfo.limiteLeste = mapData.getLimiteLeste();
        mapInfo.limiteNorte = mapData.getLimiteNorte();
        mapInfo.limiteOeste = mapData.getLimiteOeste();
        mapInfo.limiteSul = mapData.getLimiteSul();
        mapInfo.horizontalMapSize = mapData.getHorizontalMapSize();
        mapInfo.verticalMapSize = mapData.getVerticalMapSize();
        stateChanged = true;            
    }

    public void pan(int xDistancia, int yDistancia){
        Point p = new Point(borderSize-xDistancia, borderSize-yDistancia);
        Point p2 = new Point(width-borderSize-xDistancia, height-borderSize-yDistancia);           

        Coordenada leftCoord = panelToMap(p);
        Coordenada rightCoord = panelToMap(p2);                        
        mapInfo.limiteLeste = max(rightCoord.getPontoX(),leftCoord.getPontoX());
        mapInfo.limiteOeste = min(rightCoord.getPontoX(),leftCoord.getPontoX());
        mapInfo.limiteNorte = max(leftCoord.getPontoY(),rightCoord.getPontoY());
        mapInfo.limiteSul = min(leftCoord.getPontoY(),rightCoord.getPontoY());
        mapInfo.horizontalMapSize = mapInfo.limiteLeste - mapInfo.limiteOeste;
        mapInfo.verticalMapSize = mapInfo.limiteNorte - mapInfo.limiteSul; 
        float hMapRatio = (width-borderSize * 2) / mapInfo.horizontalMapSize;
        float vMapRatio = (height-borderSize * 2) / mapInfo.verticalMapSize;
        mapRatio = Math.min(hMapRatio,vMapRatio);
        stateChanged = true;    
        
    }
    public void zoom(Rectangle rect) {
        
        Coordenada leftCoord = panelToMap(new Point(rect.x,rect.y));
        Coordenada rightCoord = panelToMap(new Point(rect.x+rect.width,
                rect.height+rect.y));                
        mapInfo.limiteLeste = max(rightCoord.getPontoX(),leftCoord.getPontoX());
        mapInfo.limiteOeste = min(rightCoord.getPontoX(),leftCoord.getPontoX());
        mapInfo.limiteNorte = max(leftCoord.getPontoY(),rightCoord.getPontoY());
        mapInfo.limiteSul = min(leftCoord.getPontoY(),rightCoord.getPontoY());
        mapInfo.horizontalMapSize = mapInfo.limiteLeste - mapInfo.limiteOeste;
        mapInfo.verticalMapSize = mapInfo.limiteNorte - mapInfo.limiteSul;  
        float hMapRatio = (width-borderSize * 2) / mapInfo.horizontalMapSize;
        float vMapRatio = (height-borderSize * 2) / mapInfo.verticalMapSize;
        mapRatio = Math.min(hMapRatio,vMapRatio); 
        stateChanged = true;                
    }

    public Coordenada panelToMap(Point panelPoint)
    {
        MapCoordenada c = new MapCoordenada();
        int difCentroX = 0;
        int difCentroY = 0;       

        difCentroX = ((width) - (int)((mapInfo.limiteLeste-mapInfo.limiteOeste)*mapRatio + borderSize*2))/2;
        int h = Math.round((mapInfo.limiteNorte - mapInfo.limiteSul) * mapRatio + borderSize * 2);
        difCentroY = (height - h) / 2;
                
        c.setPontoX((panelPoint.x - borderSize - difCentroX   )  / mapRatio + mapInfo.limiteOeste );
        c.setPontoY(((height  -  borderSize*2  - difCentroY ) - (panelPoint.y - borderSize)) /
                mapRatio + mapInfo.limiteSul);                        

        return c;                             
    }    
    
    public Point mapToPanel(Coordenada mapCoord){

        Point p = new Point();
        int difCentroX = 0;
        int difCentroY = 0;
        
        difCentroX = (width - (int)((mapInfo.limiteLeste-mapInfo.limiteOeste)*mapRatio + borderSize *2 ))/2;
        int h = Math.round((mapInfo.limiteNorte - mapInfo.limiteSul) * mapRatio + borderSize * 2);
        difCentroY = (height - h) / 2;

        int x = (int)((mapCoord.getPontoX()-mapInfo.limiteOeste)*mapRatio + borderSize  + difCentroX) ;
        int y = (int)((height  - borderSize*2 )-(mapCoord.getPontoY()-mapInfo.limiteSul) *                        
                mapRatio   + borderSize - difCentroY) ;                

        p.setLocation(x,y);                       
        
        
        return p;
    }    
    
    public void drawMap(Graphics g, AreaColorChooser areaColorChooser){
        AreaColorChooser oldArea = this.cm;
        this.cm = areaColorChooser;
        drawMap(g);
        this.cm = oldArea;
        
    }
    public void drawMap(Graphics g) {
//        g.setColor(cm.getColor(""));
  //      g.drawLine(0,0,width,height);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        g.setColor(Color.BLACK);
        if (mapData != null)
        {  
            /*Graphics2D color2d = null;
            if (drawColorMap){
                colorMap = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);                
                color2d = colorMap.createGraphics();                        
            }*/
            Graphics2D g2d = (Graphics2D)g;
                        
            float hMapRatio = (width-borderSize * 2) / mapInfo.horizontalMapSize;
            float vMapRatio = (height-borderSize * 2) / mapInfo.verticalMapSize;
            mapRatio = Math.min(hMapRatio,vMapRatio);
                                                                     
            int ctArea = 0;
            Iterator it = mapData.getAreaIterator();
            while(it.hasNext()){
                Area aArea = (Area)it.next();

                Color areaColor = cm.getColor(aArea.getCodigo());
                Iterator areaIterator = aArea.getCoordenadaIterator();
                Coordenada c0 = (Coordenada)areaIterator.next();                
                Coordenada ci = c0;
                Polygon poly = new Polygon();                 
                while(areaIterator.hasNext()){

                    poly.addPoint(mapToPanel(ci).x, mapToPanel(ci).y);                                        
                    ci = (Coordenada) areaIterator.next();                    

                    if (ci.equals(c0)){
                        g2d.setStroke(stroke);
                        g2d.setColor(Color.BLACK);                    
                        g2d.drawPolygon(poly);                                                     
                        g2d.setColor(areaColor);                    
                        g2d.fillPolygon(poly); 
                        poly = new Polygon();
                        if (areaIterator.hasNext()){
                            ci = (Coordenada) areaIterator.next(); 
                        }
                    }                    
                }
            }  
        }     
    }

    public void removeMapLayer(MapLayer aMapLayer) {
    }

    public void setColorChooser(AreaColorChooser colorChooser) {
        cm = colorChooser;
        stateChanged = true;            
    }

    public void setMapData(Data aMapData) {
        mapData = aMapData;
        mapInfo.limiteLeste = aMapData.getLimiteLeste();
        mapInfo.limiteNorte = aMapData.getLimiteNorte();
        mapInfo.limiteOeste = aMapData.getLimiteOeste();
        mapInfo.limiteSul = aMapData.getLimiteSul();
        mapInfo.horizontalMapSize = aMapData.getHorizontalMapSize();
        mapInfo.verticalMapSize = aMapData.getVerticalMapSize();
        stateChanged = true;            
   
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }



    

    
}
