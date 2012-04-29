package i3.microblogging.distribue;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

import com.techxplorer.testmeshms.MainActivity;
import com.techxplorer.testmeshms.R;


public class Tweets extends ListActivity implements OnClickListener {

	DBAdapter db = MainActivity.db;

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweets);
		((Button)findViewById(R.id.rafraichir)).setOnClickListener(this);
		((Button)findViewById(R.id.send_tweet)).setOnClickListener(this);

		DataBind();
	}

	public void DataBind() {
		Cursor C = db.recupererTweets();
		startManagingCursor(C);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.list_item, C, new String[] {"sender", "content", "timestamp"},
				new int[] {R.id.sender, R.id.content, R.id.timestamp}
				);
		setListAdapter(adapter);
	}

	/*
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.send_tweet:
			// show the send a message activity
			Intent mSendActivityIntent = new Intent(this, com.techxplorer.testmeshms.SendActivity.class);
			startActivity(mSendActivityIntent);
			break;
		case R.id.rafraichir:
			DataBind(); //pour rafraichir la liste
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	public void onDestroy() {
		db.close();
		super.onDestroy();
	}
}
