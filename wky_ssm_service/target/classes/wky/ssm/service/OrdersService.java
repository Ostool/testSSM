package wky.ssm.service;

import wky.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findAll() throws Exception;
    List<Orders> findAll(String page,String size) throws Exception;

    Orders findById(String orderId) throws Exception;
}
