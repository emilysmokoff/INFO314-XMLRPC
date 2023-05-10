package edu.uw.info314.xmlrpc.server;

import java.util.*;
import java.util.logging.*;

import static spark.Spark.*;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
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

class Call {
    public String name;
    public List<Object> args = new ArrayList<Object>();
}

public class App {
    public static final Logger LOG = Logger.getLogger(App.class.getCanonicalName());
    private static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    public static void main(String[] args) {
        LOG.info("Starting up on port 8080");
        port(8080);

        //Throw an error if the path isn't RPC
        before((request, response) -> {
            String path = ((String) request.pathInfo()).trim();
            if(!path.equals("/RPC")) {
                throw new FileNotFoundException();
            }
        });

        //handle POST requests
        post("/RPC", (request, response) -> { 
            int responseStatus = 200;
            String requestBody = request.body();
    
            //read the body
            DocumentBuilderFactory requestFactory = DocumentBuilderFactory.newInstance();  
            DocumentBuilder builderRequest = requestFactory.newDocumentBuilder();
            Document docRequest = builderRequest.parse(new InputSource(new StringReader(requestBody)));

            Call getCall = new Call();
            //find method
            Element root = docRequest.getDocumentElement();
            getCall.name = root.getElementsByTagName("methodName").item(0).getTextContent();
            //LOG.info(getCall.name);

            //find integers
            NodeList nodeList = root.getElementsByTagName("i4");
            List<Object> splitResponse = new ArrayList<Object>();
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                splitResponse.add(nodeList.item(i).getTextContent());
            }
            getCall.args = splitResponse;
            //LOG.info((String) getCall.args.get(0));

            Document returnDocument = xmlResults(getCall.name, getCall.args);

            StringWriter documentString = new StringWriter();
            DOMSource xmlSource = new DOMSource(returnDocument);
            StreamResult outputTarget = new StreamResult(documentString);
            TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);

            response.status(responseStatus);
            response.type("text/xml");
            return documentString.toString(); 
        });

        //Handle any method besides POST being put in
        get("/RPC", (request, response) -> {
            throw new UnsupportedOperationException();
        });

        put("/RPC", (request, response) -> {
            throw new UnsupportedOperationException();
        });

        delete("/RPC", (request, response) -> {
            throw new UnsupportedOperationException();
        });

        options("/RPC", (request, response) -> {
            //throw halt(405, "Method Not Supported");
            throw new UnsupportedOperationException();
        });

        //handle file not found exception
        exception(FileNotFoundException.class, (exception, request, response) -> {
            // Handle the exception here
            response.status(404);

            response.body("File Not Found");
        });

        //handle method not found error
        exception(UnsupportedOperationException.class, (exception, request, response) -> {
            // Handle the exception here
            response.status(405);

            response.body("Method Not Found");
        });
    }

    public static Document xmlResults(String name, List<Object> args) throws Exception{
        Boolean nonNum = false;
        
        Calc calculator = new Calc();

        // DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document document = docBuilder.newDocument();

        document.setXmlVersion("1.0");

        Element root = document.createElement("result");
        document.appendChild(root);

        int[] processingNums = new int[args.size()];
        for(int i = 0; i < args.size(); i++) {
            try {
                String numToProcess = ((String) args.get(i)).trim();
                //LOG.info("created a string out of trim");
                int num = Integer.parseInt(numToProcess);
                //LOG.info("set an int to the parse int");
                processingNums[i] = num;
                //LOG.info("made it past setting array");
            } catch (Exception err) {
                //LOG.info("throwing exception");
                nonNum = true;
            }
        }
        if(nonNum == false) {
            try {
                if(name.contains("add")) {
                    String returnStatement = String.valueOf(calculator.add(processingNums));

                    Element i4 = document.createElement("i4");
                    i4.appendChild(document.createTextNode(returnStatement));
                    root.appendChild(i4);

                } else if (name.contains("subtract")) {
                    String returnStatement = String.valueOf(calculator.subtract(processingNums[0], processingNums[1]));

                    Element i4 = document.createElement("i4");
                    i4.appendChild(document.createTextNode(returnStatement));
                    root.appendChild(i4);

                } else if (name.contains("divide")) {
                    if(processingNums[1] == 0 ) {
                        //return faultCode of 1 and faultString of "divide by zero"
                        Element faultCode = document.createElement("faultCode");
                        faultCode.appendChild(document.createTextNode("1"));
                        root.appendChild(faultCode);

                        Element faultString = document.createElement("faultString");
                        faultString.appendChild(document.createTextNode("divide by zero"));
                        root.appendChild(faultString);

                    } else {
                        String returnStatement = String.valueOf(calculator.divide(processingNums[0], processingNums[1]));

                        Element i4 = document.createElement("i4");
                        i4.appendChild(document.createTextNode(returnStatement));
                        root.appendChild(i4);

                    }
                } else if (name.contains("multiply")) {
                    String returnStatement = "1";
                    if(processingNums.length == 1) {
                        //processingNums[1] = 1;
                        returnStatement = String.valueOf(calculator.multiply(new int[]{processingNums[0], 1}));
                    } else if (processingNums.length > 1) {
                        returnStatement = String.valueOf(calculator.multiply(processingNums));
                    }
                    
                    Element i4 = document.createElement("i4");
                    i4.appendChild(document.createTextNode(returnStatement));
                    root.appendChild(i4);

                } else if (name.contains("modulo")) {
                    if(processingNums[1] == 0 ) {
                        //return faultCode of 1 and faultString of "divide by zero"
                        Element faultCode = document.createElement("faultCode");
                        faultCode.appendChild(document.createTextNode("1"));
                        root.appendChild(faultCode);

                        Element faultString = document.createElement("faultString");
                        faultString.appendChild(document.createTextNode("divide by zero"));
                        root.appendChild(faultString);

                    } else {
                        String returnStatement = String.valueOf(calculator.modulo(processingNums[0], processingNums[1]));

                        Element i4 = document.createElement("i4");
                        i4.appendChild(document.createTextNode(returnStatement));
                        root.appendChild(i4);

                    }
                }
            } catch (ArithmeticException err) {
                Element faultCode = document.createElement("faultCode");
                faultCode.appendChild(document.createTextNode("4"));
                root.appendChild(faultCode);

                Element faultString = document.createElement("faultString");
                faultString.appendChild(document.createTextNode("overflow error"));
                root.appendChild(faultString);
            }
            
        } else {
            //return a faultCode of 3 and a faultString of "illegal argument type".
            Element faultCode = document.createElement("faultCode");
            faultCode.appendChild(document.createTextNode("3"));
            root.appendChild(faultCode);

            Element faultString = document.createElement("faultString");
            faultString.appendChild(document.createTextNode("illegal argument type"));
            root.appendChild(faultString);
        }
        
        return document;
    }
}
