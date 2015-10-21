package ru.kbles.dekar.tirneri;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//       / GPS.SetUpLocationListener(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*    public void update(Location loc) {
        if(loc == null)
        {
            JSONSender.sendPoint(
                    0,
                    0,
                    0,
                    0);
            return;
        }
        JSONSender.sendPoint(
                0,
                loc.getLatitude(),
                loc.getLongitude(),
                loc.getAccuracy());
    }
    public void onMainA(){
        LocationManager locationManager = (LocationManager)
                this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener ()
        {
            @Override
            public void onLocationChanged(Location loc) {

                update(loc);
            }
            @Override
            public void onProviderDisabled(String provider) {}
            @Override
            public void onProviderEnabled(String provider) {}
            @Override
            public void onStatusChanged (String provider, int status, Bundle extras) {

            }

        };

        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    500,
                    1,
                    locationListener,
                    Looper.myLooper()); // здесь можно указать другие более подходящие вам параметры

            update(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        }
        catch (SecurityException e)
        {
            String s = e.toString();
        }
        catch (Exception e)
        {
            String s = e.toString();
        }/////////////////////////
    }

*/
public  void updateClick(View v){
/*    Location loc = GPS.imHere;
    if(loc == null)
    {
        JSONSender.sendPoint(
                0,
                0,
                0,
                0);
        return;
    }
    JSONSender.sendPoint(
            0,
            loc.getLatitude(),
            loc.getLongitude(),
            loc.getAccuracy());*/

        Intent i= new Intent(this, GPSService.class);
// potentially add data to the intent
//        i.putExtra("KEY1", "Value to be used by the service");
        this.startService(i);

//        this.onMainA();

}
}


