package wky.ssm.service;

import wky.ssm.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll()throws Exception;
    public List<Product> findAll(String page,String size)throws Exception;
    public void saveProduct(Product product)throws Exception;
}
