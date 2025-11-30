package domain;
<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.io.File;
<<<<<<< HEAD
import java.io.Serializable;
=======
>>>>>>> 08ef52b (Controlador completado)
=======
import java.io.*;
>>>>>>> 7f67065 (Guardar ready)
import java.awt.Color;
>>>>>>> Mich
import java.io.*;
import java.awt.Color;

/**
 * La clase valle representa el valle donde ocurre la simulacion
 * Es una cuadricula bidimencional de un tamaño fijo donde habitan animales y recursos
 * La cuadricula es de 25x25 celdas
 */
<<<<<<< HEAD
public class Valley implements Serializable{
=======
<<<<<<< HEAD
public class Valley implements Serializable {
=======
public class Valley implements Serializable{
>>>>>>> 7f67065 (Guardar ready)
>>>>>>> Mich
    static private int SIZE=25;
    private Unit[][] places;
    private Color lastHayColor;
    
    public Valley() {
        places=new Unit[SIZE][SIZE];
        lastHayColor = Color.YELLOW;
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                places[r][c]=null;
            }
        }
        someUnits();
    }
    
    public Color getNextHayColor(){
        if(lastHayColor.equals(Color.YELLOW)){
            lastHayColor = Color.RED;
        } else {
            lastHayColor = Color.YELLOW;
        }
        return lastHayColor;        
    }
    
    public int  getSize(){
        return SIZE;
    }

    public Unit getUnit(int r,int c){
        return places[r][c];
    }

    public void setUnit(int r, int c, Unit e){
        places[r][c]=e;
    }

