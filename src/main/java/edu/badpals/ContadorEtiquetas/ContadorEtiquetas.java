package edu.badpals.ContadorEtiquetas;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ContadorEtiquetas {

    public static Map<String,Integer> contarEtiquetas(String path) {
        Map<String,Integer> contador = new HashMap<>();
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(new File(path));
            Element rootElement = doc.getRootElement();
            contador.put(rootElement.getName(),1);
            for(Element child: rootElement.getChildren()){
                contarEtiqueta(contador,child);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return contador;
    }

    public static Map<String,Integer> contarEtiqueta(Map<String,Integer> contador, Element child){
        contador.compute(child.getName(),(k,v) -> (v == null) ? 1 : v+1);
        for (Element grandchild: child.getChildren()){
            contador = contarEtiqueta(contador, grandchild);
        }
        return contador;
    }
}
