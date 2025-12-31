package com.lesliefic.asesoriasfic.repositorios;

import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsesorParRepository {

    private final ApiService api;

    public AsesorParRepository(){
        api = RetrofitClient.getClient().create(ApiService.class);
    }

    public interface ResultCallback<T> {
        void onSuccess(T data);
        void onError(String error);
    }

    public void obtenerAsesoresPar(ResultCallback<List<AsesorPar>> cb) {

        api.obtenerAsesoresPar().enqueue(new Callback<List<AsesorPar>>() {
            @Override
            public void onResponse(Call<List<AsesorPar>> call, Response<List<AsesorPar>> response) {

                if (!response.isSuccessful()) {
                    cb.onError("HTTP ERROR: " + response.code());
                    return;
                }

                List<AsesorPar> lista = response.body();
                if (lista == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(lista);
            }

            @Override
            public void onFailure(Call<List<AsesorPar>> call, Throwable t) {
                cb.onError(t.getMessage());
            }
        });
    }

}
