package africa.apeiron.batafind.SMS_ACTIVITIES;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import africa.apeiron.batafind.OpeningActivity;
import africa.apeiron.batafind.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Granson on 02-Apr-18.
 */

public class SmsReceiver extends BroadcastReceiver {

    String address, body;
    String number = "+254712288371";
    NotificationManager nm;
    Notification n;
    final int Id = 394275;

    @SuppressWarnings("deprecation")
    @Override
    public void onReceive(Context myContext, Intent myIntent) {
        // TODO Auto-generated method stub

        nm = (NotificationManager) myContext.getSystemService(NOTIFICATION_SERVICE);

        Bundle bundle = myIntent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[pdus.length];

        for (int i = 0; i < messages.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

            address = messages[i].getDisplayOriginatingAddress();
            body = messages[i].getDisplayMessageBody();

            if (address.contains(number)) {

                long[] vibrate = { 0, 200, 700 };
                Intent intent = new Intent(myContext, OpeningActivity.class);
                intent.putExtra("myKey", body);
                PendingIntent pendingIntent = PendingIntent.getActivity(myContext, 1, intent, 0);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Notification.Builder builder = new Notification.Builder(myContext);

                builder.setAutoCancel(false);
                builder.setTicker("this is ticker text");
                builder.setContentTitle("BataFind results");
                builder.setSmallIcon(R.drawable.bata_logo);
                builder.setContentIntent(pendingIntent);
                builder.setOngoing(true);
                builder.setContentText("Request successfully processed.");
                builder.setNumber(100);
                builder.setAutoCancel(true).setVibrate(vibrate);
                builder.build();

                n = builder.getNotification();
                nm.notify(Id, n);
            }
        }
    }

}
