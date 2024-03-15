/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataManagement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author bazas
 */
public class Data implements Comparable<Data> {
    
    private String name;
    private String categoria;
    private Path path; // Ruta de acceso al archivo
    private long size;

    public Data(Path path) throws IOException {
        if (Files.exists(path)) {
            this.path = path;
            this.name = getFileName(path);
            this.size = Files.size(path);
            this.categoria = findCategory(path);
        }
    }

    
    
    public String getName() {
        return name;
    }

    public Path getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static String getFileName(Path path) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.indexOf('.'); // Cambio aqu� para buscar el primer punto
        if (dotIndex > 0) { // Asegura que hay un punto y no est� al inicio
            return fileName.substring(0, dotIndex);
        }
        return fileName; // Devuelve el nombre completo si no tiene extensi�n
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public static String findCategory(Path filePath) {
        // Obtiene el nombre de la carpeta padre
        Path parentFolder = filePath.getParent();
        if (parentFolder != null) {
            String folderName = parentFolder.getFileName().toString();

            // Compara el nombre de la carpeta con las categor�as conocidas
            switch (folderName) {
                case "biske":
                    return "Bike";
                case "cars":
                    return "Cars";
                case "cats":
                    return "Cats";
                case "dogs":
                    return "Dogs";
                case "flowers":
                    return "Flowers";
                case "horses":
                    return "Horses";
                case "human":
                    return "Human";
                default:
                    return "Unknown"; // Si no coincide con ninguna categor�a conocida
            }
        } else {
            return "Unknown"; // Si el archivo no tiene una carpeta padre
        }
    }

    @Override
    public int compareTo(Data o) {
        return this.name.compareTo(o.name);
    }
}
