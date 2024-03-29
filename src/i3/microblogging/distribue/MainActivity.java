/*
 * Copyright (C) 2012 ESIROI. All rights reserved.
 * StimTweets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * StimTweets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with StimTweets.  If not, see <http://www.gnu.org/licenses/>.
 */

package i3.microblogging.distribue;

import org.servalproject.maps.rhizome.RhizomeBroadcastReceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import i3.microblogging.distribue.R;

/**
 * The main activity for the StimTweet application
 */
public class MainActivity extends Activity implements OnClickListener
{

	RhizomeBroadcastReceiver receiver = null;
	
	public static final String TAG = "MainActivity";
	public static String User = null;

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
		Button button_connexion = (Button) findViewById(R.id.connexion);
		button_connexion.setOnClickListener(this);

		// capture broadcast intents
		IntentFilter mIntentFilter = new IntentFilter();
		mIntentFilter.addAction("org.servalproject.rhizome.RECIEVE_FILE");
		receiver = new RhizomeBroadcastReceiver();
		registerReceiver(new RhizomeBroadcastReceiver(), mIntentFilter);

	}

	/*
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {

		// check which button was touched
		switch(v.getId()){
		case R.id.connexion:
			TextView mTextView = (TextView) findViewById(R.id.identifiant);
			String mContent = mTextView.getText().toString();
			if(mContent.length() == 0) {
				Log.i(TAG, "Aucun nom d'utilisateur");
				Toast.makeText(this, "Veuillez entrer un nom d'utilisateur", Toast.LENGTH_LONG).show();
			} else {
				// get the user name
				User = mContent;
				// add the user name in the strings.xml file
				Resources res = getResources();
				String.format(res.getString(R.string.user_connected), User);
				// launch the user interface
				Intent UserList = new Intent(this, User.class);
				startActivity(UserList);
			}
			break;
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
