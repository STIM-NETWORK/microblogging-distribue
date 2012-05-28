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

import java.util.Vector;

import org.servalproject.maps.rhizome.Rhizome;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Tweets extends ListActivity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Vector<String> tweets = Rhizome.getTweets(this, UserList.User);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, tweets);
		// set background image
		getListView().setCacheColorHint(0);
		getListView().setBackgroundResource(R.drawable.bg);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
		//TODO implémenter Reply, Retweet, Favorite
		//TODO changer l'ordre d'affichage des tweets (le tweet le plus récent est au début de la liste et non à la fin)
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	public void onDestroy() {
		super.onDestroy();
	}
}
