package com.eventivities.android.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eventivities.android.R;
import com.eventivities.android.domain.Local;

public class LocalesAdapter extends ArrayAdapter<Local> {
	
	private int layoutResourceId;

	public LocalesAdapter(Context context, int layoutResourceId, List<Local> objects) {
		super(context, layoutResourceId, objects);
		this.layoutResourceId = layoutResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
	        view = inflater.inflate(layoutResourceId, parent, false);
		}

		Local local = this.getItem(position);

		if (local != null) {
			TextView textViewNombre = (TextView)view.findViewById(R.id.textViewNombreLocal);
			if (textViewNombre != null)
				textViewNombre.setText(local.getNombreLocal());
		}
		
		return view;
	}

}
