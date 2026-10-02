package com.Prueba_Tecnica.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Prueba_Tecnica.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
