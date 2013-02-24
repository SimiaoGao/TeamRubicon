package org.teamrubiconusa.teamrubicon.adapters;

import java.util.Calendar;
import java.util.List;
import org.teamrubiconusa.teamrubicon.R;
import org.teamrubiconusa.teamrubicon.model.Donate;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DonateListAdapter extends ArrayAdapter<Donate> {
	  private final Context context;
	  private final List<Donate> values;

	  public DonateListAdapter(Context context, List<Donate> values) {
	    super(context, R.layout.activity_team_rubicon, values);
	    this.context = context;
	    this.values = values;
	  }
	  
	  static class RowHolder
	  {
		  protected TextView donationType;
		  protected TextView donationTime;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  if (convertView == null) {
			    LayoutInflater inflater = (LayoutInflater) context
			            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    convertView = inflater.inflate(R.layout.row_layout, null);
		      RowHolder holder = new RowHolder();
		      holder.donationType = (TextView) convertView.findViewById(R.id.name);
		      holder.donationTime = (TextView) convertView.findViewById(R.id.location);
		      convertView.setTag(holder);
		  }
		  RowHolder holder = (RowHolder) convertView.getTag();
		  int tmpDonationType = values.get(position).getId();
		  Calendar tmpDontationTime = values.get(position).getTime();
		  long id = values.get(position).getId();
		  
		  
		  holder.donationType.setText("Hello");
		  holder.donationTime.setText("Hi There");
		    
	    return convertView;
	  }
	} 
