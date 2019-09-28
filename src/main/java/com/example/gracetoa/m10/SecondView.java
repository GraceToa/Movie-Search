package com.example.gracetoa.m10;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by GraceToa on 5/2/16.
 */
public class SecondView extends Activity {


    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.main_activity2);

        TextView secondView = (TextView)findViewById(R.id.title);
        TextView dataView = (TextView) findViewById(R.id.data);
        ImageView poster = (ImageView) findViewById(R.id.poster2);
        //localizar los controles
        Intent intent = getIntent();



         String name=  intent.getStringExtra("name");
        String data= intent.getStringExtra("data");
        String imagen = intent.getStringExtra("image");
        //String director = intent.getStringExtra("director");
        Picasso.with(this).load(imagen).fit().into(poster);

        secondView.setText(name);
        dataView.setText(data);


    }
}
