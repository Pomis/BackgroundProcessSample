package pomis.app.backgroundprocesssample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ID = 021312;
    private static final int NOTIF_ID = 23984;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindButton();
    }

    private void bindButton() {
        Button b = ((Button)findViewById(R.id.b_craken));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent pendingIntent = createPendingResult(REQUEST_ID, new Intent(), 0);
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("pending", pendingIntent);
                startService(intent);
            }
        });

        Button buttonNotif = ((Button)findViewById(R.id.b_notif));
        assert buttonNotif != null;
        buttonNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotifications();
            }
        });

        Button broadCastButton = ((Button)findViewById(R.id.b_broadcast));
        broadCastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBroadcast();
            }
        });
    }

    private void startBroadcast() {
        Intent intent = new Intent();
        intent.setAction("pomis.app.backgroundprocesssample.EVENT");
        sendBroadcast(intent);
    }

    private void sendNotifications() {
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("КАКОЙЕ-ТО УВЕДАМЛЕНИЭ")
                .setNumber(1)
                .setTicker("БУГАГАШЕНЬКА!)))00")
                .setWhen(System.currentTimeMillis())
                .setContentText("ТЕКСТ УВЕДАМЛЕНИЙА")
                .setOngoing(true);
        Notification notification = builder.build();
        manager.notify(NOTIF_ID, notification);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case MyService.RESPONSE_CODE:
                Toast.makeText(this, "Сервис отработал своё и обратился к активности", Toast.LENGTH_LONG)
                        .show();
                break;
        }
    }
}
