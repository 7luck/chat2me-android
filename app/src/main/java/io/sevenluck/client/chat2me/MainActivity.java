package io.sevenluck.client.chat2me;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.sevenluck.client.chat2me.activity.TabActivity;
import io.sevenluck.client.chat2me.client.HttpResult;
import io.sevenluck.client.chat2me.domain.MemberTo;
import io.sevenluck.client.chat2me.tasks.LoginAsyncTask;
import io.sevenluck.client.chat2me.tasks.RegisterAsyncTask;
import io.sevenluck.client.chat2me.tasks.callbacks.HttpRequestCallback;


/**
 * test commit
 */
public class MainActivity extends AppCompatActivity {


    private EditText        nicknameTb;
    private EditText        passwordTb;
    private Button          loginBtn;
    private Button          registerBtn;
    private ProgressDialog  dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn    = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        nicknameTb  = (EditText) findViewById(R.id.nicknameText);
        passwordTb  = (EditText) findViewById(R.id.passwordEdit);
        dialog      = new ProgressDialog(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAsyncTask loginTask = new LoginAsyncTask(MainActivity.this, new LoginCallback());
                loginBtn.setEnabled(false);
                showDialog();
                loginTask.execute(getMember());
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterAsyncTask registrationTask = new RegisterAsyncTask(MainActivity.this, new RegistrationCallback());
                registerBtn.setEnabled(false);
                showDialog();
                registrationTask.execute(getMember());
            }
        });

    }

    private MemberTo getMember() {
        MemberTo member = new MemberTo();
        member.setNickname(nicknameTb.getText().toString());
        member.setPassword(passwordTb.getText().toString());

        return member;
    }

    private void showDialog() {
        dialog.setTitle("Loading");
        dialog.setMessage("Waiting while Loading...");
        dialog.show();
    }

    private void clearFields() {
        nicknameTb.setText("");
        passwordTb.setText("");
    }

    public class LoginCallback extends HttpRequestCallback<HttpResult<MemberTo>> {

        @Override
        public void onFishedRequest(HttpResult<MemberTo> result) {
            loginBtn.setEnabled(true);
            dialog.dismiss();

            if (result.isSuceeded()) {
                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }


    public class RegistrationCallback extends HttpRequestCallback<HttpResult<MemberTo>> {

        @Override
        public void onFishedRequest(HttpResult<MemberTo> result) {
            registerBtn.setEnabled(true);
            dialog.dismiss();
            if (null == result) {
                return;
            }

            if (result.isSuceeded()) {
                Toast.makeText(getApplicationContext(), "Registrierung erfolgreich. Bitte loggen Sie sich ein.", Toast.LENGTH_LONG).show();
                clearFields();
            } else {
                Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }





}
