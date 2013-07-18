
package cvv.pelmen;

import android.os.Bundle;
import android.app.Activity;
import android.content.*;
import android.view.*;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Set up click listeners for all the buttons
		View findNearestButton = findViewById(R.id.findNearestButton);
		findNearestButton.setOnClickListener(this);
		
		View addStoreButton = findViewById(R.id.addStoreButton);
		addStoreButton.setOnClickListener(this);
		
		View catalogueButton = findViewById(R.id.catalogueButton);
		catalogueButton.setOnClickListener(this);
		
		View gameButton = findViewById(R.id.gameButton);
		gameButton.setOnClickListener(this);
		
		View aboutButton = findViewById(R.id.aboutButton);
		aboutButton.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.findNearestButton:
				Intent fN = new Intent(this, FindNearest.class);
				startActivity(fN);
				break;
			//@ TODO
//			case R.id.addStoreButton:
//				Intent a = new Intent(this, AddStore.class);
//				startActivity(a);
//				break;
//			case R.id.catalogueButton:
//				Intent c = new Intent(this, Catalog.class);
//				startActivity(c);
//				break;
//			case R.id.gameButton:
//				Intent g = new Intent(this, Game.class);
//				startActivity(g);
//				break;
//			case R.id.aboutButton:
//				Intent ab = new Intent(this, FindNearest.class);
//				startActivity(ab);
//				break;
			
		}
		
	}

}
