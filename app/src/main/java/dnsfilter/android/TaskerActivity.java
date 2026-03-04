package dnsfilter.android;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class TaskerActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Do nothing here, wait for onResume so the app is fully in the foreground
    }

    @Override
    protected void onResume() {
        super.onResume();
        
        Intent intent = getIntent();
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            Intent serviceIntent = new Intent(this, DNSFilterService.class);
            
            try {
                // Double check that Android hasn't revoked our VPN permission while idle
                Intent vpnPrepare = VpnService.prepare(this);
                if (vpnPrepare != null) {
                    Log.e("TaskerActivity", "VPN Permission missing or revoked!");
                } else {
                    if ("dnsfilter.android.intent.START".equals(action)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startForegroundService(serviceIntent);
                        } else {
                            startService(serviceIntent);
                        }
                    } else if ("dnsfilter.android.intent.STOP".equals(action)) {
                        serviceIntent.setAction("dnsfilter.android.intent.ACTION_SHUTDOWN");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startForegroundService(serviceIntent);
                        } else {
                            startService(serviceIntent);
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("TaskerActivity", "Failed to send intent to service", e);
            }
        }
        
        // Delay the finish by half a second. 
        // This keeps the app in the foreground just long enough for the VPN to establish!
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 500); 
    }
}
