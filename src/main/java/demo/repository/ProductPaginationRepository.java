package demo.repository;

import demo.entity.Product;
import demo.entity.speccification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductPaginationRepository extends JpaRepository<Product, Integer> {

}
