package com.eventivities.android.util;

import java.util.List;
import java.util.Locale;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
 
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.eventivities.android.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsActivity extends MapActivity {
	
	private MapView mapView;
    private MapController mc;
    private LocationListener miLocationListener;
    private ParticularItemizedOverlay itemizedoverlay;
    private Geocoder geoCoder;
    
    public void onCreate(Bundle savedInstanceState){
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa); 
    	
    	 LocationManager milocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	 
    	 if (milocManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
 			miLocationListener = new MiLocationListener();
 			milocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, miLocationListener);
 						
 		}
    	 else if (milocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
 			miLocationListener = new MiLocationListener();
 			milocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, miLocationListener);
 						
 		}
 		else{
 				
 			AlertDialog.Builder dialogoGps = new AlertDialog.Builder(this);  
 	        dialogoGps.setTitle(R.string.mensaje_dialogo_gps);  
 	        dialogoGps.setMessage(R.string.mensaje_dialogo_gps);            
 	        dialogoGps.setCancelable(false); 
 	        dialogoGps.setIcon(R.drawable.icongps);
 	        
 	        dialogoGps.setPositiveButton(R.string.mensaje_dialogo_gps_btn_aceptar, new DialogInterface.OnClickListener() {  
 	            public void onClick(DialogInterface dialogoGps, int id) {  
 	            	Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
 	 				startActivity(intent);
 	            }  
 	        });  
 	        dialogoGps.setNegativeButton(R.string.mensaje_dialogo_gps_btn_cancelar, new DialogInterface.OnClickListener() {  
 	            public void onClick(DialogInterface dialogoGps, int id) {  
 	                dialogoGps.dismiss();
 	            }  
 	        });            
 	        dialogoGps.show();        
 	    }//del else
 	    	 
    	 mapView = (MapView) findViewById(R.id.mapa);
         mc = mapView.getController();
         mapView.setBuiltInZoomControls(true);
         
         Drawable drawable = this.getResources().getDrawable(R.drawable.marcador_google_maps);
         itemizedoverlay = new ParticularItemizedOverlay(drawable,this);
         geoCoder = new Geocoder(this, Locale.getDefault());
    }


	protected boolean isRouteDisplayed() {
		return false;
	}
	
	private class MiLocationListener implements LocationListener{
		
        public void onLocationChanged(Location loc){
        	double lat = loc.getLatitude() * 1E6;
			double lon = loc.getLongitude() * 1E6;

		

			//creamos un geoPunto necesario para dibujar en el mapa
			GeoPoint point = new GeoPoint((int) (lat),(int) (lon));
			mc.animateTo(point);
			mc.setZoom(17);
			String address="";
			
			List<Overlay> mapOverlays = mapView.getOverlays();//añadimos la nueva capa
			OverlayItem overlayitem = new OverlayItem(point, "", address);
			itemizedoverlay.addOverlay(overlayitem);
			 
			mapOverlays.add(itemizedoverlay);

			mapView.postInvalidate();//actualizamos la capa y el mapa

        }
        public void onProviderDisabled(String provider){
        	//Toast.makeText( getApplicationContext(),"Gps Desactivado",Toast.LENGTH_SHORT ).show();
        }
        public void onProviderEnabled(String provider){
        	//Toast.makeText( getApplicationContext(),"Gps Activo",Toast.LENGTH_SHORT ).show();
        }
        public void onStatusChanged(String provider, int status, Bundle extras){}
    }

}
