package com.example.rickandmorty.util;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.rickandmorty.R;

public class GlideImageLoader {

    private static final int DEFAULTIMAGELOAD = R.drawable.ic_image;

    public static void setImage(final Context context, final String imgURL, final ImageView imageView, final ProgressBar mProgressBar){
        RoundedCorners roundedCorners = new RoundedCorners((int) convertDpToPx(context, 10));
        RequestOptions options = new RequestOptions().placeholder(DEFAULTIMAGELOAD).centerInside().diskCacheStrategy(DiskCacheStrategy.ALL).transform(roundedCorners);
        Glide.with(context).load(imgURL).apply(options).listener(new RequestListener<>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                return false;
            }
        }).into(imageView);
    }

    public static void setUrlImage(final Context context, final String imgURL, final ImageView imageView, final ProgressBar mProgressBar){
        int size = (int) convertDpToPx(context, 80);
        RequestOptions options = new RequestOptions().placeholder(DEFAULTIMAGELOAD).centerInside().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(imgURL).apply(options).override(size).listener(new RequestListener<>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                imageView.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                return false;
            }
        }).into(imageView);
    }

    public static void setGridImage(final Context context, final String imgURL, final ImageView imageView, final ProgressBar mProgressBar){
        RequestOptions options = new RequestOptions().placeholder(DEFAULTIMAGELOAD).centerInside().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(imgURL).apply(options).listener(new RequestListener<>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                return false;
            }
        }).into(imageView);
    }

    public static void setPostImage(final Context context, final String imgURL, final ImageView imageView, final ProgressBar mProgressBar){
        int height = (int) convertDpToPx(context, 200);//500
        RoundedCorners roundedCorners = new RoundedCorners(Math.round(convertDpToPx(context, 5)));
        RequestOptions options = new RequestOptions().placeholder(DEFAULTIMAGELOAD).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().transform(roundedCorners);
        Glide.with(context).load(imgURL).apply(options).override(Target.SIZE_ORIGINAL, height).listener(new RequestListener<>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                return false;
            }
        }).into(imageView);
    }

    public static void setUnroundedImage(Context context, String imgURL, ImageView imageView, final ProgressBar mProgressBar){
        RequestOptions options = new RequestOptions().centerInside().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(imgURL).apply(options).transition(withCrossFade()).listener(new RequestListener<>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                return false;
            }
        }).into(imageView);
    }

    public static float convertDpToPx(Context context, float dp){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertPxToDp(Context context, float px){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
