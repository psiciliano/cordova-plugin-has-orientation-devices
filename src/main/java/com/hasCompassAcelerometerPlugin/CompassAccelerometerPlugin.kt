package com.tu_paquete.plugin

import org.apache.cordova.CallbackContext
import org.apache.cordova.CordovaPlugin
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager



class CompassAccelerometerPlugin : CordovaPlugin() {

    private const val FEATURE_SENSOR_COMPASS = "android.hardware.sensor.compass"
    private const val FEATURE_SENSOR_ACCELEROMETER = "android.hardware.sensor.accelerometer"


    override fun execute(action: String, args: JSONArray, callbackContext: CallbackContext): Boolean {
        when (action) {
            "hasCompass" -> {
                val hasCompass = hasCompass()
                callbackContext.success(if (hasCompass) 1 else 0)
                return true
            }
            "hasAccelerometer" -> {
                val hasAccelerometer = hasAccelerometer()
                callbackContext.success(if (hasAccelerometer) 1 else 0)
                return true
            }
        }
        return false
    }

    private fun checkCompass(): Boolean {
    val sensorManager = cordova.activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val compassSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    val hasCompass=this.hasCompass();

    if(!hasCompass) return false;

    if (compassSensor != null) {
        val hasPermission = cordova.activity.packageManager.checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION,cordova.activity.packageName) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            cordova.requestPermission { permissionResult ->
                 if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                     return true
                 } else {
                     return false
                 }
            }
            return false
        }
        return true
    }

    return false
    }


    private fun hasCompass(): Boolean {
        val packageManager = cordova.activity.packageManager
        val hasCompassFeature = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)
        return hasCompassFeature
    }


    private fun checkAccelerometer(): Boolean {
        val sensorManager = cordova.activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometerSensor != null) {
            val hasPermission =cordova.activity.packageManager.checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION,cordova.activity.packageName) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission) {
                 cordova.requestPermission { permissionResult ->
                     if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                         return true
                     } else {
                         return false
                     }
                }
                return false
            }

        return true
        }

    return false
    }

}



