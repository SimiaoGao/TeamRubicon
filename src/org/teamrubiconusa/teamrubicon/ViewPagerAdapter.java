package org.teamrubiconusa.teamrubicon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.teamrubiconusa.teamrubicon.WallaceDB.LocationDataSource;
import org.teamrubiconusa.teamrubicon.WallaceModels.Event;
import org.teamrubiconusa.teamrubicon.adapters.DonateListAdapter;
import org.teamrubiconusa.teamrubicon.adapters.PersonListAdapter;
import org.teamrubiconusa.teamrubicon.dao.DonateDao;
import org.teamrubiconusa.teamrubicon.dao.PersonDao;
import org.teamrubiconusa.teamrubicon.model.Donate;
import org.teamrubiconusa.teamrubicon.model.Person;
import org.teamrubiconusa.teamrubicon.model.TeamRubiconDb;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ViewPagerAdapter extends PagerAdapter implements OnItemClickListener, OnClickListener, OnItemLongClickListener
{	
	//list adapter
	public static PersonListAdapter listAdapter;
	public static DonateListAdapter donateListAdapter;
	private TeamRubiconDb db;
	private ListView donateList;
	
	//Old list of notes that was used before I had the database up
	//private List<NoteRow> rowList = new ArrayList<NoteRow>();
	
	//Define a NoteRow
	private Event rowData;	
	
	//Calendar for date/time info
	private static final Calendar c = Calendar.getInstance();
	private static String date;
	private static String time;
	
	public static ListView eventList;
	
	//SqlLite variables
	private static LocationDataSource sqlLiteDatabase;
	
	// Used to vibrate the phone upon longItemClick
	Vibrator vibrator;
	
	//
	private EditText type;
	private Button submit;

	
	//Returns the number of panes in the ViewPager
    public int getCount() {
        return 2;
    }
     
	@Override
    public Object instantiateItem(View collection, int position) {
		if(listAdapter != null){
			listAdapter.notifyDataSetChanged();
		}

		db = new TeamRubiconDb(collection.getContext());
    	
    	vibrator = (Vibrator) collection.getContext().getSystemService(Context.VIBRATOR_SERVICE);
    	
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Initialize to first pane
        int resId = R.layout.first_pane;
        View view = inflater.inflate(resId, null);
        switch (position) {
        case 0:        	
            resId = R.layout.first_pane;
            view = inflater.inflate(resId, null);
            
            type = (EditText) view.findViewById(R.id.input1);
            submit = (Button) view.findViewById(R.id.button_send);
            
            donateList = (ListView) view.findViewById(R.id.donation_List);
            List<Donate> tmpDonateList = DonateDao.getInstance().getAllDonates();
            donateListAdapter = new DonateListAdapter(view.getContext(), tmpDonateList);
            donateList.setAdapter(donateListAdapter);
            
            submit.setOnClickListener((OnClickListener) this);
            donateList.setOnItemLongClickListener((OnItemLongClickListener) this);
            
            break;
        case 1:
            resId = R.layout.third_pane;
            view = inflater.inflate(resId, null);

			Map<Integer, Person> myMap = PersonDao.getInstance().getPersonMap();
			List<Person> myList= new ArrayList<Person>();
			for(int i = 0; i < myMap.size(); i++){
				if(myMap.get(i) != null)
					myList.add(myMap.get(i));
			}
		    if(myList.size() > 0){
				listAdapter = new PersonListAdapter(collection.getContext(), myList);	
		    } else{
		    	List<Person> myList2 = new ArrayList<Person>();
		    	Person blankPerson = new Person();
		    	blankPerson.setName("None Found!");
		    	blankPerson.setName("None Found!");
		    	myList2.add(blankPerson);
				listAdapter = new PersonListAdapter(collection.getContext(), myList2);		   
		    }
		    //Get the noteList by id.
		    eventList = (ListView) view.findViewById(R.id.notes_list);
		    eventList.setAdapter(listAdapter);
		    eventList.setOnItemClickListener((OnItemClickListener) this);
       }

       ((ViewPager) collection).addView(view, 0);        
        return view;
    }
	
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		Person person = (Person) eventList.getItemAtPosition(position);
		//DEBUG Toast.makeText(view.getContext(), position + " " + note.getId() + " " + note.getNoteTitle(), Toast.LENGTH_SHORT).show();
		//Make a new dialog
		DialogFragment newDialog = new personListDialog(person);
		newDialog.show(TeamRubicon.getInstance().getFragmentManager(), "Edit Note");
		
	}
	
	@Override
	public float getPageWidth(int position){
		return(1.0f);
	}
    
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }
    
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }
    
    @Override
    public Parcelable saveState() {
        return null;
    }

	public static void notifyAdapterOfChange(){
		eventList.setAdapter(listAdapter);
		listAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.button_send) {
			Toast.makeText(v.getContext(), "Button Click", Toast.LENGTH_LONG).show();
			
			type.getText();
			
			db.addDonate(type.getText().toString(), "00:00:00");
			
		}
	}
	
	public boolean onItemLongClick(AdapterView<?> adapterView, final View view, final int position,	long arg3) {
		//New ALertDialog
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(adapterView.getContext());
 
		// set title
		alertDialogBuilder.setTitle("Delete Item?");
 
		// set dialog message
		alertDialogBuilder.setMessage("Are you sure you want to delete this item?")
			.setCancelable(false)
			.setPositiveButton("Yes, Delete it!",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// if this button is clicked, close
				// current activity
				Donate donation = (Donate) donateList.getItemAtPosition(position);
				db.deleteDonate(donation.getId());
				donateListAdapter = new DonateListAdapter(view.getContext(), DonateDao.getInstance().getAllDonates());				
				donateList.setAdapter(donateListAdapter);
				donateListAdapter.notifyDataSetChanged();
			}
		});
		alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.cancel();
			}
		});
 
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
 
		// show it
		alertDialog.show();

		return true;
	}


}
