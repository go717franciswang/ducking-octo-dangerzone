package com.example.sensordemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

@SuppressLint("InlinedApi")
public class DisplayStatActivity extends Activity implements
		SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private TextView xAxis;
	private TextView yAxis;
	private TextView zAxis;
	private Sensor mGyroscope;
	private TextView gxAxis;
	private TextView gyAxis;
	private TextView gzAxis;
	private Sensor mLight;
	private TextView light;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_stat);

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		mLight = mSensorManager
				.getDefaultSensor(Sensor.TYPE_LIGHT);

		xAxis = (TextView) findViewById(R.id.axisXValue);
		yAxis = (TextView) findViewById(R.id.axisYValue);
		zAxis = (TextView) findViewById(R.id.axisZValue);
		gxAxis = (TextView) findViewById(R.id.gyroAxisXValue);
		gyAxis = (TextView) findViewById(R.id.gyroAxisYValue);
		gzAxis = (TextView) findViewById(R.id.gyroAxisZValue);
		light = (TextView) findViewById(R.id.lightValue);
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this, mGyroscope,
				SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this, mLight,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	protected void onPause() {
		super.onPause();
        mSensorManager.unregisterListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_stat, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Sensor sensor = event.sensor;
		switch (sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			xAxis.setText(Float.toString(event.values[0]));
			yAxis.setText(Float.toString(event.values[1]));
			zAxis.setText(Float.toString(event.values[2]));
			break;

		case Sensor.TYPE_GYROSCOPE:
			gxAxis.setText(Float.toString(event.values[0]));
			gyAxis.setText(Float.toString(event.values[1]));
			gzAxis.setText(Float.toString(event.values[2]));
			break;

		case Sensor.TYPE_LIGHT:
			light.setText(Float.toString(event.values[0]));
			break;
		}
	}
}
