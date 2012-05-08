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

package i3.microblogging.distribue;

import com.techxplorer.testmeshms.R;
import com.techxplorer.testmeshms.R.id;
import com.techxplorer.testmeshms.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The main activity for the StimTweet application
 */
public class MainActivity extends Activity implements OnClickListener
{

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
				User = mContent;
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
