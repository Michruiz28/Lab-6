package domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class ValleyPersistanceTest {
    private Valley valley;

    // JUnit crea automáticamente un directorio temporal para las pruebas
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        // Se ejecuta antes de cada prueba
        valley = new Valley();
    }

    @AfterEach
    void tearDown() {
        // Se ejecuta después de cada prueba
        valley = null;
    }

    @Test
    void shouldSaveValleyWithUnits() {
        // Arrange (Preparar)
        File archivo = tempDir.resolve("valle_test.valley").toFile();

        // Act (Actuar)
        assertDoesNotThrow(() -> valley.guardar00(archivo));

        // Assert (Verificar)
        assertTrue(archivo.exists(), "El archivo debería existir");
        assertTrue(archivo.length() > 0, "El archivo no debería estar vacío");
    }

    @Test
    void shouldCreateANewFileIfDontExist() throws ValleyException {
        // Arrange
        File archivo = tempDir.resolve("nuevo_valle.valley").toFile();
        assertFalse(archivo.exists(), "El archivo no debería existir antes");

        // Act
        valley.guardar00(archivo);

        // Assert
        assertTrue(archivo.exists(), "El archivo debería haberse creado");
    }

    @Test
    void shouldOverrideAnExistenceArchive() throws ValleyException, IOException {
        // Arrange
        File archivo = tempDir.resolve("sobrescribir.valley").toFile();

        // Crear archivo con contenido basura
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("contenido viejo");
        }
        long tamañoOriginal = archivo.length();

        // Act
        valley.guardar00(archivo);

        // Assert
        assertTrue(archivo.exists());
        assertNotEquals(tamañoOriginal, archivo.length(),
                "El tamaño del archivo debería haber cambiado");
    }

}
