package domain;
import java.awt.Color;
/**
 * La clase oveja representa al animal en la simulacion
 * Este animal es una presa que se caracteriza por:
 *     -Moverse verticalmente (norte-sur)
 *     -Es de color gris claro y forma rectangular
 *     -Muere instantaneamente al detectar un lobo vecino
 *     -Consume energia con cada movimiento
 */
public class Sheep extends Mammal {
    private boolean movingNorth;
    private static final Color SHEEP_COLOR = new Color(200, 200, 200);
    
    public Sheep(Valley valley, int row, int column) {
        super(valley, row, column);
        this.color = SHEEP_COLOR; 
        this.movingNorth = true;
    }
    
    @Override
    public void act() {
        if (isNextToWolf()) {
            die();
            return;
        }
        if (getEnergy() <= 0) {
            die();
            return;
        }
        int newRow = movingNorth ? getRow() - 1 : getRow() + 1;
        int newColumn = getColumn();
        if (newRow < 0) {
            movingNorth = false;
            return;
        }
        if (newRow >= getValley().getSize()) {
            movingNorth = true;
            return;
        }
        move(newRow, newColumn);
    }
    
    @Override
    public int shape() {
        return Unit.SQUARE;
    }
    
    /**
     * Verifica si la oveja tiene lobos vecinos examinando las 8 celdas alrededor
     */
    private boolean isNextToWolf() {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                
                int checkRow = getRow() + dr;
                int checkCol = getColumn() + dc;
                
                if (checkRow >= 0 && checkRow < getValley().getSize() && 
                    checkCol >= 0 && checkCol < getValley().getSize()) {
                    
                    Unit neighbor = getValley().getUnit(checkRow, checkCol);
                    if (neighbor instanceof Wolf) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Verifica si la oveja tiene ovejas vecinas examinando las 8 celdas alrededor
     */
    private boolean isNextToSheep() {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                
                int checkRow = getRow() + dr;
                int checkCol = getColumn() + dc;
                
                if (checkRow >= 0 && checkRow < getValley().getSize() && 
                    checkCol >= 0 && checkCol < getValley().getSize()) {
                    
                    Unit neighbor = getValley().getUnit(checkRow, checkCol);
                    if (neighbor instanceof Sheep && neighbor != this) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isMovingNorth() {
        return movingNorth;
    }
    @Override
    public String getTipoExportacion(){
        return "Sheep";
    }

}