    public void someUnits(){   
        Wolf aleka = new Wolf(this, 10, 10);
        Wolf larka = new Wolf(this, 15, 15);
        setUnit(10,10, aleka);
        setUnit(15,15, larka);
        Sheep shaun = new Sheep(this, 3, 3);
        Sheep wooly = new Sheep(this, 17, 8);
        setUnit(3, 3, shaun);
        setUnit(17, 8, wooly);
        Hay alarm = new Hay(this, 0, 0);    
        Hay alert = new Hay(this, 0, SIZE-1);
        setUnit(0, 0, alarm);
        setUnit(0, SIZE-1, alert);
        Bear ruiz = new Bear(this, 5, 12);
        Bear leyva = new Bear(this, 6, 13); 
        setUnit(5, 12, ruiz);
        setUnit(6, 13, leyva);
        Rock Mich = new Rock(this,12,12);
        Rock Kata = new Rock(this,20,20);
        setUnit(12, 12, Mich);
        setUnit(20, 20, Kata);
    }
    
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inValley(r,c) && places[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inValley(r+dr,c+dc) && 
                    (places[r+dr][c+dc]!=null) &&  (places[r][c].getClass()==places[r+dr][c+dc].getClass())) num++;
                }
            }
        }
        return num;
    }
   
    public boolean isEmpty(int r, int c){
        return (inValley(r,c) && places[r][c]==null);
    }    
        
    private boolean inValley(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    /**
     * ticTac: Método tic tac que mueve las unidades del valle de forma aleatoria pero sigiendo el orden de izquierda a derecha y de norte a sur. No contiene parámetros.
     * @return void
     */
    public void ticTac(){
        boolean[][] hasActed = new boolean[SIZE][SIZE];
        
        for(int r = 0; r < SIZE; r++){
            for(int c = 0; c < SIZE; c++){
                Unit unit = getUnit(r, c);
                
                if (unit != null && !hasActed[r][c]){
                    int oldRow = unit.getRow();
                    int oldCol = unit.getColumn();
                    
                    hasActed[oldRow][oldCol] = true;
                    
                    unit.act();
                    
                    int newRow = unit.getRow();
                    int newCol = unit.getColumn();
                    if(inValley(newRow, newCol)){
                        hasActed[newRow][newCol] = true;
                    }
                }
            }
        }
    }
    
<<<<<<< HEAD
    public void abrir00(File selectedFile) throws ValleyException{
        throw new ValleyException(ValleyException.OPCION_ABRIR_EN_CONSTRUCCION);
   }


    public static void importar00(File file) throws ValleyException {
        throw new ValleyException(ValleyException.OPCION_IMPORTAR_EN_CONSTRUCCION);
    }

    public void exportar00(File file) throws ValleyException{
=======
<<<<<<< HEAD
    public void abrir(File file) throws ValleyException{
        throw new ValleyException(ValleyException.OPCION_ABRIR_EN_CONSTRUCCION);
   }
<<<<<<< HEAD
    public void salvar(File file) throws ValleyException{
        throw new ValleyException(ValleyException.OPCION_GUARDAR_EN_CONSTRUCCION);
   }
    public void importar(File file) throws ValleyException{
=======
    public void guardar(File archivo) throws ValleyException{
        throw new ValleyException(ValleyException.OPCION_GUARDAR_EN_CONSTRUCCION);
   }
    public static void importar(File archivo) throws ValleyException{
>>>>>>> 491cf12 (new, save and import)
        throw new ValleyException(ValleyException.OPCION_IMPORTAR_EN_CONSTRUCCION);
   }
    public void exportar(File file) throws ValleyException{
>>>>>>> Mich
        try (PrintWriter writer = new PrintWriter (new FileWriter (file))){
            for ( int r = 0; r < SIZE; r++){
                for ( int c = 0; c<SIZE ; c++){
                    Unit unidad = places [r][c];
                    if(unidad != null){
                        String tipo = unidad.getTipoExportacion();
                        writer.println(tipo + "," + r + "," + c);
                    }
                }
            }
        } catch (IOException e ){
            throw new ValleyException("Errpr al exportar : " + e.getMessage());
        }
    }
    public void nuevo() throws ValleyException{
        throw new ValleyException(ValleyException.OPCION_NUEVO_EN_CONSTRUCCION);
    }
    public void importar00(File file) throws ValleyException {
        throw new ValleyException(ValleyException.OPCION_IMPORTAR_EN_CONSTRUCCION);
    }

<<<<<<< HEAD
=======
    public void exportar00(File file) throws ValleyException {
=======
    public void abrir(File selectedFile) throws ValleyException{
        throw new ValleyException(ValleyException.OPCION_ABRIR_EN_CONSTRUCCION);
   }
<<<<<<< HEAD
    public void salvar(File file) throws ValleyException{
        throw new ValleyException(ValleyException.OPCION_GUARDAR_EN_CONSTRUCCION);
   }
    public void importar(File file) throws ValleyException{
        throw new ValleyException(ValleyException.OPCION_IMPORTAR_EN_CONSTRUCCION);
   }
<<<<<<< HEAD
=======

    public static void importar00(File file) throws ValleyException {
        throw new ValleyException(ValleyException.OPCION_IMPORTAR_EN_CONSTRUCCION);
    }
>>>>>>> 7f67065 (Guardar ready)
    public void exportar(File selectedFile) throws ValleyException{
>>>>>>> 08ef52b (Controlador completado)
        throw new ValleyException(ValleyException.OPCION_EXPORTAR_EN_CONSTRUCCION);
<<<<<<< HEAD
    }

=======
   }
<<<<<<< HEAD
   public void nuevo() throws ValleyException{
        try {
            throw new ValleyException(ValleyException.OPCION_NUEVO_EN_CONSTRUCCION);
        } catch (ValleyException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 491cf12 (new, save and import)
=======

=======
    public void exportar(File file) throws ValleyException{
        try (PrintWriter writer = new PrintWriter (new FileWriter (file))){
            for ( int r = 0; r < SIZE; r++){
                for ( int c = 0; c<SIZE ; c++){
                    Unit unidad = places [r][c];
                    if(unidad != null){
                        String tipo = unidad.getTipoExportacion();
                        writer.println(tipo + "," + r + "," + c);
                    }
                }
            }
        } catch (IOException e ){
            throw new ValleyException("Errpr al exportar : " + e.getMessage());
        }
    }
>>>>>>> 7a6c2b5 (Exportar terminado)
    public void nuevo() throws ValleyException{
        throw new ValleyException(ValleyException.OPCION_NUEVO_EN_CONSTRUCCION);
    }
    public void importar00(File file) throws ValleyException {
        throw new ValleyException(ValleyException.OPCION_IMPORTAR_EN_CONSTRUCCION);
    }

    public void exportar00(File file) throws ValleyException {
        throw new ValleyException(ValleyException.OPCION_EXPORTAR_EN_CONSTRUCCION);
    }

>>>>>>> Mich
    public void guardar00(File file) throws ValleyException {
        try (FileOutputStream fileOut = new FileOutputStream(file);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(this);

        } catch (IOException e) {
            e.printStackTrace();
            if (e.getCause() != null) e.getCause().printStackTrace();
            throw new ValleyException("Error al guardar la partida: " + e.toString());
        }
    }
<<<<<<< HEAD
=======
>>>>>>> 08ef52b (Controlador completado)
>>>>>>> Mich
}




