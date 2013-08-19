package cvv.pelmen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.app.Activity;
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
		if (lat == null || lon == null) 
			    Toast.makeText(this, "Please, enter Store coordinates first", Toast.LENGTH_SHORT).show();
		else {
			String URL = "http://geocode-maps.yandex.ru/1.x/?"
					+ "results=1"
					+ "&kind=house"
					//+ "&key=bZNyz0CtiO34Cj8ryedFTLiiXKB0OJ3ACNEExHI5qDZKLLw0~EUf9hhNADD6aM7fVLkdIYvW1XQrHEvSx7vXeeMrO4-xuxcjuRBnKTwPOsk="
					+ "&format=json"
					+ "&geocode="
						+ lon.toString() + ","
						+ lat.toString();
			EditText address = (EditText) findViewById(R.id.address_field);
			
			
		
/*	
 * 		{"response": 
			{"GeoObjectCollection":
				{"metaDataProperty":
					{"GeocoderResponseMetaData":
						{"request":"30.36593901476908,50.45725574442445","found":"4","results":"10","Point":{"pos":"30.365939 50.457256"}}},"featureMember":[
							{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"kind":"house","text":"Украина, Киев, улица Феодоры Пушиной, 23","precision":"exact","AddressDetails":{"Country":{"AddressLine":"Киев, улица Феодоры Пушиной, 23","CountryNameCode":"UA","CountryName":"Украина","Locality":{"LocalityName":"Киев","Thoroughfare":{"ThoroughfareName":"улица Феодоры Пушиной","Premise":{"PremiseNumber":"23"}}}}}}},"description":"Киев, Украина","name":"улица Феодоры Пушиной, 23","boundedBy":{"Envelope":{"lowerCorner":"30.364179 50.45605","upperCorner":"30.368276 50.458665"}},"Point":{"pos":"30.366228 50.457358"}}},
							{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"kind":"street","text":"Украина, Киев, улица Феодоры Пушиной","precision":"street","AddressDetails":{"Country":{"AddressLine":"Киев, улица Феодоры Пушиной","CountryNameCode":"UA","CountryName":"Украина","Locality":{"LocalityName":"Киев","Thoroughfare":{"ThoroughfareName":"улица Феодоры Пушиной"}}}}}},"description":"Киев, Украина","name":"улица Феодоры Пушиной","boundedBy":{"Envelope":{"lowerCorner":"30.340868 50.456188","upperCorner":"30.371968 50.458292"}},"Point":{"pos":"30.355771 50.457375"}}},
							{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"kind":"locality","text":"Украина, Киев","precision":"other","AddressDetails":{"Country":{"AddressLine":"Киев","CountryNameCode":"UA","CountryName":"Украина","Locality":{"LocalityName":"Киев"}}}}},"description":"Украина","name":"Киев","boundedBy":{"Envelope":{"lowerCorner":"30.239439 50.213269","upperCorner":"30.825941 50.590765"}},"Point":{"pos":"30.522328 50.451095"}}},
							{"GeoObject":{"metaDataProperty":{"GeocoderMetaData":{"kind":"country","text":"Украина","precision":"other","AddressDetails":{"Country":{"CountryNameCode":"UA","CountryName":"Украина"}}}},"name":"Украина","boundedBy":{"Envelope":{"lowerCorner":"22.135899 44.386438","upperCorner":"40.227511 52.379374"}},"Point":{"pos":"31.181700 48.541290"}}}]}}}
	*/
		
		
			HttpClient httpclient = new DefaultHttpClient();
		    
			try {
				HttpResponse response = httpclient.execute(new HttpGet(URL));
				StatusLine statusLine = response.getStatusLine();
				    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				        ByteArrayOutputStream out = new ByteArrayOutputStream();
				        response.getEntity().writeTo(out);
				        out.close();
				        address.setText(out.toString());
				        //..more logic
				    } else{
				        //Closes the connection.
				        response.getEntity().getContent().close();
				        throw new IOException(statusLine.getReasonPhrase());
				    }
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		}
	}
}

