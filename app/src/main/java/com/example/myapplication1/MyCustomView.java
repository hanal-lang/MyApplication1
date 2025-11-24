package com.example.myapplication1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyCustomView extends LinearLayout{
    Context context;
    TextView tvName;
    TextView tvDetails;
    ImageView imgPicture;

    public String getName() {
        return tvName.getText().toString();
    }
    public void setName(String value) {
        tvName.setText(value);
    }

    public String getDetails() {
        return tvDetails.getText().toString();
    }
    public void setDetails(String value) {
        tvDetails.setText(value);
    }

    public void setImageResourceId(int value) {
        imgPicture.setImageResource(value);
    }

    public MyCustomView(Context context) {
        super(context);
        Init(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    private void Init(Context context) {
        this.context=context;
        View view = LayoutInflater.from(context).inflate(R.layout.my_custom_view, this, true);
        tvName = view.findViewById(R.id.tvName);
        tvDetails = view.findViewById(R.id.tvDetails);
        imgPicture = view.findViewById(R.id.imgPicture);
    }

    public void ToggleDetails() {
        if(tvDetails.getVisibility() == View.VISIBLE)
            tvDetails.setVisibility(View.GONE);
        else
            tvDetails.setVisibility(View.VISIBLE);
    }
}
