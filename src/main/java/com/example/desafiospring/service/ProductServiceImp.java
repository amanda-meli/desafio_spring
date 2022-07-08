package com.example.desafiospring.service;

import com.example.desafiospring.dto.ProductDto;
import com.example.desafiospring.exception.BadRequestException;
import com.example.desafiospring.exception.NotFoundException;
import com.example.desafiospring.model.Product;
import com.example.desafiospring.model.Purchase;
import com.example.desafiospring.model.PurchaseRequest;
import com.example.desafiospring.model.Ticket;
import com.example.desafiospring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImp implements IProductService {

    @Autowired
    private ProductRepo repo;

    private List<Product> allProducts(String order) {
        switch (order) {
            case "0":
                return repo.getAllProducts()
                        .stream()
                        .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                        .collect(Collectors.toList());
            case "1":
                return repo.getAllProducts()
                        .stream()
                        .sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
                        .collect(Collectors.toList());
            case "2":
                return repo.getAllProducts()
                        .stream()
                        .sorted((p1, p2) -> p1.compareTo(p2))
                        .collect(Collectors.toList());
            case "3":
                return repo.getAllProducts()
                        .stream()
                        .sorted((p1, p2) -> p2.compareTo(p1))
                        .collect(Collectors.toList());
            default:
                return repo.getAllProducts();
        }
    }

    @Override
    public List<ProductDto> createProducts(ArrayList<Product> products) {
        ArrayList<Product> listProducts = repo.saveProducts(products);

        List<ProductDto> listProductsDto = listProducts.stream()
                .map(ProductDto::new).collect(Collectors.toList());

        return listProductsDto;
    }

    @Override
    public List<ProductDto> getAllProducts(String order) {
        return allProducts(order).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByCategory(String category, String order) {
        List<ProductDto> allProductsDto = allProducts(order).stream()
                .filter(p -> p.getCategory().equals(category))
                .map(ProductDto::new).collect(Collectors.toList());

        if (allProductsDto.size() == 0) {
            throw new NotFoundException("Categoria não encontrada.");
        }

        return allProductsDto;
    }

    @Override
    public List<ProductDto> findByFreeShipping(String category, String order) {
        List<ProductDto> allProductsDto = allProducts(order).stream()
                .filter(p -> p.getCategory().equals(category) && p.isFreeShipping())
                .map(ProductDto::new).collect(Collectors.toList());

        if (allProductsDto.size() == 0) {
            throw new NotFoundException("Não existe produto dessa categoria com frete grátis.");
        }

        return allProductsDto;
    }

    @Override
    public List<ProductDto> findByPrestige(String prestige, String order) {
        List<ProductDto> allProductsDto = allProducts(order).stream()
                .filter(p -> p.isFreeShipping() && p.getPrestige().equals(prestige))
                .map(ProductDto::new).collect(Collectors.toList());

        if (allProductsDto.size() == 0) {
            throw new NotFoundException("Não existe nenhum produto com frete grátis com essa avaliação.");
        }
        return allProductsDto;
    }

    @Override
    public ProductDto checkStock(int id) {
        List<ProductDto> allProducts = getAllProducts("");
        ProductDto productDto = allProducts.stream().filter(p -> p.getProductId() == id).findFirst().orElse(null);

        if (productDto == null) {
            throw new NotFoundException("Produto não encontrado.");
        }
        return productDto;
    }

    @Override
    public Ticket purchaseRequest(List<Purchase> purchases) {
        List<Product> allProducts = repo.getAllProducts();
        Ticket ticket = Ticket.builder()
                .articles(new ArrayList<>())
                .build();

        for (Purchase p : purchases) { //Percore toda a lista de produtos que eu quero add ao ticket
            Product prod = getProductById(p.getProductId(), allProducts);
            Product article = Product.builder()
                    .productId(prod.getProductId())
                    .name(prod.getName())
                    .brand(prod.getBrand())
                    .category(prod.getCategory())
                    .freeShipping(prod.isFreeShipping())
                    .prestige(prod.getPrestige())
                    .price(prod.getPrice())
                    .quantity(p.getQuantity())
                    .build();
            if(prod.getQuantity() < p.getQuantity()){
                throw new BadRequestException("Quantidade do produto '" + prod.getName() + "' em estoque insuficiente." +
                        " - Em estoque: " + prod.getQuantity() + " - Solicitado: " + p.getQuantity());
            }
            prod.setQuantity(prod.getQuantity() - article.getQuantity());
            ticket.getArticles().add(article); // add ao ticket
            ticket.setTotal(ticket.getTotal() + prod.getPrice() * p.getQuantity()); // atualiza o valor total do ticket
        }

        repo.updateProduct(allProducts);

        //Define o id do novo ticket
        List<Ticket> allTicketsCurrent = repo.getAllTicket();

        ticket.setId(allTicketsCurrent.size() + 1);
        allTicketsCurrent.add(ticket);

        //salva o no arquivo
        repo.saveTicket(ticket);

        return ticket;
    }

    @Override
    public Ticket shoppingCar(PurchaseRequest purchases) {
        List<Ticket> allTicketsCurrent = repo.getAllTicket();
        Ticket ticket = getTicketById(purchases.getTicketId(), allTicketsCurrent);
        List<Product> allProducts = repo.getAllProducts();

        for (Purchase p : purchases.getArticlesPurchaseRequest()) { //Percore toda a lista de produtos que eu quero add ao ticket
            Product prod = getProductById(p.getProductId(), allProducts);
            Product article = Product.builder()
                    .productId(prod.getProductId())
                    .name(prod.getName())
                    .brand(prod.getBrand())
                    .category(prod.getCategory())
                    .freeShipping(prod.isFreeShipping())
                    .prestige(prod.getPrestige())
                    .price(prod.getPrice())
                    .quantity(p.getQuantity())
                    .build();
            if(prod.getQuantity() < p.getQuantity()){
                throw new BadRequestException("Quantidade do produto '" + prod.getName() + "' em estoque insuficiente." +
                        " - Em estoque: " + prod.getQuantity() + " - Solicitado: " + p.getQuantity());
            }
            prod.setQuantity(prod.getQuantity() - article.getQuantity());
            ticket.getArticles().add(article); // add ao ticket
            ticket.setTotal(ticket.getTotal() + prod.getPrice() * p.getQuantity()); // atualiza o valor total do ticket
        }

        repo.updateProduct(allProducts);

        //atualiza o arquivo
        repo.updateTicket(allTicketsCurrent);

        return ticket;
    }

    private Ticket getTicketById(int id, List<Ticket> allTickets) {
        for (Ticket t : allTickets) { //procura se esse produto exite na lista
            if (t.getId() == id) { //quando encontrar
                return t;
            }
        }
        throw new NotFoundException("Não existe ticket com o id: " + id);
    }

    private Product getProductById(int id, List<Product> allProducts) {
        for (Product prod : allProducts) { //procura se esse produto exite na lista
            if (prod.getProductId() == id) { //quando encontrar
                return prod;
            }
        }
        throw new NotFoundException("Não existe produto com o id: " + id);
    }

}
