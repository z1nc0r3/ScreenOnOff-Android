package com.zincore.screenonoff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private PowerManager.WakeLock wakeLock;
    private Button power;
    private boolean isOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        power = findViewById(R.id.power);
        wakeLock = screenOff();

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOn) {
                    wakeLock.release();
                    isOn = false;
                    power.setText("OFF");
                } else {
                    wakeLock.acquire();
                    isOn = true;
                    power.setText("ON");
                }
            }
        });
    }

    public PowerManager.WakeLock screenOff() {
        return ((PowerManager) getSystemService(POWER_SERVICE)).newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, "zincore:screenOffTag");
    }
}