
package cvv.pelmen;

import android.location.*;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.*;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// @TODO
		// разместить в своем мобильном приложении в разделе «О программе» 
		// или ином подобном разделе мобильного приложения, гиперссылку на 
		// Условия использования сервиса Яндекс.Карты, размещенные по адресу: 
		// http://legal.yandex.ru/maps_termsofuse/, следующего вида - 
		// «Условия использования сервиса Яндекс.Карты».
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Request an instance of LocationManager from system
		// Obtain appropriate handle
		LocationManager locationManager =
		        (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		// Query needed location providers
		LocationProvider provider =
		        locationManager.getProvider(LocationManager.GPS_PROVIDER);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
	    super.onStart();

	    // This verification should be done during onStart() because the system calls
	    // this method when the user returns to the activity, which ensures the desired
	    // location provider is enabled each time the activity resumes from the stopped state.
	    LocationManager locationManager =
	            (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

	    if (!gpsEnabled) {
	        //@ TODO
	    	// Build an alert dialog here that requests that the user enable
	        // the location services, then when the user clicks the "OK" button,
	        // call enableLocationSettings()
	    }
	}

	private void enableLocationSettings() {
	    Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    startActivity(settingsIntent);
	}

}
