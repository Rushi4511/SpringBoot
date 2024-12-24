package Hibernate_And_SpringJPA.Hibernate_And_SpringJPA.controller;

import Hibernate_And_SpringJPA.Hibernate_And_SpringJPA.entities.ProductEntity;
import Hibernate_And_SpringJPA.Hibernate_And_SpringJPA.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/products")
public class ProductController {

    private final ProductRepository productRepository;

    private  int PAGE_SIZE =5;


    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts(){
        return productRepository.findByOrderByPrice();
    }

//    @GetMapping(path = "/filteredProducts")
//    public List<ProductEntity> getAllFilteredProducts(@RequestParam(defaultValue = "id") String sortBy){
////        return productRepository.findAll(Sort.by(Sort.Direction.ASC,sortBy));
////        return productRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION,sortBy,"price"));
//
//        return productRepository.findAll(Sort.by(Sort.Order.desc(sortBy),Sort.Order.asc("Price")));
//
//    }

//    @GetMapping(path = "/filteredProducts")
//    public Page<ProductEntity> getAllFilteredProducts(@RequestParam(defaultValue = "1") Integer pagenumber, @RequestParam(defaultValue = "id") String sortBy){
//
//        Pageable pageable = PageRequest.of(pagenumber,PAGE_SIZE,Sort.by(sortBy));
//        return productRepository.findAll(pageable);
//
//    }

    @GetMapping(path = "/filteredProducts")
    public List<ProductEntity> getAllFilteredProducts(@RequestParam(defaultValue = "1") Integer pagenumber,@RequestParam(defaultValue = "") String title, @RequestParam(defaultValue = "id") String sortBy){

        Pageable pageable = PageRequest.of(pagenumber,PAGE_SIZE,Sort.by(sortBy));
        return productRepository.findByTitleContainingIgnoreCase(title, PageRequest.of(pagenumber,PAGE_SIZE,Sort.by(sortBy)));

    }


}
