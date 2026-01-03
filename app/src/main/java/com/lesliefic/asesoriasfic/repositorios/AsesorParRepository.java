package com.lesliefic.asesoriasfic.repositorios;

import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;
import com.lesliefic.asesoriasfic.network.request.CrearAsesorParRequest;

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

    public void crearAsesorPar(CrearAsesorParRequest request, ResultCallback<Integer> cb){
        api.crearAsesorPar(request).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(!response.isSuccessful()){
                    String msg = "HTTP " + response.code();
                    try{
                        if (response.errorBody() != null){
                            msg = response.errorBody().string();
                        }
                    } catch (Exception ignored) {
                        cb.onError(msg);
                        return;
                    }
                }
                Integer r = response.body();
                if(r == null){
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(r);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                cb.onError(throwable.getMessage());
            }
        });
    }

}
