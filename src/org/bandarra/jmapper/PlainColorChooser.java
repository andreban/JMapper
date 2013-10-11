package org.bandarra.jmapper;

import java.awt.Color;

/**
 *
 * @author andreban
 */
public class PlainColorChooser implements AreaColorChooser {
    private Color color;
    
    public PlainColorChooser() {
        this(Color.WHITE);
    }
    public PlainColorChooser(Color color) {
        this.color = color;
    }
    
    @Override
    public Color getColor(String nomeArea) {
        return color;
    }
    
}
