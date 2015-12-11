package com.android.mcsin.lab11;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private MediaPlayer song1;


    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.bn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivityForResult(intent, 0);
                startActivity(intent);
            }
        });





    }

    public void allocateSong1()
    {
        if(song1 == null)
            song1 = MediaPlayer.create(this.getBaseContext(), R.raw.menu);
        song1.setOnPreparedListener(song1PListener);
        song1.setOnCompletionListener(song1CListener);
    }

    private MediaPlayer.OnPreparedListener song1PListener = new MediaPlayer.OnPreparedListener()
    {
        @Override
        public void onPrepared(MediaPlayer mp)
        {
            playSong1();
        }
    };

    private MediaPlayer.OnCompletionListener song1CListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mp)
        {
            playSong1();
        }
    };

    public void playSong1()
    {
        if (song1.isPlaying())
        {
            song1.pause();
        }
        if(song1.getCurrentPosition() != 1)
        {
            song1.seekTo(1);
        }
        song1.start();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        allocateSong1();
    }


    @Override
    protected void onPause()
    {
        deallocateSong1();
        super.onPause();
    }

    public void deallocateSong1()
    {

        if (song1.isPlaying())
        {
            song1.stop();
        }
        if (!(song1 == null))
        {
            song1.release();
            song1 = null;
        }
        song1CListener = null;
        song1PListener = null;
    }






}
