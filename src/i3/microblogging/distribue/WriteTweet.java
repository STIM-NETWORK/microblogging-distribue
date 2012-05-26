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

import org.servalproject.maps.rhizome.Rhizome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.techxplorer.testmeshms.R;

public class WriteTweet extends Activity implements OnClickListener {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writetweet);
		
		Button button_tweet = (Button) findViewById(R.id.send_tweet);
		button_tweet.setOnClickListener(this);

	}

	public void onClick(View v) {

		// check which button was touched
		switch(v.getId()){
		case R.id.send_tweet:
			//TODO Vérifier le nombre de caractère (140 caractères max)
			TextView mTextView = (TextView) findViewById(R.id.text);
			String mContent = mTextView.getText().toString();
			Rhizome.writeToFile(this, MainActivity.User+".stimtweets", mContent);
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

