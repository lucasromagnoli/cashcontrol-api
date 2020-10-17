package br.com.lucasromagnoli.cashcontrol.subcategory;

import br.com.lucasromagnoli.cashcontrol.transaction.TransactionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    
    List<Subcategory> findAllByCategoryType(TransactionTypeEnum transactionTypeEnum);
    boolean existsByNameAndCategoryId(String name, Integer id);
    boolean existsByCategoryId(Integer id);

    @Query(value = "select c.type from subcategory s inner join category c on s.category_id = c.id_category where s.id = ?1", nativeQuery = true)
    TransactionTypeEnum findTransactionTypeSubcategoryId(Integer id);
}
