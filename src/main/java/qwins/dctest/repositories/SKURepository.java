package qwins.dctest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qwins.dctest.models.SKU;
@Repository
public interface SKURepository extends JpaRepository<SKU, Long> {
}
