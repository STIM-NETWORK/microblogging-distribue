package i3.microblogging.distribue;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBAdapter {
	
	DatabaseHelper		DBHelper;
	Context				context;
	SQLiteDatabase		db;
	
	public DBAdapter(Context context) {
		this.context = context;
		DBHelper = new DatabaseHelper(context);
	}
	
	public class DatabaseHelper extends SQLiteOpenHelper {
		
		Context		context;

		public DatabaseHelper(Context context) {
			super(context, "tweets", null, 1);
			this.context = context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("Create table tweets (_id integer primary key autoincrement, "
					+ "sender text not null, sender_number text not null, content text not null, timestamp text not null "
					+ ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Toast.makeText(context, "Mise à jour de la base de données version " + oldVersion + " vers la version " + newVersion, Toast.LENGTH_SHORT).show();
			db.execSQL("DROP TABLE IF EXISTS produits");
			onCreate(db);
		}
		
	}
	
	//Permet d'ouvrir la BDD
	public DBAdapter open() {
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	
	//Permet de fermer la BDD
	public void close() {
		db.close();
	}
	
	//Permet de vider la BDD
	public void Truncate() {
		db.execSQL("DELETE FROM TWEETS");
	}
	
	public long saveTweet(String sender, String sender_number, String content, String timestamp) {
		ContentValues values = new ContentValues();
		values.put("sender", sender);
		values.put("sender_number", sender_number);
		values.put("content", content);
		values.put("timestamp", timestamp);
		
		return db.insert("tweets", null, values);
	}
	
	//Retourne vrai si le produit a bien été supprimé
	/*public Boolean supprimerProduit(long id) {
		return db.delete("tweets", "_id="+id, null) > 0;
	}*/
	
	
	public Cursor recupererTweets() {
		return db.query("tweets", new String [] {
				"_id",
				"sender",
				"sender_number",
				"content",
				"timestamp"}, null, null, null, null, null);
	}

}
