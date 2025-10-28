package dnsfilter.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TaskerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }

        String action = intent.getAction();

        if ("dnsfilter.android.action.START_FROM_TASKER".equals(action)) {
            // This is the code to start the service from *within* the app
            Intent startIntent = new Intent(context, DNSFilterService.class);
            context.startService(startIntent);
        } else if ("dnsfilter.android.action.STOP_FROM_TASKER".equals(action)) {
            // This is the code to stop the service from *within* the app
            Intent stopIntent = new Intent(context, DNSFilterService.class);
            context.stopService(stopIntent); // <-- This is the correct way to stop it
        }
    }
}
