package dnsfilter.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build; // <-- IMPORT THIS

public class TaskerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }

        String action = intent.getAction();

        if ("dnsfilter.android.action.START_FROM_TASKER".equals(action)) {
            Intent startIntent = new Intent(context, DNSFilterService.class);
            
            // --------------------------------------------------
            // THIS IS THE CORRECTED START CODE
            // --------------------------------------------------
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(startIntent);
            } else {
                context.startService(startIntent);
            }
            // --------------------------------------------------

        } else if ("dnsfilter.android.action.STOP_FROM_TASKER".equals(action)) {
            // This code was already correct
            Intent stopIntent = new Intent(context, DNSFilterService.class);
            context.stopService(stopIntent);
        }
    }
}
