package org.teamrubiconusa.teamrubicon.REST;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.teamrubiconusa.teamrubicon.DataLoaderListener;
import org.teamrubiconusa.teamrubicon.WallaceDB.LocationDataSource;
import org.teamrubiconusa.teamrubicon.dao.ItemDao;
import org.teamrubiconusa.teamrubicon.dao.PersonDao;
import org.teamrubiconusa.teamrubicon.dao.TypeDao;
import org.teamrubiconusa.teamrubicon.dao.WarehouseDao;
import org.teamrubiconusa.teamrubicon.model.Active;
import org.teamrubiconusa.teamrubicon.model.Item;
import org.teamrubiconusa.teamrubicon.model.Person;
import org.teamrubiconusa.teamrubicon.model.Warehouse;
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
	
	public static final int WAREHOUSE = 1;
	public static final int PERSON = 2;
	public static final int ITEM = 3;
	public static final int ACTIVE = 4;
	public static final int INACTIVE = 5;
	public static final int LENT = 6;
	
	private ProgressBar progressBar;
	private int modelType;
	private Activity parent;
	
	//SqlLite variables
	private static LocationDataSource sqlLiteDatabase;
	private final Collection<DataLoaderListener> listeners = new LinkedList<DataLoaderListener>();


	public XMLParser(ProgressBar progress, Activity parent, int modelType, DataLoaderListener dll){
		this.progressBar = progress;
		this.parent = parent;
		this.modelType = modelType;
		listeners.add(dll);
    	//Open our database connection
    	if(sqlLiteDatabase == null){
    		sqlLiteDatabase = new LocationDataSource(parent.getApplicationContext());
    		sqlLiteDatabase.open();
    	}
 	}
	
	public void addDataLoadingListener(DataLoaderListener d) {
		listeners.add(d);
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
        for (DataLoaderListener dll : listeners) {
        	dll.onDataReceived(this.modelType);
        }
    }
	
	public void parseText(String xml){
		sqlLiteDatabase.deleteAllEvents();
		Document doc = getDomElement(xml);
		 
		//The list is in 'Row' elements
		NodeList nl = doc.getElementsByTagName(KEY_ITEM);

		store(nl);
		// looping through all row nodes <row>
//		for (int i = 0; i < nl.getLength(); i++) {
//			Element e = (Element) nl.item(i);
//			Event event = new Event();
//			
//			event.setEventName(getValue(e, KEY_NAME));
//			event.setEventLocation(getValue(e, KEY_LOCATION));
//
//		    sqlLiteDatabase.createEvent(event);
//		}
	}
	
	public void store(NodeList nl) {
		switch (modelType) {
		
		case WAREHOUSE:
			for (int i = 0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);
				WarehouseDao wd = WarehouseDao.getInstance();
				wd.addWarehouse(new Warehouse(Integer.parseInt(getValue(e, "id")),
									 getValue(e, "name"),
									 getValue(e, "location")));
				Log.i(this.getClass().getName(), getValue(e, "id") + " " + getValue(e, "name") + " " + getValue(e, "location"));
			}
			break;
		case ITEM:
			for (int i = 0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);
				TypeDao td = TypeDao.getInstance();
				td.addType(getValue(e, "name"));
				ItemDao id = ItemDao.getInstance();
				id.addItem(new Item(Integer.parseInt(getValue(e, "id")),
									getValue(e, "name"),
									getValue(e, "cond"),
									-1));
			}
			break;
		case PERSON:
			for (int i = 0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);
				PersonDao pd = PersonDao.getInstance();
				pd.addPerson(new Person(Integer.parseInt(getValue(e, "id")),
										getValue(e, "name"),
										getValue(e, "title"),
										getValue(e, "phone")));
			}
			break;
		case ACTIVE:
//			for (int i = 0; i < nl.getLength(); i++) {
//				Element e = (Element) nl.item(i);
//				ActiveDao ad = ActiveDao.getInstance();
//				ad.addActive(new Active(WarehouseDao.getInstance().getWarehouseById(Integer.parseInt(getValue(e, "")))))
//			}
			break;
		case INACTIVE:
			break;
		case LENT:
			break;
		default:
			
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
