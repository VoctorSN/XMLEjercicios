package edu.badpals.OperacionesPersona;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Persona p1 = new Persona("Pablo", 21);
        Persona p2 = new Persona("Joel", 20);
        List<Object> personasObj = new ArrayList<>();
        personasObj.add(p1);
        personasObj.add(p2);
        List<Persona> personas = new ArrayList<>();
        personas.add(p1);
        personas.add(p2);
        BinaryHandler.binarizar(personasObj);
        System.out.println(BinaryHandler.desbinarizar());
        XMLHandler.crearXMLPersonas(personas);
        System.out.println(XMLHandler.lectorXML("personas.xml"));
        XMLHandler.visualizarXML("personas.xml");
    }
}