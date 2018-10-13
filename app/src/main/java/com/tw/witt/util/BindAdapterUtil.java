package com.tw.witt.util;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.tw.witt.R;
import com.tw.witt.model.RecyclerViewConfiguration;
import com.tw.witt.model.SeekBarConfiguration;
import com.tw.witt.model.ViewPagerConfiguration;
import com.tw.witt.view.custom.SeekArc;
import com.tw.witt.view.custom.VerticalViewPager;

/**
 * Created by gustavoterras on 25/01/18.
 */

public class BindAdapterUtil {

    @BindingAdapter({"seekBar_configuration"})
    public static void configureSeekBar(SeekArc seekBar, SeekBarConfiguration seekBarConfiguration) {
        seekBar.setProgressColor(ContextCompat.getColor(seekBar.getContext(), R.color.colorAccent));
        seekBar.setMax(seekBarConfiguration.getMax());
        seekBar.setMin(seekBarConfiguration.getMin());
        seekBar.setProgress(seekBarConfiguration.getProgress());
        seekBar.setOnSeekArcChangeListener(seekBarConfiguration.getOnSeekChangeListener());

        seekBar.invalidate();
    }

    @BindingAdapter({"recyclerView_configuration"})
    public static void configureRecyclerView(RecyclerView recyclerView, RecyclerViewConfiguration recyclerViewConfiguration) {
        recyclerView.setLayoutManager(recyclerViewConfiguration.getLayoutManager());
        recyclerView.setItemAnimator(recyclerViewConfiguration.getItemAnimator());
        recyclerView.setAdapter(recyclerViewConfiguration.getAdapter());
    }

    @BindingAdapter({"viewPager_configuration"})
    public static void configureViewPager(VerticalViewPager viewPager, ViewPagerConfiguration viewPagerConfiguration) {
        viewPager.setAdapter(viewPagerConfiguration.getAdapter());
        viewPager.setCurrentItem(viewPagerConfiguration.getPosition());
        viewPager.addOnPageChangeListener(viewPagerConfiguration.getOnPageChangeListener());

        viewPager.setOffscreenPageLimit(3);
    }

    @BindingAdapter({"viewPager_position"})
    public static void viewPagerPosition(VerticalViewPager viewPager, int position) {
        viewPager.setCurrentItem(position);
    }

    @BindingAdapter({"shake_animation"})
    public static void shakeAnimation(View view, int visibility) {
        Animation shakeAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake_animation);
        view.startAnimation(shakeAnimation);

        if (visibility == View.GONE) {
            view.clearAnimation();
        }

        view.setVisibility(visibility);
    }
}