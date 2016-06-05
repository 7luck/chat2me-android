package io.sevenluck.client.chat2me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.sevenluck.client.chat2me.domain.HttpResult;
import io.sevenluck.client.chat2me.domain.Member;
import io.sevenluck.client.chat2me.tasks.RegisterAsyncTask;


/**
 * test commit
 */
public class MainActivity extends AppCompatActivity {


    private EditText nicknameTb;
    private EditText passwordTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);

        nicknameTb = (EditText) findViewById(R.id.nicknameText);
        passwordTb = (EditText) findViewById(R.id.passwordEdit);

        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterAsyncTask regTask = new RegisterAsyncTask(MainActivity.this, new RegistrationCallback());

                Member member = new Member();
                member.setNickname(nicknameTb.getText().toString());
                member.setPassword(passwordTb.getText().toString());
                regTask.execute(member);
            }
        });

    }

    public class RegistrationCallback {

        public void onFishedRequest(HttpResult<Member> result) {
            if (null == result) {
                return;
            }

            if (result.isSuceeded()) {
                Toast.makeText(getApplicationContext(), "Registrierung erfolgreich. Bitte loggen Sie sich ein.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }





}
