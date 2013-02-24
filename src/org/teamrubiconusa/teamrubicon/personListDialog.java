package org.teamrubiconusa.teamrubicon;

import org.teamrubiconusa.teamrubicon.model.Person;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class personListDialog  extends DialogFragment {
	private Person currentPerson;
	
	private TextView eventNameTextView;
	private TextView eventLocationTextView;
	
	public personListDialog(){}
	
	public personListDialog(Person person)
	{
		this.currentPerson = person;
	}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getDialog().setCancelable(true);
    	//Get the view
        View view = inflater.inflate(R.layout.list_dialog, container, false);
        
        eventNameTextView = (TextView) view.findViewById(R.id.dialog_label1);
        eventLocationTextView = (TextView) view.findViewById(R.id.dialog_label2);
        
        eventNameTextView.setText(currentPerson.getName());
        eventLocationTextView.setText(currentPerson.getRole());
        
        return view;
    }
}

