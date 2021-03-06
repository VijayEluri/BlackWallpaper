package br.android.blackwallpaper2;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;
import br.android.blackwallpaper2.R;

public class BlackWallpaperActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//we don't need to reload the saved instance
		super.onCreate(savedInstanceState);

		AlertDialog alert = new AlertDialog.Builder(this).create();

		alert.setTitle(R.string.app_name);
		alert.setMessage("This will change your current background to pure black background. Are you sure?");
		alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//createNotification("There was a problem setting your wallpaper.");
				Toast.makeText(getApplicationContext(), "Action canceled", Toast.LENGTH_LONG).show();
				finish();
				
			}
		});
		
		alert.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					changeBackground();
					
					Toast.makeText(getApplicationContext(), "Your background was set", Toast.LENGTH_LONG).show();

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "There was a problem while setting your background", Toast.LENGTH_LONG).show();
				}
				finally
				{
					finish();
				}

			}
		});
		alert.show();
	}

	public void changeBackground() throws IOException {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		Bitmap backBitmap = Bitmap.createBitmap(metrics.widthPixels,
				metrics.heightPixels, Bitmap.Config.ARGB_4444);
		WallpaperManager.getInstance(getApplicationContext()).setBitmap(backBitmap);
	}



}