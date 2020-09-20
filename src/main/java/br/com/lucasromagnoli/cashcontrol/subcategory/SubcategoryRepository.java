package br.com.lucasromagnoli.cashcontrol.subcategory;

import br.com.lucasromagnoli.cashcontrol.movimentation.MovimentationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    
    List<Subcategory> findAllByCategoryType(MovimentationTypeEnum movimentationTypeEnum);
    boolean existsByIdAndCategoryType(Integer id, MovimentationTypeEnum movimentationTypeEnum);
}
