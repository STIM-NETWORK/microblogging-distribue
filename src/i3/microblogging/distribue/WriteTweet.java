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

import i3.microblogging.distribue.R;

import org.servalproject.maps.rhizome.Rhizome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class WriteTweet extends Activity implements OnClickListener {

	public static final String TAG = "WriteTweet"; 

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writetweet);

		// Edit the textView
		TextView tv = (TextView) findViewById(R.id.User);
		tv.append("Connecté en tant que "+MainActivity.User);

		// Set listener
		Button button_tweet = (Button) findViewById(R.id.send_tweet);
		button_tweet.setOnClickListener(this);

	}

	public void onClick(View v) {

		// check which button was touched
		switch(v.getId()){
		case R.id.send_tweet:
			//TODO Vérifier le nombre de caractère (140 caractères max)
			// get the content
			TextView mTextView = (TextView) findViewById(R.id.text);
			String mContent = mTextView.getText().toString();
			// check if the TextView is not empty
			if(mContent.length() == 0) {
				Log.i(TAG, "Champ vide");
				Toast.makeText(this, "Champ vide!", Toast.LENGTH_LONG).show();
			} else {
				// write in the user file 
				Rhizome.writeToFile(this, MainActivity.User+".stimtweets", mContent);

				// show a toast
				Toast.makeText(getBaseContext(), "Message envoyé!", Toast.LENGTH_LONG).show();

				// back to the user interface
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

