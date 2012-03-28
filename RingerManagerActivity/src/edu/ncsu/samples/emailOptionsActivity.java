package edu.ncsu.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class emailOptionsActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emailoptions);
        EditText em2 = (EditText) findViewById(R.id.editText1);
        EditText pwd2 = (EditText) findViewById(R.id.editText2);
        em2.setText(MailSender.email);
        pwd2.setText(MailSender.password);
        
	Button save1 = (Button) findViewById(R.id.button1);
	save1.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
        	EditText em = (EditText) findViewById(R.id.editText1);
        	String emailid= em.getText().toString();
        	EditText pwd = (EditText) findViewById(R.id.editText2);
        	String pwd1= pwd.getText().toString();
        	
        	MailSender.email=emailid;
        	MailSender.password=pwd1;
        	
            Intent myIntent = new Intent(view.getContext(), SamplePlatysClientActivity.class);
            startActivityForResult(myIntent, 0);
        }

    });

	
	}
}
