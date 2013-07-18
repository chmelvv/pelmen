/**
 * 
 */
package cvv.pelmen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

/**
 * @author Chmel-VV
 *
 */
public class FindNearest extends Activity {
			 
@Override
protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	
	LinearLayout ll = new LinearLayout(this);
	TextView textView = new TextView(this);
	
    GPSTracker gps = new GPSTracker(this);
	if(gps.canGetLocation()){ 
		//Getting Latitude and Longitude
		textView.setText("Latitude: " + gps.getLatitude() + "\nLongitude: " + gps.getLongitude());
		textView.setTextSize(50);
		ll.addView(textView);
	} else {
		//Showing GPS Settings Alert Dialog
		gps.showSettingsAlert();
	}
	
	// Really make views visible
	setContentView(ll);

}

@Override
protected void onResume() {
    super.onResume();
}

}
