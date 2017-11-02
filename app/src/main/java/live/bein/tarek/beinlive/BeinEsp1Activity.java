package live.bein.tarek.beinlive;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class BeinEsp1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bein_esp1);

        VideoView vidView = (VideoView)findViewById(R.id.bb);

        String vidAddress = "http://iptv.fastcccam.tv:8080/live/fasttest/002df6as/2639.ts";
        Uri vidUri = Uri.parse(vidAddress);
        MediaController vidControl = new MediaController(this);

        vidView.setVideoURI(vidUri);
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);
        vidView.start();
    }
}
