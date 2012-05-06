package i3.microblogging.distribue;

import java.io.FileNotFoundException;

import org.servalproject.maps.rhizome.Rhizome;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class UserList extends ListActivity {

	public static final String TAG="UserList";
	
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
		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	public void onDestroy() {
		super.onDestroy();
	}
}
