package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import domain.*;
import java.awt.Color;

/**
 * Pruebas unitarias para la clase Hay
 */
public class HayTest{
    private Valley valley;
    
    @Before
    public void setUp(){
        valley = new Valley();
        // Limpiar el valle
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
     * El heno debe alternar colores al crearse
     * GIVEN: Un valle vacío
     * WHEN: Se crean dos paquetes de heno consecutivos
     * THEN: Deben tener colores diferentes (alternados)
     */
    @Test
    public void shouldAlternateColorsWhenCreated(){
        Hay primerHeno = new Hay(valley, 5, 5);
        Hay segundoHeno = new Hay(valley, 6, 6);
        
        Color colorPrimero = primerHeno.getColor();
        Color colorSegundo = segundoHeno.getColor();
        
        assertFalse(colorPrimero.equals(colorSegundo));
    }
    
    /**
     * El heno debe cambiar de color al actuar
     * GIVEN: Un paquete de heno en el valle
     * WHEN: El heno actúa (act)
     * THEN: Debe cambiar de color
     */
    @Test
    public void shouldChangeColorWhenActs(){
        Hay heno = new Hay(valley, 10, 10);
        Color colorInicial = heno.getColor();
        
        heno.act();
        Color colorDespuesDeActuar = heno.getColor();
        
        assertFalse(colorInicial.equals(colorDespuesDeActuar));
    }
    
    /**
     * El heno debe ser un recurso, no un animal
     * GIVEN: Un paquete de heno
     * WHEN: Se consulta si es recurso o animal
     * THEN: Debe ser recurso y no animal
     */
    @Test
    public void shouldBeResourceNotAnimal(){
        Hay heno = new Hay(valley, 7, 7);

        assertTrue(heno.isResource());
        assertFalse(heno.isAnimal());
    }
}
