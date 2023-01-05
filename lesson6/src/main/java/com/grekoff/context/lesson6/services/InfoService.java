package com.grekoff.context.lesson6.services;


import com.grekoff.context.lesson6.entities.Customer;
import com.grekoff.context.lesson6.entities.Product;
import com.grekoff.context.lesson6.repositories.CustomerDaoImpl;
import com.grekoff.context.lesson6.repositories.ProductDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InfoService {
    private ProductDaoImpl productDao;
    private CustomerDaoImpl customerDao;

    @Autowired
    public void setProductDaoImpl(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }
    @Autowired
    public void CustomerDaoImpl(CustomerDaoImpl customerDao) {
        this.customerDao = customerDao;
    }

    public Product findProductById(Long id){
        return productDao.findById(id);
    }

    public Customer findCustomerById(Long id) {
        return customerDao.findById(id);
    }

    public List<Product> findAllProducts (){
        return productDao.findAll();
    }
    public List<Customer> findAllCustomers (){
        return customerDao.findAll();
    }

    public Product saveOrUpdateProduct(Product product){
        return productDao.saveOrUpdate(product);
    }

    public Customer saveOrUpdateCustomer(Customer customer){
        return customerDao.saveOrUpdate(customer);
    }

    public void deleteProductById(Long id) {
        productDao.deleteById(id);
    }

    public void deleteCustomerById(Long id) {
        customerDao.deleteById(id);
    }

    public List<Customer> findCustomerByProductId(Long id) {
        return productDao.findCustomerByProductId(id);
    }

    public List<Product> findProductsByCustomerId(Long id) {
        return customerDao.findProductsByCustomerId(id);
    }
    public void intro(){
        System.out.println();
        System.out.println("Узнать информацию о товаре или покупателе: 'покупатель'('товар') и номер через пробел;\n" +
                "Узнать информацию о покупателях товара: 'покупатели товара' и номер через пробел;\n" +
                "Узнать информацию о товарах покупателя: 'товары покупателя' и номер через пробел; Выход: выход.\n");
    }
    public boolean checkInput(String cmd){
        boolean result = true;
        if (cmd.equalsIgnoreCase("выход")) {
            result  = false;
        } else if(cmd.startsWith("товар ")) {
            cmd = cmd.replace("товар ", "");
            long numId = Long.parseLong(cmd);
            System.out.println(findProductById(numId));
        } else if (cmd.startsWith("покупатель ")) {
            cmd = cmd.replace("покупатель ", "");
            long numId = Long.parseLong(cmd);
            System.out.println(findCustomerById(numId));
        } else if (cmd.startsWith("товары покупателя ")){
            cmd = cmd.replace("товары покупателя ", "");
            long numId = Long.parseLong(cmd);
            List<Product> products = findProductsByCustomerId(numId);
            System.out.println(findCustomerById(numId).toString() + ", список приобретенных товаров: ");
            for (Product p: products) {
                System.out.println(p.toString());
            }
            System.out.println();
        } else if (cmd.startsWith("покупатели товара ")) {
            cmd = cmd.replace("покупатели товара ", "");
            long numId = Long.parseLong(cmd);
            List<Customer> customers = findCustomerByProductId(numId);
            System.out.println(findProductById(numId).toString() + " приобретен покупателями: ");
            for (Customer c: customers) {
                System.out.println(c.toString());
            }
            System.out.println();
        }
        return result;
    }
}
