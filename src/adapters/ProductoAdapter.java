package adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eventivities.android.R;

import domain.Producto;

public class ProductoAdapter extends ArrayAdapter<Producto> {
	
	private int layoutResourceId;

	public ProductoAdapter(Context context, int layoutResourceId, List<Producto> objects/*Producto[] objects*/) {
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

		Producto producto = this.getItem(position);

		if (producto != null) {
			
			TextView textViewNombre = (TextView)view.findViewById(R.id.textViewNombre);
			if (textViewNombre != null)
				textViewNombre.setText(producto.getNombre());
		}
		
		return view;
	}

}
