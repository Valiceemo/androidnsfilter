package dnsfilter.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log; // <-- IMPORT THIS

public class TaskerReceiver extends BroadcastReceiver {

    // Define a tag for our logs
    private static final String TAG = "TaskerReceiver_DEBUG";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Log.d(TAG, "Receiver called with null intent or action.");
            return;
        }

        String action = intent.getAction();
        Log.d(TAG, "onReceive called with action: " + action);

        if ("dnsfilter.android.action.START_FROM_TASKER".equals(action)) {
            Log.d(TAG, "Executing START action...");
            Intent startIntent = new Intent(context, DNSFilterService.class);
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(startIntent);
            } else {
                context.startService(startIntent);
            }
            Log.d(TAG, "startForegroundService() called.");

        } else if ("dnsfilter.android.action.STOP_FROM_TASKER".equals(action)) {
            Log.d(TAG, "Executing STOP action...");
            Intent stopIntent = new Intent(context, DNSFilterService.class);

            // This tells the service to run its new "stop" logic
            stopIntent.setAction(DNSFilterService.ACTION_STOP);

            // We use startForegroundService to deliver the "stop" intent
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(stopIntent);
            } else {
                context.startService(stopIntent);
            }
            Log.d(TAG, "Sent ACTION_STOP to service.");
        }
    }
}
