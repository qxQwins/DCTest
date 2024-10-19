package qwins.dctest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qwins.dctest.models.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
