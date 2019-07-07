package com.glomaxsolutions.smartsign_player.Controller;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

import com.glomaxsolutions.smartsign_player.R;

public class CampaignActivity extends AppCompatActivity {

    private final static int FILE_MANAGER_REQUEST_CODE = 105;
    private static final long ALERT_DIALOG_TIME_OUT = 3000;
    private static final String TAG = "";

    VideoView videoView;
    String folderName = "/Smart Sign Video List";
    String videoName = "/video.mp4";
    String videoPath = Environment.getExternalStorageDirectory() + folderName + videoName;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO Hide Status bar code below only two lines
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_campaign);

        VideoStreamer();

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(CampaignActivity.this, WebActivity.class));
                finish();
                return false;
            }
        });
    }

    //TODO Video Streamer Method
    private void VideoStreamer() {

        videoView = findViewById(R.id.video_view);
       // String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                videoView.start();

            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Toast.makeText(CampaignActivity.this, "Please upload video file.", Toast.LENGTH_LONG).show();
                UploadVideoDialog();
                return true;
            }
        });
    }

    private void UploadVideoDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Upload Video");
        dialog.setIcon(R.drawable.ic_file_upload);
        dialog.setMessage(R.string.video_file_isExist);
        dialog.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_warning)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to close this App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();

        // Auto Close Dialog
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog.cancel();
            }
        }, ALERT_DIALOG_TIME_OUT);
    }
}