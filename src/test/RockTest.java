package test;

import org.junit.Test;
import org.junit.Assert;
import domain.Rock;
import domain.Valley;
import java.awt.Color;

/**
 * Pruebas de unidad para la clase Rock.
 */
public class RockTest {
    
    /**
     * PRUEBA 1: Verifica la correcta inicialización de las rocas
     * y sus propiedades como unidad estática del valle.
     */
    @Test
    public void testRockInitializationAndProperties() {
        Valley valley = new Valley();
        int expectedRow = 5;
        int expectedColumn = 5;
        Color expectedColor = new Color(100, 100, 100);
        Rock rock = new Rock(valley, expectedRow, expectedColumn);
        Assert.assertEquals("La roca debe crearse en la fila correcta", 
                           expectedRow, rock.getRow());
        Assert.assertEquals("La roca debe crearse en la columna correcta", 
                           expectedColumn, rock.getColumn());
        Assert.assertEquals("La roca debe tener color gris oscuro", 
                           expectedColor, rock.getColor());
        Assert.assertEquals("La roca debe tener forma cuadrada", 
                           domain.Unit.SQUARE, rock.shape());
        Assert.assertTrue("La roca debe ser considerada recurso", 
                         rock.isResource());
        Assert.assertFalse("La roca NO debe ser animal", 
                          rock.isAnimal());
        Assert.assertEquals("La roca debe estar registrada en el valle", 
                           rock, valley.getUnit(expectedRow, expectedColumn));
    }
    
    /**
     * PRUEBA 2: Verifica el comportamiento estático de las rocas.
     * Las rocas no deben moverse ni cambiar su estado durante la simulación.
     */
    @Test
    public void testRockRemainsStaticDuringAct() {
        Valley valley = new Valley();
        int initialRow = 10;
        int initialColumn = 10;
        Rock rock = new Rock(valley, initialRow, initialColumn);
        for (int i = 0; i < 10; i++) {
            rock.act(); 
        }
        Assert.assertEquals("La roca debe mantener su posición después de act()", 
                           initialRow, rock.getRow());
        Assert.assertEquals("La roca debe mantener su columna después de act()", 
                           initialColumn, rock.getColumn());
        Assert.assertEquals("La roca debe permanecer en el valle en la misma posición", 
                           rock, valley.getUnit(initialRow, initialColumn));
    }
}
