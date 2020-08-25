package demo.entity.speccification;

import demo.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification implements Specification<Product> {
 private SearchCriteria searchCriteria;
 public  ProductSpecification(SearchCriteria searchCriteria){
     this.searchCriteria = searchCriteria;
 }
    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
     if (this.searchCriteria.getOperation().equals("=")) {
         if (root.get(searchCriteria.getKey()).getJavaType() == String.class) {
             return criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue().toString() + "%");
         } else {
             return criteriaBuilder.equal(root.get(this.searchCriteria.getKey()), this.searchCriteria.getValue().toString());
         }
     }


     else if(this.searchCriteria.getOperation().equals(">")){
         return criteriaBuilder.greaterThan(root.get(this.searchCriteria.getKey()), this.searchCriteria.getValue().toString());
     }else if(this.searchCriteria.getOperation().equals(">=")){
         return criteriaBuilder.greaterThanOrEqualTo(root.get(this.searchCriteria.getKey()), this.searchCriteria.getValue().toString());
     }else if(this.searchCriteria.getOperation().equals("<")){
         return criteriaBuilder.lessThan(root.get(this.searchCriteria.getKey()), this.searchCriteria.getValue().toString());
     }else if(this.searchCriteria.getOperation().equals("<=")){
         return criteriaBuilder.lessThanOrEqualTo(root.get(this.searchCriteria.getKey()), this.searchCriteria.getValue().toString());
     }
        return null;
    }
}
