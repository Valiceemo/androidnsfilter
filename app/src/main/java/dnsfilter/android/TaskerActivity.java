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
        // Instantly kill the enter animation so the window doesn't slide or fade in
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        
        Intent intent = getIntent();
        if (intent != null && intent.getAction() != null) {
            String action = intent.getAction();
            Intent serviceIntent = new Intent(this, DNSFilterService.class);
            
            try {
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
        
        // Lowered the delay to 200ms and added the exit animation override
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                // Instantly kill the exit animation so it doesn't fade out
                overridePendingTransition(0, 0);
            }
        }, 200); 
    }
}
