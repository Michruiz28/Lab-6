package test;
import org.junit.Test;
import org.junit.Assert;
import domain.Sheep;
import domain.Valley;
import domain.Wolf;
import java.awt.Color;

public class SheepTest {
    
    @Test
    public void testSheepInitialization() {
        Valley valley = new Valley();
        Sheep sheep = new Sheep(valley, 10, 10);
        Assert.assertNotNull("La oveja no debería ser null", sheep);
        Assert.assertEquals("Fila incorrecta", 10, sheep.getRow());
        Assert.assertEquals("Columna incorrecta", 10, sheep.getColumn());
        Assert.assertTrue("Debería comenzar moviéndose al norte", sheep.isMovingNorth());
        Assert.assertEquals("Debería estar en el valle", sheep, valley.getUnit(10, 10));
    }
    
    @Test
    public void testSheepColorAndShape() {
        Valley valley = new Valley();
        Sheep sheep = new Sheep(valley, 5, 5);
        
        Color expectedColor = new Color(200, 200, 200);
        Assert.assertEquals("Color debería ser gris claro", expectedColor, sheep.getColor());
        Assert.assertEquals("Forma debería ser cuadrada", domain.Unit.SQUARE, sheep.shape());
    }
    
    @Test
    public void testSheepMovementNorth() {
        Valley valley = new Valley();
        Sheep sheep = new Sheep(valley, 10, 10);
        sheep.act();
        Assert.assertEquals("Debería haberse movido al norte", 9, sheep.getRow());
        Assert.assertEquals("No debería cambiar de columna", 10, sheep.getColumn());
        Assert.assertTrue("Debería mantener dirección norte", sheep.isMovingNorth());
    }
    
    @Test
    public void testSheepMovementSouth() {
        Valley valley = new Valley();
        Sheep sheep = new Sheep(valley, 10, 10);
        sheep.act();
        Sheep sheepAtNorth = new Sheep(valley, 0, 10);
        sheepAtNorth.act();
        
        Assert.assertFalse("Debería cambiar a dirección sur al llegar al borde norte", 
                          sheepAtNorth.isMovingNorth());
    }
    
    @Test
    public void testSheepDirectionChangeAtNorthBorder() {
        Valley valley = new Valley();
        Sheep sheep = new Sheep(valley, 0, 10);
        sheep.act();
        Assert.assertFalse("Debería cambiar a sur al llegar al borde norte", 
                          sheep.isMovingNorth());
    }
    
    @Test
    public void testSheepDirectionChangeAtSouthBorder() {
        Valley valley = new Valley();
        Sheep sheep = new Sheep(valley, 24, 10); 
        sheep.act(); 
        Sheep sheepAtSouth = new Sheep(valley, 24, 10);
        for (int i = 0; i < 2; i++) {
            sheepAtSouth.act();
        }
        Assert.assertTrue("Debería cambiar a norte al llegar al borde sur", 
                         sheepAtSouth.isMovingNorth());
    }
    
    @Test
    public void testSheepDiesWhenNextToWolf() {
        Valley valley = new Valley();
        Sheep sheep = new Sheep(valley, 10, 10);
        Wolf wolf = new Wolf(valley, 10, 11);

        Assert.assertNotNull("Oveja debería existir inicialmente", valley.getUnit(10, 10));
        Assert.assertNotNull("Lobo debería existir inicialmente", valley.getUnit(10, 11));
        sheep.act();
        Assert.assertNull("Oveja debería morir cuando está junto a un lobo", 
                         valley.getUnit(10, 10));
    }
    
    @Test
    public void testSheepEnergyDecreasesOnMove() {
        Valley valley = new Valley();
        Sheep sheep = new Sheep(valley, 10, 10);
        int initialEnergy = sheep.getEnergy();
        sheep.act();
        int finalEnergy = sheep.getEnergy();
        Assert.assertTrue("La energía debería disminuir después de moverse", 
                         finalEnergy < initialEnergy);
    }
    
    @Test
    public void testSheepDiesWhenNoEnergy() {
        Valley valley = new Valley();
        Sheep sheep = new Sheep(valley, 10, 10);
     for (int i = 0; i < 105; i++) { 
            if (valley.getUnit(sheep.getRow(), sheep.getColumn()) == null) {
                break;
            }
            sheep.act();
        }
        Assert.assertNull("Oveja debería morir cuando se queda sin energía", 
                         valley.getUnit(sheep.getRow(), sheep.getColumn()));
    }
    
    @Test
    public void testIsNextToSheepDetection() {

        Valley valley = new Valley();
        Sheep sheep1 = new Sheep(valley, 10, 10);
        Sheep sheep2 = new Sheep(valley, 10, 11);
    }
}
