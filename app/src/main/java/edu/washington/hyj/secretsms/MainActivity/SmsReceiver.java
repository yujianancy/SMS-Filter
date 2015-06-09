package edu.washington.hyj.secretsms.MainActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.washington.hyj.secretsms.Database.BlockDbAdapter;

/**
 * Created by yizhouhuang on 5/30/15.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b=intent.getExtras();
        Object[] objs=(Object[]) b.get("pdus");
        BlockDbAdapter db=new BlockDbAdapter(context);

        for(int i=0;i<objs.length;i++){
            SmsMessage sms=SmsMessage.createFromPdu((byte[]) objs[i]);

            String number=sms.getDisplayOriginatingAddress();

            String content=sms.getDisplayMessageBody();

            String blackName=db.findNameByNumber(number);

            Date date=new Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            String ctime=f.format(date);

            Cursor c=db.findAllBlackList();
            if(null!=blackName){
                db.addBlockedSms(blackName, number, ctime, content);

                Toast.makeText(context, number + "Block", Toast.LENGTH_LONG).show();
                abortBroadcast();
                break;
            }

        }
    }
}
