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

    private static final String TUTORIAL_URL = "http://onedrive.live.com/?authkey=%21ANwdKIEN5lNuGgQ&cid=2B3F4AE08BE4FF47&id=2B3F4AE08BE4FF47%2115500&group=0&parId=2B3F4AE08BE4FF47%2115490&o=OneUp";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_tutorial);
        VideoView vidView = (VideoView)findViewById(R.id.myVideo);
        //Uri vidUri = Uri.parse(TUTORIAL_URL);
        vidView.setVideoPath("/sdcard/skeleton.mp4");
       // vidView.setVideoURI(vidUri);
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);
        vidView.start();
        vidView.requestFocus();

    }


}
