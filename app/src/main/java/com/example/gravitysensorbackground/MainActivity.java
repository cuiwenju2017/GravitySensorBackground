package com.example.gravitysensorbackground;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GyroscopeManager gyroscopeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = getPackageManager();
        // 获取是否支持电话
        boolean telephony = pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
        // 是否支持GSM
        boolean gsm = pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM);
        // 是否支持CDMA
        boolean cdma = pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA);
        //是否支持NFC
        boolean nfc = pm.hasSystemFeature(PackageManager.FEATURE_NFC);
        //是否支持陀螺仪传感器
        boolean gyroscope = pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE);
        Toast.makeText(MainActivity.this, gyroscope ? "支持：陀螺仪" : "不支持：陀螺仪", Toast.LENGTH_SHORT).show();

        gyroscopeManager = new GyroscopeManager();
        XImageView imageView1 = (XImageView) findViewById(R.id.iv1);
        XImageView imageView2 = (XImageView) findViewById(R.id.iv2);
        imageView1.setGyroscopeManager(gyroscopeManager);
        imageView2.setGyroscopeManager(gyroscopeManager);
    }

    @Override
    protected void onResume() {
        gyroscopeManager.register(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        gyroscopeManager.unregister();
        super.onPause();
    }
}