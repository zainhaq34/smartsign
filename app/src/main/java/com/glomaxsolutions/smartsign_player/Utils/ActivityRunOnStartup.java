package com.glomaxsolutions.smartsign_player.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.glomaxsolutions.smartsign_player.Controller.SplashActivity;

public class ActivityRunOnStartup extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent i = new Intent(context, SplashActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
