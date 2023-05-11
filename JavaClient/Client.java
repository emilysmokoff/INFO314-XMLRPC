import java.io.*;
import java.net.*;
import java.net.http.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

import org.w3c.dom.Node;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.TransformerFactory;
import org.xml.sax.InputSource;

import java.io.FileNotFoundException;
import java.lang.UnsupportedOperationException;

/**
 * This approach uses the java.net.http.HttpClient classes, which
 * were introduced in Java11.
 */
public class Client {
    private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private static String host = "";
    private static int port = 8080;

    public static void main(String... args) throws Exception {
        host = args[0];
        port = Integer.valueOf(args[1]);
        
        System.out.println(add() == 0);
        System.out.println(add(1, 2, 3, 4, 5) == 15);
        System.out.println(add(2, 4) == 6);
        System.out.println(subtract(12, 6) == 6);
        System.out.println(multiply(3, 4) == 12);
        System.out.println(multiply(1, 2, 3, 4, 5) == 120);
        System.out.println(divide(10, 5) == 2);
        System.out.println(modulo(10, 5) == 0);

    }
    public static int add(int lhs, int rhs) throws Exception {
        try {
            //build the xml document structure
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            document.setXmlVersion("1.0");

            Element root = document.createElement("methodCall");
            document.appendChild(root);

            Element methodName = document.createElement("methodName");
            methodName.appendChild(document.createTextNode("add"));
            root.appendChild(methodName);

            Element params = document.createElement("params");
            root.appendChild(params);

            for(int i = 0; i < 2; i++) {
                Element param = document.createElement("param");
                params.appendChild(param);

                Element value = document.createElement("value");
                param.appendChild(value);

                Element i4 = document.createElement("i4");
                
                if(i == 0) {
                    i4.appendChild(document.createTextNode(String.valueOf(lhs)));
                    value.appendChild(i4);
                } else {
                    i4.appendChild(document.createTextNode(String.valueOf(rhs)));
                    value.appendChild(i4);
                }

            }

            //create the xml file to be passed into the http request
            // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // DOMSource xmlSource = new DOMSource(document);
            // StreamResult outputTarget = new StreamResult(outputStream);
            return returnForMethods(document);
            

        } catch (Exception err) {
            err.printStackTrace();
        }

        return -1;
    }
    public static int add(Integer... processing) throws Exception {
        try {
            //build the xml document structure
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            document.setXmlVersion("1.0");

            Element root = document.createElement("methodCall");
            document.appendChild(root);

            Element methodName = document.createElement("methodName");
            methodName.appendChild(document.createTextNode("add"));
            root.appendChild(methodName);

            Element params = document.createElement("params");
            root.appendChild(params);

            for(int i = 0; i < processing.length; i++) {
                Element param = document.createElement("param");
                params.appendChild(param);

                Element value = document.createElement("value");
                param.appendChild(value);

                Element i4 = document.createElement("i4");
                i4.appendChild(document.createTextNode(String.valueOf(processing[i])));
                value.appendChild(i4);

            }

            //create the xml file to be passed into the http request
            // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // DOMSource xmlSource = new DOMSource(document);
            // StreamResult outputTarget = new StreamResult(outputStream);
            return returnForMethods(document);
            

        } catch (Exception err) {
            err.printStackTrace();
        }

        return -1;
    }
    public static int subtract(int lhs, int rhs) throws Exception {
        try {
            //build the xml document structure
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            document.setXmlVersion("1.0");

            Element root = document.createElement("methodCall");
            document.appendChild(root);

            Element methodName = document.createElement("methodName");
            methodName.appendChild(document.createTextNode("subtract"));
            root.appendChild(methodName);

            Element params = document.createElement("params");
            root.appendChild(params);

            for(int i = 0; i < 2; i++) {
                Element param = document.createElement("param");
                params.appendChild(param);

                Element value = document.createElement("value");
                param.appendChild(value);

                Element i4 = document.createElement("i4");
                
                if(i == 0) {
                    i4.appendChild(document.createTextNode(String.valueOf(lhs)));
                    value.appendChild(i4);
                } else {
                    i4.appendChild(document.createTextNode(String.valueOf(rhs)));
                    value.appendChild(i4);
                }

            }

            //create the xml file to be passed into the http request
            // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // DOMSource xmlSource = new DOMSource(document);
            // StreamResult outputTarget = new StreamResult(outputStream);
            return returnForMethods(document);
            

        } catch (Exception err) {
            err.printStackTrace();
        }

        return -1;
    }
    public static int multiply(int lhs, int rhs) throws Exception {
        try {
            //build the xml document structure
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            document.setXmlVersion("1.0");

            Element root = document.createElement("methodCall");
            document.appendChild(root);

            Element methodName = document.createElement("methodName");
            methodName.appendChild(document.createTextNode("multiply"));
            root.appendChild(methodName);

            Element params = document.createElement("params");
            root.appendChild(params);

            for(int i = 0; i < 2; i++) {
                Element param = document.createElement("param");
                params.appendChild(param);

                Element value = document.createElement("value");
                param.appendChild(value);

                Element i4 = document.createElement("i4");
                
                if(i == 0) {
                    i4.appendChild(document.createTextNode(String.valueOf(lhs)));
                    value.appendChild(i4);
                } else {
                    i4.appendChild(document.createTextNode(String.valueOf(rhs)));
                    value.appendChild(i4);
                }

            }

            //create the xml file to be passed into the http request
            // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // DOMSource xmlSource = new DOMSource(document);
            // StreamResult outputTarget = new StreamResult(outputStream);
            return returnForMethods(document);
            

        } catch (Exception err) {
            err.printStackTrace();
        }

        return -1;
    }
    public static int multiply(Integer... processing) throws Exception {
        try {
            //build the xml document structure
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            document.setXmlVersion("1.0");

            Element root = document.createElement("methodCall");
            document.appendChild(root);

            Element methodName = document.createElement("methodName");
            methodName.appendChild(document.createTextNode("multiply"));
            root.appendChild(methodName);

            Element params = document.createElement("params");
            root.appendChild(params);

            for(int i = 0; i < processing.length; i++) {
                Element param = document.createElement("param");
                params.appendChild(param);

                Element value = document.createElement("value");
                param.appendChild(value);

                Element i4 = document.createElement("i4");
                i4.appendChild(document.createTextNode(String.valueOf(processing[i])));
                value.appendChild(i4);

            }

            //create the xml file to be passed into the http request
            // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // DOMSource xmlSource = new DOMSource(document);
            // StreamResult outputTarget = new StreamResult(outputStream);
            return returnForMethods(document);
            

        } catch (Exception err) {
            err.printStackTrace();
        }
        
        return -1;
    }
    public static int divide(int lhs, int rhs) throws Exception {
        try {
            //build the xml document structure
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            document.setXmlVersion("1.0");

            Element root = document.createElement("methodCall");
            document.appendChild(root);

            Element methodName = document.createElement("methodName");
            methodName.appendChild(document.createTextNode("divide"));
            root.appendChild(methodName);

            Element params = document.createElement("params");
            root.appendChild(params);

            for(int i = 0; i < 2; i++) {
                Element param = document.createElement("param");
                params.appendChild(param);

                Element value = document.createElement("value");
                param.appendChild(value);

                Element i4 = document.createElement("i4");
                
                if(i == 0) {
                    i4.appendChild(document.createTextNode(String.valueOf(lhs)));
                    value.appendChild(i4);
                } else {
                    i4.appendChild(document.createTextNode(String.valueOf(rhs)));
                    value.appendChild(i4);
                }

            }

            //create the xml file to be passed into the http request
            // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // DOMSource xmlSource = new DOMSource(document);
            // StreamResult outputTarget = new StreamResult(outputStream);
            return returnForMethods(document);
            

        } catch (Exception err) {
            err.printStackTrace();
        }

        return -1;
    }
    public static int modulo(int lhs, int rhs) throws Exception {
        try {
            //build the xml document structure
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            document.setXmlVersion("1.0");

            Element root = document.createElement("methodCall");
            document.appendChild(root);

            Element methodName = document.createElement("methodName");
            methodName.appendChild(document.createTextNode("modulo"));
            root.appendChild(methodName);

            Element params = document.createElement("params");
            root.appendChild(params);

            for(int i = 0; i < 2; i++) {
                Element param = document.createElement("param");
                params.appendChild(param);

                Element value = document.createElement("value");
                param.appendChild(value);

                Element i4 = document.createElement("i4");
                
                if(i == 0) {
                    i4.appendChild(document.createTextNode(String.valueOf(lhs)));
                    value.appendChild(i4);
                } else {
                    i4.appendChild(document.createTextNode(String.valueOf(rhs)));
                    value.appendChild(i4);
                }

            }

            //create the xml file to be passed into the http request
            // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // DOMSource xmlSource = new DOMSource(document);
            // StreamResult outputTarget = new StreamResult(outputStream);
            return returnForMethods(document);
            

        } catch (Exception err) {
            err.printStackTrace();
        }

        return -1;
    }

