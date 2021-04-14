package ru.xbitly.jokes.Joke;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokeInterface {
    @GET("/random?json=true")
    Call<Joke> getRandomJoke();
}
