package com.formation.android;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
// cette classe main a pour but d'apprendre comment on apprend les barres de progression 
public class Main extends Activity {
	 private Button monBouton;//
	 private ProgressDialog progressDialog;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	progressDialog = new ProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Log.i("Projet1", "Bonjour depuis le log");
        //Log.e("erreur","erreur depuis le logi");

        monBouton = (Button)findViewById(R.id.button1);
        monBouton.setOnClickListener(new OnClickListener()
        {
        	
        	public void onClick(View v)
        	{
        		traitementDesDonnees();
        	}
        });
        }
    private void traitementDesDonnees() {
    	// On ajoute un message à notre progress dialog
    	progressDialog.setMessage("Chargement en cours");
    	// On affiche notre message
    	progressDialog.show();
    	new Thread(new Runnable() {
    	@Override
    	public void run() {
    	// Boucle de 1 a 10
    	for (int i = 0; i < 10; i++) {
    	try {
    	// Attends 500 millisecondes
    	Thread.sleep(500);
    	} catch (InterruptedException e) {
    	e.printStackTrace();
    	}
    	}
    	// A la fin du traitement, on fait disparaitre notre message
    	progressDialog.dismiss();
    	}
    	}).start();
    	}
    }
