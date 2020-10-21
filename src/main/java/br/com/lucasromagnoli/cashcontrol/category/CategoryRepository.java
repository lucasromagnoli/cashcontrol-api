package br.com.lucasromagnoli.cashcontrol.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Repository
interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByNameIgnoreCase(String name);
}
