package org.bandarra.jmapper;
import java.awt.*;
import java.text.DecimalFormat;

/**
 *
 * @author andre.bandarra
 */
public class RangesDrawer {
    
    private TabDataColorChooser tabDataColorChooser;
    private Rectangle sizeRectangle;//retangulo que determina os limits a serem desenhados
    private DecimalFormat dc = new DecimalFormat("#,###.####");    
    public RangesDrawer(TabDataColorChooser tabDataColorChooser) {
        this.tabDataColorChooser = tabDataColorChooser;
    }
    public void setSize(Rectangle sizeRectangle){
        this.sizeRectangle = sizeRectangle;
    }
    public void draw(Graphics g){
        int margem = sizeRectangle.height / 40;
        g.setColor(new Color(1.0f,1.0f,1.0f,0.6f));
        g.fillRoundRect(sizeRectangle.x,sizeRectangle.y,sizeRectangle.width,
                margem*4* tabDataColorChooser.getArrayValues().size()+4,20,20);        
        for (int i=0; i<tabDataColorChooser.getArrayValues().size(); i++){

            Color c = tabDataColorChooser.getArrayValues().get(i).color;
            g.setColor(c);
            g.fillRoundRect(sizeRectangle.x+2,(margem*4)*i+margem,sizeRectangle.width/5,margem*3,10,10);
            g.setColor(Color.BLACK);
            g.drawRoundRect(sizeRectangle.x+2,(margem*4)*i+margem,sizeRectangle.width/5,margem*3,10,10);            
            g.setFont(new Font("Default",Font.BOLD,10));
            g.drawString(dc.format(tabDataColorChooser.getArrayValues().get(i).minValue)
                    +" - "+dc.format(tabDataColorChooser.getArrayValues().get(i).maxValue)
                    ,sizeRectangle.x+2+sizeRectangle.width/5+5,(margem*4)*i+margem*3);
        }
        
    }
    
}
