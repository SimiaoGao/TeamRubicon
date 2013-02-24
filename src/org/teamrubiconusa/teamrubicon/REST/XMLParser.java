package org.teamrubiconusa.teamrubicon.REST;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.teamrubiconusa.teamrubicon.ViewPagerAdapter;
import org.teamrubiconusa.teamrubicon.WallaceDB.LocationDataSource;
import org.teamrubiconusa.teamrubicon.WallaceModels.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class XMLParser extends AsyncTask<String, Integer, Void>{
	static final String KEY_ITEM = "row"; 
	static final String KEY_ID = "id";
	static final String KEY_NAME = "name";
	static final String KEY_LOCATION = "location";
	
	private ProgressBar progressBar;
	private Activity parent;
	
	//SqlLite variables
	private static LocationDataSource sqlLiteDatabase;


	public XMLParser(ProgressBar progress, Activity parent){
		this.progressBar = progress;
		this.parent = parent;
    	//Open our database connection
    	if(sqlLiteDatabase == null){
    		sqlLiteDatabase = new LocationDataSource(parent.getApplicationContext());
    		sqlLiteDatabase.open();
    	}
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
//        ViewPagerAdapter.updateList(sqlLiteDatabase.getAllEvents());
    }
	
	public void parseText(String xml){
		sqlLiteDatabase.deleteAllEvents();
		Document doc = getDomElement(xml);
		 
		//The list is in 'Row' elements
		NodeList nl = doc.getElementsByTagName(KEY_ITEM);

		// looping through all row nodes <row>
		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
			Event event = new Event();
			
			event.setEventName(getValue(e, KEY_NAME));
			event.setEventLocation(getValue(e, KEY_LOCATION));

		    sqlLiteDatabase.createEvent(event);
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
