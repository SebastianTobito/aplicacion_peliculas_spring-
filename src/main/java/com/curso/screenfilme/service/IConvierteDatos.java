package com.curso.screenfilme.service;

public interface IConvierteDatos {
 <T> T obtenerDatos(String json, Class <T> clase);
}
