package com.example.gyroscopeservice;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor mAccelerometer;
    Sensor mMagnetic;
    SensorManager mSensorManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this, 0, 0));

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);




    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mMagnetic, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);

    }

    float[] accel = new float[] {0,0,0};
    float[] magnet = new float[] {0,0,0};
    float[] rotationMatrix = new float[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    float[] orientation = new float[]  {0,0,0};

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accel = sensorEvent.values.clone();
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magnet = sensorEvent.values.clone();
        }
        SensorManager.getRotationMatrix(rotationMatrix, null, accel, magnet);
        SensorManager.getOrientation(rotationMatrix, orientation);
        setContentView(new MyView(this, (int) (Math.toDegrees(orientation[2])*20), (int) (-Math.toDegrees(orientation[1])*20)));


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}