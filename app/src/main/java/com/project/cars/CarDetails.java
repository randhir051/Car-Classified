package com.project.cars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

public class CarDetails extends ActionBarActivity {


    public boolean likedNow = false;
    public static int pos;
    public static final String LIKES = "SelfieDetail:likes", EXTRA_IMAGE = "SelfieDetail:image", YEAR = "year", BUILD = "build", NAME = "name";
    TextView name, build , year;
    ImageView like;
    Integer liked = 0;
    Intent in;
    MenuItem likeMenu;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        in = getIntent();
        cardView = (CardView) findViewById(R.id.card_view);
        String image_url = in.getStringExtra(EXTRA_IMAGE);
        String model = in.getStringExtra(NAME);
        String company = in.getStringExtra(BUILD);
        String year_manf = in.getStringExtra(YEAR);

        ImageView image = (ImageView) findViewById(R.id.image);
        ViewCompat.setTransitionName(image, EXTRA_IMAGE);
        image.setImageResource(Integer.parseInt(image_url));

        like = (ImageView) findViewById(R.id.ivLike1);
        name = (TextView) findViewById(R.id.name);
        name.setText(model);
        build = (TextView) findViewById(R.id.build);
        build.setText(company);
        year = (TextView) findViewById(R.id.year);
        year.setText(year_manf);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (liked == 0) {
                    Toast.makeText(getApplicationContext(), "Added to favourites", Toast.LENGTH_SHORT).show();
                    liked = 1;
                    YoYo.with(Techniques.BounceIn).withListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            like.setImageResource(R.drawable.star_gold_);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    }).duration(500).playOn(v);
                } else if (liked == 1) {
                    Toast.makeText(getApplicationContext(), "Already in favourites", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_details, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_report) {
            return true;
        }
        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }


        return super.onOptionsItemSelected(item);
    }




    public static void launch(MainActivity activity,
                              View transitionView, String url,
                              String year, String build,
                              String name) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, EXTRA_IMAGE);
        Intent intent = new Intent(activity, CarDetails.class);
        intent.putExtra(EXTRA_IMAGE, url);
        intent.putExtra(YEAR, year);
        intent.putExtra(BUILD, build);
        intent.putExtra(NAME, name);
        ActivityCompat.startActivityForResult(activity, intent, 1234, options.toBundle());
    }

}
