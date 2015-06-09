package edu.washington.hyj.secretsms.MainActivity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.washington.hyj.secretsms.Preference.SaveUserPwd;
import edu.washington.hyj.secretsms.R;


public class RePwdActivity extends ActionBarActivity {

    private Spinner ret_qusetionsp;
    private EditText ret_answeret;
    private Button retpwdbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_pwd);

        ret_qusetionsp=(Spinner) findViewById(R.id.retspinner1);
        ret_answeret=(EditText) findViewById(R.id.reteditText1);
        retpwdbtn=(Button) findViewById(R.id.retbutton1);

        retpwdbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                find();
            }
        });
    }

    private void find() {
        // TODO Auto-generated method stub
        String answer=ret_answeret.getText().toString();
        String question=ret_qusetionsp.getSelectedItem().toString();

        SaveUserPwd util=new SaveUserPwd(this);
        String oldanswer=util.getAnswer();
        String oldquestion=util.getQuestion();

        if(answer.trim().equals(oldanswer.trim())&&question.equals(oldquestion)){
            String password=util.getPassword();
            Toast.makeText(this, "Your password:" + password, Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this, "Your answer is wrong!", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_re_pwd, menu);
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
