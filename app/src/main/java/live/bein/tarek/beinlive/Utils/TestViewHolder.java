package live.bein.tarek.beinlive.Utils;


import live.bein.tarek.beinlive.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.github.florent37.materialleanback.MaterialLeanBack;

/**
 * Created by Tarek on 08/01/2017.
 */

public class TestViewHolder extends MaterialLeanBack.ViewHolder {
    public TextView textViewNewLayout;
    public ImageView imageViewNewLayout;

    public TestViewHolder(View itemView) {
        super(itemView);
        textViewNewLayout = (TextView) itemView.findViewById(R.id.textViewNewLayout);
        imageViewNewLayout = (ImageView) itemView.findViewById(R.id.imageViewNewLayout);
    }
}
