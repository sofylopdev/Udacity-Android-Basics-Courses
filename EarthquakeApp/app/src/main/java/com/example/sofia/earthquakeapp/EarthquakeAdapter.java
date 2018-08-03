package com.example.sofia.earthquakeapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Sofia on 10/3/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public static final String SPLIT_SEQUENCE = " of ";

    public EarthquakeAdapter( Context context, int resource,  List earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);


        TextView mag = (TextView) listItemView.findViewById(R.id.magnitude);
        mag.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = ContextCompat.getColor(getContext(),getMagnitudeColor(currentEarthquake.getMagnitude()));

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        TextView offSite = (TextView) listItemView.findViewById(R.id.offSite);
        TextView primary = (TextView) listItemView.findViewById(R.id.primaryLocal);

        String completeLocation = currentEarthquake.getLocation();
        String offsiteLocal;
        String primaryLocal;
        if(completeLocation.contains(SPLIT_SEQUENCE) && completeLocation.contains("km")){
            String[] splitString =completeLocation.split(SPLIT_SEQUENCE);
            offsiteLocal = splitString[0] +SPLIT_SEQUENCE;
            primaryLocal = splitString[1];

        }else{
            offsiteLocal ="Near the";//Context.getResources().getString(R.string.near_the);
            primaryLocal = completeLocation;
        }
        offSite.setText(offsiteLocal);
        primary.setText(primaryLocal);

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        TextView time = (TextView) listItemView.findViewById(R.id.time);


        Date dateObject = new Date(currentEarthquake.getDate());
       // SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        //String dateToDisplay = dateFormatter.format(dateObject);
        String formattedDate = formatDate(dateObject);
        date.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        time.setText(formattedTime);

        return listItemView;



    }

    private int getMagnitudeColor(double magnitude) {
        int integerMagnitude = (int) Math.floor(magnitude);
        switch (integerMagnitude) {
            case 1:
                return R.color.magnitude1;
            case 2:
                return R.color.magnitude2;
            case 3:
                return R.color.magnitude3;
            case 4:
                return R.color.magnitude4;
            case 5:
                return R.color.magnitude5;
            case 6:
                return R.color.magnitude6;
            case 7:
                return R.color.magnitude7;
            case 8:
                return R.color.magnitude8;
            case 9:
                return R.color.magnitude9;
            default:
                return R.color.magnitude10plus;

        }
    }




    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
