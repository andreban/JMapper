package org.bandarra.tabulacao;

public interface TabData {
    public String getNomeMapa();
    public String getTitulo1();
    public String getTitulo2();
    public String getRodape();
    public String[] getNomeColunas();
    public String[] getCodigoAreas();
    public String[] getNomesAreas();
    public int getIdxArea(String nomeArea);
    public float getValorArea(int idxArea, int idxColuna);       
    public float getValorArea(String nomeArea, String nomeColuna);
    public float[] getValoresColuna(int idxColuna);
    public float[] getValoresColuna(String coluna);
}
