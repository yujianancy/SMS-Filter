package edu.washington.hyj.secretsms.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.washington.hyj.secretsms.Preference.SaveUserPwd;
import edu.washington.hyj.secretsms.R;

public class RegisterActivity extends ActionBarActivity {
    private EditText passwordet,counpasswordet,answeret;
    private Button setbtn;
    private Spinner questionsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        passwordet = (EditText) findViewById(R.id.maineditText1);
        counpasswordet = (EditText) findViewById(R.id.maineditText2);
        answeret = (EditText) findViewById(R.id.maineditText3);
        setbtn = (Button) findViewById(R.id.mainbutton1);
        questionsp = (Spinner) findViewById(R.id.mainspinner1);

        setbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean flag = set();
                if (flag) {
                    Intent intent = new Intent();
                    intent.setClass(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean set() {
        String password = passwordet.getText().toString();
        String counpassword=counpasswordet.getText().toString();
        String answer=answeret.getText().toString();
        String question=questionsp.getSelectedItem().toString();

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter a password!", Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(counpassword)){
            Toast.makeText(this, "Please re-enter your password!", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!password.equals(counpassword)){
            Toast.makeText(this, "Your password doesn't match!", Toast.LENGTH_LONG).show();
            return false;
        }

        if(TextUtils.isEmpty(answer)){
            Toast.makeText(this, "Please enter an answer!", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!TextUtils.equals(counpassword, password)){
            Toast.makeText(this, "Your verify password is incorrect!", Toast.LENGTH_LONG).show();
            return false;
        }else{
            SaveUserPwd util=new SaveUserPwd(this);
            util.setPassword(password);
            util.setQuestion(question);
            util.setAnswer(answer);
            return true;

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
