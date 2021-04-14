package ru.xbitly.jokes.Joke;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokeClient {
    public static Retrofit getClient(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
                .baseUrl("https://developerslife.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
