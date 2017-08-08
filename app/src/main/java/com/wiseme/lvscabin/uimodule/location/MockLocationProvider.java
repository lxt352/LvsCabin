package com.wiseme.lvscabin.uimodule.location;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MockLocationProvider implements OnSharedPreferenceChangeListener {
    public static final int East = 2;
    public static final int MOVEMENT_TYPE_CAR = 2;
    public static final int MOVEMENT_TYPE_PLANE = 3;
    public static final int MOVEMENT_TYPE_RUNNING = 1;
    public static final int MOVEMENT_TYPE_WALKING = 0;
    public static final int North = 0;
    public static final int North_East = 1;
    public static final int North_West = 7;
    public static final int South = 4;
    public static final int South_East = 3;
    public static final int South_West = 5;
    public static final int West = 6;
    static HashMap<Integer, Integer> l = new HashMap();
    Context context;
    LocationManager locationManager;
    Random mathRandom;
    Runnable runnable;
    Handler handler = new Handler();
    boolean isMoving;
    int movementType;
    int movementDirection;
    boolean altitude = false;
    int altitudeValue;
    SharedPreferences sharedPreferences;
    private List<String> locationProviders;
    private double n;
    private double latitude;
    private double longtitude;
    private float q;
    private float gpsAccuracy;
    private float networkAccuracy;

    public MockLocationProvider(Context ctx) {
        int i = 0;
        this.context = ctx;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        this.mathRandom = new Random();
        this.locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        this.locationProviders = this.locationManager.getAllProviders();
        while (i < this.locationProviders.size()) {
            if (this.locationProviders.get(i).matches("passive")) {
                this.locationProviders.remove(i);
                break;
            }
            i++;
        }
//        for (String str : this.locationProviders) {
//            LocationProvider provider = this.locationManager.getProvider(str);
//            if (!locationManager.getAllProviders().contains(str)) {
//                Log.d("TAG", "Add test provider " + str);
//                this.locationManager.addTestProvider(str, provider.requiresNetwork(), provider.requiresSatellite(), provider.requiresCell(), provider.hasMonetaryCost(), provider.supportsAltitude(), provider.supportsSpeed(), provider.supportsBearing(), provider.getPowerRequirement(), provider.getAccuracy());
//                this.locationManager.setTestProviderEnabled(str, true);
//                locationManager.setTestProviderStatus(str, 2, null, System.currentTimeMillis());
//            }
//        }
        locationManager.addTestProvider(LocationManager.GPS_PROVIDER, false, false, false, false, false, false, false, 1, 1);
        locationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);
        locationManager.setTestProviderStatus(LocationManager.GPS_PROVIDER, 2, null, System.currentTimeMillis());
        locationManager.addTestProvider(LocationManager.NETWORK_PROVIDER, true, false, true, false, false, false, false, 1, 2);
        locationManager.setTestProviderEnabled(LocationManager.NETWORK_PROVIDER, true);
        locationManager.setTestProviderStatus(LocationManager.NETWORK_PROVIDER, 2, null, System.currentTimeMillis());
        saveMovement();
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        saveMovement();
    }

    private void saveMovement() {
        this.isMoving = this.sharedPreferences.getBoolean("pref_movement", false);
//        this.movementType = a(this.sharedPreferences.getString("pref_movementType", this.context.getString(R.string.settings_movement_type_default)));
//        this.movementDirection = b(this.sharedPreferences.getString("pref_movementDirection", this.context.getString(R.string.settings_movement_type_default)));
        this.altitude = this.sharedPreferences.getBoolean("pref_altitude", true);
        try {
//            this.altitudeValue = Integer.parseInt(this.sharedPreferences.getString("pref_altitudeValue", this.context.getString(R.string.settings_altitude_default)));
        } catch (NumberFormatException e) {
            this.altitude = false;
        }
    }

    private int a(String str) {
        if (str.compareTo("Walking") == 0) {
            return 0;
        }
        if (str.compareTo("Running") == 0) {
            return 1;
        }
        if (str.compareTo("Driving") == 0) {
            return 2;
        }
        return 3;
    }

    private int b(String str) {
        if (str.compareTo("North") == 0) {
            return 0;
        }
        if (str.compareTo("Northeast") == 0) {
            return 1;
        }
        if (str.compareTo("East") == 0) {
            return 2;
        }
        if (str.compareTo("Southeast") == 0) {
            return 3;
        }
        if (str.compareTo("South") == 0) {
            return 4;
        }
        if (str.compareTo("Southwest") == 0) {
            return 5;
        }
        if (str.compareTo("West") == 0) {
            return 6;
        }
        return 7;
    }

    public void pushLocation(double lat, double lon) throws SecurityException {
        this.n = 50.0d + (this.mathRandom.nextDouble() * 457.0d);
        if (this.altitude) {
//            this.n = ((double) (((float) this.altitudeValue) + GroundOverlayOptions.NO_DIMENSION)) + (this.mathRandom.nextDouble() * 2.0d);
        }
        this.q = this.mathRandom.nextFloat() * 360.0f;
        this.gpsAccuracy = c("gps");
        this.networkAccuracy = c("network");
        this.latitude = lat;
        this.longtitude = lon;
        for (String a : this.locationProviders) {
            setTestProviderLocation(a, lat, lon);
        }
        if (this.runnable == null) {
            this.runnable = new Runnable() {
                long a = 0;
                final /* synthetic */ MockLocationProvider mockLocationProvider;

                {
                    this.mockLocationProvider = MockLocationProvider.this;
                }

                public void run() {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (this.a == 0) {
                        this.a = currentTimeMillis - 500;
                    }
                    this.mockLocationProvider.updateCurrentMockLocation(currentTimeMillis - this.a);
                    this.a = currentTimeMillis;
                    this.mockLocationProvider.handler.postDelayed(this.mockLocationProvider.runnable, (long) 50);
                }
            };
            this.handler.postDelayed(this.runnable, (long) (this.mathRandom.nextInt(1000) + 500));
//            this.handler.postDelayed(this.runnable, (long) (this.mathRandom.nextInt(1000) + 500));
        }
    }

    public void updateCurrentMockLocation(long deltaTime) {
        if (this.runnable != null) {
            if (this.isMoving) {
                a(deltaTime);
            }
            double nextDouble;
            if (this.mathRandom.nextInt() > 90) {
                nextDouble = (this.latitude - 5.0E-4d) + (10.0d * (this.mathRandom.nextDouble() / 10000.0d));
                nextDouble = (this.longtitude - 5.0E-4d) + (10.0d * (this.mathRandom.nextDouble() / 10000.0d));
                setTestProviderLocation("network", this.latitude, this.longtitude);
                return;
            }
            nextDouble = (this.latitude - 2.0E-4d) + (4.0d * (this.mathRandom.nextDouble() / 10000.0d));
            nextDouble = (this.longtitude - 2.0E-4d) + (4.0d * (this.mathRandom.nextDouble() / 10000.0d));
            setTestProviderLocation("gps", this.latitude, this.longtitude);
        }
    }

    private void a(long j) {
        float b = b(j);
        switch (this.movementDirection) {
            case 0:
                this.latitude = a(b) + this.latitude;
                break;
            case 1:
                this.longtitude += a(this.latitude, b / 2.0f);
                this.latitude = a(b / 2.0f) + this.latitude;
                break;
            case 2:
                this.longtitude = a(this.latitude, b) + this.longtitude;
                break;
            case 3:
                this.longtitude += a(this.latitude, b / 2.0f);
                this.latitude -= a(b / 2.0f);
                break;
            case 4:
                this.latitude -= a(b);
                break;
            case 5:
                this.longtitude -= a(this.latitude, b / 2.0f);
                this.latitude -= a(b / 2.0f);
                break;
            case 6:
                this.longtitude -= a(this.latitude, b);
                break;
            case 7:
                this.longtitude -= a(this.latitude, b / 2.0f);
                this.latitude = a(b / 2.0f) + this.latitude;
                break;
        }
        if (this.latitude > 90.0d) {
            this.latitude = 90.0d;
        }
        if (this.latitude < -90.0d) {
            this.latitude = 90.0d;
        }
        if (this.longtitude > 180.0d) {
            this.latitude = -180.0d;
        }
        if (this.latitude < -180.0d) {
            this.latitude = 180.0d;
        }
    }

    private double a(float f) {
        return (double) (f / 110.574f);
    }

    private double a(double d, float f) {
        return ((double) f) / (111.32d * Math.cos(Math.toRadians(d)));
    }

    private float b(long j) {
        return ((float) (((long) ((Integer) l.get(Integer.valueOf(this.movementType))).intValue()) * j)) / 3600000.0f;
    }

    static {
        l.put(Integer.valueOf(0), Integer.valueOf(5));
        l.put(Integer.valueOf(1), Integer.valueOf(10));
        l.put(Integer.valueOf(2), Integer.valueOf(80));
        l.put(Integer.valueOf(3), Integer.valueOf(800));
    }

    private void setTestProviderLocation(String str, double latitude, double longtitude) throws SecurityException {
        LocationProvider provider = this.locationManager.getProvider(str);
        Location location = new Location(str);
        if (provider.supportsAltitude()) {
            location.setAltitude(10.0d);
//            location.setAltitude((this.n - 1.0d) + ((double) (this.mathRandom.nextFloat() * 2.0f)));
        }
        if (provider.supportsSpeed()) {
//            location.setSpeed(this.mathRandom.nextFloat() * 0.665f);
        }
        if (provider.supportsBearing()) {
//            location.setBearing(((this.q - 10.0f) + (this.mathRandom.nextFloat() * 20.0f)) % 360.0f);
        }
        location.setLatitude(latitude);
        location.setLongitude(longtitude);
        if (this.mathRandom.nextInt(100) > 90) {
            this.gpsAccuracy += (float) (this.mathRandom.nextInt(4) - 2);
            this.gpsAccuracy = Math.min(Math.max(1.0f, this.gpsAccuracy), 22.0f);
            this.networkAccuracy += (float) (this.mathRandom.nextInt(8) - 4);
            this.networkAccuracy = Math.min(Math.max(6.0f, this.networkAccuracy), 50.0f);
        }
        location.setAccuracy(1.0f);
        location.setTime(System.currentTimeMillis());
//        location.setAccuracy(str.matches("gps") ? this.gpsAccuracy : this.networkAccuracy);
//        location.setTime(System.currentTimeMillis() - ((long) this.mathRandom.nextInt(InputDeviceCompat.SOURCE_KEYBOARD)));
        if (VERSION.SDK_INT >= 17) {
            location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        }
        if (locationManager.isProviderEnabled(str))
            this.locationManager.setTestProviderLocation(str, location);
    }

    private float c(String str) {
        if (str.matches("gps")) {
            return 1.0f + (this.mathRandom.nextFloat() * 15.0f);
        }
        return 6.0f + (this.mathRandom.nextFloat() * 44.0f);
    }

    public void shutdown() {
        this.handler.removeCallbacks(this.runnable);
        this.runnable = null;
        for (String removeTestProvider : this.locationProviders) {
            this.locationManager.removeTestProvider(removeTestProvider);
        }
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
