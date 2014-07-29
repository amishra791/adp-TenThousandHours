package edu.foothill.tenthousandhours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ActivitiesListAdapter extends ActivitiesViewAdapter {

	public ActivitiesListAdapter(Context c) {
		super(c);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View rowView;
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.activity_item, parent,false);
		}
		else {
			rowView = convertView;
		}
		
		
		TextView nameTextView = (TextView) rowView.findViewById(R.id.activityName);
		nameTextView.setText(getActivityName(position));
		return rowView;
	}

}
