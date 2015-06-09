package edu.washington.hyj.secretsms.MainActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.washington.hyj.secretsms.Preference.SaveUserPwd;
import edu.washington.hyj.secretsms.R;

public class MainActivity extends ActionBarActivity {
    private EditText login_pwdet;
    private Button quitLogin,loginbtn;
    private TextView retpwdbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_pwdet=(EditText) findViewById(R.id.logineditText1);
        quitLogin=(Button) findViewById(R.id.loginbutton1);
        loginbtn=(Button) findViewById(R.id.loginbutton2);
        retpwdbtn = (TextView) findViewById(R.id.forgot);

        if(check()){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }

        quitLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                MainActivity.this.finish();

            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String password = login_pwdet.getText().toString();
                SaveUserPwd util = new SaveUserPwd(MainActivity.this);
                String oldpassword = util.getPassword();
                if (password.equals(oldpassword)) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, SmsListActivity.class);
                    startActivity(intent);
                } else {
                    loginRetry();
                }
            }
        });

        retpwdbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RePwdActivity.class);
                startActivity(intent);

            }
        });
    }

    boolean check() {
        SaveUserPwd util = new SaveUserPwd(this);
        String password = util.getPassword();
        if (TextUtils.isEmpty(password)) {
            return true;
        } else
            return false;
    }

    public void loginRetry(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false)
                .setTitle("Prompt")
                .setMessage("Your password is incorrect,Do you want to retry?")
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("Forgot Password", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, RePwdActivity.class);
                        startActivity(intent);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
