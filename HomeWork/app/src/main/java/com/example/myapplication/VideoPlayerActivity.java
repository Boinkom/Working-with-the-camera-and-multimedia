package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoView = findViewById(R.id.videoView);

        Uri videoUri = getIntent().getData();
        if (videoUri != null) {
            videoView.setVideoURI(videoUri);
            videoView.setOnPreparedListener(mp -> videoView.start());
        }
    }
}
