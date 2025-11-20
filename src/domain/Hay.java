package domain;
import java.awt.Color;

/**
 * Se define la clase Heno.
 * 
 * @author Michelle Dayana Carranza Ruíz y Maria Katalina Leyva Diaz 
 * @version 18.10.2025
 */
public class Hay implements Unit{
    private Valley valley;
    private int row, column;
    private Color color;
    
    /**
     * Se inicializa un objeto Heno.
     */
    public Hay(Valley valley, int row, int column){
        this.valley = valley;
        this.row = row;
        this.column = column;
        this.color = valley.getNextHayColor();
        valley.setUnit(row, column, this);
    }
    
    /**
     * Actúa para cada tic cambiando de color, alternando entre amarillo y rojo
     */
    public void act(){
        if(color.equals(Color.RED)){
            color = Color.YELLOW;
        } else {
            color = Color.RED;
        }
    }
    
    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return column;
    }
    
    public Color getColor(){
        return color;
    }
    
    public int shape(){
        return Unit.SQUARE;
    }
    
    public int getShape(){
        return Unit.SQUARE;
    }
    
    public boolean isResource(){
        return true;
    }
    
    public boolean isAnimal(){
        return false;
    }
}