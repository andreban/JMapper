package org.bandarra.jmapper;

import org.bandarra.map.*;
import org.bandarra.tabulacao.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public interface MapDrawer {
    public void setSize(int width, int height);
    public int getWidth();
    public int getHeight();
    public void setMapData(Data aMapData);
    public void setColorChooser(AreaColorChooser colorChooser);
    public void addMapLayer(MapLayer aMapLayer);
    public void removeMapLayer(MapLayer aMapLayer);
    public void drawMap(Graphics g);
    public void drawMap(Graphics g, AreaColorChooser colorChooser);    
    public void zoom(Rectangle rect);
    public void pan(int xDistancia, int yDistancia);   
    public void reset();
    public boolean stateChanged();
}
