package live.bein.tarek.beinlive;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.florent37.materialleanback.MaterialLeanBack;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import live.bein.tarek.beinlive.Fragments.BeinEsp1;
import live.bein.tarek.beinlive.Fragments.NoCnxFragment;
import live.bein.tarek.beinlive.Utils.TestViewHolder;
import com.google.android.gms.ads.AdRequest;


public class MainActivity extends AppCompatActivity {

public static int r;
    InterstitialAd mInterstitialAd;

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5479210638221190~7487900266");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5479210638221190/1441366662");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                showInterstitial();
            }
        });


        startGame();

        OneSignal.startInit(this).init();


        final RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        if (isInternetOn() == false) {
            findViewById(R.id.materialLeanBack).setVisibility(View.INVISIBLE);
            findViewById(R.id.container).setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new NoCnxFragment()).commit();
        }
        else {
            MaterialLeanBack materialLeanBack = (MaterialLeanBack) findViewById(R.id.materialLeanBack);
            materialLeanBack.setAdapter(new MaterialLeanBack.Adapter<TestViewHolder>() {
                @Override
                public int getLineCount() {
                    return 7;
                }

                @Override
                public int getCellsCount(int line) {

                    if (line == 0 || line == 1) return 16;
                    else if (line == 2 || line == 3) return 3;
                    else if (line == 4) return 6;
                    else if (line == 5) return 5;
                    else {
                        mRequestQueue.start();
                        String url = "http://xxx.com/belive/getrows.php";
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        System.out.println(response);
                                        try {
                                            r = Integer.parseInt(response);
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
                        return r;
                    }
                }

                @Override
                public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int line) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.testlayout, viewGroup, false);
                    return new TestViewHolder(view);
                }

                @Override
                public void onBindViewHolder(final TestViewHolder viewHolder, final int i) {
                    final Bundle b = new Bundle();

                    if (viewHolder.row == 0) {
                        if (viewHolder.cell == 0) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b1ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 1");
                            b.putString("ch", "1");

                        }
                        if (viewHolder.cell == 1) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b2ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 2");
                            b.putString("ch", "2");
                        }
                        if (viewHolder.cell == 2) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b3ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 3");
                            b.putString("ch", "3");
                        }
                        if (viewHolder.cell == 3) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b4ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 4");
                            b.putString("ch", "4");
                        }
                        if (viewHolder.cell == 4) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b5ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 5");
                            b.putString("ch", "5");
                        }
                        if (viewHolder.cell == 5) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b6ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 6");
                            b.putString("ch", "6");
                        }
                        if (viewHolder.cell == 6) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b7ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 7");
                            b.putString("ch", "7");
                        }
                        if (viewHolder.cell == 7) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b8ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 8");
                            b.putString("ch", "8");
                        }
                        if (viewHolder.cell == 8) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b9ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 9");
                            b.putString("ch", "9");
                        }
                        if (viewHolder.cell == 9) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b10ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 10");
                            b.putString("ch", "10");
                        }
                        if (viewHolder.cell == 10) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b11ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 11");
                            b.putString("ch", "11");
                        }
                        if (viewHolder.cell == 11) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b12ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 12");
                            b.putString("ch", "12");
                        }
                        if (viewHolder.cell == 12) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b13ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 13");
                            b.putString("ch", "13");
                        }
                        if (viewHolder.cell == 13) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b14ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 14");
                            b.putString("ch", "14");
                        }
                        if (viewHolder.cell == 14) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b15ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 15");
                            b.putString("ch", "15");
                        }
                        if (viewHolder.cell == 15) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b16ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 16");
                            b.putString("ch", "16");
                        }
                    } else if (viewHolder.row == 1) {
                        if (viewHolder.cell == 0) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b1arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 1HD");
                            b.putString("ch", "1hd");
                        }
                        if (viewHolder.cell == 1) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b2arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 2HD");
                            b.putString("ch", "2hd");
                        }
                        if (viewHolder.cell == 2) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b3arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 3HD");
                            b.putString("ch", "3hd");
                        }
                        if (viewHolder.cell == 3) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b4arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 4HD");
                            b.putString("ch", "4hd");
                        }
                        if (viewHolder.cell == 4) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b5arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 5HD");
                            b.putString("ch", "5hd");
                        }
                        if (viewHolder.cell == 5) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b6arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 6HD");
                            b.putString("ch", "6hd");
                        }
                        if (viewHolder.cell == 6) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b7arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 7HD");
                            b.putString("ch", "7hd");
                        }
                        if (viewHolder.cell == 7) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b8arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 8HD");
                            b.putString("ch", "8hd");
                        }
                        if (viewHolder.cell == 8) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b9arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 9HD");
                            b.putString("ch", "9hd");
                        }
                        if (viewHolder.cell == 9) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b10arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 10HD");
                            b.putString("ch", "10hd");
                        }
                        if (viewHolder.cell == 10) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b11arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 11HD");
                            b.putString("ch", "11hd");
                        }
                        if (viewHolder.cell == 11) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b12arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 12HD");
                            b.putString("ch", "12hd");
                        }
                        if (viewHolder.cell == 12) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b13arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 13HD");
                            b.putString("ch", "13hd");
                        }
                        if (viewHolder.cell == 13) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b14arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 14HD");
                            b.putString("ch", "14hd");
                        }
                        if (viewHolder.cell == 14) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b15arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 15HD");
                            b.putString("ch", "15hd");
                        }
                        if (viewHolder.cell == 15) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b16arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein 16HD");
                            b.putString("ch", "16hd");
                        }
                    } else if (viewHolder.row == 2) {
                        if (viewHolder.cell == 0) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b1ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein Fr 1");
                            b.putString("ch", "1fr");

                        }
                        if (viewHolder.cell == 1) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b2ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein Fr 2");
                            b.putString("ch", "2fr");
                        }
                        if (viewHolder.cell == 2) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b3ar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein Fr 3");
                            b.putString("ch", "3fr");
                        }
                    } else if (viewHolder.row == 3) {
                        if (viewHolder.cell == 0) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b1arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein FR 1HD");
                            b.putString("ch", "1hdfr");
                        }
                        if (viewHolder.cell == 1) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b2arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein FR 2HD");
                            b.putString("ch", "2hdfr");
                        }
                        if (viewHolder.cell == 2) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.b3arhd).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein FR 3HD");
                            b.putString("ch", "3hdfr");
                        }
                    } else if (viewHolder.row == 4) {
                        if (viewHolder.cell == 0) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.movies1).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein Movies 1HD");
                            b.putString("ch", "mv1");
                        }
                        if (viewHolder.cell == 1) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.movies2).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein Movies 2HD");
                            b.putString("ch", "mv2");
                        }
                        if (viewHolder.cell == 2) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.movies3).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein Movies 3HD");
                            b.putString("ch", "mv3");
                        }
                        if (viewHolder.cell == 3) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.movies4).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein Movies 4HD");
                            b.putString("ch", "mv4");
                        }
                        if (viewHolder.cell == 4) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.series).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein Series");
                            b.putString("ch", "sr");
                        }
                        if (viewHolder.cell == 5) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.junior).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Bein Junior");
                            b.putString("ch", "jn");
                        }
                    } else if (viewHolder.row == 5) {
                        if (viewHolder.cell == 0) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.wataniya1).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Wataneya 1");
                            b.putString("ch", "wt1");
                        }
                        if (viewHolder.cell == 1) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.watania2).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Wataneya 2");
                            b.putString("ch", "wt2");
                        }
                        if (viewHolder.cell == 2) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.elhiwar).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Elhiwar Ettounssi");
                            b.putString("ch", "tns");
                        }
                        if (viewHolder.cell == 3) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.nessma).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Nessma TV");
                            b.putString("ch", "nsm");
                        }
                        if (viewHolder.cell == 4) {
                            Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(R.drawable.hannibaltv).into(viewHolder.imageViewNewLayout);

                            viewHolder.textViewNewLayout.setText("Hannibal TV");
                            b.putString("ch", "hnb");
                        }
                    } else if (viewHolder.row == 6) {
                        if (viewHolder.cell == 0) {
                            mRequestQueue.start();
                            String url = "http://xxx.com/belive/getsponsor.php";
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray array = new JSONArray(response);
                                                //for (int k = 0; k < array.length(); k++) {
                                                JSONObject j = array.getJSONObject(0);
                                                //if(j.getString("username").equals(login)&&j.getString("password").equals(password)) {
                                                viewHolder.textViewNewLayout.setText(j.getString("nom"));
                                                final String ss = j.getString("link");
                                                Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(j.getString("photo")).into(viewHolder.imageViewNewLayout);
                                                viewHolder.imageViewNewLayout.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Uri uri = Uri.parse(ss); // missing 'http://' will cause crashed
                                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                        startActivity(intent);
                                                    }
                                                });

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
                        }
                        if (viewHolder.cell == 1) {
                            mRequestQueue.start();
                            String url = "http://xxx.com/belive/getsponsor.php";
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray array = new JSONArray(response);
                                                JSONObject j = array.getJSONObject(1);
                                                viewHolder.textViewNewLayout.setText(j.getString("nom"));
                                                final String ss = j.getString("link");

                                                Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(j.getString("photo")).into(viewHolder.imageViewNewLayout);
                                                viewHolder.imageViewNewLayout.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Uri uri = Uri.parse(ss); // missing 'http://' will cause crashed
                                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                        startActivity(intent);
                                                    }
                                                });
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
                        }
                        if (viewHolder.cell == 2) {
                            mRequestQueue.start();
                            String url = "http://xxx.com/belive/getsponsor.php";
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray array = new JSONArray(response);
                                                //for (int k = 0; k < array.length(); k++) {
                                                JSONObject j = array.getJSONObject(2);
                                                //if(j.getString("username").equals(login)&&j.getString("password").equals(password)) {
                                                viewHolder.textViewNewLayout.setText(j.getString("nom"));
                                                final String ss = j.getString("link");

                                                   Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(j.getString("photo")).into(viewHolder.imageViewNewLayout);
                                                viewHolder.imageViewNewLayout.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Uri uri = Uri.parse(ss); // missing 'http://' will cause crashed
                                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                        startActivity(intent);
                                                    }
                                                });
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
                        }
                        if (viewHolder.cell == 3) {
                            mRequestQueue.start();
                            String url = "http://xxx.com/belive/getsponsor.php";
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray array = new JSONArray(response);
                                                //for (int k = 0; k < array.length(); k++) {
                                                JSONObject j = array.getJSONObject(3);
                                                //if(j.getString("username").equals(login)&&j.getString("password").equals(password)) {
                                                viewHolder.textViewNewLayout.setText(j.getString("nom"));
                                                final String ss = j.getString("link");

                                                Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(j.getString("photo")).into(viewHolder.imageViewNewLayout);
                                                viewHolder.imageViewNewLayout.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Uri uri = Uri.parse(ss); // missing 'http://' will cause crashed
                                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                        startActivity(intent);
                                                    }
                                                });
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
                        }
                        if (viewHolder.cell == 4) {
                            mRequestQueue.start();
                            String url = "http://xxx.com/belive/getsponsor.php";
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray array = new JSONArray(response);
                                                //for (int k = 0; k < array.length(); k++) {
                                                JSONObject j = array.getJSONObject(4);
                                                //if(j.getString("username").equals(login)&&j.getString("password").equals(password)) {
                                                viewHolder.textViewNewLayout.setText(j.getString("nom"));
                                                final String ss = j.getString("link");

                                                Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(j.getString("photo")).into(viewHolder.imageViewNewLayout);
                                                viewHolder.imageViewNewLayout.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Uri uri = Uri.parse(ss); // missing 'http://' will cause crashed
                                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                        startActivity(intent);
                                                    }
                                                });
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
                        }
                        if (viewHolder.cell == 5) {
                            mRequestQueue.start();
                            String url = "http://xxx.com/belive/getsponsor.php";
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray array = new JSONArray(response);
                                                //for (int k = 0; k < array.length(); k++) {
                                                JSONObject j = array.getJSONObject(5);
                                                //if(j.getString("username").equals(login)&&j.getString("password").equals(password)) {
                                                viewHolder.textViewNewLayout.setText(j.getString("nom"));
                                                final String ss = j.getString("link");

                                                Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(j.getString("photo")).into(viewHolder.imageViewNewLayout);
                                                viewHolder.imageViewNewLayout.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Uri uri = Uri.parse(ss); // missing 'http://' will cause crashed
                                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                        startActivity(intent);
                                                    }
                                                });
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
                        }
                        if (viewHolder.cell == 6) {
                            mRequestQueue.start();
                            String url = "http://xxx.com/belive/getsponsor.php";
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONArray array = new JSONArray(response);
                                                //for (int k = 0; k < array.length(); k++) {
                                                JSONObject j = array.getJSONObject(6);
                                                //if(j.getString("username").equals(login)&&j.getString("password").equals(password)) {
                                                viewHolder.textViewNewLayout.setText(j.getString("nom"));
                                                final String ss = j.getString("link");

                                                Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(j.getString("photo")).into(viewHolder.imageViewNewLayout);
                                                viewHolder.imageViewNewLayout.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Uri uri = Uri.parse(ss); // missing 'http://' will cause crashed
                                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                        startActivity(intent);
                                                    }
                                                });
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
                        }
                    } else {
                        viewHolder.textViewNewLayout.setText("test " + i);

                        String url = "http://www.lorempixel.com/40" + viewHolder.row + "/40" + viewHolder.cell + "/";
                        Picasso.with(viewHolder.imageViewNewLayout.getContext()).load(url).into(viewHolder.imageViewNewLayout);
                    }
                    viewHolder.imageViewNewLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            findViewById(R.id.materialLeanBack).setVisibility(View.INVISIBLE);
                            findViewById(R.id.container).setVisibility(View.VISIBLE);
                            BeinEsp1 f = new BeinEsp1();
                            showInterstitial();
                            f.setArguments(b);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, f)
                                    .addToBackStack(null).commit();


                        }

                    });
                }

                @Override
                public String getTitleForRow(int row) {
                    if (row == 0) return "Bein sports AR/EN normal quality";
                    else if (row == 1) return "Bein sports AR/EN HD";
                    else if (row == 2) return "Bein sports FR normal quality";
                    else if (row == 3) return "Bein sports FR HD";
                    else if (row == 4) return "Movies";
                    else if (row == 5) return "Other";
                    else if (row == 6) return "Sponsors";
                    else return "Line " + row;
                }

            });
        }
    }


    public final boolean isInternetOn() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conMgr.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        findViewById(R.id.container).setVisibility(View.INVISIBLE);
        findViewById(R.id.materialLeanBack).setVisibility(View.VISIBLE);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(findViewById(R.id.container).getVisibility()==View.VISIBLE) {
                findViewById(R.id.materialLeanBack).setVisibility(View.INVISIBLE);
                findViewById(R.id.container).setVisibility(View.VISIBLE);
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startGame();
        }
    }


    private void startGame() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }


    }
}
