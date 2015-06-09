package edu.washington.hyj.secretsms.MainActivity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.washington.hyj.secretsms.Database.BlockDbAdapter;
import edu.washington.hyj.secretsms.R;

public class SmsListActivity extends ActionBarActivity {
    String name,ctime,content,id;
    ListView message;
    BlockDbAdapter test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_list);

        //test = new BlockDbAdapter(getApplicationContext());
        //test.addBlockedSms("professor", "1234567890", "yyyy/mm/dd", "This is an awesome app!");
        message = (ListView) findViewById(R.id.SmsList);
        message.setAdapter(new MyAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sms_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent=new Intent();

        switch(id){
            case R.id.action_settings:
                return true;
            case R.id.blackList_add:
                intent.setClass(this, AddBlackActivity.class);
                startActivity(intent);
                break;
            case R.id.blackList_detail:
                intent.setClass(this, BlackListActivity.class);
                startActivity(intent);
            default :
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends BaseAdapter {
        Cursor c;
        LayoutInflater inflater;
        BlockDbAdapter db=new BlockDbAdapter(getApplicationContext());

        public MyAdapter(){
            inflater=LayoutInflater.from(getApplicationContext());
        }
        @Override
        public int getCount() {
            c=db.findAllBlockedSms();
            return c.getCount();
        }


        @Override
        public Object getItem(int position) {

            return 0;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //View v = null;
            MyViewHolder mViewHolder;

            if(convertView==null){
                //v=inflater.inflate(R.layout.activity_sms_list, null);
                convertView =inflater.inflate(R.layout.customlist, null);
                mViewHolder = new MyViewHolder();
                convertView.setTag(mViewHolder);
            }else {
                //v=convertView;
                mViewHolder = (MyViewHolder) convertView.getTag();
            }

            c.moveToPosition(position);
            name =c.getString(c.getColumnIndex(BlockDbAdapter.NAME));
            ctime=c.getString(c.getColumnIndex(BlockDbAdapter.CTIME));
            content=c.getString(c.getColumnIndex(BlockDbAdapter.CONTENT));
            id=c.getString(c.getColumnIndex(BlockDbAdapter.ID));

            mViewHolder.person = (ImageView) convertView.findViewById(R.id.ivPerson);
            mViewHolder.nameTv=(TextView) convertView.findViewById(R.id.name_textView1);
            mViewHolder.ctimeTv=(TextView) convertView.findViewById(R.id.ctime_textView2);
            LinearLayout toDetail = (LinearLayout) convertView.findViewById(R.id.toDetail);


            toDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("ctime", ctime);
                    intent.putExtra("content", content);
                    startActivity(intent);
                }
            });

            Button del=(Button) convertView.findViewById(R.id.del_button2);
            del.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    db.deleteSms(id);
                    notifyDataSetChanged();

                }
            });

            mViewHolder.nameTv.setText(name);
            mViewHolder.ctimeTv.setText(ctime);
            mViewHolder.person.setImageResource(R.mipmap.ic_launcher_message);

            return convertView;
        }

        private class MyViewHolder {
            TextView nameTv, ctimeTv;
            ImageView person;
        }

    }

}
