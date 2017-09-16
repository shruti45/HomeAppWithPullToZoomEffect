package com.example.user.pulltozoom;

/**
 * Created by user on 4/14/2016.
 */
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;



public class PullToZoomScrollActivity extends ActionBarActivity {

    private PullToZoomScrollViewEx scrollView;
    LinearLayout footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_scroll_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();

        //Pull to zoom effect for image
        loadViewForCode();
        scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);

        //code for using youtube thumbnails
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerAdapter adapter=new RecyclerAdapter(PullToZoomScrollActivity.this);
        recyclerView.setAdapter(adapter);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                 footer=(LinearLayout)findViewById(R.id.footer_layout);
                    footer.setVisibility(View.GONE);
                    Animation animation= AnimationUtils.loadAnimation(PullToZoomScrollActivity.this,R.anim.fade_in);
                    footer.startAnimation(animation);
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    /*footer.setVisibility(View.VISIBLE);
                    Animation anim= AnimationUtils.loadAnimation(PullToZoomScrollActivity.this,R.anim.fade_out);
                    footer.startAnimation(anim);*/
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scroll_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
//        else if (id == R.id.action_settings) {
//            loadViewForCode();
//            return true;
//        }
        else if (id == R.id.action_normal) {
            scrollView.setParallax(false);
            return true;
        } else if (id == R.id.action_parallax) {
            scrollView.setParallax(true);
            return true;
        } else if (id == R.id.action_show_head) {
//            scrollView.showHeaderView();
            scrollView.setHideHeader(false);
            return true;
        } else if (id == R.id.action_hide_head) {
//            scrollView.hideHeaderVie˛w();
            scrollView.setHideHeader(true);
            return true;
        } else if (id == R.id.action_disable_zoom) {
//            scrollView.setEnableZoom(false);
            scrollView.setZoomEnabled(false);
            return true;
        } else if (id == R.id.action_enable_zoom) {
//            scrollView.setEnableZoom(true);
            scrollView.setZoomEnabled(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadViewForCode() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) findViewById(R.id.scroll_view);
        //View headView = LayoutInflater.from(this).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(this).inflate(R.layout.profile_content_view, null, false);
        //scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }
}