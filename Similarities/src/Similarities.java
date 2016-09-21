import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Similarities {


	public static void main(String[] argv){
		
		File[] files = new File("c:\\Users\\RR\\desktop\\Rogers").listFiles();
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		
		
		for(int i=0; i<files.length; i++){
			
			String articlename = getArticleName(files[i]);
			String filename = files[i].getName();
			lhm.put(filename, articlename);
			
		}
		
		Map<String, String> map = new TreeMap<String, String>(lhm);
		Iterator<Entry<String, String>> itr = map.entrySet().iterator();
		
		while (itr.hasNext()) {
		    
			Entry e = itr.next();
			String filename = e.getKey().toString();
			String attr = e.getValue().toString();
			
			System.out.println(filename + " : \t" + attr + "\n");
			
		}
	
		
		
		
	}
	
	public static String getArticleName(File file){
		

		try {
			
		      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc;
			  doc = db.parse(file);
			  doc.getDocumentElement().normalize();
			  
			  NodeList nodeLst;
  			  org.w3c.dom.Node fstNode;
  			  
  			  nodeLst = doc.getElementsByTagName("fixture");
  				  
			  fstNode = nodeLst.item(0);				  
			  Element element = (Element) fstNode;
			  String attr = element.getAttribute("fix-id");
			  
			  if(attr.isEmpty()){
				  return "BLANK ARTICLE";
			  }else
				  return attr;
			  
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		  
		return null;
		
	}
	
}
