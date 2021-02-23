package wky.ssm.dao;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import wky.ssm.domain.Product;

import java.util.List;

@Repository
public interface ProductDao {
    //根据id查询产品
    @Select("select *from product where id=#{id}")
    public Product findById(String id)throws Exception;


    @Select("select * from product")
    public List<Product> findAll()throws Exception;

    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice," +
            "productDesc,productStatus)values" +
            "(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice}" +
            ",#{productDesc},#{productStatus})")
    void saveProduct(Product product)throws Exception;
}
