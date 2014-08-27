package com.llp.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	LocationManager locmanager;
	EditText show;
	Location location;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (EditText)findViewById(R.id.show);
		
		locmanager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		location = locmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		updateView(location);
		locmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 8, new LocationListener() {
			
			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				//updateView(location);
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				updateView(locmanager.getLastKnownLocation(provider));
			}
			
			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				updateView(null);
			}
			
			@Override
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				updateView(arg0);
			}
		});
	
	}
	
	public void updateView(Location newlocation)
	{
		if(newlocation!=null)
		{
			String str;
			str = "经度为："+newlocation.getLongitude()+"\n纬度为："+newlocation.getLatitude();
			show.setText(str);
			Toast.makeText(MainActivity.this, "location has changed!", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(MainActivity.this, "location is null!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
