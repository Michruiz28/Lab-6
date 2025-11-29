package domain;
import java.awt.Color;
import java.io.Serializable;

/**
 * Representa un oso en el valle
 * Los osos son territoriales, se mueven lentamente hacia el sur.
 * Ganan energía con osos vecinos y pierden energía con lobos vecinos (pelean)
 */
public class Bear extends Mammal implements Serializable {
    private int turnosSinMoverse;
    private static final Color BROWN = new Color(139, 69, 19);
    
    /**
     * Crea un nuevo oso en la posición indicada
     * @param valley El valle donde habita
     * @param row Fila inicial
     * @param column Columna inicial
     */
    public Bear(Valley valley, int row, int column){
        super(valley, row, column);
        color = BROWN;
        turnosSinMoverse = 0;
        valley.setUnit(row, column, this);
    }
    
    public int shape(){
        return Unit.ROUND;
    }
    
    public void act(){
        if(getEnergy() == 0){
            die();
            return;
        }
        
        turnosSinMoverse++;
        
        if(turnosSinMoverse >= 2){
            turnosSinMoverse = 0;
            
            int newRow = row + 1;
            if(newRow >= 25) newRow = 0;
            
            move(newRow, column);
        } else {
            step();
        }
        
        checkNeighbors();
    }
    
    /**
     * Verifica los vecinos y ajusta la energía según las interacciones
     * - Osos vecinos: +2 energía por cada uno (se sienten seguros)
     * - Lobos vecinos: -3 energía por cada uno (pelean)
     */
    private void checkNeighbors(){
        Valley valley = getValley();
        
        int ososVecinos = valley.neighborsEquals(row, column);
        if(ososVecinos > 0){
            int bearEnergy = this.getEnergy();
            this.setEnergy(bearEnergy + 2 * ososVecinos);
        }
        
        int lobosVecinos = countWolfNeighbors();
        if(lobosVecinos > 0){
            int bearEnergy = this.getEnergy();
            this.setEnergy(bearEnergy - 3 * lobosVecinos); 
            
            if(this.getEnergy() <= 0){
                this.setEnergy(0);
                die();
            }
        }
    }  
    
    /**
     * Cuenta cuántos lobos hay en las casillas vecinas
     * @return Número de lobos vecinos
     */
    private int countWolfNeighbors(){
        Valley valley = getValley();
        int count = 0;
        
        for(int dr = -1; dr <= 1; dr++){
            for(int dc = -1; dc <= 1; dc++){
                if(dr == 0 && dc == 0) continue; 
                
                int newR = row + dr;
                int newC = column + dc;
                
                if(newR >= 0 && newR < 25 && newC >= 0 && newC < 25){
                    Unit neighbor = valley.getUnit(newR, newC);
                    
                    if(neighbor instanceof Wolf){
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    @Override
    public String getTipoExportacion(){
        return "Bear";
    }

}