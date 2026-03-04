package dnsfilter.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class TaskerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) return;

        String action = intent.getAction();
        Intent serviceIntent = new Intent(context, DNSFilterService.class);

        if ("dnsfilter.android.intent.START".equals(action)) {
            // Android 8+ requires foreground service explicitly
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent);
            } else {
                context.startService(serviceIntent);
            }
        } else if ("dnsfilter.android.intent.STOP".equals(action)) {
            context.stopService(serviceIntent);
        }
    }
}
