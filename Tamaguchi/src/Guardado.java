import java.io.*;

public class Guardado {
    public static void guardarJuego(Mascota pet, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pet);
        }
    }

    public static Mascota cargarJuego(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Mascota) ois.readObject();
        }
    }
}
