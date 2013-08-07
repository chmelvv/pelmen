package cvv.pelmen;

import java.util.ArrayList;

import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;

public class FindNearest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_nearest);
		
		final MapView mapView = (MapView) findViewById(R.id.mapView);
		mapView.showBuiltInScreenButtons(true);
		mapView.showFindMeButton(true);
		mapView.showJamsButton(true);
		mapView.showScaleView(true);
		mapView.showZoomButtons(true);
		 
		// Получаем MapController
		MapController mapController = mapView.getMapController();

		// Pass map contorller to overlay to manage them
		Overlay overlay = new Overlay(mapController);
		OverlayManager overlayManager = mapController.getOverlayManager();
		
		//Add Pelmen shop points to ArrayList
		//@TODO convert to database store
		ArrayList<GeoPoint> pelmenStore = new ArrayList<GeoPoint>();
		pelmenStore.add(new GeoPoint(30.453759,50.366064)); //вул. Ак.Глушкова, 31 а
		pelmenStore.add(new GeoPoint(30.46356,50.364564));  //вул. Ак.Заболотного, 46
		pelmenStore.add(new GeoPoint(30.617226,50.459514)); //вул. А.Малышко, 9
		pelmenStore.add(new GeoPoint(30.453759,50.366064)); //вул. Братиславская, 14 б
		pelmenStore.add(new GeoPoint(30.453759,50.366064)); //пр. Перемоги, 87
		pelmenStore.add(new GeoPoint( 30.363603,50.459761)); // вул. Василя Стуса 9
		
		
		//Add shops to the map
		// Load required resources
        Resources res = getResources();
		for (int g=0; g <= pelmenStore.size(); g++) {
			OverlayItem pelmens = new OverlayItem(pelmenStore.get(g), res.getDrawable(R.drawable.pelmen));
		}
		
		
		// Add layer on map
		overlayManager.addOverlay(overlay);
		
		
		
        GPSTracker gps = new GPSTracker(this);
    	if(gps.canGetLocation()){ 
    		//Getting Latitude and Longitude
    		GeoPoint currentLocation = new GeoPoint(gps.getLatitude(),gps.getLongitude());
    		mapController.setPositionAnimationTo(currentLocation);
    	} else {
    		//Showing GPS Settings Alert Dialog
    		gps.showSettingsAlert();
    	}
		 
		mapController.setZoomCurrent(5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
