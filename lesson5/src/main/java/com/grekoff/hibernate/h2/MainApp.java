package com.grekoff.hibernate.h2;


import com.grekoff.hibernate.h2.data.Product;
import com.grekoff.hibernate.h2.repository.ProductDao;
import com.grekoff.hibernate.h2.repository.ProductDaoImpl;
import com.grekoff.hibernate.h2.repository.SessionFactoryUtils;

public class MainApp {

    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {
            ProductDao productDao = new ProductDaoImpl(sessionFactoryUtils);
//  получить данные продукта по Id
//            Product product = productDao.findById(1L);
//            product.print();
//  получить данные продукта по названию
//            Product product = productDao.findByTitle("Сыр");
//            product.print();
//  изменить данные продукта с правильным Id
//            Product product = new Product(1L,"Хлеб", 100);
//            System.out.println(productDao.saveOrUpdate(product));
//            System.out.println(productDao.findAll());
//  изменить данные продукта с произвольным Id по названию
//            Product product = new Product(10L,"Хлеб", 100);
//            System.out.println(productDao.saveOrUpdate(product));
//            System.out.println(productDao.findAll());
//  изменить данные продукта без Id по названию и цене
//            Product product = new Product("Хлеб", 100);
//            System.out.println(productDao.saveOrUpdate(product));
//            System.out.println(productDao.findAll());
//  добавить продукт по названию и цене
//            Product product = new Product("Сливки", 100);
//            System.out.println(productDao.saveOrUpdate(product));
//            System.out.println(productDao.findAll());
//  добавить продукт с произвольным Id по названию и цене
//            Product product = new Product(10L,"Сливки", 100);
//            System.out.println(productDao.saveOrUpdate(product));
//            System.out.println(productDao.findAll());
//  удалить продукт по Id
//            productDao.deleteById(5L);
//            System.out.println(productDao.findAll());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryUtils.shutdown();
        }
    }
}
