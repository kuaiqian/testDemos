package jdk.study;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class TestXmlDOM {
    
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {  
        read();  
        //write();   
    }  
      
    public static void read() throws ParserConfigurationException, SAXException, IOException {  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
            DocumentBuilder builder = dbf.newDocumentBuilder();  
            InputStream in = TestXmlDOM.class.getClassLoader().getResourceAsStream("bean.xml");  
            Document doc = builder.parse(in);  
            // root <university>   
            Element root = doc.getDocumentElement();  
            System.err.println(root.toString());
            NodeList list=root.getChildNodes();
           for (int i = 0; i < list.getLength(); i++) {
            Node node=list.item(i);
            System.out.println(node.getNodeName()+" = "+node.getNodeValue());
        } 
    }  
      
    public static void write() {  
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        try {  
            DocumentBuilder builder = dbf.newDocumentBuilder();  
            InputStream in = TestXmlDOM.class.getClassLoader().getResourceAsStream("test.xml");  
            Document doc = builder.parse(in);  
            // root <university>   
            Element root = doc.getDocumentElement();  
            if (root == null) return;  
            // 修改属性   
            root.setAttribute("name", "tsu");  
            NodeList collegeNodes = root.getChildNodes();  
            if (collegeNodes != null) {  
                for (int i = 0; i <collegeNodes.getLength() - 1; i++) {  
                    // 删除节点   
                    Node college = collegeNodes.item(i);  
                    if (college.getNodeType() == Node.ELEMENT_NODE) {  
                        String collegeName = college.getAttributes().getNamedItem("name").getNodeValue();  
                        if ("c1".equals(collegeName) || "c2".equals(collegeName)) {  
                            root.removeChild(college);  
                        } else if ("c3".equals(collegeName)) {  
                            Element newChild = doc.createElement("class");  
                            newChild.setAttribute("name", "c4");  
                            college.appendChild(newChild);  
                        }  
                    }  
                }  
            }  
            // 新增节点   
            Element addCollege = doc.createElement("college");  
            addCollege.setAttribute("name", "c5");  
            root.appendChild(addCollege);  
            Text text = doc.createTextNode("text");  
            addCollege.appendChild(text);  
              
            // 将修改后的文档保存到文件   
            TransformerFactory transFactory = TransformerFactory.newInstance();  
            Transformer transFormer = transFactory.newTransformer();  
            DOMSource domSource = new DOMSource(doc);  
            File file = new File("src/dom-modify.xml");  
            if (file.exists()) {  
                file.delete();  
            }  
            file.createNewFile();  
            FileOutputStream out = new FileOutputStream(file);           
            StreamResult xmlResult = new StreamResult(out);  
            transFormer.transform(domSource, xmlResult);  
            System.out.println(file.getAbsolutePath());  
        } catch (ParserConfigurationException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TransformerConfigurationException e) {  
            e.printStackTrace();  
        } catch (TransformerException e) {  
            e.printStackTrace();  
        }  
    }  
}
