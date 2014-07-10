package com.example.frcscouting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class autonData extends Activity {


	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		String teamNumber = getIntent().getStringExtra("teamNumber");
		Boolean isRed = getIntent().getBooleanExtra("isRed", false);
		Boolean isBlue = getIntent().getBooleanExtra("isBlue",false);
		setContentView(R.layout.activity_autondata);
		super.onCreate(savedInstanceState);
		TextView textView = (TextView) findViewById(R.id.textView3);
		TextView alliance = (TextView) findViewById(R.id.textView4);
		textView.setText(teamNumber);
		if(isBlue == true && isRed == false){
			alliance.setText("Blue");
		}
		if(isRed == true && isBlue == false){
			alliance.setText("Red");
		}


	}
	public void teleopInitiate(View view){
		String teamNumber = getIntent().getStringExtra("teamNumber");
		Boolean isRed = getIntent().getBooleanExtra("isRed", false);
		Boolean isBlue = getIntent().getBooleanExtra("isBlue",false);
		boolean moves = ((CheckBox)findViewById(R.id.checkBox1)).isChecked();
		RadioButton highHot = (RadioButton)findViewById(R.id.radio0);
		RadioButton highNormal = (RadioButton)findViewById(R.id.radio1);
		RadioButton highMiss = (RadioButton)findViewById(R.id.radio2);
		RadioButton lowHit = (RadioButton)findViewById(R.id.radio3);
		RadioButton lowMiss = (RadioButton)findViewById(R.id.radio4);

		int highGoalAuton;
		int lowGoalAuton;
		if(highHot.isChecked())
			highGoalAuton = 1; 
		else if(highNormal.isChecked())
			highGoalAuton = 2;
		else if(highMiss.isChecked())
			highGoalAuton = 3;
		else
			highGoalAuton = 0;

		if(lowHit.isChecked())
			lowGoalAuton = 1;
		else if(lowMiss.isChecked())
			lowGoalAuton = 2;
		else
			lowGoalAuton = 0;
		Intent il = new Intent(getApplicationContext(), Teleop.class);
		il.putExtra("teamNumber",teamNumber);
		il.putExtra("isRed",isRed);
		il.putExtra("isBlue",isBlue);
		il.putExtra("moves", moves);
		il.putExtra("highGoalAuton", highGoalAuton);
		il.putExtra("lowGoalAuton", lowGoalAuton);
		startActivity(il);

	}

}