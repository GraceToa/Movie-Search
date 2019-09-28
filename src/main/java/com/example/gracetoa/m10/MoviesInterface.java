package com.example.gracetoa.m10;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * El contrato entre el Retrofit y nuestra Applicacion
 *
 * Created by GraceToa on 1/2/16.
 */
public interface  MoviesInterface {

    //Asincrona, metodo que no devuelve nada, recibe como parametro un objeto CAll
    //del paquete Retrofit , este tiene un tipo que corresponde al tipo de retorno
    //en este caso un MovieList(array de Movies)


//para acceder al cliente a traves de http utilizamos el metodo GET
    @GET("/?r=json&type=movie")
    Call <MoviesList> getMovie(@Query("s") String buscar);

}
