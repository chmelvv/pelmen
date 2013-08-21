package cvv.pelmen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddStore extends Activity {
	EditText lat, lon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_store);
}

	public void getCoordinates(View view){
		
		GPSTracker gps = new GPSTracker(this);
    	if(gps.canGetLocation()){ 
    		//Getting Latitude and Longitude
    		lat = (EditText) findViewById(R.id.lat_field);
    		lon = (EditText) findViewById(R.id.lon_field);
    		lat.setText( ((Double)gps.getLatitude()).toString() );
    		lon.setText( ((Double)gps.getLongitude()).toString() );
    	} else {
    		//Showing GPS Settings Alert Dialog
    		gps.showSettingsAlert();
    	}
	}
	
	public void getAddress(View view){
		if (lat == null || lon == null) {
			Toast toast = Toast.makeText(this, "Please, enter Store coordinates first", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
		else {
			// URL example:
			// http://geocode-maps.yandex.ru/1.x/?results=1&kind=house&format=json&geocode=30.52086642935999,50.46592397135805
			String url = "http://geocode-maps.yandex.ru/1.x/?"
					+ "results=1"
					+ "&lang=uk-UA"
					+ "&kind=street"
					+ "&format=json"
					+ "&geocode="
						+ lon.getText().toString() + ","
						+ lat.getText().toString();
			EditText address = (EditText) findViewById(R.id.address_field);
			Geocode myGeocode = new Geocode();
			String decodedAddress = myGeocode.getAddressByCoordinates(this, url);
			if (decodedAddress.length() != 0) { 
				address.setText( decodedAddress );
			}
			else Toast.makeText(this, "No network connection ;(", Toast.LENGTH_LONG).show(); 
				
		}
	}

}




