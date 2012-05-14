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

/*
 * Originally from:
 * https://github.com/servalproject/ServalMaps/blob/master/src/org/servalproject/maps/rhizome/Rhizome.java 
 */

package org.servalproject.maps.rhizome;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Vector;

import org.servalproject.maps.utils.FileUtils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.techxplorer.testmeshms.R;

/**
 * used to add a file to the Rhizome repository
 */
public class Rhizome {

	/*
	 * class level constants
	 */
	private static final String TAG = "Rhizome";

	/**
	 * add a file to the Rhizome repository
	 * 
	 * @param context a context object used to get access to system resources
	 * @param filePath the full path to the file
	 */
	public static void addFile(Context context, String filePath) {

		// check on the parameters
		if(context == null) {
			throw new IllegalArgumentException("the context parameter is required");
		}

		if(FileUtils.isFileReadable(filePath) == false) {
			throw new IllegalArgumentException("unable to access the specified file");
		}

		// get access to the preferences
		SharedPreferences mPreferences = context.getSharedPreferences("rhizome", Context.MODE_PRIVATE);

		String mVersionName = new File(filePath).getName() + "-version";

		Long mVersion = mPreferences.getLong(mVersionName, 0);

		// increment the version
		mVersion++;

		// get the name identifier
		String mName = context.getString(R.string.app_name);

		// build the intent
		Intent mIntent = new Intent("org.servalproject.rhizome.ADD_FILE");
		mIntent.putExtra("path", filePath);
		mIntent.putExtra("version", mVersion);
		mIntent.putExtra("author", mName);
		context.getApplicationContext().startService(mIntent);

		// update version
		Editor mEditor = mPreferences.edit();
		mEditor.putLong(mVersionName, mVersion);
		mEditor.commit();
		Log.i(TAG, "File commited : " + filePath);
	}

	/**
	 * check to see if a file is in Rhizome
	 * 
	 * @param filePath the full path to the file
	 * @return the full path to the file
	 */
	public static String checkForFile(Context context, String filePath) throws FileNotFoundException{

		// check on the parameters
		if(context == null) {
			throw new IllegalArgumentException("the context parameter is required");
		}

		if(TextUtils.isEmpty(filePath)) {
			throw new IllegalArgumentException("the file name parameter is required");
		}

		// check to see if the file is available
		if(FileUtils.isFileReadable(filePath) == true) {
			return filePath;
		} else {
			throw new FileNotFoundException("Unable to read the specified file : " + filePath);
		}
	}

