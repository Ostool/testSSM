package wky.ssm.dao;

import org.apache.ibatis.annotations.*;
import wky.ssm.domain.Member;
import wky.ssm.domain.Orders;
import wky.ssm.domain.Product;

import java.util.List;

public interface OrdersDao {
    /*查询出所有订单*/
    @Select("select *from orders")
    @Results({
         @Result(id=true,property = "id",column = "id"),
         @Result(property = "orderNum",column = "orderNum"),
         @Result(property = "orderTime",column = "orderTime"),
         @Result(property = "orderStatus",column = "orderStatus"),
         @Result(property = "peopleCount",column = "peopleCount"),
         @Result(property = "payType",column = "payType"),
         @Result(property = "orderDesc",column = "orderDesc"),
            //根据订单里的产品ID查询出对应的产品信息
         @Result(property = "product",column = "productId",javaType = Product.class,
            one = @One(select = "wky.ssm.dao.ProductDao.findById"))
    })
    public List<Orders> findAll() throws Exception;

    //根据ID查订单
    @Select("select *from orders where id=#{orderId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one = @One(select = "wky.ssm.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,
                    one = @One(select = "wky.ssm.dao.MemberDao.findByOrdersId")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "wky.ssm.dao.TravellerDao.findByOrdersId"))

    })
    public Orders findById(String ordersId)throws Exception;
}












































