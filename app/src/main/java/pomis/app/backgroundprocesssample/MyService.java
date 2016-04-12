package pomis.app.backgroundprocesssample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {


    public static final int RESPONSE_CODE = 23423424;
    private static final int NOTIF_ID = 34234;
    int counter = 1;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    PendingIntent pendingIntent;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "Сервис запущен");
        //pendingIntent = intent.getParcelableExtra("pending");
        refresh();
        return super.onStartCommand(intent, flags, startId);
    }

    void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Log.d("MyService", "Прошло 5 sec.");
                    //pendingIntent.send(RESPONSE_CODE);
                    sendNotifications();
                    refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendNotifications() {
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("КАКОЙЕ-ТО УВЕДАМЛЕНИЭ")
                .setNumber(1)
                .setWhen(System.currentTimeMillis())
                .setContentText(String.valueOf(++counter))
                .setOngoing(true);
        Notification notification = builder.build();
        manager.notify(NOTIF_ID, notification);


    }
}
