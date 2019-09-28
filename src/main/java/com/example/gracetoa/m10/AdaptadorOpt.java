package com.example.gracetoa.m10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



/**
 * Created by iam23929882 on 2/2/16.
 */
public class AdaptadorOpt extends BaseAdapter{
    private LayoutInflater inflater;
   private ArrayList<Movie> movies;
    private Context mycontext;

    public AdaptadorOpt(Context context,ArrayList<Movie> movies) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.movies = movies;
        mycontext=context;

    }


    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView= inflater.inflate(R.layout.twolayout, null, true);

            //Usamos -->ViewHolder
            ViewHolder holder = new ViewHolder();
            holder.poster =(ImageView) convertView.findViewById(R.id.poster);
            holder.titol= (TextView) convertView.findViewById(R.id.title);
           holder.data= (TextView) convertView.findViewById(R.id.year);


            convertView.setTag(holder);
        }

        Movie movie = movies.get(position);

        ViewHolder holder = (ViewHolder) convertView.getTag();
        Picasso.with(mycontext).load(movie.getPoster()).fit().into(holder.poster);
        holder.titol.setText(movie.getTitle());
        holder.data.setText(movie.getYear());



        return (convertView);
    }

    static class ViewHolder{

        TextView titol;
        TextView data;
        TextView director;
        ImageView poster;
    }


    public int getCount() {
        return movies.size();
    }


    public Object getItem(int position) {
        return movies.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
}
