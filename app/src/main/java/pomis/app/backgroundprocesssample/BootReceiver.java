package pomis.app.backgroundprocesssample;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("SERVICE", "BROADCAST RECEIVED");
        Toast.makeText(context, "Бродкаст получен", Toast.LENGTH_LONG).show();
        Intent intent1 = new Intent(context, MyService.class);
        context.startService(intent1);
    }
}
