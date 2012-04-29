/*
 *  This file is part of TestMeshMS.
 *
 *  TestMeshMS is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  TestMeshMS is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with TestMeshMS.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Copyright 2012, Corey Wallis (techxplorer)
 */

package com.techxplorer.testmeshms;

import i3.microblogging.distribue.DBAdapter;
import i3.microblogging.distribue.Tweets;

import java.io.FileNotFoundException;

import org.servalproject.maps.rhizome.Rhizome;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * The main activity for the TestMeshMS application
 */
public class MainActivity extends Activity implements OnClickListener
{

	public static DBAdapter db;
	public static final String TAG = "MainActivity";

	String Filename = null;
	// private class level variables
	MeshMSReceiver receiver = null;

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// capture the click on the buttons
		Button button_tweets = (Button) findViewById(R.id.tweets);
		button_tweets.setOnClickListener(this);
		Button button_peerList = (Button) findViewById(R.id.peerList);
		button_peerList.setOnClickListener(this);
		Button button_addFile = (Button) findViewById(R.id.addFile);
		button_addFile.setOnClickListener(this);
		Button button_writeToFile = (Button) findViewById(R.id.writeToFile);
		button_writeToFile.setOnClickListener(this);
		Button button_readFile= (Button) findViewById(R.id.readFile);
		button_readFile.setOnClickListener(this);

		//Instanciation de la BDD
		db = new DBAdapter(this);
		db.open();

		// capture broadcast intents
		IntentFilter mIntentFilter = new IntentFilter();
		mIntentFilter.addAction("org.servalproject.meshms.RECEIVE_MESHMS");
		mIntentFilter.addAction("org.servalproject.meshms.RECEIVE_BROADCASTS");
		receiver = new MeshMSReceiver();
		registerReceiver(new MeshMSReceiver(), mIntentFilter);

	}

	/*
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {

		// check which button was touched
		switch(v.getId()){
		case R.id.tweets:
			Intent bdd_intent = new Intent(this, Tweets.class);
			startActivity(bdd_intent);
			break;
		case R.id.peerList:
			Log.i(TAG, "Test bouton peerlist");
			break;
		case R.id.addFile:
			Log.i(TAG, "Test bouton addFile");
			try {
				Rhizome.addFile(this, Rhizome.checkForFile(this, "test_addfile2"));
				Rhizome.addFile(this, Rhizome.checkForFile(this, "test_rhizome"));
			} catch (FileNotFoundException e) {
				Log.e(TAG, "unable to determine the full path", e);
			}
			break;
		case R.id.writeToFile:
			Rhizome.writeToFile(this, "test_rhizome", "test");
		case R.id.readFile:
			Rhizome.readFile(this, "test_rhizome");
		default:
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	public void onDestroy() {
		super.onDestroy();
		if(receiver != null) {
			unregisterReceiver(receiver);
		}
	}
}
