package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sofia on 10/17/2017.
 */

public class WordAdapter extends ArrayAdapter<Word>{
    private int backgroundColorId;

    public WordAdapter(Context context, List<Word> objects, int colorId) {
        super(context, 0, objects);
        backgroundColorId = colorId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        View list_item = convertView;
        if (list_item == null) {
            list_item = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Word currentWord = getItem(position);

        ImageView image = (ImageView) list_item.findViewById(R.id.imageView1);

        if (currentWord.getImageResourceId() != 0){
            image.setImageResource(currentWord.getImageResourceId());
            image.setVisibility(View.VISIBLE);
        }else{
            image.setVisibility(View.GONE);
        }

        LinearLayout textviews = (LinearLayout) list_item.findViewById(R.id.lLayout);
        textviews.setBackgroundColor(ContextCompat.getColor(getContext(),backgroundColorId));


        TextView miwak = (TextView) list_item.findViewById(R.id.miwak);
        miwak.setText(currentWord.getMiwakTranslation());

        TextView english = (TextView) list_item.findViewById(R.id.english);
        english.setText(currentWord.getDefaultTranslation());

        return list_item;
    }
}
