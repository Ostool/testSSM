package wky.ssm.serviceImpl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wky.ssm.dao.ProductDao;
import wky.ssm.domain.Product;
import wky.ssm.service.ProductService;

import java.util.List;
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(String page, String size)throws Exception {
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(size));
        return productDao.findAll();

    }

    @Override
    public void saveProduct(Product product)throws Exception {
        productDao.saveProduct(product);
    }

    @Override
    public List<Product> findAll()throws Exception {
        return productDao.findAll();
    }
}
