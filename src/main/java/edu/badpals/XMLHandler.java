package edu.badpals;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLHandler {
    public static void crearXMLPersonas(List<Persona> personas){

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element personasElement = doc.createElement("personas");
            doc.appendChild(personasElement);
            for(Persona persona: personas){
                Element personaElement = doc.createElement("persona");
                personasElement.appendChild(personaElement);

                Element nombreElement = doc.createElement("nombre");
                personaElement.appendChild(nombreElement);
                nombreElement.appendChild(doc.createTextNode(persona.getNombre()));

                Element edadElement = doc.createElement("edad");
                personaElement.appendChild(edadElement);
                edadElement.appendChild(doc.createTextNode(persona.getEdad().toString()));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("personas.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Persona>
}
