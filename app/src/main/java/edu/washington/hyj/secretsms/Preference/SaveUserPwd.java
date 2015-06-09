package edu.washington.hyj.secretsms.Preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yizhouhuang on 5/30/15.
 */
public class SaveUserPwd {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static final String PASSWORD="password";
    public static final String QUESTION="question";
    public static final String ANSWER="answer";

    public SaveUserPwd(Context context){
        sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
        editor=sp.edit();
    }
    public String getPassword(){
        String getPass = sp.getString(PASSWORD, "");
        return Desec(getPass);
    }
    public void setPassword(String password){
        editor.putString(PASSWORD, Secret(password));
        editor.commit();
    }

    private String Secret(String password){
        if (password.length()>1) {
            return password.substring(1,password.length())+password.substring(0,1);
        } else{
            return password;
        }
    }

    private String Desec(String getPass){
        if (getPass.length()>1) {
            return getPass.substring(getPass.length()-1,getPass.length())+getPass.substring(0,getPass.length()-1);
        } else{
            return getPass;
        }
    }
    public String getQuestion(){
        return sp.getString(QUESTION, "");
    }
    public void setQuestion(String question){
        editor.putString(QUESTION, question);
        editor.commit();
    }
    public String getAnswer(){
        return sp.getString(ANSWER, "");
    }
    public void setAnswer(String answer){
        editor.putString(ANSWER, answer);
        editor.commit();
    }
}
