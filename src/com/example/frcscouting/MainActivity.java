package com.example.frcscouting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button start;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void onStartButtonClick(View view){
		Toast.makeText(getApplicationContext(), "Scouting app by Team 3322, still a WIP!",
				Toast.LENGTH_LONG).show();
		Intent myIntent = new Intent(MainActivity.this, teamSelect.class);
		MainActivity.this.startActivity(myIntent);
	}
}