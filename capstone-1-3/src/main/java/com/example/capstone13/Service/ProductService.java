package com.example.capstone13.Service;


import com.example.capstone13.Model.Product;
import com.example.capstone13.Repository.CategoryRepository;
import com.example.capstone13.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Boolean addProduct(Product product){
        for (int i = 0; i < categoryRepository.findAll().size(); i++) {
            if (product.getCategory_id().equals(categoryRepository.findAll().get(i).getId())) {
                productRepository.save(product);
                return true;
            }

        }
        return false;
    }

    public Boolean updateProduct(Integer id,Product product){
        Product oldProduct = productRepository.getById(id);
        if (oldProduct != null) {
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setComments(product.getComments());
            for (int i = 0; i < categoryRepository.findAll().size(); i++) {
                if(oldProduct.getCategory_id().equals(categoryRepository.findAll().get(i).getId())) {
                    oldProduct.setCategory_id(product.getCategory_id());
                }
            }
            productRepository.save(oldProduct);

        }
        return false;
    }

    public Boolean deleteProduct(Integer id){
       Product deleteProduct = productRepository.getById(id);
       if (deleteProduct != null) {
           productRepository.delete(deleteProduct);
           return true;
       }
        return false;
    }




}
