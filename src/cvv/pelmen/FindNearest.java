package cvv.pelmen;

import java.util.ArrayList;

import ru.yandex.yandexmapkit.*;
import ru.yandex.yandexmapkit.map.GeoCode;
import ru.yandex.yandexmapkit.map.GeoCodeListener;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;

@TargetApi(18)
public class FindNearest extends Activity implements GeoCodeListener{
    MapController mapController;
    OverlayManager overlayManager;
    MapView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_nearest);
	
		// Получаем объект MapView  
		mapView = (MapView) findViewById(R.id.map); 
		mapView.showBuiltInScreenButtons(true);
		 
		// Получаем объект MapController 
		mapController = mapView.getMapController();
		// Изменяем зум 
		mapController.setZoomCurrent(11);

		GPSTracker gps = new GPSTracker(this);
    	if(gps.canGetLocation()){ 
    		//Getting Latitude and Longitude
    		GeoPoint currentLocation = new GeoPoint(gps.getLatitude(),gps.getLongitude());
    		mapController.setPositionAnimationTo(currentLocation);
    	} else {
    		//Showing GPS Settings Alert Dialog
    		gps.showSettingsAlert();
    	}

		
		// Получаем объект OverlayManager  
		overlayManager = mapController.getOverlayManager();
		
		Resources res = getResources();
		Overlay overlay = new Overlay(mapController);
		
		//Add Pelmen shop points to ArrayList
		//@TODO convert to database store
		ArrayList<GeoPoint> pelmenStore = new ArrayList<GeoPoint>();
			pelmenStore.add(new GeoPoint(50.366064, 30.453759)); //вул. Ак.Глушкова, 31 а
			pelmenStore.add(new GeoPoint(50.364564, 30.46356));  //вул. Ак.Заболотного, 46
			pelmenStore.add(new GeoPoint(50.459514, 30.617226)); //вул. А.Малышко, 9
			pelmenStore.add(new GeoPoint(50.366064, 30.453759)); //вул. Братиславская, 14 б
			pelmenStore.add(new GeoPoint(50.366064, 30.453759)); //пр. Перемоги, 87
			pelmenStore.add(new GeoPoint(50.459761, 30.363603)); // вул. Василя Стуса 9
		
		//Add shops to the map
		// Load required resources
        for (int g=0; g < pelmenStore.size(); g++) {
			OverlayItem store = new OverlayItem(pelmenStore.get(g), res.getDrawable(R.drawable.small_pelmen));
			overlay.addOverlayItem(store);
			BalloonItem storeBalloon = new BalloonItem(this, store.getGeoPoint());
			storeBalloon.setText("My address");
			store.setBalloonItem(storeBalloon);
			overlay.addOverlayItem(store);
		}
		
        mapController.getDownloader().getGeoCode(this, new GeoPoint(50.366064, 30.453759)); 
     		// Обрабатываем ответ о GeoCode 
        		        
		//Добавляем новый слой 
		overlayManager.addOverlay(overlay);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public boolean onFinishGeoCode(GeoCode geoCode) { 
        if (geoCode!= null){ 
        		System.out.println(GeoCode.OBJECT_KIND_ROUTE);
        }
        return false;
    }
}
