package com.eventivities.android.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eventivities.android.R;
import com.eventivities.android.domain.Producto;

public class EventosAdapter extends ArrayAdapter<Producto> {
	
	private int layoutResourceId;

	public EventosAdapter(Context context, int layoutResourceId, List<Producto> objects) {
		super(context, layoutResourceId, objects);
		this.layoutResourceId = layoutResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view == null) {
	        LayoutInflater inflater = ((Activity)this.getContext()).getLayoutInflater();
	        view = inflater.inflate(layoutResourceId, parent, false);
		}	

		Producto evento = this.getItem(position);

		if (evento != null) {			
			TextView textViewNombre = (TextView)view.findViewById(R.id.textViewNombre);
			if (textViewNombre != null)
				textViewNombre.setText(evento.getNombre());
		}
		
		return view;
	}

}
