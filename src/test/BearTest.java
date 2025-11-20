package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.*;

/**
 * Pruebas unitarias para la clase Bear
 */
public class BearTest{
    private Valley valley;
    
    @Before
    public void setUp(){
        valley = new Valley();
        for(int r = 0; r < 25; r++){
            for(int c = 0; c < 25; c++){
                valley.setUnit(r, c, null);
            }
        }
    }
    
    @After
    public void tearDown(){
    }
    
    /**
     * Prueba 1: El oso debe moverse solo cada 2 tic-tacs
     * GIVEN: Un oso en posición (10,10)
     * WHEN: Actúa una vez
     * THEN: No debe moverse (solo pierde energía)
     * WHEN: Actúa por segunda vez
     * THEN: Debe moverse hacia el sur (fila 11)
     */
    @Test
    public void shouldMoveEveryTwoTurns(){
        Bear oso = new Bear(valley, 10, 10);
        int filaInicial = oso.getRow();
        int columnaInicial = oso.getColumn();
        
        oso.act();
        
        assertEquals(filaInicial, oso.getRow());
        assertEquals(columnaInicial, oso.getColumn());
        
        oso.act();
        
        assertEquals(filaInicial + 1, oso.getRow());
        assertEquals(columnaInicial, oso.getColumn());
    }
    
    /**
     * Prueba 2: El oso debe ganar energía cuando tiene osos vecinos
     * GIVEN: Dos osos vecinos en el valle
     * WHEN: Uno de los osos actúa
     * THEN: Debe ganar 2 puntos de energía por tener un vecino oso
     */
    @Test
    public void shouldGainEnergyWithBearNeighbors(){
        Bear oso1 = new Bear(valley, 5, 5);
        Bear oso2 = new Bear(valley, 5, 6); 
        
        int energiaInicial = oso1.getEnergy();
        
        oso1.act(); 
        
        int energiaEsperada = energiaInicial - 1 + 2; 
        assertEquals("Debe ganar 2 puntos por vecino oso (menos 1 por step)", 
                     energiaEsperada, oso1.getEnergy());
    }
    
    /** ESCENARIO: Un oso experimenta diferentes situaciones durante su vida en el valle:
     * 1. Está solo (pierde energía normal)
     * 2. Encuentra otro oso (gana energía)
     * 3. Pelea con un lobo (pierde energía extra)
     * 4. Se reúne con múltiples osos (gana mucha energía)
     * 
     * Se verifica que:
     * - Pierde energía cuando está solo
     * - Gana energía cuando está con osos
     * - Pierde energía extra cuando pelea con lobos
     * - Se mueve correctamente cada 2 turnos
     * - Sobrevive todo el ciclo
     */
    @Test
    public void shouldExperienceCompleteLifeCycleInValley(){
        // ========== FASE 1: OSO SOLO ==========
        Bear ruiz = new Bear(valley, 5, 12);
        int energiaInicial = ruiz.getEnergy();
        
        ruiz.act(); 
        ruiz.act(); 
        assertEquals(6, ruiz.getRow());
        assertEquals(energiaInicial - 2, ruiz.getEnergy());
        
        int energiaDespuesFase1 = ruiz.getEnergy();
        
        
        // ========== FASE 2: CON OTRO OSO ==========
        Bear leyva = new Bear(valley, 6, 13); 
        
        ruiz.act(); 
        
        assertEquals(energiaDespuesFase1 + 1, ruiz.getEnergy());
        assertEquals(6, ruiz.getRow());
    
        
        int energiaDespuesFase2 = ruiz.getEnergy();
        
        
        // ========== FASE 3: PELEA CON LOBO ==========
        valley.setUnit(6, 13, null);
        
        Wolf lobo = new Wolf(valley, 6, 11); 
 
        ruiz.act(); 
        ruiz.act();
        
        valley.setUnit(6, 11, null); 
        
        int filaActualruiz = ruiz.getRow();
        Wolf loboNuevo = new Wolf(valley, filaActualruiz, 11); 
        
        ruiz.act(); 
        
        int energiaDespuesTurno6 = ruiz.getEnergy();
        
        assertTrue(energiaDespuesTurno6 < energiaDespuesFase2);
        
        assertTrue(ruiz.getEnergy() > 0);
        
        int energiaDespuesFase3 = ruiz.getEnergy();
    
        
        // ========== FASE 4: CON MÚLTIPLES OSOS (MANADA) ==========
        valley.setUnit(filaActualruiz, 11, null);
        
        int filaActual = ruiz.getRow();
        int colActual = ruiz.getColumn();
        
        Bear oso2 = new Bear(valley, filaActual, colActual + 1); 
        Bear oso3 = new Bear(valley, filaActual + 1, colActual); 
        
        ruiz.act(); 
        
        int energiaFinal = ruiz.getEnergy();
    
        assertTrue("Debe ganar energía con múltiples osos vecinos", 
               energiaFinal > energiaDespuesFase3);
    
        int ganancia = energiaFinal - energiaDespuesFase3;
        assertEquals("Debe ganar 3 puntos netos (-1 step + 4 por 2 osos)", 
                 3, ganancia);
    }
}

