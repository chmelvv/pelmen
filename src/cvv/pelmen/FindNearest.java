package cvv.pelmen;

import ru.yandex.yandexmapkit.*;
import ru.yandex.yandexmapkit.utils.GeoPoint;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class FindNearest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_nearest);
		
		final MapView mMapView = (MapView) findViewById(R.id.mapView);
		mMapView.showBuiltInScreenButtons(true);
		mMapView.showFindMeButton(true);
		mMapView.showJamsButton(true);
		mMapView.showScaleView(true);
		mMapView.showZoomButtons(true);
		 
		// Получаем MapController
		MapController mMapController = mMapView.getMapController();
		
        GPSTracker gps = new GPSTracker(this);
    	if(gps.canGetLocation()){ 
    		//Getting Latitude and Longitude
    		GeoPoint currentLocation = new GeoPoint(gps.getLatitude(),gps.getLongitude());
    		mMapController.setPositionAnimationTo(currentLocation);
    	} else {
    		//Showing GPS Settings Alert Dialog
    		gps.showSettingsAlert();
    	}
		 
		mMapController.setZoomCurrent(5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
