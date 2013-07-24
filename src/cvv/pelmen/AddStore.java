package cvv.pelmen;

import com.google.android.maps.*;

import android.os.Bundle;

public class AddStore extends MapActivity {
	@Override
    public void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.add_store);
        
        
        final MapView mMapView = (MapView) findViewById(R.id.mapView);
        
        
        // Получаем MapController
        MapController mMapController = mMapView.getController();
      
        GPSTracker gps = new GPSTracker(this);
    	if(gps.canGetLocation()){ 
    		//Getting Latitude and Longitude
    		GeoPoint currentLocation = new GeoPoint((int)gps.getLatitude()*1000000,(int)gps.getLongitude*1000000); 
    		mMapController.animateTo(currentLocation);
    	} else {
    		//Showing GPS Settings Alert Dialog
    		gps.showSettingsAlert();
    	}
    	
    	
        mMapController.setZoom(15);
        
        
//        final MapView mapView = (MapView) findViewById(R.id.mapView);
//        mapView.setBuiltInZoomControls(true);
//        
//        
//        List<Overlay> mapOverlays = mapView.getOverlays();
//        Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
//        MyItemizedOverlay itemizedoverlay = new MyItemizedOverlay(drawable,this);
//        
//        GeoPoint point = new GeoPoint(30443769,-91158458);
//        OverlayItem overlayitem = new OverlayItem(point, "Laissez les bon temps rouler!", "I'm in Louisiana!");
//
//        GeoPoint point2 = new GeoPoint(17385812,78480667);
//        OverlayItem overlayitem2 = new OverlayItem(point2, "Namashkaar!", "I'm in Hyderabad, India!");
//
//        itemizedoverlay.addOverlay(overlayitem);
//        itemizedoverlay.addOverlay(overlayitem2);
//
//        mapOverlays.add(itemizedoverlay);
}
        

   
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
