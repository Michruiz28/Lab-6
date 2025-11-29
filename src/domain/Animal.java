package domain;
import java.awt.Color;
import java.io.Serializable;


public abstract class Animal implements Serializable {
    
    private int days;
    private int energy;

    /**Create a new animal
     * 
     */
    public Animal(){
        energy=100;
        days=0;
    }

    /**The animal makes one step
     * 
     */
    final boolean step(){
        boolean ok=false;
        if (energy>=1){
            energy-=1;
            ok=true;
        }
        return ok;
    }    
    
    /**The animal eats
     * 
     */
    protected void eat(){
        energy=100;
    }
    
     /**Returns the energy
    @return 
     */   
    public final int getEnergy(){
        return energy;
    }    

    /**Incrementa la energía
     * @return void
     */
    public void setEnergy(int newEnergy){
        this.energy = newEnergy;
    }
    
    /**It's an animal
     */
    public final boolean isAnimal(){
        return true;
    }  
    
}
