package com.example.gracetoa.m10;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;




public class MainActivity extends Activity  {

    ListView myList;
    EditText search;
    Button searchMovie;
    ArrayList<Movie> movies = new ArrayList<>();
    //Movie datos=new Movie();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList= (ListView)findViewById(R.id.mylist);

        search = (EditText)findViewById(R.id.search);
        searchMovie = (Button)findViewById(R.id.searchMovie);
        String trobarMovie=search.getText().toString();

        if(TextUtils.isEmpty(trobarMovie)){
            search.setError("cannot be empty");
        }
        searchMovie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String trobarMovie = search.getText().toString();

                if (TextUtils.isEmpty(trobarMovie)){
                    Toast.makeText(MainActivity.this,"The edit text is empty¡",Toast.LENGTH_SHORT).show();
                }else{
                    ejecutParser(trobarMovie);

                }

            }
        });

        /**
         * Captura el evento click sobre los items de la Lista
         */
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie moviesPosition = (Movie) parent.getItemAtPosition(position);
                CharSequence texto = "Title: "+ moviesPosition.getTitle();
                Toast.makeText(MainActivity.this,texto,Toast.LENGTH_SHORT).show();
                CharSequence year = "Data: " + moviesPosition.getYear();
                CharSequence image = moviesPosition.getPoster();

                Intent intent = new Intent(MainActivity.this,SecondView.class);
                intent.putExtra("image",image);
                intent.putExtra("name",texto);
                intent.putExtra("data", year);

                startActivity(intent);
            }
        });



    }

    /**
     * Retrofit es una libreria para consumir servicios de tipo Res que utiliza serializacion en json
     * y la libreria gson convierte a objetos java
     * @param trobarMovie
     */
    public void ejecutParser(String trobarMovie){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.omdbapi.com/").addConverterFactory(GsonConverterFactory.create()).build();

        MoviesInterface service = retrofit.create(MoviesInterface.class);

        Call<MoviesList> call = service.getMovie(trobarMovie);

        call.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Response<MoviesList> response) {
                //Array de Objetos movie se guarda todos las peliculas que coinciden con la busqueda
                movies= response.body().getListMovies();

                //Adaptador recibe el contexto y un Array de Objetos movie
                AdaptadorOpt adaptadorOpt = new AdaptadorOpt(MainActivity.this, movies);
                myList.setAdapter(adaptadorOpt);
                Toast.makeText(getApplicationContext(), "conectado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}
