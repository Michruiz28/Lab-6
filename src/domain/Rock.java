package domain;
import java.awt.Color;

/**
 * Esta clase roca representa una roca en el valle
 * Las rocas son elementos del terreno que hacen lo siguiente:
 *    - No se mueven
 *    - Bloquean el paso de los animales
 *    - Son recursos no consumibles
 */
public class Rock implements Unit {
    private static final Color ROCK_COLOR = new Color(100, 100, 100);
    private int row;
    private int column;
    private Valley valley;
    
    /**
     * Crea una nueva roca en la posición especificada.
     * 
     * @param valley El valle donde se ubicará la roca
     * @param row La fila de posición
     * @param column La columna de posición
     */
    public Rock(Valley valley, int row, int column) {
        this.valley = valley;
        this.row = row;
        this.column = column;
        valley.setUnit(row, column, this);
    }
    
    /**
     * Las rocas no realizan ninguna acción ya que son estaticas
     */
    @Override
    public void act() {
    }
    
    /**
     * Retorna la forma cuadrada de la roca. 
     * @return Unit.SQUARE - forma rectangular
     */
    @Override
    public int shape() {
        return Unit.SQUARE;
    }
    
    /**
     * Retorna el color gris oscuro de la roca.
     * @return Color gris oscuro 
     */
    @Override
    public Color getColor() {
        return ROCK_COLOR;
    }
    
    /**
     * Las rocas son consideradas recursos.
     * @return true - las rocas son recursos del terreno
     */
    @Override
    public boolean isResource() {
        return true;
    }
    
    /**
     * Las rocas no son animales.
     * @return false - las rocas no son animales
     */
    @Override
    public boolean isAnimal() {
        return false;
    }
    
    /**
     * Obtiene la fila actual de la roca.
     * @return La fila de la roca
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Obtiene la columna actual de la roca.
     * @return La columna de la roca
     */
    public int getColumn() {
        return column;
    }
    
}