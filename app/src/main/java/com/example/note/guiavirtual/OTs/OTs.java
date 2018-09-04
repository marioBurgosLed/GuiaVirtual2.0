package com.example.note.guiavirtual.OTs;

import java.util.Date;

/**
 * Created by Note on 03/09/2018.
 */

public class OTs {
    private String descOT;
    private Integer idUsuario;
    private Integer idEquipo;
    private Integer idEstado;
    private Date fechaOT;
    private Integer idTipo;

    public String getDescOT() {
        return descOT;
    }

    public void setDescOT(String descOT) {
        this.descOT = descOT;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFechaOT() {
        return fechaOT;
    }

    public void setFechaOT(Date fechaOT) {
        this.fechaOT = fechaOT;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }
}
