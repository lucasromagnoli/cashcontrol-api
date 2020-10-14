package br.com.lucasromagnoli.cashcontrol.subcategory;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    
    List<Subcategory> findAllByCategoryType(TransactionTypeEnum transactionTypeEnum);
    boolean existsByNameAndCategoryType(String id, TransactionTypeEnum transactionTypeEnum);
}
