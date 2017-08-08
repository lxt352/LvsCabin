package com.wiseme.lvscabin.uimodule.location;

import android.Manifest.permission;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.LocaleList;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

import com.wiseme.lvscabin.R;
import com.wiseme.lvscabin.view.activity.MainActivity;

import java.util.List;

/**
 * @author lxt <lxt352@gmail.com>
 * @since 2017/8/7
 */

public class GpsService extends Service {

    private static final String TAG = GpsService.class.getSimpleName();

    private MockLocationProvider locationProvider;

    private LocationManager locationManager;

    private Listener gpsListener;

    private LocationListener locationListener;

    private boolean isMocking;

    public class LocalBinder extends Binder {

        GpsService getService() {
            return GpsService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
//        this.preferenceUtils = SharedPreferenceUtils.getSharedInstance(this);
//        this.isMocking = this.preferenceUtils.getBooleanValue(SharedPreferenceUtils.SHARED_STATUS_ON, false);
        this.locationListener = new LocationListener() {
            final /* synthetic */ GpsService service;

            {
                this.service = GpsService.this;
            }

            public void onLocationChanged(Location location) {
            }

            public void onProviderDisabled(String provider) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        };
        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        List<String> allProviders = locationManager.getAllProviders();
        if (allProviders.contains(LocationManager.NETWORK_PROVIDER))
            this.locationManager.requestLocationUpdates("network", 0, 0f, this.locationListener);
        if (allProviders.contains(LocationManager.GPS_PROVIDER))
            this.locationManager.requestLocationUpdates("gps", 0, 0f, this.locationListener);
        if (allProviders.contains(LocationManager.PASSIVE_PROVIDER))
            this.locationManager.requestLocationUpdates("passive", 0, 0f, this.locationListener);
//        if (allProviders.contains(LocationManager.NETWORK_PROVIDER))
//            this.locationManager.requestLocationUpdates("network", 100, 0.1f, this.locationListener);
//        if (allProviders.contains(LocationManager.GPS_PROVIDER))
//            this.locationManager.requestLocationUpdates("gps", 100, 0.1f, this.locationListener);
//        if (allProviders.contains(LocationManager.PASSIVE_PROVIDER))
//            this.locationManager.requestLocationUpdates("passive", 100, 0.1f, this.locationListener);
        LocationManager locationManager = this.locationManager;
        Listener anonymousClass2 = new Listener() {
            final /* synthetic */ GpsService service;

            {
                this.service = GpsService.this;
            }

            public void onGpsStatusChanged(int event) {
                switch (event) {
                    case 1:
                        if (this.service.locationProvider != null) {
                            this.service.locationProvider.updateCurrentMockLocation(0);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.gpsListener = anonymousClass2;
        locationManager.addGpsStatusListener(anonymousClass2);
    }

    public boolean startMockGPS(double latitude, double longtitude) throws SecurityException {
        if (this.isMocking) {
            changeMockGPS(latitude, longtitude);
            return true;
        }
        this.locationProvider = new MockLocationProvider(this);
        this.locationProvider.pushLocation(latitude, longtitude);
        this.isMocking = true;
        startNotification(latitude, longtitude);
//        this.preferenceUtils.setBooleanValue(SharedPreferenceUtils.SHARED_STATUS_ON, this.isMocking);
        return true;
    }

    public void changeMockGPS(double latitude, double longtitude) {
        if (!this.isMocking)
            return;
        this.locationProvider.pushLocation(latitude, longtitude);
        updateNotification(latitude, longtitude);
    }

    private void startNotification(double latitude, double longtitude) {
        startForeground(61356, buildNotification(latitude, longtitude));
//        this.notificationShowing = true;
    }

    private void updateNotification(double latitude, double longtitude) {
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(61356, buildNotification(latitude, longtitude));
    }

    private Notification buildNotification(double latitude, double longtitude) {
        return new Builder(this)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setContentText("Fake position: latitude " + latitude + " longtitude " + longtitude)
                .setContentTitle(getString(R.string.app_name))
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0))
                .setSmallIcon(R.mipmap.ic_launcher).build();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        return new LocalBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }
}
