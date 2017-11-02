package live.bein.tarek.beinlive.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import live.bein.tarek.beinlive.R;

public class BeinEsp1 extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    Bundle b;
    View view;
    public String s;
    public BeinEsp1() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_bein_esp1, container, false);


        RequestQueue mRequestQueue = Volley.newRequestQueue(getContext());
        mRequestQueue.start();
        String url = "http://xxx.com/belive/getlink.php?ch="+b.getString("ch");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {

                            VideoView vidView = (VideoView)view.findViewById(R.id.beinEsp);

                            String vidAddress = "http://iptv.fastcccam.tv:8080/live/fasttest/002df6as/2639.ts";
                            //vidAddress = s;


                            Uri vidUri = Uri.parse(vidAddress);
                            MediaController vidControl = new MediaController(getContext());

                            vidView.setVideoURI(Uri.parse(response));
                            vidControl.setAnchorView(vidView);
                            vidView.setMediaController(vidControl);
                            vidView.start();

                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        mRequestQueue.add(stringRequest);

        return view;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        VideoView vidView = (VideoView)view.findViewById(R.id.beinEsp);
vidView.stopPlayback();
    }
}
