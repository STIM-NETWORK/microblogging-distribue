package i3.microblogging.distribue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.techxplorer.testmeshms.R;


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

		// capture the click on the buttons
		Button button_readTweets = (Button) findViewById(R.id.read_tweets);
		button_readTweets.setOnClickListener(this);
		Button button_writeTweet = (Button) findViewById(R.id.write_tweet);
		button_writeTweet.setOnClickListener(this);

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

