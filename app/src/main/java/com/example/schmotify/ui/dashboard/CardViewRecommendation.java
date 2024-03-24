package com.example.schmotify.ui.dashboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.schmotify.R;

public class CardViewRecommendation extends CardView {
    private ImageView recommendedAlbumArt;
    private TextView recommendedSong;
    private TextView recommendedArtist;

    public CardViewRecommendation(Context context) {
        super(context);
        init(context);
    }

    public CardViewRecommendation(Context context, AttributeSet attributes) {
        super(context, attributes);
        init(context);
    }

    public CardViewRecommendation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.card_view_recommendation, this, true);
        recommendedSong = findViewById(R.id.textview_song);
        recommendedArtist = findViewById(R.id.textview_artist);
        recommendedAlbumArt = findViewById(R.id.img_rec);
    }

    public void setRecommendedSong(String song) {
        recommendedSong.setText(song);
    }

    public void setRecommendedArtist(String artist) {
        recommendedArtist.setText(artist);
    }

    public void setRecommendedAlbumArt(String uri, Context context) {
        Glide.with(context)
                .load(uri)
                .error(R.drawable.ic_launcher_foreground)
                .override(300, 300)
                .into(recommendedAlbumArt);
    }
}
