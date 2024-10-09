package edu.badpals;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Persona p1 = new Persona("Pablo",21);
        Persona p2 = new Persona("Joel",20);
        List<Object> personasObj = new ArrayList<>();
        personasObj.add(p1);
        personasObj.add(p2);
        List<Persona> personas = new ArrayList<>();
        personas.add(p1);
        personas.add(p2);
        BinaryHandler.binarizar(personasObj);
        System.out.println(BinaryHandler.desbinarizar());
        XMLHandler.crearXMLPersonas(personas);
    }
}