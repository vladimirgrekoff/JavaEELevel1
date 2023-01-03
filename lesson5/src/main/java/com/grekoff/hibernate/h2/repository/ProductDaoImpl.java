package com.grekoff.hibernate.h2.repository;

import com.grekoff.hibernate.h2.data.Product;
import org.hibernate.Session;

import java.util.List;


public class ProductDaoImpl implements ProductDao {
    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public Product findByTitle(String title) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session
                    .createQuery("select product from Product product where product.title = :title", Product.class)
                    .setParameter("title", title)
                    .getSingleResult();
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id= :id")
                   .setParameter("id", id)
                   .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactoryUtils.getSession()) {
            Long id = product.getId();
            String title = product.getTitle();
            int cost = product.getCost();
            session.beginTransaction();
            Product productFound;
            if (id != null) {
                productFound = session.get(Product.class, id);
                if (productFound == null) {
                    productFound = getProductByTitleInTable(session, title);
                }
            } else if (id == null) {
                productFound = getProductByTitleInTable(session, title);
            } else {
                productFound = null;
            }
            if (productFound != null) {
                productFound.setTitle(title);
                productFound.setCost(cost);
                session.saveOrUpdate(productFound);
                Long findId = (Long) session.getIdentifier(productFound);
                Product productNewValue = session.get(Product.class, findId);
                session.getTransaction().commit();
                return productNewValue;
            } else {
                session.save(product);
                Long findId = (Long) session.getIdentifier(product);
                Product productNewValue = session.get(Product.class, findId);
                session.getTransaction().commit();
                return productNewValue;
            }
        }
    }

    private Product getProductByTitleInTable(Session session, String title) {
        Product productFound;
        try {
            productFound = session
                .createQuery("select product from Product product where product.title = :title", Product.class)
                .setParameter("title", title)
                .getSingleResult();
        }  catch (Exception e) {
            productFound = null;
        }
        return productFound;
    }


}
