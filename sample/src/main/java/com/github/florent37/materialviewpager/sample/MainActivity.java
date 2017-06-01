package com.github.florent37.materialviewpager.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.materialViewPager)

    MaterialViewPager mViewPager;

    DatabaseReference database;

    private FragmentStatePagerAdapter pageAdapter;


    private  TrackGPS trackGPS ;

    private RecyclerViewFragment rcf = RecyclerViewFragment.newInstance(Collections.<Dossier>emptyList());

    private ScrollFragment scrf = ScrollFragment.newInstance();



    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        trackGPS= new TrackGPS(MainActivity.this);

        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance().getReference();


        Log.e("Erroor","habsate hna");

        //database = FirebaseDatabase.getInstance().getReference();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d("tag", "Refreshed token: " + refreshedToken);

        //  gpsTracker = new GPSTracker(this);

        user User = new user();

        User.setId(database.child("installations").push().getKey());
       if(trackGPS.canGetLocation){
           User.setLatitude(trackGPS.getLatitude());
           User.setLongitude(trackGPS.getLongitude());
       }

        // User.setLatitude(gpsTracker.getLatitude());

        User.setToken(refreshedToken);

        database.child("users").child(User.getId()).setValue(User);




        ButterKnife.bind(this);

        pageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                //
                switch (position){
                    case 0:
                        return rcf;
                    case 1:
                         return scrf;

                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 2) {
                    case 0:
                        return "Mes dossiers";
                    case 1:
                        return "ajouter dossier";
                }
                return "";
            }
        };


        final Toolbar toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mViewPager.getViewPager().setAdapter(pageAdapter);


        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.green,
                            "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.blue,
                            "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.cyan,
                            "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.red,
                            "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }


        final com.github.clans.fab.FloatingActionButton fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("myTag","FAB Clicked");
                   /* new MaterialDialog.Builder(MainActivity.this)
                            .limitIconToDefaultSize()
                            .title(R.string.example_prompt)
                            .positiveText(R.string.allow)
                            .negativeText(R.string.deny)
                            .onAny(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    Toast.makeText(getApplicationContext(), "Yes, the Button is clickable", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .checkBoxPR.string.dont_ask_again, false, null)
                            .show();*/
                    boolean wrapInScrollView = true;
                    new MaterialDialog.Builder(MainActivity.this )
                            .title(R.string.title)
                            .customView(R.layout.add_accident, wrapInScrollView)
                            .positiveText(R.string.positive)
                            .theme(Theme.LIGHT)
                            .negativeText(R.string.negative)
                            .show();
                }
            });

        Log.e("Erroor","habsate hna2");
    }
    public void clicked(View v) {
        Toast.makeText(getApplicationContext(), "Yes, the Button is clickable", Toast.LENGTH_SHORT).show();
    }


    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {

        super.onResume();

        Log.e("Erroor","habsate hna3");

        database.child("dossiers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Dossier> installations = new ArrayList<Dossier>();

                for (DataSnapshot installationSnapshot : dataSnapshot.getChildren()) {


                    Dossier installation = installationSnapshot.getValue(Dossier.class);

                    installation.setId(installationSnapshot.getKey());

                    // Log.i("ms",installation.getEtat().toString());

                    installations.add(installation);
                }

                rcf.myAdapter.updateList(installations);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}
