/**
 * 
 */
package com.example.frcscouting;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * @author Miles
 *
 */
public class teamSelect extends Activity {

	/** Called when the activity is first created. */
	EditText editText1;
	Button selectTeam;
	RadioGroup allianceSelect;
	RadioButton blue,red;
	boolean isRed = false;
	boolean isBlue = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auton);
		editText1 = (EditText)findViewById(R.id.editText1);
		selectTeam = (Button)findViewById(R.id.button1);
		allianceSelect = (RadioGroup) findViewById(R.id.allianceSelect);
		red = (RadioButton)findViewById(R.id.radioButton1);
		blue = (RadioButton)findViewById(R.id.radioButton2);

	}
	public void onTeamSelectButtonClick(View view){
		isRed = (red.isChecked());
		isBlue = (blue.isChecked());
		String teamNumber =  editText1.getText().toString();

		Intent i = new Intent(getApplicationContext(), autonData.class);
		i.putExtra("teamNumber",teamNumber);
		i.putExtra("isRed",isRed);
		i.putExtra("isBlue",isBlue);
		startActivity(i);
	}


}