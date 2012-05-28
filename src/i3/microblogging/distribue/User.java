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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import i3.microblogging.distribue.R;



public class User extends Activity implements OnClickListener
{

	public static final String TAG = "MainActivity";
	public static String User = MainActivity.User;

	String Filename = null;

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_interface);

		// Edit the textView
		TextView tv = (TextView) findViewById(R.id.User);
		tv.append("Connecté en tant que "+MainActivity.User);
		
		// capture the click on the buttons
		Button button_readTweets = (Button) findViewById(R.id.read_tweets);
		button_readTweets.setOnClickListener(this);
		Button button_writeTweet = (Button) findViewById(R.id.write_tweet);
		button_writeTweet.setOnClickListener(this);
		Button button_deconnexion = (Button) findViewById(R.id.deconnexion);
		button_deconnexion.setOnClickListener(this);

	}

	/*
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {

		// check which button was touched
		switch(v.getId()){
		case R.id.read_tweets:
			Intent UserList = new Intent(this, UserList.class);
			startActivity(UserList);
			break;
		case R.id.write_tweet:
			Intent WriteTweet = new Intent(this, WriteTweet.class);
			startActivity(WriteTweet);
			break;
		case R.id.deconnexion:
			MainActivity.User = null;
			Intent MainActivity = new Intent(this, MainActivity.class);
			startActivity(MainActivity);
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

