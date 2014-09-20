package com.example.frcscouting;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;

public class Teleop extends Activity {
	public static final int Bluetooth_Intent_ID=42;
	private static final String PATH = null;
	/** Called when the activity is first created. */

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.activity_teleop);
		super.onCreate(savedInstanceState);
		NumberPicker highGoal = (NumberPicker) findViewById(R.id.numberPicker2);
		NumberPicker highMiss = (NumberPicker) findViewById(R.id.numberPicker4);
		NumberPicker lowGoal = (NumberPicker) findViewById(R.id.numberPicker1);
		NumberPicker lowMiss = (NumberPicker) findViewById(R.id.numberPicker3);
		NumberPicker trussSuccess = (NumberPicker) findViewById(R.id.numberPicker5);
		NumberPicker trussFail = (NumberPicker) findViewById(R.id.numberPicker6);
		NumberPicker trussCatch = (NumberPicker) findViewById(R.id.numberPicker7);
		NumberPicker Assists = (NumberPicker) findViewById(R.id.numberPicker8);
        TextView teamnumber = ((TextView)findViewById(R.id.textView));
        String teamNumber = getIntent().getStringExtra("teamNumber");
        teamnumber.setText(teamNumber);
		highGoal.setMinValue(0);
		highGoal.setMaxValue(15);
		highMiss.setMinValue(0);
		highMiss.setMaxValue(15);
		lowGoal.setMinValue(0);
		lowGoal.setMaxValue(15);
		lowMiss.setMinValue(0);
		lowMiss.setMaxValue(15);
		trussSuccess.setMinValue(0);
		trussSuccess.setMaxValue(15);
		trussFail.setMinValue(0);
		trussFail.setMaxValue(15);
		trussCatch.setMinValue(0);
		trussCatch.setMaxValue(15);
		Assists.setMinValue(0);
		Assists.setMaxValue(15);
	} 
	static{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();  
		StrictMode.setThreadPolicy(policy);  
	}
	public void submitData(View view){
		NumberPicker highGoal = (NumberPicker) findViewById(R.id.numberPicker2);
		NumberPicker highMiss = (NumberPicker) findViewById(R.id.numberPicker4);
		NumberPicker lowGoal = (NumberPicker) findViewById(R.id.numberPicker1);
		NumberPicker lowMiss = (NumberPicker) findViewById(R.id.numberPicker3);
		NumberPicker trussSuccess = (NumberPicker) findViewById(R.id.numberPicker5);
		NumberPicker trussFail = (NumberPicker) findViewById(R.id.numberPicker6);
		NumberPicker trussCatch = (NumberPicker) findViewById(R.id.numberPicker7);
		NumberPicker Assists = (NumberPicker) findViewById(R.id.numberPicker8);
		CheckBox fail = (CheckBox) findViewById(R.id.checkBox1);
		int highGoals=highGoal.getValue();
		int highMisses=highMiss.getValue();
		int lowGoals=lowGoal.getValue();
		int lowMisses=lowMiss.getValue();
		int trusses=trussSuccess.getValue();
		int failedTrusses=trussFail.getValue();
		int caughtTrusses=trussCatch.getValue();
		int assists=Assists.getValue();
        String teamNumber = getIntent().getStringExtra("teamNumber");
        String twoBall = getIntent().getStringExtra("twoBall");
        String matchnumber = getIntent().getStringExtra("matchnumber");
        String threeBall = getIntent().getStringExtra("threeBall");
		boolean isRed = getIntent().getBooleanExtra("isRed", false);
		boolean isBlue = getIntent().getBooleanExtra("isBlue",false);
		boolean moves = getIntent().getBooleanExtra("moves", false);
		boolean fails = ((CheckBox)findViewById(R.id.checkBox1)).isChecked();
		int highGoalAuton = getIntent().getIntExtra("highGoalAuton", 1);
		int lowGoalAuton = getIntent().getIntExtra("lowGoalAuton", 1);
		String alliance = "null";
		String move = "null";
		String failed = "null";
		if(isBlue == true && isRed == false){
			alliance = "Blue";
		}
		if(isRed == true && isBlue == false){
			alliance = "Red";
		}
		if(moves == true){
			move = "Yes";
		}
		else
			move = "No";
		if(fails == true)
			failed = "Yes";
		else
			failed = "No";

		Workbook wb = new HSSFWorkbook();

		Sheet sheet1 = wb.createSheet("Data");

		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("0", new Object[]{teamNumber,alliance,move,highGoalAuton,twoBall,threeBall,lowGoalAuton,highGoals,highMisses,lowGoals,lowMisses,trusses,failedTrusses,caughtTrusses,assists,failed});
		int rownum = 0;
		Set<String> keyset = data.keySet();
		for (String key : keyset)
		{
			Row row = sheet1.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}
		}
		File root = Environment.getExternalStorageDirectory();
		File file = new File(root, "scouting_"+matchnumber+"_"+teamNumber+".xls");
		FileOutputStream os = null;
		try { 

			os = new FileOutputStream(file);
			wb.write(os);
			Log.w("FileUtils", "Writing file" + file); 
			Toast.makeText(getApplicationContext(), "File written to sdcard!",
					Toast.LENGTH_LONG).show();

		} catch (IOException e) { 
			Log.w("FileUtils", "Error writing " + file, e); 
			Toast.makeText(getApplicationContext(), "Error writing file.",
					Toast.LENGTH_LONG).show();
		} catch (Exception e) { 
			Log.w("FileUtils", "Failed to save file", e); 
			Toast.makeText(getApplicationContext(), "Error saving file.",
					Toast.LENGTH_LONG).show();
		} finally { 
			try { 
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SEND);
				intent.setType("image/*");
				intent.setPackage("com.android.bluetooth");
				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file) );
				startActivityForResult(intent,Bluetooth_Intent_ID);
				if (null != os) 
					os.close(); 

			} catch (Exception ex) { 
			} 
		} 

	}
	public void onActivityResult (int requestCode, int resultCode, Intent data){
		if(requestCode == Bluetooth_Intent_ID){
			Intent myIntent = new Intent(Teleop.this, MainActivity.class);
			Teleop.this.startActivity(myIntent);	
		}
	}
}
