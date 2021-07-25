package org.smart4j.chapter2.service;

import java.util.List;
import java.util.Map;
import org.smart4j.chapter2.model.Customer;

/**
 * 提供客户数据服务
 */
public class CustomerService {



    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        //return DatabaseHelper.queryEntityList(Customer.class, sql);
        return null;
    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        //return DatabaseHelper.queryEntity(Customer.class, sql, id);
        return null;
    }

    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        //return DatabaseHelper.insertEntity(Customer.class, fieldMap);
        return false;
    }

    /**
     * 更新客户
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        //return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
        return false;
    }

    /**
     * 删除客户
     */
    public boolean deleteCustomer(long id) {
        //return DatabaseHelper.deleteEntity(Customer.class, id);
        return false;
    }
}
