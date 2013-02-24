package org.teamrubiconusa.teamrubicon;

import java.util.List;

import org.teamrubiconusa.teamrubicon.REST.RESTfulRequest;
<<<<<<< HEAD
import org.teamrubiconusa.teamrubicon.REST.XMLParser;
import org.teamrubiconusa.teamrubicon.dao.ItemDao;
import org.teamrubiconusa.teamrubicon.dao.PersonDao;
import org.teamrubiconusa.teamrubicon.dao.WarehouseDao;
=======
import org.teamrubiconusa.teamrubicon.dao.InactiveDao;
import org.teamrubiconusa.teamrubicon.dao.PersonDao;
import org.teamrubiconusa.teamrubicon.model.Inactive;
>>>>>>> fdce5dbc77e531f08c297624e6265fe2bb0b8c4f

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TeamRubicon extends FragmentActivity implements DataLoaderListener {

	private ViewPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	
    private ProgressBar progressBar;
    private RESTfulRequest myRequest;
    
    public static TeamRubicon thisInstance;
    
    private ActionBar actionBar;
    
    private static String URL = "http://54.235.71.143/htm/rest_home.php/person.xml";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		thisInstance = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_rubicon);

		// Create the adapter that will return a fragment for each of the three
		mSectionsPagerAdapter = new ViewPagerAdapter();

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	
		
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(R.layout.progress_layout, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		progressBar = (ProgressBar) this.findViewById(R.id.action_bar_progress);


		myRequest = new RESTfulRequest(this, progressBar, XMLParser.PERSON, this);
		myRequest.execute(URL);
		
<<<<<<< HEAD
		//Toast.makeText(this, WarehouseDao.getInstance().getWarehouseById(1).toString(), Toast.LENGTH_LONG);
=======
		
		List<Inactive> value = InactiveDao.getInstance().getAllInactives();
		value.get(0).getItem();
		
				
>>>>>>> fdce5dbc77e531f08c297624e6265fe2bb0b8c4f
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.team_rubicon, menu);
		return true;
	}
<<<<<<< HEAD
=======
	
	public static TeamRubicon getInstance(){
		return thisInstance;
	}
	
>>>>>>> fdce5dbc77e531f08c297624e6265fe2bb0b8c4f

	@Override
	public void onDataReceived(int type) {
		switch (type) {
		case XMLParser.WAREHOUSE:
			Toast.makeText(this, WarehouseDao.getInstance().getWarehouseById(1).toString(), Toast.LENGTH_LONG).show();
			break;
		case XMLParser.ITEM:
			Toast.makeText(this, ItemDao.getInstance().getItemById(1).toString(), Toast.LENGTH_LONG).show();
			break;
		case XMLParser.PERSON:
			Toast.makeText(this, PersonDao.getInstance().getPersonById(1).toString(), Toast.LENGTH_LONG).show();
			break;
		case XMLParser.ACTIVE:
			break;
		case XMLParser.INACTIVE:
			break;
		case XMLParser.LENT:
			break;
			
		default:
		}
	}
}
