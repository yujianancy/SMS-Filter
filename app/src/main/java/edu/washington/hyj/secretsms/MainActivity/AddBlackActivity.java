package edu.washington.hyj.secretsms.MainActivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.washington.hyj.secretsms.Database.BlockDbAdapter;
import edu.washington.hyj.secretsms.R;

public class AddBlackActivity extends ActionBarActivity {
    EditText nameet,numberet;
    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_black);

        nameet=(EditText) findViewById(R.id.addblackteditText1);
        numberet=(EditText) findViewById(R.id.addblackteditText2);
        addbtn=(Button) findViewById(R.id.addblacktbutton1);

        addbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                add();
                finish();
            }
        });
    }

    private void add() {
        // TODO Auto-generated method stub
        String name = nameet.getText().toString();
        String number=numberet.getText().toString();

        BlockDbAdapter db=new BlockDbAdapter(this);
        db.addBlackList(name,number);


        Intent intent=new Intent(this,BlackListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_black, menu);
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
