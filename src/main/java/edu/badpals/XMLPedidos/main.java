package edu.badpals.XMLPedidos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class main {
    private static String RUTADAT = "Pedidos.dat";
    private static String RUTAXML = "Pedidos.xml";

    public static void main(String[] args) {
        Producto monitor = new Producto(100, "Monitor", 100.0);
        Producto raton = new Producto(101, "Ratón", 10.0);
        Producto portatil = new Producto(102, "Portatil", 600.0);
        Producto tablet = new Producto(103, "Tablet", 400.0);
        Producto teclado = new Producto(104, "Teclado", 200.0);

        Pedido pedido1 = new Pedido(1, "Cliente1");
        pedido1.addProducto(monitor);
        pedido1.addProducto(raton);

        Pedido pedido2 = new Pedido(2, "Cliente2");
        pedido2.addProducto(portatil);
        pedido2.addProducto(tablet);

        Pedido pedido3 = new Pedido(3, "Cliente2");
        pedido3.addProducto(portatil);
        pedido3.addProducto(tablet);

        Pedido pedido4 = new Pedido(4, "Cliente3");
        pedido4.addProducto(monitor);
        pedido4.addProducto(raton);

        Pedido pedido5 = new Pedido(5, "Cliente4");
        pedido5.addProducto(teclado);

        Pedido pedido6 = new Pedido(6, "Cliente5");
        pedido6.addProducto(monitor);
        pedido6.addProducto(raton);

        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);
        pedidos.add(pedido3);
        pedidos.add(pedido4);
        pedidos.add(pedido5);
        pedidos.add(pedido6);

        escribirPedidosObjetos(pedidos);
        System.out.println(pedidos);

        pedidos = leerPedidosObjetos();
        System.out.println(pedidos);

        crearXMLPedidos(pedidos);

    }

    private static void crearXMLPedidos(List<Pedido> pedidos) {
        try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, "pedidos", null);
            doc.setXmlVersion("1.0");

            Element raiz = doc.getDocumentElement();

            for(Pedido pedido: pedidos){
                Element pedidoEl = doc.createElement("pedido");
                raiz.appendChild(pedidoEl);

                Element idPedido = doc.createElement("idPedido");
                pedidoEl.appendChild(idPedido);
                Text idPedidoText = doc.createTextNode(pedido.getId().toString());
                idPedido.appendChild(idPedidoText);

                Element nombreCliente = doc.createElement("nomeCliente");
                pedidoEl.appendChild(nombreCliente);
                Text nombreClienteText = doc.createTextNode(pedido.getCliente());
                nombreCliente.appendChild(nombreClienteText);

                Element productosElement = doc.createElement("productos");
                pedidoEl.appendChild(productosElement);

                for (Producto producto : pedido.getProductos()){

                    Element productoElement = doc.createElement("producto");
                    productosElement.appendChild(productoElement);

                    Element idProducto = doc.createElement("idProducto");
                    productoElement.appendChild(idProducto);
                    Text idProductoText = doc.createTextNode(producto.getId().toString());
                    idProducto.appendChild(idProductoText);

                    Element descripcionProducto = doc.createElement("descripcion");
                    productoElement.appendChild(descripcionProducto);
                    Text descripcionProductoText = doc.createTextNode(producto.getDescripcion());
                    descripcionProducto.appendChild(descripcionProductoText);

                    Element precioProducto = doc.createElement("idPrecio");
                    productoElement.appendChild(precioProducto);
                    Text precioProductoText = doc.createTextNode(String.valueOf(producto.getPrecio()));
                    precioProducto.appendChild(precioProductoText);
                }

            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc),new StreamResult(new File(RUTAXML)));

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static List<Pedido> leerPedidosObjetos() {
        List<Pedido> pedidos = new ArrayList<>();
        try(ObjectInputStream lector = new ObjectInputStream(new FileInputStream(RUTADAT))){
            while(true) {
                try {
                    Object objeto = lector.readObject();
                    if (objeto instanceof Pedido){
                        pedidos.add((Pedido) objeto);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return pedidos;
    }


    public static void escribirPedidosObjetos(List<Pedido> pedidos) {
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(RUTADAT))) {
            for (Pedido pedido : pedidos) {
                escritor.writeObject(pedido);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}