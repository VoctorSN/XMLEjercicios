package edu.badpals.OperacionesPersona;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryHandler {

    public static void binarizar(List<Object> objetos) {
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("objeto.bin"))) {
            for (Object objeto : objetos) {
                escritor.writeObject(objeto);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Object> desbinarizar() {
        List<Object> objetos = new ArrayList<>();
        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream("objeto.bin"))) {
            while (true) {
                try {
                    objetos.add(lector.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objetos;
    }

}
