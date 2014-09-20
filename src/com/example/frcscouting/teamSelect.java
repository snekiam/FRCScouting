/**
 * 
 */
package com.example.frcscouting;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * @author Miles
 *
 */
public class teamSelect extends Activity {

	/** Called when the activity is first created. */
	EditText teamNumberInput;
	Button selectTeam;
	RadioGroup allianceSelect;
	RadioButton blue,red;
	boolean isRed = false;
	boolean isBlue = false;
    String matchnumber = "0";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auton);
		teamNumberInput = (EditText)findViewById(R.id.editText1);
		teamNumberInput.setText("");
		selectTeam = (Button)findViewById(R.id.button1);
		allianceSelect = (RadioGroup) findViewById(R.id.allianceSelect);
		red = (RadioButton)findViewById(R.id.radioButton1);
		blue = (RadioButton)findViewById(R.id.radioButton2);
        matchnumber = getIntent().getStringExtra("matchnumber");

	}
	public void onTeamSelectButtonClick(View view){
		isRed = (red.isChecked());
		isBlue = (blue.isChecked());
		String teamNumber =  teamNumberInput.getText().toString();
		int teamCheck = (teamNumber).length();
		if((teamCheck == 1 || teamCheck == 2 || teamCheck == 3 || teamCheck == 4 )&& (isRed == true || isBlue == true)){
			Intent i = new Intent(getApplicationContext(), autonData.class);
            i.putExtra("matchnumber",matchnumber);
			i.putExtra("teamNumber",teamNumber);
			i.putExtra("isRed",isRed);
			i.putExtra("isBlue",isBlue);
			startActivity(i);
		}
		else
			Toast.makeText(getApplicationContext(), "You failed to enter in a valid value for one or more fields, please try again.",
					Toast.LENGTH_LONG).show();
	}


}