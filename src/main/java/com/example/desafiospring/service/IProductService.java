package com.example.desafiospring.service;

import com.example.desafiospring.dto.ProductDto;
import com.example.desafiospring.model.Product;
import com.example.desafiospring.model.Purchase;
import com.example.desafiospring.model.PurchaseRequest;
import com.example.desafiospring.model.Ticket;

import java.util.List;
import java.util.ArrayList;

public interface IProductService {
    /***
     * Salva uma lista de produtos
     * @param products produtos que serão adicionados a lista
     * @return lista com as informações básicas dos produtos recém adicionados
     */
    List<ProductDto> createProducts(ArrayList<Product> products);

    /***
     * Obtem todos os produtos
     * @param order define qual a ordem que os Produtos estarão na lista.
     *              Opções: por nome -> "0" - A a Z, "1" - Z a A. por preço -> "2" - Decrescente, "3" - Crescente.
     * @return Lista contendo dos os produtos cadastrados
     */
    List<ProductDto> getAllProducts(String order);

    /***
     * Busca os produtos por categoria
     * @param category para o filtro
     * @param order define qual a ordem que os Produtos estarão na lista.
     *              Opções: por nome -> "0" - A a Z, "1" - Z a A. por preço -> "2" - Decrescente, "3" - Crescente.
     * @return Uma lista contendo os produtos
     */
    List<ProductDto> findByCategory(String category, String order);

    /***
     * Busca todos os produtos por categoria que tem frete grátis
     * @param category para o filtro
     * @param order define qual a ordem que os Produtos estarão na lista.
     *              Opções: por nome -> "0" - A a Z, "1" - Z a A. por preço -> "2" - Decrescente, "3" - Crescente.
     * @return Uma lista contendo os produtos
     */
    List<ProductDto> findByFreeShipping(String category, String order);

    /***
     * Busca todos os produtos por prestígio
     * @param prestige para o filtro
     * @param order define qual a ordem que os Produtos estarão na lista.
     *              Opções: por nome -> "0" - A a Z, "1" - Z a A. por preço -> "2" - Decrescente, "3" - Crescente.
     * @return Uma lista contendo os produtos
     */
    List<ProductDto> findByPrestige(String prestige, String order);
    ProductDto checkStock(int id);
    Ticket purchaseRequest(List<Purchase> purchases);
    Ticket shoppingCar(PurchaseRequest purchases);
}
