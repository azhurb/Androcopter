package com.azhurb.androcopter;

import android.app.Activity;
import android.hardware.*;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AndrocopterActivity extends Activity  implements SensorEventListener {
	
	private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private TextView mAzimuthValue;
    private TextView mPitchValue;
    private TextView mRollValue;
    
    /*public AndrocopterActivity() {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }*/
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mAzimuthValue = (TextView) this.findViewById(R.id.azimuth); 
        mPitchValue   = (TextView) this.findViewById(R.id.pitch); 
        mRollValue    = (TextView) this.findViewById(R.id.roll); 
        
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }
    
    
    
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	Log.d("AndrocopterActivity", "onAccuracyChanged");
    }

    public void onSensorChanged(SensorEvent event) {
    	Log.d("onSensorChanged", "Azimuth: "+event.values[0]+"; Pitch: "+event.values[1]+"; Roll: "+event.values[2]);
    	if (mAzimuthValue != null){
    		mAzimuthValue.setText("" +  event.values[0]);
    		mPitchValue.setText("" + event.values[1]);
    		mRollValue.setText("" + event.values[2]);
    	}
    }
    
}