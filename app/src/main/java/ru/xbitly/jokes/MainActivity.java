package ru.xbitly.jokes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.xbitly.jokes.Joke.Joke;
import ru.xbitly.jokes.Joke.JokeClient;
import ru.xbitly.jokes.Joke.JokeInterface;
import ru.xbitly.jokes.Network.NetworkManager;
import ru.xbitly.jokes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    JokeInterface jokeInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        jokeInterface = JokeClient.getClient().create(JokeInterface.class);

        loadRandomJoke();

        binding.buttonNextJoke.setOnClickListener(view -> loadRandomJoke());

    }

    private String httpToStringHttps(String url){
        url = url.substring(0,4)+"s"+url.substring(4);
        return url;
    }

    private void loadRandomJoke(){
        binding.progressBar.setVisibility(ProgressBar.VISIBLE);
        if (NetworkManager.isNetworkAvailable(this)) {
            Call<Joke> call = jokeInterface.getRandomJoke();
            call.enqueue(new Callback<Joke>() {
                @Override
                public void onResponse(Call<Joke> call, Response<Joke> response) {
                    Joke randomJoke = response.body();
                    binding.textOfJoke.setText(randomJoke.getDescription());
                    if (randomJoke.getGifSize() == 0) {
                        Glide
                                .with(MainActivity.this)
                                .load(randomJoke.getPreviewURL())
                                .into(binding.imageOfJoke);
                    } else {
                        Glide
                                .with(MainActivity.this)
                                .load(httpToStringHttps(randomJoke.getGifURL()))
                                .into(binding.imageOfJoke);
                    }
                    binding.progressBar.setVisibility(ProgressBar.GONE);

                }

                @Override
                public void onFailure(Call<Joke> call, Throwable t) {
                    binding.textOfJoke.setText("Сервер не отвечает");
                    Glide
                            .with(MainActivity.this)
                            .load("https://cdn.dribbble.com/users/2469324/" +
                                    "screenshots/6538803/comp_3.gif")
                            .into(binding.imageOfJoke);
                }
            });
        }else{
            Snackbar.make(binding.getRoot(), "Отсутсвует подключение", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Повторить", view -> loadRandomJoke()).show();
        }
    }
}