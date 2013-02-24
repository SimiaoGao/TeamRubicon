package org.teamrubiconusa.teamrubicon.WallaceModels;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable
{
    private String eventName = new String();
    private String eventLocation = new String();    
    private long id;  
    /**
     * Default Constructor - does nothing
     */
    public Event() { }
    
    private Event(Parcel in){
    	eventName = in.readString();
    	eventLocation = in.readString();
    }
    
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String name) {
		this.eventName = name;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String location) {
		this.eventLocation = location;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	//
	// Parcel Implementation
	//
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(eventName);
		dest.writeString(eventLocation);
		dest.writeLong(id);		
	}


	public static final Event.Creator<Event> CREATOR = new Event.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };  

}
