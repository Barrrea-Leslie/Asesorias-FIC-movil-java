package com.lesliefic.asesoriasfic.modelo;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    @SerializedName("ok")
    private Integer ok;

    @SerializedName("id_persona")
    private Integer idPersona;

    @SerializedName("id_rol")
    private Integer idRol;

    @SerializedName("data")
    private JsonObject data;

    @SerializedName("msg")
    private String msg;

    public Integer getOk() {
        return ok;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public JsonObject getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}