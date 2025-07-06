package com.app.ecom_application.repository;

import com.app.ecom_application.dto.ProductResponse;
import com.app.ecom_application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByActiveTrue();
    @Query("Select p from products p where p.active = true AND p.stockQuantity > 0 and lower(p.name) like lower(concat('%', :keyword,'%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);
}
