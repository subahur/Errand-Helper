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

    private static final String TUTORIAL_URL = "http://webbie.org/youtube/skeleton.mp4";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_tutorial);

        VideoView vid = (VideoView) findViewById(R.id.videoView);
        Uri video = Uri.parse(TUTORIAL_URL);
        vid.setMediaController(new MediaController(this));
        vid.setVideoURI(video);
        vid.start();
        vid.requestFocus();

    }


}
