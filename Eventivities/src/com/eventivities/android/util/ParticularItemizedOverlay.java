package com.eventivities.android.util;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class ParticularItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	
	 private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	 private Context mContext;
	 
	 public ParticularItemizedOverlay(Drawable defaultMarker, Context context) {
		 super(boundCenterBottom(defaultMarker));
		 mContext = context;
	 }
 
	 public void addOverlay(OverlayItem overlay) {
		 mOverlays.clear();
		 mOverlays.add(overlay);
		 populate();
	 }
 
	 protected OverlayItem createItem(int i) {
		 return mOverlays.get(i);
	 }
 
	 public int size() {
		 return mOverlays.size();
	 }
 
	 protected boolean onTap(int index) {
		
		 return true;
	 }

	 
	 public boolean onTap(GeoPoint p, MapView mapView) {
		 
			  	 
			return true;
		 
	 }
}