    public static int returnForMethods (Document document) {
        try {
            StringWriter documentString = new StringWriter();
            DOMSource xmlSource = new DOMSource(document);
            StreamResult outputTarget = new StreamResult(documentString);
            TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);

            //build the http request
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + host + ":" + String.valueOf(port) + "/RPC"))
                .version(HttpClient.Version.HTTP_1_1)
                .headers("User-Agent", "Emily Smokoff", "Content-Type", "text/xml")
                .POST(HttpRequest.BodyPublishers.ofString(documentString.toString()))
                .build();

            //sending post request
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            //String responseBody = response.body();
            String responseBody = response.body();

            //prepare result to parse out
            DocumentBuilderFactory factoryReturn = DocumentBuilderFactory.newInstance();  
            DocumentBuilder builderReturn = factoryReturn.newDocumentBuilder(); 
            //Document docReturn = builderReturn.parse(new InputSource(new StringReader(responseBody)));
            Document docReturn = builderReturn.newDocument();
            if(response.statusCode() != 404 && response.statusCode() != 405) { 
                docReturn = builderReturn.parse(new InputSource(new StringReader(responseBody)));
            } 

            if(responseBody.contains("faultString") || response.statusCode() == 405 || response.statusCode() == 404) {
                //throw exception here
                String error="";
                // Element root = docReturn.getDocumentElement();
                if(responseBody.contains("faultString")) {
                    Element root = docReturn.getDocumentElement();
                    error = root.getElementsByTagName("faultString").item(0).getTextContent();
                } 

                if(error.equals("divide by zero")) {
                    throw new ArithmeticException();
                } else if (error.equals("illegal argument type")) {
                    throw new IllegalArgumentException();
                } else if (response.statusCode() == 404) {
                    throw new FileNotFoundException();
                } else if (response.statusCode() == 405) {
                    throw new UnsupportedOperationException();
                } else {
                    throw new RuntimeException();
                }
            } else {
                Element root = docReturn.getDocumentElement();
                String returnResult = root.getElementsByTagName("i4").item(0).getTextContent();
                return Integer.valueOf(returnResult);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return -1;
    }
    
}
