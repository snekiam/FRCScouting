package com.example.frcscouting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button start;
    EditText matchnumber1;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        matchnumber1 =  (EditText)findViewById(R.id.editText);
        matchnumber1.setText("");
	}
	public void onStartButtonClick(View view){
		Toast.makeText(getApplicationContext(), "Scouting app by Team 3322, still a WIP!",
				Toast.LENGTH_LONG).show();
        String matchnumber = matchnumber1.getText().toString();
		Intent myIntent = new Intent(MainActivity.this, teamSelect.class);
        myIntent.putExtra("matchnumber", matchnumber);
		MainActivity.this.startActivity(myIntent);
	}
}