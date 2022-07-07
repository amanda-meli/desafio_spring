package com.example.desafiospring.repository;

import com.example.desafiospring.exception.NotFoundException;
import com.example.desafiospring.model.Product;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepo {
    private final String linkFile = "src/main/resources/products.json";
    private final String dir = "src/main/resources/";
    private final String fileName = "products.json";

    public List<Product> getAllProducts() {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> allProducts = null;

        File data = new File(linkFile);
        if (!data.exists()) {
            throw new NotFoundException("Arquivo não existe");
        }
        if (data.length() == 0) {
            throw new NotFoundException("Não ha produto cadastrado.");
        }

        try {
            allProducts = Arrays.asList(
                    mapper.readValue(data, Product[].class)
            );
        } catch (Exception ex) {
            System.out.println("Error getting the products. Error: " + ex);
        }
        return allProducts;
    }

    public ArrayList<Product> saveProducts(ArrayList<Product> products) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> currentListProducts = null;

        try {
            File data = new File(linkFile);
            if (!data.exists() || data.length() == 0) {
                java.io.File newFile = new java.io.File(dir, fileName);
                newFile.createNewFile();

                writer.writeValue(newFile, products);
                return products;
            }

            currentListProducts = Arrays.asList(
                    mapper.readValue(data, Product[].class)
            );

            List<Product> toEditProductsList = new ArrayList<>(currentListProducts);
            toEditProductsList.addAll(products);
            writer.writeValue(data, toEditProductsList);
        } catch (Exception ex) {
            System.out.println("Error to save products. Error: " + ex);
        }

        return products;
    }

    public void saveProduct(Product products) {
        //bonus
    }

}
