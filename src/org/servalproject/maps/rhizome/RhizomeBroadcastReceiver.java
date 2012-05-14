/*
 * Copyright (C) 2012 ESIROI. All rights reserved.
 * 
 * This file is part of the Serval Maps Software
 * 
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

/*
 * Originally from:
 * https://github.com/servalproject/ServalMaps/blob/master/src/org/servalproject/maps/rhizome/RhizomeBroadcastReceiver.java
 */

package org.servalproject.maps.rhizome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * receives the broadcasts from Rhizome about new files being available
 */
public class RhizomeBroadcastReceiver extends BroadcastReceiver {

	// private class level constants
	private final boolean V_LOG = true;
	private final String TAG = "RhizomeBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {

		Bundle mBundle = intent.getExtras();

		if(intent.getAction().equals("org.servalproject.rhizome.RECIEVE_FILE") == false) {
			Log.e(TAG, "called with an intent with an unexepcted intent action");
			return;
		}
		
		if(V_LOG && mBundle.getString("name").contains("stimtweets")) {
			//We retrieve the user name
			char[] user = mBundle.getString("name").toCharArray();
			int i = 0;
			String userString = new String();
			//TODO Trouver éventuellement une autre méthode d'obtenir le nom d'utilisateur
			while(user[i]!='.') {
				userString = userString + Character.toString(user[i]);
				i = i + 1;
			}
			Log.v(TAG, "received intent with action: " + intent.getAction());
			Log.v(TAG, "file name: " + mBundle.getString("path"));
			Log.v(TAG, "version: " + Long.toString(mBundle.getLong("version")));
			Log.v(TAG, "name: " + mBundle.getString("name"));
			Log.v(TAG, "user: " + userString);
			Toast.makeText(context, "Nouveau Tweet reçu de " + userString+"!", Toast.LENGTH_LONG).show();
		}
	}
}