package org.bandarra.jmapper.desktop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bandarra.jmapper.MapDrawer;
import org.bandarra.jmapper.MapLayer;
import org.bandarra.map.Coordenada;

/**
 *
 * @author andreban
 */
public class ColorPointGeoMapLayer implements MapLayer {
    private Map<Color, List<Coordenada>> coordinateMap = new HashMap<>();
    
    public ColorPointGeoMapLayer() {

    }
    
    public void addCoordinate(Color color, Coordenada coordenada) {
        List<Coordenada> coordenadas = coordinateMap.get(color);
        if (coordenadas == null) {
            coordenadas = new ArrayList<>();
            coordinateMap.put(color, coordenadas);
        }
        coordenadas.add(coordenada);
    }
    
    @Override
    public void drawLayer(MapDrawer drawer, Graphics g) {
        for (Color c: coordinateMap.keySet()) {
            g.setColor(c);
            for (Coordenada co: coordinateMap.get(c)) {
                Point p = drawer.mapToPanel(co);
                g.drawOval(p.x, p.y, 1, 1);
            }
        }
    }
    
}
