package com.Prueba_Tecnica.demo.dto;

import lombok.Data;

@Data 
public class ProductRequestDTO {
    private String nombre;
    private String descripcion;
    private float precio;
    private int cantidad;
}
