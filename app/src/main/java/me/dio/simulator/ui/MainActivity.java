package me.dio.simulator.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import me.dio.simulator.data.MatchesApi;
import me.dio.simulator.databinding.ActivityMainBinding;
import me.dio.simulator.domain.Match;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MatchesApi matchesApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        
        setupMatchesList();            // metodo que vai fazer as configuraões para listar as partidas
        setupMatchesRefresh();         // configuração dos refreshs das partidas, quando arrastar para baixo faz o loding
        setupFloatingActionButton();   // botao flutuante

    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flaviobarcel.github.io/matches-simulator-api/")
                . addConverterFactory(GsonConverterFactory.create())
                .build();
        matchesApi = retrofit.create(MatchesApi.class);
    }

    private void setupMatchesList() {
        matchesApi.getMatches().enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful()) {
                    List<Match> matches = response.body();
                } else {
                    showErrormessage();
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {

            }
        });
    }

    private void setupMatchesRefresh() {
        // TODO Atualizar as partidas na ação de swipe.
    }

    private void setupFloatingActionButton() {
        //TODO Criar evento de click e simulação de partidas.
    }

    private void showErrormessage() {
    }
}
