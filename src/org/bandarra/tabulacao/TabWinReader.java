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
        String destino = origem.replaceAll("�","a");
         destino = destino.replaceAll("�","a");
         destino = destino.replaceAll("�","a");
         destino = destino.replaceAll("�","a");
         destino = destino.replaceAll("�","e");
         destino = destino.replaceAll("�","e");
         destino = destino.replaceAll("�","e");         
         destino = destino.replaceAll("�","i");         
         destino = destino.replaceAll("�","i");
         destino = destino.replaceAll("�","i");
         destino = destino.replaceAll("�","o");         
         destino = destino.replaceAll("�","o");
         destino = destino.replaceAll("�","o");
         destino = destino.replaceAll("�","o");
         destino = destino.replaceAll("�","u");
         destino = destino.replaceAll("�","u");         
         destino = destino.replaceAll("�","u");
         destino = destino.replaceAll("�","c");        
         destino = destino.replaceAll("�","A");        
         destino = destino.replaceAll("�","A");        
         destino = destino.replaceAll("�","A");        
         destino = destino.replaceAll("�","A");        
         destino = destino.replaceAll("�","E");        
         destino = destino.replaceAll("�","E");        
         destino = destino.replaceAll("�","E");        
         destino = destino.replaceAll("�","I");        
         destino = destino.replaceAll("�","I");        
         destino = destino.replaceAll("�","I");        
         destino = destino.replaceAll("�","O");        
         destino = destino.replaceAll("�","O");        
         destino = destino.replaceAll("�","O");                 
         destino = destino.replaceAll("�","O");        
         destino = destino.replaceAll("�","U");        
         destino = destino.replaceAll("�","U");                 
         destino = destino.replaceAll("�","U");        
         destino = destino.replaceAll("�","C");       
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
