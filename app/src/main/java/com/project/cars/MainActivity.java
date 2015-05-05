package com.project.cars;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    RecyclerView gridView;
    MyAdapter myAdapter;
    public ArrayList<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Cars");
        setSupportActionBar(toolbar);
        gridView = (RecyclerView) findViewById(R.id.grid_view);
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gridView.setLayoutManager(staggeredGridLayoutManager);
        setData();
        myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);
        gridView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        context = this;
    }

    void setData(){
        cars.add(new Car("2014", "Swift", "Maruti Suzuki", R.drawable.swift + ""));
        cars.add(new Car("2012", "Bolero", "Mahindra", R.drawable.car + ""));
        cars.add(new Car("2013", "Punto", "Fiat", R.drawable.punt + ""));
        cars.add(new Car("2015", "GranTurismo", "Maserati", R.drawable.grandturismo + ""));
        cars.add(new Car("2010", "Stratos", "Lancia", R.drawable.stratt + ""));
        cars.add(new Car("2012", "Scorpio", "Mahindra", R.drawable.scorp + ""));
        cars.add(new Car("2015", "Linea", "Fiat", R.drawable.car + ""));
        cars.add(new Car("2014", "Xylo", "Mahindra", R.drawable.car + ""));
        cars.add(new Car("2009", "Quattroporte", "Maserati", R.drawable.quatrop + ""));
        cars.add(new Car("2015", "Ciaz", "Maruti Suzuki", R.drawable.ciaz + ""));
        cars.add(new Car("2010", "Thema", "Lancia", R.drawable.thema + ""));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    Context context;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            cars.clear();
            setData();
            FilterFragment filterFragment = new FilterFragment();
            filterFragment.show(getFragmentManager(), "Filter");
            filterFragment.setCancelable(false);
        }

        return super.onOptionsItemSelected(item);
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

        @Override
        public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = getLayoutInflater().inflate(R.layout.single_image_view, viewGroup, false);
            Holder holder = new Holder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder mHolder, final int position) {
            mHolder.cv.setPreventCornerOverlap(false);
            mHolder.year.setText(cars.get(position).year);
            mHolder.make.setText(cars.get(position).make);
            mHolder.name.setText(cars.get(position).names);
            mHolder.iv.setImageResource(Integer.parseInt(cars.get(position).image));
            mHolder.iv.setHeightRatio(getRandomHeight());
            mHolder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String url = (String) view.getTag();
                    CarDetails.launch(MainActivity.this, v.findViewById(R.id.imageView)
                            , cars.get(position).image, cars.get(position).year,
                            cars.get(position).make, cars.get(position).names);
                }
            });
        }

        @Override
        public int getItemCount() {
            return cars.size();
        }

        class Holder extends RecyclerView.ViewHolder {

            DynamicHeightImageView iv;
            TextView year, make, name;
            CardView cv;

            public Holder(View v) {
                super(v);
                iv = (DynamicHeightImageView) v.findViewById(R.id.imageView);
                year = (TextView) v.findViewById(R.id.yearText);
                make = (TextView) v.findViewById(R.id.buildText);
                name = (TextView) v.findViewById(R.id.modelText);
                cv = (CardView) v.findViewById(R.id.card_view);
            }
        }
    }

    Random rand = new Random();
    private double getRandomHeight() {
        int randomNum = rand.nextInt(40);
        return 0.80+randomNum/100.0;
    }
}
