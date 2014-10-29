package cutonala.example.grabadora;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	private static final String LOG_TAG ="Grabadora";
	private MediaRecorder mediaRecorder;
	private MediaPlayer mediaPlayer;
	private static String fichero=Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp";
	private Button bGrabar, bDetener, bReproducir, bDetenerReproduccion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bGrabar = (Button) findViewById(R.id.bGrabar) ;
		bDetener = (Button) findViewById(R.id.bDetener) ;
		bReproducir = (Button) findViewById(R.id.bReproducir);
		bDetenerReproduccion = (Button) findViewById(R.id.bDetenerReproduccion);
		bDetener.setEnabled(false);
		bReproducir.setEnabled(false);
		bDetenerReproduccion.setEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void grabar(View view) {
		bGrabar.setEnabled(false);
		bDetener.setEnabled(true);
		bReproducir.setEnabled(false);
		bDetenerReproduccion.setEnabled(false);
		mediaRecorder = new MediaRecorder();
		mediaRecorder.setOutputFile(fichero);
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try {
			mediaRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "Fallo la grabacion");
		}
		mediaRecorder.start();
	}
	
	public void detenerGrabacion(View view) {
		mediaRecorder.stop();
		mediaRecorder.release();
		bGrabar.setEnabled(true);
		bDetener.setEnabled(false);
		bReproducir.setEnabled(true);
	}
	
	public void reproducir(View view) {
		bDetenerReproduccion.setEnabled(true);
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(fichero);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			Log.e (LOG_TAG,"Fal1o la reproduccion");
		}
	}
	
	public void detener(View view) {
		try {
			bDetenerReproduccion.setEnabled(true);
			mediaPlayer.stop();
		} catch (Exception e) {
			Log.e (LOG_TAG,"Fal1o la reproduccion");
		}
	}
}
