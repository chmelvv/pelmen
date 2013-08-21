package cvv.pelmen;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;


public class Geocode {
	private static final String DEBUG_TAG = "PelmenHttp";
	private String  httpResult;

	public String getAddressByCoordinates(Context mContext, String url){
		
		ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	// Make async HTTP request. Result put in httpResult var
	    	new DownloadWebpageTask().execute(url);
	    	if (httpResult != null) {
				// We find string  "text": "<address>"
	    		Integer first = httpResult.trim().indexOf("text");
	    		Log.i("first", first.toString());
	    		if (first != -1) {
	    			first += "\"text\":\"".length()-1;
	    			Integer second = httpResult.indexOf('"', first+1);
	    			return httpResult.substring(first, second);
	    		} else { 
	    			return "Error";
	    		}
	    	}
	    }
		return ""; 
	    
	}
	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
	    @Override
	    protected String doInBackground(String... urls) {
	          
	        // params comes from the execute() call: params[0] is the url.
	        try {
	            return downloadUrl(urls[0]);
	        } catch (IOException e) {
	            return "Unable to retrieve web page. URL may be invalid.";
	        }
	    }
	    // onPostExecute displays the results of the AsyncTask.
	    @Override
	    protected void onPostExecute(String result) {
	       httpResult = result;
	   }
	    
	 // Given a URL, establishes an HttpUrlConnection and retrieves
	 // the web page content as a InputStream, which it returns as
	 // a string.
	 private String downloadUrl(String myurl) throws IOException {
	     InputStream is = null;
	     // Only display the first 500 characters of the retrieved
	     // web page content.
	     int len = 500;
	         
	     try {
	         URL url = new URL(myurl);
	         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	         conn.setReadTimeout(10000 /* milliseconds */);
	         conn.setConnectTimeout(15000 /* milliseconds */);
	         conn.setRequestMethod("GET");
	         conn.setDoInput(true);
	         // Starts the query
	         conn.connect();
	         int response = conn.getResponseCode();
	         Log.d(DEBUG_TAG, "The response is: " + response);
	         is = conn.getInputStream();

	         // Convert the InputStream into a string
	         String contentAsString = readIt(is, len);
	         return contentAsString;
	         
	     // Makes sure that the InputStream is closed after the app is
	     // finished using it.
	     } finally {
	         if (is != null) {
	             is.close();
	         } 
	     }
	 }
	 
	// Reads an InputStream and converts it to a String.
	 public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	     Reader reader = null;
	     reader = new InputStreamReader(stream, "UTF-8");        
	     char[] buffer = new char[len];
	     reader.read(buffer);
	     return new String(buffer);
	 }
	}
}