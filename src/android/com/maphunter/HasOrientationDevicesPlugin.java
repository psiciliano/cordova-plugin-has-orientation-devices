package com.maphunter;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class HasOrientationDevicesPlugin extends CordovaPlugin {

    private static final String FEATURE_SENSOR_COMPASS = PackageManager.FEATURE_SENSOR_COMPASS;
    private static final String FEATURE_SENSOR_ACCELEROMETER = PackageManager.FEATURE_SENSOR_ACCELEROMETER;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        switch (action) {
            case "hasCompass":
                boolean hasCompass = hasCompass();
                callbackContext.success(hasCompass ? 1 : 0);
                return true;
            case "hasAccelerometer":
                boolean hasAccelerometer = hasAccelerometer();
                callbackContext.success(hasAccelerometer ? 1 : 0);
                return true;
            case "checkCompass":
                boolean compassAvailable = checkCompass();
                callbackContext.success(compassAvailable ? 1 : 0);
                return true;
            case "checkAccelerometer":
                boolean accelerometerAvailable = checkAccelerometer();
                callbackContext.success(accelerometerAvailable ? 1 : 0);
                return true;
        }
        return false;
    }

    private boolean hasCompass() {
        SensorManager sensorManager = (SensorManager) cordova.getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor compassSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    
        return compassSensor != null;
    }

    private boolean hasAccelerometer() {
        SensorManager sensorManager = (SensorManager) cordova.getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        return accelerometerSensor != null;
    }


    private boolean checkCompass() {
        SensorManager sensorManager = (SensorManager) cordova.getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor compassSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    
        boolean hasCompass = this.hasCompass();
    
        if (!hasCompass) {
            return false;
        }
    
        if (compassSensor != null) {
            boolean hasPermission = cordova.getActivity().checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, android.os.Process.myPid(), android.os.Process.myUid()) == PackageManager.PERMISSION_GRANTED;
    
            if (!hasPermission) {
                cordova.requestPermission(this, 0, android.Manifest.permission.ACCESS_FINE_LOCATION);
                return false;
            }
    
            return true;
        }
    
        return false;
    }
    

    private boolean checkAccelerometer() {
        SensorManager sensorManager = (SensorManager) cordova.getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    
        boolean hasAccelerometer=hasAccelerometer();
        if(!hasAccelerometer) return false;

        if (accelerometerSensor != null) {
            boolean hasPermission = cordova.getActivity().checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, android.os.Process.myPid(), android.os.Process.myUid()) == PackageManager.PERMISSION_GRANTED;
    
            if (!hasPermission) {
                cordova.requestPermission(this, 0, android.Manifest.permission.ACCESS_FINE_LOCATION);
                return false;
            }
    
            return true;
        }
    
        return false;
    }
    
}

