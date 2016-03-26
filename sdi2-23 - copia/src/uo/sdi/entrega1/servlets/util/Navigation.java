package uo.sdi.entrega1.servlets.util;

import alb.util.log.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uo.sdi.entrega1.acciones.Accion;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Navigation {
    public static final String ROL_PUBLICO = "PUBLICO";
    public static final String ROL_REGISTRADO = "REGISTRADO";
    public static final String OPCION_EXITO = "EXITO";
    public static final String OPCION_FRACASO = "FRACASO";

    public static final String BASE_PATH = "/pages";
    public static final String ERROR_PAGE = BASE_PATH + "/error/error.jsp";
    public static final String ERROR_403 = BASE_PATH + "/error/403.jsp";
    public static final String REDIRECTION_JSP = BASE_PATH + "/util/redirect.jsp";


    private static Map<String, String> urlMap;
    private static Map<String, Map<String, Accion>> mapaDeAcciones;
    // <rol, <opcion, objeto Accion>>
    private static Map<String, Map<String, Map<String, String>>>
            mapaDeNavegacion; // <rol, <opcion, <resultado, JSP>>>

    static {
        try {
            initMaps();
            buildMaps(read("navigation.xml"));
        } catch (Exception e) {
            Log.error("Los mapas no se han podido cargar");
        }
    }

    private static void buildMaps(NodeList navigation) throws Exception {

        for (int i = 0; i < navigation.getLength(); i++) {
            NodeList action = navigation.item(i).getChildNodes();
            List<String> roles = new ArrayList<>();
            String url = null;
            Accion command = null;
            String name = null;
            for (int j = 0; j < action.getLength(); j++) {
                Node node = action.item(j);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getNodeName().equals("name")) {
                        name = node.getTextContent().trim();
                    }

                    if (node.getNodeName().equals("roles")) {
                        roles = getRoles(node.getChildNodes(), roles);
                    }

                    if (node.getNodeName().equals("url")) {
                        url = node.getTextContent().trim();
                    }

                    if (node.getNodeName().equals("command")) {
                        command = getAction(node.getTextContent().trim());
                    }

                    if (node.getNodeName().equals("pages")) {
                        addPage(url, node.getChildNodes());
                    }
                }
            }

            for (String role : roles) {
                mapaDeAcciones.get(role).put(url, command);
                urlMap.put(name, url);
            }
        }
    }


    private static void initMaps() {
        mapaDeAcciones = new HashMap<>();
        mapaDeNavegacion = new HashMap<>();
        mapaDeAcciones.put(ROL_PUBLICO, new HashMap<String, Accion>());
        mapaDeAcciones.put(ROL_REGISTRADO, new HashMap<String, Accion>());

        mapaDeNavegacion = new HashMap<>();
        mapaDeNavegacion.put(ROL_PUBLICO,
                new HashMap<String, Map<String, String>>());
        mapaDeNavegacion.put(ROL_REGISTRADO,
                new HashMap<String, Map<String, String>>());

        urlMap = new HashMap<>();
    }

    private static List<String> getRoles(NodeList roles, List<String> list) {
        for (int i = 0; i < roles.getLength(); i++) {
            if (roles.item(i).getNodeType() == Node.ELEMENT_NODE) {
                list.add(roles.item(i).getFirstChild().getTextContent().trim());
            }
        }
        return list;
    }

    private static Accion getAction(String name) throws Exception {
        Class<?> clazz = Class.forName(name);
        Constructor<?> ctor = clazz.getConstructor();
        return (Accion) ctor.newInstance();
    }

    private static void addPage(String url, NodeList pages) {
        for (int i = 0; i < pages.getLength(); i++) {
            Node page = pages.item(i);
            if (page.getNodeType() == Node.ELEMENT_NODE) {
                String role = page.getAttributes()
                        .getNamedItem("rol")
                        .getNodeValue().trim();
                String result = page.getAttributes()
                        .getNamedItem("result")
                        .getNodeValue().trim();

                Map<String, String> resJSP =
                        mapaDeNavegacion.get(role).get(url);
                if (resJSP == null) {
                    resJSP = new HashMap<>();
                }

                resJSP.put(result,
                        page.getFirstChild().getTextContent().trim());

                mapaDeNavegacion.get(role).put(url, resJSP);
            }
        }
    }

    public static NodeList read(String file) throws Exception {
        InputStream stream = Navigation.class.getClassLoader()
                .getResourceAsStream(file);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(stream);

        doc.getDocumentElement().normalize();

        return doc.getElementsByTagName("action");
    }

    public static Accion getAction(String rol, String action) {
        return mapaDeAcciones.get(rol).get(action);
    }

    public static boolean isNavigationForwarded(String rol,
                                                String action,
                                                String resultado) {
        return !getJsp(rol, action, resultado).contains(".jsp");
    }

    public static String getJsp(String rol, String opcion, String resultado) {
        return BASE_PATH + mapaDeNavegacion.get(rol).get(opcion).get(resultado);
    }

    public static String getNextOption(String rol,
                                       String opcion,
                                       String resultado) {
        return urlMap.get(mapaDeNavegacion.get(rol).get(opcion).get(resultado));
    }

    public static String getUrlByAction(String action) {
        return urlMap.get(action);
    }
}
