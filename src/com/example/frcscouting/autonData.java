package com.example.frcscouting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class autonData extends Activity { 
	/** Called when the activity is first created. */
	String secondBall = null;
	String thirdBall = null;
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
		else{
			alliance.setText("Red");
		}

	}
	
	public void lowClear(View view){
		RadioGroup rg2 = (RadioGroup)findViewById(R.id.radioGroup2);
		rg2.clearCheck();
	}
	public void highClear(View view){
		RadioGroup rg1 = (RadioGroup)findViewById(R.id.radioGroup1);
		rg1.clearCheck();
	}
	public void twoBall(View view1){
		final CharSequence[] items={"High hot","High","High Miss","Low","Low Miss"};
    	AlertDialog.Builder secondBallDialog=new AlertDialog.Builder(this);
    	secondBallDialog.setTitle("Select the valid turnout for the second ball");
    	secondBallDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override				

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
    	secondBallDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {teleopInitiate(null);
			}
		});
    	
     	secondBallDialog.setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				if("High hot".equals(items[which]))
				{
					secondBall="High hot";
				}
				else if("High".equals(items[which]))
				{
					secondBall="High";

				}
				else if("High Miss".equals(items[which]))
				{
					secondBall="High miss";

				}
				else if("Low".equals(items[which]))
				{
					secondBall="low";

				}
				else if("Low Miss".equals(items[which]))
				{
					secondBall="Low miss";

				}
				
				
			}
		});
    	secondBallDialog.show();
	}
	public void threeBall(View view2){
		final CharSequence[] items={"High hot","High","High Miss","Low","Low Miss"};
    	AlertDialog.Builder thirdBallDialog1=new AlertDialog.Builder(this);
    	thirdBallDialog1.setTitle("Select the valid turnout for the second ball");
    	thirdBallDialog1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override				

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
    	thirdBallDialog1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {threeBall2();
			}
		});
    	
    	thirdBallDialog1.setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				if("High hot".equals(items[which]))
				{
					secondBall="High hot";
				}
				else if("High".equals(items[which]))
				{
					secondBall="High";

				}
				else if("High Miss".equals(items[which]))
				{
					secondBall="High miss";

				}
				else if("Low".equals(items[which]))
				{
					secondBall="low";

				}
				else if("Low Miss".equals(items[which]))
				{
					secondBall="Low miss";

				}
				
				
			}
		});
    	thirdBallDialog1.show();

	}
	public void threeBall2(){
		final CharSequence[] items={"High hot","High","High Miss","Low","Low Miss"};
    	AlertDialog.Builder thirdBallDialog2=new AlertDialog.Builder(this);
    	thirdBallDialog2.setTitle("Select the valid turnout for the third ball");
    	thirdBallDialog2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override				

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
    	thirdBallDialog2.setPositiveButton("ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {teleopInitiate(null);
			}
		});
    	
    	thirdBallDialog2.setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				if("High hot".equals(items[which]))
				{
					thirdBall="High hot";
				}
				else if("High".equals(items[which]))
				{
					thirdBall="High";

				}
				else if("High Miss".equals(items[which]))
				{
					thirdBall="High miss";

				}
				else if("Low".equals(items[which]))
				{
					thirdBall="low";

				}
				else if("Low Miss".equals(items[which]))
				{
					thirdBall="Low miss";

				}
				
				
			}
		});
    	thirdBallDialog2.show();
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
		int highGoalAuton = 17;
		int lowGoalAuton = 17;
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

		if(((highGoalAuton != 0 || lowGoalAuton != 0 )&& moves == true)|| (moves == false && highGoalAuton == 0 && lowGoalAuton == 0)|| moves == true){
			Intent il = new Intent(getApplicationContext(), Teleop.class);
            String matchnumber = getIntent().getStringExtra("matchnumber");
            il.putExtra("teamNumber",teamNumber);
            il.putExtra("matchnumber",matchnumber);
			il.putExtra("isRed",isRed);
			il.putExtra("isBlue",isBlue);
			il.putExtra("moves", moves);
			il.putExtra("highGoalAuton", highGoalAuton);
			il.putExtra("lowGoalAuton", lowGoalAuton);
			il.putExtra("secondBall", secondBall);
			il.putExtra("thirdBall", thirdBall);
			startActivity(il);}	
		else{
			Toast.makeText(getApplicationContext(), "You failed to enter a value under one or more sections! Please check your input and try again.",
					Toast.LENGTH_LONG).show();
		}
	}

}


