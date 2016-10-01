package com.example.abc.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerseek;
    TextView simpletxt;
    Button but;
    boolean counterisactive = false;
    CountDownTimer countdowntimer;

public void resettimer(){
    simpletxt.setText("0:00");
    counterisactive=false;
    but.setText("START");
    timerseek.setProgress(30);
    countdowntimer.cancel();
    timerseek.setEnabled(true);
}
    public void updatetimer(int i) {
        int minutes = (int) i / 60;
        int seconds = i - minutes * 60;
        String sec = Integer.toString(seconds);
        if (seconds <= 9) {
            sec = "0" + sec;
        }
        simpletxt.setText(Integer.toString(minutes) + ":" + sec);
    }

    public void Controltimer(View view) {
        if (counterisactive == false)
        {
            but.setText("STOP");
        timerseek.setEnabled(false);
            counterisactive=true;

       countdowntimer= new CountDownTimer(timerseek.getProgress() * 1000+100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                // Log.i("button", "onTick: ");
                updatetimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                resettimer();
                MediaPlayer mplayer=MediaPlayer.create(getApplicationContext(),R.raw.aliensiren);
                mplayer.start();

            }
        }.start();
    }
        else{

           resettimer();
        }

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerseek=(SeekBar)findViewById(R.id.seekBar);
         simpletxt=(TextView)findViewById(R.id.TimertextView);
        but=(Button)findViewById(R.id.button);
        timerseek.setMax(600);
        timerseek.setProgress(30);
        timerseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

               updatetimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
