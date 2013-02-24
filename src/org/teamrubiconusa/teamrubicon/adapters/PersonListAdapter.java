package org.teamrubiconusa.teamrubicon.adapters;

import java.util.List;

import org.teamrubiconusa.teamrubicon.R;
import org.teamrubiconusa.teamrubicon.WallaceModels.Event;
import org.teamrubiconusa.teamrubicon.adapters.DonateListAdapter.RowHolder;
import org.teamrubiconusa.teamrubicon.model.Person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PersonListAdapter extends ArrayAdapter<Person> {
	  private final Context context;
	  private final List<Person> values;

	  public PersonListAdapter(Context context, List<Person> values) {
	    super(context, R.layout.activity_team_rubicon, values);
	    this.context = context;
	    this.values = values;
	  }
	  
	  static class RowHolder
	  {
		  protected TextView personName;
		  protected TextView personRole;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  if (convertView == null) {
			    LayoutInflater inflater = (LayoutInflater) context
			            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    convertView = inflater.inflate(R.layout.row_layout, null);
		      RowHolder holder = new RowHolder();
		      holder.personName = (TextView) convertView.findViewById(R.id.name);
		      holder.personRole = (TextView) convertView.findViewById(R.id.location);
		      convertView.setTag(holder);
		  }
		  RowHolder holder = (RowHolder) convertView.getTag();
		  String tmpPersonName = values.get(position).getName();
		  String tmpPersonRole = values.get(position).getRole();
//		  long id = values.get(position).getId();
		  
		  
		  holder.personName.setText(tmpPersonName);
		  holder.personRole.setText(tmpPersonRole);
		    
	    return convertView;
	  }
	} 