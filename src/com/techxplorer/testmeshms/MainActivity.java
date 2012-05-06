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

import i3.microblogging.distribue.Tweets;
import i3.microblogging.distribue.UserList;

import org.servalproject.maps.rhizome.Rhizome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * The main activity for the StimTweet application
 */
public class MainActivity extends Activity implements OnClickListener
{

	public static final String TAG = "MainActivity";

	String Filename = null;

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
		Button button_readTweets = (Button) findViewById(R.id.read_tweets);
		button_readTweets.setOnClickListener(this);
		Button button_writeTweet = (Button) findViewById(R.id.write_tweet);
		button_writeTweet.setOnClickListener(this);

	}

	/*
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {

		// check which button was touched
		switch(v.getId()){
		case R.id.read_tweets:
			Intent bdd_intent = new Intent(this, UserList.class);
			startActivity(bdd_intent);
			break;
//		case R.id.addFile:
//			Log.i(TAG, "Test bouton addFile");
//			try {
//				Rhizome.addFile(this, Rhizome.checkForFile(this, "test_addfile2"));
//				Rhizome.addFile(this, Rhizome.checkForFile(this, "test_rhizome"));
//			} catch (FileNotFoundException e) {
//				Log.e(TAG, "unable to determine the full path", e);
//			}
//			break;
		case R.id.write_tweet:
			Rhizome.writeToFile(this, "test_rhizome", "test");
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
	}
}