	/**
	 * write to a file
	 * 
	 * @param context
	 * @param name of the file
	 * @param content to write
	 */
	public static void writeToFile(Context context, String fileName, String content) {
		String filePath = null;
		try {
			filePath = Rhizome.getRhizomeStimTweetsPath(context) + fileName;
		} catch (FileNotFoundException e) {
			Log.e(TAG, "Unable to find the file : " + filePath, e);
		}
		Log.i(TAG, "Write to file : " + filePath);
		BufferedWriter writer = null;
		try {
			FileWriter fw = new FileWriter(Rhizome.checkForFile(context, filePath), true);
			writer = new BufferedWriter(fw);
			writer.append(content);
			writer.newLine();
			Log.i(TAG, "Write to file : " + content);
			Rhizome.addFile(context, filePath);
		} catch (FileNotFoundException e1) {
			Log.e(TAG, "FileNotFoundException", e1);
			e1.printStackTrace();
		} catch (IOException e1) {
			Log.e(TAG, "IOException", e1);
			e1.printStackTrace();
		}
		//Close the BufferedWriter
		try {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	/** NB : this method is no longer use but can be useful as an example
	 * read a file
	 * 
	 * @param context
	 * @param filePath the full path to the file
	 */
	public static void readFile(Context context, String filePath) {
		Log.i(TAG, "Read file : " + filePath);
		BufferedReader in = null;
		try {
			FileReader fr = new FileReader(Rhizome.checkForFile(context, filePath));
			in = new BufferedReader(fr);
			String line;
			try {
				while ((line = in.readLine()) != null) {
					Log.i(TAG, "Read : " + line);
				}
			} catch (IOException e) {
				Log.e(TAG, "IOException", e);
			}
			//Close the BufferedReader
			try {
				if(fr != null) {
					in.close();
				}
			} catch (IOException e) {
				Log.e(TAG, "IOException", e);
			}
		}
		catch(FileNotFoundException e) {
			Log.e(TAG, "FileNotFoundException", e);
		}
	}


	/**
	 * get Tweets from a file
	 * 
	 * @param context
	 * @param Filename the name of the Tweet file 
	 * @return Vector<String> that contain all the tweet in the file
	 */
	public static Vector<String> getTweets(Context context, String fileName) {
		String filePath = null;
		try {
			filePath = Rhizome.getRhizomeDataPath(context) + fileName;
		} catch (FileNotFoundException e1) {
			Log.e(TAG, "Unable to find the file : "+fileName, e1);
		}
		Vector<String> Tweets = new Vector<String>();
		Log.i(TAG, "Read file : " + filePath);
		BufferedReader in = null;
		try {
			FileReader fr = new FileReader(Rhizome.checkForFile(context, filePath));
			in = new BufferedReader(fr);
			String line;
			try {
				while ((line = in.readLine()) != null) {
					Tweets.add(line);
				}
			} catch (IOException e) {
				Log.e(TAG, "IOException", e);
			}
			//Close the BufferedReader
			try {
				if(fr != null) {
					in.close();
				}
			} catch (IOException e) {
				Log.e(TAG, "IOException", e);
			}
		}
		catch(FileNotFoundException e) {
			Log.e(TAG, "FileNotFoundException", e);
		}
		return Tweets;
	}

	/**
	 * get the list of user (file in the Rhizome directory and with the extension ".stimtweet")
	 * 
	 * @param context
	 * @return a string array of file in the Rhizome directory and with the extension ".stimtweet"
	 * @throws FileNotFoundException
	 */
	public static String[] getListUser(Context context) throws FileNotFoundException {
		File mFile = new File(Rhizome.getRhizomeDataPath(context));
		
		//We use a file name filter to return only name of file with the extension ".stimtweet"
		FilenameFilter mFilenameFilter = new  FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".stimtweets");
			}
		};
		return(mFile.list(mFilenameFilter));
	}



	/**
	 * get the path to the Rhizome data store
	 * 
	 * @param context
	 * @return the path to the Rhizome data store 
	 * @throws FileNotFoundException
	 */
	public static String getRhizomeDataPath(Context context) throws FileNotFoundException {
		// get the rhizome data path
		String mRhizomeDataPath = context.getString(R.string.system_path_rhizome_data);
		try {
			String mExternal = Environment.getExternalStorageDirectory().getCanonicalPath();
			mRhizomeDataPath = mExternal + mRhizomeDataPath;
		} catch (IOException e) {
			Log.e(TAG, "unable to determine the full path to the Rhizome data store", e);
			throw new FileNotFoundException("unable to determine the full path to the Rhizome data store");
		}

		return(mRhizomeDataPath);
	}
	
	/**
	 * get the path to the Rhizome StimTweets directory
	 * 
	 * @param context
	 * @return the path to the Rhizome StimTweets directory
	 * @throws FileNotFoundException
	 */
	public static String getRhizomeStimTweetsPath(Context context) throws FileNotFoundException {
		// get the rhizome SimTweets path
		String mRhizomeStimTweetsPath = context.getString(R.string.system_path_rhizome_stimtweets);
		try {
			String mExternal = Environment.getExternalStorageDirectory().getCanonicalPath();
			mRhizomeStimTweetsPath = mExternal + mRhizomeStimTweetsPath;
			//Check if directory is writable
			FileUtils.isDirectoryWritable(mRhizomeStimTweetsPath);
		} catch (IOException e) {
			Log.e(TAG, "unable to determine the full path to the Rhizome data store", e);
			throw new FileNotFoundException("unable to determine the full path to the Rhizome data store");
		}

		return(mRhizomeStimTweetsPath);
	}
}