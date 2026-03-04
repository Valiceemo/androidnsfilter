package dnsfilter.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

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
                    // Send the standard start command to the service
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(serviceIntent);
                    } else {
                        startService(serviceIntent);
                    }
                } else if ("dnsfilter.android.intent.STOP".equals(action)) {
                    // Send our custom shutdown command to the service
                    serviceIntent.setAction("dnsfilter.android.intent.ACTION_SHUTDOWN");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(serviceIntent);
                    } else {
                        startService(serviceIntent);
                    }
                }
            } catch (Exception e) {
                // Log the error so we can see it in Logcat if it fails, but don't crash the app
                Log.e("TaskerActivity", "Failed to send intent to service", e);
            }
        }
        
        // Instantly kill this invisible activity so the user never sees it
        finish(); 
    }
}
