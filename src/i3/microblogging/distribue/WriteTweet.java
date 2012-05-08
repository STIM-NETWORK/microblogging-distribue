package i3.microblogging.distribue;

import org.servalproject.maps.rhizome.Rhizome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.techxplorer.testmeshms.MainActivity;
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

