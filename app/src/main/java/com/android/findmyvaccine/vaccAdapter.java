package com.android.findmyvaccine;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class vaccAdapter extends ArrayAdapter<vaccine> {
    public vaccAdapter(@NonNull Context context, List<vaccine> vaccines) {
        super(context, 0,vaccines);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_view, parent, false);
        }


        TextView cityName = (TextView) convertView.findViewById(R.id.cityName);
        TextView centerName = (TextView) convertView.findViewById(R.id.centerName);
        TextView timing = (TextView) convertView.findViewById(R.id.vaccineTime);
        TextView vaccineName = (TextView) convertView.findViewById(R.id.vaccineName);
        TextView type = (TextView) convertView.findViewById(R.id.feeType);
        TextView age = (TextView) convertView.findViewById(R.id.age);
        TextView status = (TextView) convertView.findViewById(R.id.status);
        TextView address = (TextView) convertView.findViewById(R.id.centerAdd);
        LinearLayout linearLayout = convertView.findViewById(R.id.colorChange);



        vaccine vaccine = getItem(position);

        cityName.setVisibility(View.VISIBLE);
        cityName.setText(vaccine.getCity_name());
        centerName.setText(vaccine.getCenter());
        vaccineName.setText(vaccine.getVaccine_name());
        type.setText(vaccine.getFree_type());
        age.setText(vaccine.getage());
        status.setText(vaccine.getStatus());
        address.setText(vaccine.getAddress());

        timing.setText(vaccine.getTime());

        GradientDrawable DoseCircle = (GradientDrawable) linearLayout.getBackground();
        int DoseColor = getDoseColor(vaccine.getStatus());
        DoseCircle.setColor(DoseColor);

        return convertView;
    }

    private int getDoseColor(String dose) {
        int doseColorResourceId;

        if(dose.equals("0")){
            doseColorResourceId = R.color.not;
        }else {
            doseColorResourceId = R.color.ava;
        }
        return ContextCompat.getColor(getContext(), doseColorResourceId);
    }

}
