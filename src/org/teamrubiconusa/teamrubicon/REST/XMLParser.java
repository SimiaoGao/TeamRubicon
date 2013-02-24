package org.teamrubiconusa.teamrubicon.REST;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class XMLParser extends AsyncTask<String, Integer, Void>{
	static final String KEY_ITEM = "row"; 
	static final String KEY_ID = "id";
	static final String KEY_TEXT = "text";
	static final String KEY_RATING = "rating";
	static final String KEY_FLAGGED = "flagged";
	
	private ProgressBar progressBar;

	public XMLParser(ProgressBar progress){
		this.progressBar = progress;
 	}
	
	@Override
	protected Void doInBackground(String... xml) {
		this.parseText(xml[0]);
		return null;
	} 
	
    @Override
    protected void onPostExecute(Void arg0) {
        super.onPostExecute(arg0);
        progressBar.setVisibility(View.GONE);
    }
	
	public void parseText(String xml){
		Document doc = getDomElement(xml);
		 
		//The list is in 'Row' elements
		NodeList nl = doc.getElementsByTagName(KEY_ITEM);

		// looping through all row nodes <row>
		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
		    String id = getValue(e, KEY_ID);
		    String text = getValue(e, KEY_TEXT);
		    String rating = getValue(e, KEY_RATING);
		    String flagged = getValue(e, KEY_FLAGGED);
		    //
			//Here we need to update our database!
		    //
		}
	}
	
    public Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
 
            DocumentBuilder db = dbf.newDocumentBuilder();
 
            InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                doc = db.parse(is); 
 
            } catch (ParserConfigurationException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (SAXException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            }
                // return DOM
            return doc;
    }
    
    public String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }
     
    public String getElementValue( Node elem ) {
             Node child;
             if( elem != null){
                 if (elem.hasChildNodes()){
                     for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                         if( child.getNodeType() == Node.TEXT_NODE  ){
                             return child.getNodeValue();
                         }
                     }
                 }
             }
             return "";
      }
}
