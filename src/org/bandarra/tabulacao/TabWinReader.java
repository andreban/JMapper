/*
 * TabWinReader.java
 *
 * Created on 20 de Fevereiro de 2006, 08:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bandarra.tabulacao;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author andre.bandarra
 */

public class TabWinReader implements TabReader {
    
    /** Creates a new instance of TabWinReader */
    private TabWinData tabData;
    private String retiraAcentos(String origem){
        String destino = origem.replaceAll("á","a");
         destino = destino.replaceAll("ã","a");
         destino = destino.replaceAll("à","a");
         destino = destino.replaceAll("â","a");
         destino = destino.replaceAll("é","e");
         destino = destino.replaceAll("è","e");
         destino = destino.replaceAll("ê","e");         
         destino = destino.replaceAll("î","i");         
         destino = destino.replaceAll("í","i");
         destino = destino.replaceAll("ì","i");
         destino = destino.replaceAll("ô","o");         
         destino = destino.replaceAll("ó","o");
         destino = destino.replaceAll("ò","o");
         destino = destino.replaceAll("õ","o");
         destino = destino.replaceAll("ú","u");
         destino = destino.replaceAll("û","u");         
         destino = destino.replaceAll("ù","u");
         destino = destino.replaceAll("ç","c");        
         destino = destino.replaceAll("Ã","A");        
         destino = destino.replaceAll("Á","A");        
         destino = destino.replaceAll("À","A");        
         destino = destino.replaceAll("Â","A");        
         destino = destino.replaceAll("É","E");        
         destino = destino.replaceAll("È","E");        
         destino = destino.replaceAll("Ê","E");        
         destino = destino.replaceAll("Í","I");        
         destino = destino.replaceAll("Ì","I");        
         destino = destino.replaceAll("Î","I");        
         destino = destino.replaceAll("Õ","O");        
         destino = destino.replaceAll("Ó","O");        
         destino = destino.replaceAll("Ò","O");                 
         destino = destino.replaceAll("Ô","O");        
         destino = destino.replaceAll("Ú","U");        
         destino = destino.replaceAll("Ù","U");                 
         destino = destino.replaceAll("Û","U");        
         destino = destino.replaceAll("Ç","C");       
         return destino;
        
    }    
    public TabWinReader(BufferedReader bf) throws IOException {
        String line = null;                    
        tabData = new TabWinData();
        while ((line = bf.readLine()) != null && 
                !line.substring(0,1).equalsIgnoreCase("\"")){
            if (line.startsWith("Titulo1")){
                tabData.setTitulo1(line.split("=")[1]);
                
            }
            if (line.startsWith("Titulo2")){
                tabData.setTitulo1(line.split("=")[1]);                
            }            
                
        }
        
        String[] nomeColunas = line.split(";");  
        /*for (String s: nomeColunas){
            System.out.println(s);
        }*/
        
        for (int i = 0 ; i < nomeColunas.length ; i++){
            nomeColunas[i] = retiraAcentos(nomeColunas[i].replaceAll("\"",""));
        }        
        String[] nomeColuna2 = new String[nomeColunas.length-1];

        System.arraycopy(nomeColunas,1,nomeColuna2,0,nomeColunas.length-1);
        
        tabData.setNomesColunas(nomeColuna2);
        
        NumberFormat df = DecimalFormat.getInstance(Locale.FRANCE);        
        
        int ctlinhas = 0;        
        while ((line = bf.readLine()) != null){
            String[] dados = line.split(";");          
            int spacePos = line.indexOf(" ");
            if (dados[0].length() > spacePos && spacePos >= 0){
                
                tabData.codigoAreas.add(dados[0].substring(1,spacePos));
                tabData.nomesAreas.add(dados[0].substring(spacePos, dados[0].length()-1));
                
                float[] valores = new float[dados.length-1];
                       
                for (int i = 1 ; i < dados.length ; i++){
                    Number n = null;
                    try{
                        n  = df.parse(dados[i]);
                    } catch (java.text.ParseException pe){
                        pe.printStackTrace();
                    } 
                    valores[i-1] = n.floatValue();
                }                                                        
                tabData.valores.add(valores);
            }                                            
        }        
    }
   
    public TabData getTabData() {
        return tabData;
    }
    
    public static void main(String[] args){
        try{
            FileInputStream fis = new FileInputStream("C:/Andre/JMapper/DD_ES.tab");
            BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
            TabWinReader twr = new TabWinReader(bf);
            TabData td = twr.getTabData();
            int i = 0;
            for (String s :td.getNomesAreas()){
                System.out.println(s);
                System.out.println(td.getValorArea(i,0));
                System.out.println(td.getCodigoAreas()[i]);
                i++;
            }
            for (float f: td.getValoresColuna(0)){
                System.out.println(f);
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
    
}
