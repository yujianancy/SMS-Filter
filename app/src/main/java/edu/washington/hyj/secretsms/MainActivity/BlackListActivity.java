package edu.washington.hyj.secretsms.MainActivity;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.washington.hyj.secretsms.Database.BlockDbAdapter;
import edu.washington.hyj.secretsms.R;

public class BlackListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAdapter adapter=new MyAdapter();
        setListAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter {
        BlockDbAdapter adapter;
        LayoutInflater inflater;
        Cursor c;
        public MyAdapter(){
            adapter=new BlockDbAdapter(getApplicationContext());
            inflater=LayoutInflater.from(getApplicationContext());
        }
        @Override
        public int getCount() {
            c=adapter.findAllBlackList();
            return c.getCount();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder mViewHolder;
            if(convertView == null){
                convertView =inflater.inflate(R.layout.activity_black_list, null);
                mViewHolder = new MyViewHolder();
                convertView.setTag(mViewHolder);

            }else{
                mViewHolder = (MyViewHolder) convertView.getTag();
            }

            mViewHolder.person = (ImageView) convertView.findViewById(R.id.ivPerson);
            mViewHolder.nameTv=(TextView) convertView.findViewById(R.id.black_list_textView1);
            mViewHolder.numberTv=(TextView) convertView.findViewById(R.id.black_list_textView2);
            mViewHolder.delete=(Button) convertView.findViewById(R.id.black_list_button1);

            c.moveToPosition(position);
            mViewHolder.nameTv.setText("Name: "+c.getString(c.getColumnIndex(BlockDbAdapter.NAME)));
            mViewHolder.numberTv.setText("PhoneNo: "+c.getString(c.getColumnIndex(BlockDbAdapter.NUMBER)));

            final String id=c.getString(c.getColumnIndex(BlockDbAdapter.ID));

            mViewHolder.delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    adapter.deleteBlack(id);
                    notifyDataSetChanged();
                }
            });


            return convertView;
        }

        private class MyViewHolder {
            TextView nameTv, numberTv;
            ImageView person;
            Button delete;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_black_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
