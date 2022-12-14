package fpt.aptech.hss.Controller;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

//import fpt.aptech.hss.API.TokenAPI;
import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.FlashActivity;
import fpt.aptech.hss.Screen.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFirebaseService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if (message.getNotification() != null) {
            String title = message.getNotification().getTitle();
            String body = message.getNotification().getBody();
            System.out.println( title);
            System.out.println( body);
            sendNotification(title, body);
        }
    }
    private void sendNotification(String messageTitle, String messageBody) {
        Intent intent = new Intent(this, FlashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, /* Request code */intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.string_default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.l6)
                        .setColor(1)
                        .setContentTitle(messageTitle)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    @Override
    public void onNewToken(@NonNull String token) {
        //tokenServices = TokenUtilAPI.getTokenServices();
        sendRegistrationToServer(token);
        System.out.println(token);

    }
    private void sendRegistrationToServer(String token) {
        DataAPI.api.TokenAdd(token).enqueue(new Callback<ModelString>() {
            @Override
            public void onResponse(Call<ModelString> call, Response<ModelString> response) {

            }

            @Override
            public void onFailure(Call<ModelString> call, Throwable t) {

            }
        });
    }
}