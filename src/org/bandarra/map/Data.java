package org.bandarra.map;

import java.util.Iterator;

public interface Data {
    public float getHorizontalMapSize();
    public float getVerticalMapSize();
    public float getLimiteLeste();
    public float getLimiteOeste();
    public float getLimiteNorte();
    public float getLimiteSul();
    public Coordenada getCentro();
    public Iterator<Area> getAreaIterator();
         
}
