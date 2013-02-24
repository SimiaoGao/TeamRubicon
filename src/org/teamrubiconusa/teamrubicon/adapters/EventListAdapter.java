package org.teamrubiconusa.teamrubicon.adapters;

import java.util.List;

import org.teamrubiconusa.teamrubicon.R;
import org.teamrubiconusa.teamrubicon.WallaceModels.Event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventListAdapter extends ArrayAdapter<Event> {
	  private final Context context;
	  private final List<Event> values;

	  public EventListAdapter(Context context, List<Event> values) {
	    super(context, R.layout.activity_team_rubicon, values);
	    this.context = context;
	    this.values = values;
	  }
	  
	  static class RowHolder
	  {
		  protected TextView eventName;
		  protected TextView eventLocation;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  if (convertView == null) {
			    LayoutInflater inflater = (LayoutInflater) context
			            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    convertView = inflater.inflate(R.layout.row_layout, null);
		      RowHolder holder = new RowHolder();
		      holder.eventName = (TextView) convertView.findViewById(R.id.name);
		      holder.eventLocation = (TextView) convertView.findViewById(R.id.location);
		      convertView.setTag(holder);
		  }
		  RowHolder holder = (RowHolder) convertView.getTag();
		  String tmpEventName = values.get(position).getEventName();
		  String tmpEventLocation = values.get(position).getEventLocation();
		  long id = values.get(position).getId();
		  
		  
		  holder.eventName.setText(tmpEventName);
		  holder.eventLocation.setText(tmpEventLocation);
		    
	    return convertView;
	  }
	} 
