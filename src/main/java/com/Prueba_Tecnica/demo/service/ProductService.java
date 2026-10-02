package com.Prueba_Tecnica.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Prueba_Tecnica.demo.entity.Product;
import com.Prueba_Tecnica.demo.repository.ProductRepository;

@Service
public class ProductService {

    /**
     * Inyección del repositorio (Conexión a la Base de Datos)
     */
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Obtiene todos los productos
     * @return Todos los productos
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Crear un producto y guardarlo
     * @param product
     * @return el producto guardado
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Elimina el producto por ID
     * @param id
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);;
    }

    public Product getProductsByLessConsonants() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) return null;

        Product lessConsonantsProducts = null;
        int minusConsonants = Integer.MAX_VALUE;

        for (Product product : products) {
            int consonants = countConsonants(product.getNombre());
            if (consonants < minusConsonants) {
                minusConsonants = consonants;
                lessConsonantsProducts = product;
            }
        }
        return lessConsonantsProducts;
    }

    private int countConsonants(String text) {
        if (text == null) return 0;

        int contador = 0;
        String cleanedText = text.toLowerCase().replaceAll("[^a-z]", "");
        for (char c : cleanedText.toCharArray()) {
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
                contador++;
            }
        }
        return contador;
    }

    public int getCloserPrimeLessMoney() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) return -1;

        long minPrice = products.stream()
                .mapToLong(product -> (long) product.getPrecio())
                .min()
                .orElse(0L);

        int price = (int) minPrice;

        if (esPrimo(price)) {
            return price;
        }

        int down = price - 1;
        int up = price + 1;

        while (true) {
            if (down > 1 && esPrimo(down)) return down;
            if (esPrimo(up)) return up;
            down--;
            up++;
        }
    }

    private boolean esPrimo(int n) {
        if (n <= 1) return false;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public int sumTotalResources() {
        return sumTotalResources(productRepository.findAll(), 0);
    }

    private int sumTotalResources(List<Product> products, int index) {
        if (index >= products.size()) {
            return 0;
        }
        return (int) products.get(index).getCantidad() + sumTotalResources(products, index + 1);
    }
}
