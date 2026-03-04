package dnsfilter.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class TaskerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = getIntent();
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            Intent serviceIntent = new Intent(this, DNSFilterService.class);
            
            try {
                if ("dnsfilter.android.intent.START".equals(action)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(serviceIntent);
                    } else {
                        startService(serviceIntent);
                    }
                } else if ("dnsfilter.android.intent.STOP".equals(action)) {
                    stopService(serviceIntent);
                }
            } catch (Exception e) {
                // Ignore
            }
        }
        
        // Instantly kill this invisible activity so the user never sees it
        finish(); 
    }
}
