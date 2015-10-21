package ru.kbles.dekar.tirneri;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import static android.os.SystemClock.sleep;

/**
 * Created by 1 on 21.10.2015.
 */
public class GPSService extends Service {

    @Override
    public IBinder onBind (Intent intent){
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void  myStartInThread()
    {
        Looper.prepare();
        /////////////////////////
        //GPS.SetUpLocationListener(this);
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
        Looper.loop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                myStartInThread();
            }
        });
        myThread.start();
        return START_NOT_STICKY;
    }


    public void update(Location loc) {
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
/*
    public void update() {
        String l = GPS.imHere.toString();
        JSONSender.sendPoint(
                0,
                GPS.imHere.getLatitude(),
                GPS.imHere.getLongitude(),
                GPS.imHere.getAccuracy());
    }*/

}
