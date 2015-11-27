package com.example.snr.errand_helper;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoTutorial extends Activity {

    private static final String TUTORIAL_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_tutorial);
        VideoView vidView = (VideoView)findViewById(R.id.myVideo);
        Uri vidUri = Uri.parse(TUTORIAL_URL);
        vidView.setVideoURI(vidUri);
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);
        vidView.start();

    }


}
