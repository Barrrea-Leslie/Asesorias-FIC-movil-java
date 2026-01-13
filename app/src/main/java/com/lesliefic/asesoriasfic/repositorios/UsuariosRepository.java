package com.lesliefic.asesoriasfic.repositorios;

import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.LoginResponse;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;
import com.lesliefic.asesoriasfic.network.request.admin.EditarAsesorParRequest;
import com.lesliefic.asesoriasfic.network.request.admin.IniciarSesionRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosRepository {

    private final ApiService api;

    public UsuariosRepository(){
        api = RetrofitClient.getClient().create(ApiService.class);
    }

    public interface ResultCallback<T> {
        void onSuccess(T data);
        void onError(String error);
    }


    public void eliminarUsuario(int id_persona, UsuariosRepository.ResultCallback<Integer> cb){
        api.eliminarUsuario(id_persona).enqueue(new Callback<Integer>() {
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

    public void iniciarSesion(
            IniciarSesionRequest request,
            UsuariosRepository.ResultCallback<LoginResponse> cb
    ) {
        api.iniciarSesion(request).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (!response.isSuccessful()) {
                    String msg = "HTTP " + response.code();
                    try {
                        if (response.errorBody() != null) {
                            msg = response.errorBody().string();
                        }
                    } catch (Exception ignored) {
                    }
                    cb.onError(msg);
                    return;
                }

                LoginResponse r = response.body();
                if (r == null) {
                    cb.onError("Body null");
                    return;
                }

                cb.onSuccess(r);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                cb.onError(throwable.getMessage());
            }
        });


    }

}
