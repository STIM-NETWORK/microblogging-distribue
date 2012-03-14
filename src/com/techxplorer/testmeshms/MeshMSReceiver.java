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
package com.techxplorer.testmeshms;

import org.servalproject.meshms.SimpleMeshMS;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * a test class for receiving the broadcast MeshMS messages
 */
public class MeshMSReceiver extends BroadcastReceiver {
	
	// private class level constants
	private final String TAG = "TestMeshMSReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		
		SimpleMeshMS  mSimpleMessage = (SimpleMeshMS) intent.getParcelableExtra("simple");
		
		Log.d(TAG, intent.getAction());
		
		if(mSimpleMessage != null) {
			// process a simple message
			Log.d(TAG, "simple message found");
			Log.d(TAG, "sender: " + mSimpleMessage.getSender());
			Log.d(TAG, "recipient: " + mSimpleMessage.getRecipient());
			Log.d(TAG, "content: " + mSimpleMessage.getContent());
			
			// show toast to help debugging
			Toast mToast = Toast.makeText(context, R.string.toast_new_message, Toast.LENGTH_SHORT);
			mToast.show();
		} else {
			// no message found
			Log.e(TAG, "no message found");
		}
		
		
	}

}
