package domain;
public class ValleyException extends Exception {
   public static final String  OPCION_ABRIR_EN_CONSTRUCCION = "Opcion abrir en construccion";
   public static final String OPCION_EXPORTAR_EN_CONSTRUCCION = "Opcion exportar en construccion";
   public static final String OPCION_GUARDAR_EN_CONSTRUCCION = " Opcion guardar en construccion";
   public static final String OPCION_IMPORTAR_EN_CONSTRUCCION = " Opcion importar en construccion";
   public static final String OPCION_NUEVO_EN_CONSTRUCCION = "Opcion nuevo en construccion";

   public ValleyException(String message) {
        super(message);
    }
}
