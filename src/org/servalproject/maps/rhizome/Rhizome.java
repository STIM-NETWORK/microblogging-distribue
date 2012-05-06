/*
 * Copyright (C) 2012 The Serval Project
 *
 * This file is part of the Serval Maps Software
 *
 * Serval Maps Software is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This source code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this source code; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
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
	 * @param fileNname the name of the file to look for
	 * @return the full path to the file
	 */
	public static String checkForFile(Context context, String fileName) throws FileNotFoundException{

		// check on the parameters
		if(context == null) {
			throw new IllegalArgumentException("the context parameter is required");
		}

		if(TextUtils.isEmpty(fileName)) {
			throw new IllegalArgumentException("the file name parameter is required");
		}

		// check to see if the file is available
		String FullPath = Rhizome.getFullPath(context, fileName);
		if(FileUtils.isFileReadable(FullPath) == true) {
			return FullPath;
		} else {
			throw new FileNotFoundException("Unable to read the specified file : " + FullPath);
		}
	}

	/**
	 * write to a file
	 * 
	 * @param context
	 * @param path of the file
	 * @param content to write
	 */
	public static void writeToFile(Context context, String path, String content) {
		Log.i(TAG, "Write to file : " + path);
		BufferedWriter writer = null;
		try {
			FileWriter fw = new FileWriter(Rhizome.checkForFile(context, path), true);
			writer = new BufferedWriter(fw);
			writer.append(content);
			writer.newLine();
			Log.i(TAG, "Write to file : " + content);
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


	/**
	 * read a file
	 * 
	 * @param context
	 * @param Filename the file to read
	 */
	public static void readFile(Context context, String Filename) {
		Log.i(TAG, "Read file : " + Filename);
		BufferedReader in = null;
		try {
			FileReader fr = new FileReader(Rhizome.checkForFile(context, Filename));
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
	 * 
	 * @param context
	 * @param Filename the name of the Tweet file 
	 * @return
	 */
	public static Vector<String> getTweets(Context context, String Filename) {
		Vector<String> Tweets = new Vector<String>();
		//String[] Tweets = null;
		Log.i(TAG, "Read file : " + Filename);
		BufferedReader in = null;
		try {
			FileReader fr = new FileReader(Rhizome.checkForFile(context, Filename));
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
	 * 
	 * @param context
	 * @return a string array of file in the Rhizome directory and with the extension ".stimtweet"
	 * @throws FileNotFoundException
	 */
	public static String[] getListUser(Context context) throws FileNotFoundException {
		File mFile = new File(Rhizome.getRhizomePath(context));
		
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
	 * 
	 * @param context
	 * @return the path to the Rhizome data store 
	 * @throws FileNotFoundException
	 */
	public static String getRhizomePath(Context context) throws FileNotFoundException {
		// get the rhizome path
		String mRhizomePath = context.getString(R.string.system_path_rhizome_data);
		try {
			String mExternal = Environment.getExternalStorageDirectory().getCanonicalPath();
			mRhizomePath = mExternal + mRhizomePath;
		} catch (IOException e) {
			Log.e(TAG, "unable to determine the full path to the Rhizome data store", e);
			throw new FileNotFoundException("unable to determine the full path to the Rhizome data store");
		}

		return(mRhizomePath);
	}

	/**
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getFullPath(Context context, String fileName) throws FileNotFoundException {
		// check on the parameters
		if(context == null) {
			throw new IllegalArgumentException("the context parameter is required");
		}

		if(TextUtils.isEmpty(fileName)) {
			throw new IllegalArgumentException("the file name parameter is required");
		}

		// get the rhizome path
		String mRhizomePath = Rhizome.getRhizomePath(context);

		// check on the rhizome path
		if(FileUtils.isDirectoryWritable(mRhizomePath) == false) {
			Log.e(TAG, "unable to access the rhizome directory: " + mRhizomePath);
			throw new FileNotFoundException("unable to access the rhizome directory: " + mRhizomePath);
		}

		return(mRhizomePath + fileName);
	}
}