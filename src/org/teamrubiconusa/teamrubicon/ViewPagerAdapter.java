package org.teamrubiconusa.teamrubicon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.teamrubiconusa.teamrubicon.WallaceDB.LocationDataSource;
import org.teamrubiconusa.teamrubicon.WallaceModels.Event;
import org.teamrubiconusa.teamrubicon.adapters.EventListAdapter;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ViewPagerAdapter extends PagerAdapter implements OnItemClickListener
{	
	//list adapter
	public static EventListAdapter listAdapter;
	
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

	
	//Returns the number of panes in the ViewPager
    public int getCount() {
        return 3;
    }
     
	@Override
    public Object instantiateItem(View collection, int position) {
		if(listAdapter != null){
			listAdapter.notifyDataSetChanged();
		}
    	//Open our database connection
    	if(sqlLiteDatabase == null){
    		sqlLiteDatabase = new LocationDataSource(collection.getContext());
    		sqlLiteDatabase.open();
    	}
    	//sqlLiteDatabase.deleteAllEvents();
    	vibrator = (Vibrator) collection.getContext().getSystemService(Context.VIBRATOR_SERVICE);
    	
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Initialize to first pane
        int resId = R.layout.first_pane;
        View view = inflater.inflate(resId, null);
        switch (position) {
        case 0:        	
            resId = R.layout.first_pane;
            view = inflater.inflate(resId, null);
            
            break;
        case 1:        	
            resId = R.layout.second_pane;
            view = inflater.inflate(resId, null);
            
            break;
        case 2:
            resId = R.layout.third_pane;
            view = inflater.inflate(resId, null);

            //Get all of the notes
		    List<Event> values = sqlLiteDatabase.getAllEvents();
		    if(values.size() > 0){
				listAdapter = new EventListAdapter(collection.getContext(), values);	
		    } else{
		    	List<Event> myList = new ArrayList<Event>();
		    	Event blankEvent = new Event();
		    	blankEvent.setEventName("Nothing Found");
		    	blankEvent.setEventLocation("");
		    	myList.add(blankEvent);
				listAdapter = new EventListAdapter(collection.getContext(), myList);		   
		    }
		    //Get the noteList by id.
		    eventList = (ListView) view.findViewById(R.id.notes_list);
		    eventList.setAdapter(listAdapter);
		    eventList.setOnItemClickListener((OnItemClickListener) this);
			listAdapter.notifyDataSetChanged();
        }

       ((ViewPager) collection).addView(view, 0);        
        return view;
    }
	
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		Event event = (Event) eventList.getItemAtPosition(position);
		//DEBUG Toast.makeText(view.getContext(), position + " " + note.getId() + " " + note.getNoteTitle(), Toast.LENGTH_SHORT).show();
		//Make a new dialog
		DialogFragment newDialog = new eventListDialog(event);
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
		listAdapter.notifyDataSetChanged();
	}
}
