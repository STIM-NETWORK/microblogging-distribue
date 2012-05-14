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

import java.io.FileNotFoundException;

import org.servalproject.maps.rhizome.Rhizome;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class UserList extends ListActivity {

	public static final String TAG="UserList";
	public static String User = null;
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		String[] listUser = null;
		try {
			listUser = Rhizome.getListUser(this);
		} catch (FileNotFoundException e) {
			Log.e(TAG, "FileNotFoundException", e);
			e.printStackTrace();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listUser);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		User = item;
		Intent Tweets = new Intent(this, Tweets.class);
		startActivity(Tweets);
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	public void onDestroy() {
		super.onDestroy();
	}
}
