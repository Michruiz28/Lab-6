
package domain;
import java.awt.Color;
import java.io.Serializable;

public class Wolf extends Mammal implements Serializable {
    
    public Wolf(Valley valley,int row, int column){
        super(valley,row, column);
        color=Color.black;
    }
    
    public int getShape(){
        return Unit.ROUND;
    }
    
    public void act(){
        if (getEnergy()==0){
            die();
        }else{
           if (! move(row+ (int)(Math.random() * 3) - 1,column+ (int)(Math.random() * 3) - 1)){
              move(row+ (int)(Math.random() * 3) - 1,column+ (int)(Math.random() * 3) - 1); 
           }    
        }
    }

    @Override
    public String getTipoExportacion(){
        return "Wolf";
    }
}
