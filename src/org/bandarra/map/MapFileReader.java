/*
 * MapFileReader.java
 *
 * Created on 17 de Fevereiro de 2006, 15:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.map;
import java.io.*;
import java.util.Iterator;
/**
 *
 * @author andre.bandarra
 */
public class MapFileReader implements MapReader {
    private MapData mapa = new MapData();
    private DataInputStream dataIn;    

    public Data getData() {
        return mapa;
    }
    /** Creates a new instance of MapFileReader */
    private Area readArea() throws IOException{
        MapArea a = new MapArea();
        a.setTipoObj(dataIn.readByte());
        
        //ser Codigo
        byte strLen = dataIn.readByte();        
        byte[] bytesStr = new byte[strLen];        
        dataIn.read(bytesStr);        
        a.setCodigo(new String(bytesStr));
        dataIn.skipBytes(10-strLen);
        
        strLen = dataIn.readByte();
        bytesStr = new byte[strLen];
        dataIn.read(bytesStr);
        a.setNome(new String(bytesStr));
        dataIn.skipBytes(25-strLen);        
        
        MapCoordenada mc = new MapCoordenada();
        mc.setPontoX(Float.intBitsToFloat(Integer.reverseBytes(dataIn.readInt())));
        mc.setPontoY(Float.intBitsToFloat(Integer.reverseBytes(dataIn.readInt())));
        a.setCoordenadaCentral(mc);
        
        short qtdCoordenadas = Short.reverseBytes(dataIn.readShort());
        
        for (int i=0; i < qtdCoordenadas; i++ ){
            
            a.addCoordenada(
                    new MapCoordenada(Float.intBitsToFloat(Integer.reverseBytes(dataIn.readInt())),
                    Float.intBitsToFloat(Integer.reverseBytes(dataIn.readInt()))));
        }
        
        return a;
    }
    
    public MapFileReader(DataInputStream newDataIn) throws IOException {
        dataIn = newDataIn;
        mapa.setVersao(Short.reverseBytes(newDataIn.readShort()));
        mapa.setLimiteLeste(Float.intBitsToFloat(Integer.reverseBytes(newDataIn.readInt())));
        mapa.setLimiteNorte(Float.intBitsToFloat(Integer.reverseBytes(newDataIn.readInt())));
        mapa.setLimiteOeste(Float.intBitsToFloat(Integer.reverseBytes(newDataIn.readInt())));
        mapa.setLimiteSul(Float.intBitsToFloat(Integer.reverseBytes(newDataIn.readInt()))); 
        while (dataIn.available() > 0){
            mapa.addArea(readArea());
        }
    }
    
    public static void main(String[] args){
        try{
            FileInputStream fisMap = new FileInputStream("C:\\Andre\\JMapper\\rn.map");
            DataInputStream dis = new DataInputStream(fisMap);
            MapFileReader mfr = new MapFileReader(dis);
            Data d = mfr.getData();
            System.out.println(d.getLimiteLeste());
            System.out.println(d.getLimiteNorte());
            System.out.println(d.getLimiteSul());
            System.out.println(d.getLimiteOeste());
            System.out.println(d.getHorizontalMapSize());
            System.out.println(d.getVerticalMapSize()); 
            Iterator<Area> i = d.getAreaIterator();
            while (i.hasNext()){
                Area a = i.next();
                System.out.println(a.getNome());
                Iterator<Coordenada> ic = a.getCoordenadaIterator();
                while (ic.hasNext()){
                    Coordenada c = ic.next();
                    System.out.println(c);
                }
            }

        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        
    }
    
}
