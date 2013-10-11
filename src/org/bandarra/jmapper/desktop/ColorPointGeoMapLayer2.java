package org.bandarra.jmapper.desktop;

import org.bandarra.jmapper.MapDrawer;
import org.bandarra.jmapper.MapLayer;
import org.bandarra.map.Coordenada;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andreban
 */
public class ColorPointGeoMapLayer2 implements MapLayer {
    private List<Color> colors = new ArrayList<>();
    private List<Coordenada> coordinates = new ArrayList<>();
    private List<Coordenada> vectors = new ArrayList<>();

    public ColorPointGeoMapLayer2() {

    }

    public void addCoordinate(Color color, Coordenada coordenada, Coordenada vector) {
        colors.add(color);
        coordinates.add(coordenada);
        vectors.add(vector);

    }
    
    @Override
    public void drawLayer(MapDrawer drawer, Graphics g) {
        for (int i = 0; i < colors.size(); i++) {
            Color c = colors.get(i);
            Coordenada co = coordinates.get(i);
            Coordenada vec = vectors.get(i);

            Point pc = drawer.mapToPanel(co);
            Point pv = drawer.mapToPanel(vec);

            int r = (int)Math.sqrt(Math.pow(pc.getX() - pv.getX(), 2) + Math.pow(pc.getY() - pv.getY(), 2));

            g.setColor(c);
            g.fillOval(pc.x-r, pc.y-r, 2 * r, 2 * r);

            g.setColor(Color.BLACK);
            g.fillOval(pc.x-2, pc.y-2, 4, 4);
        }

    }
    
}
