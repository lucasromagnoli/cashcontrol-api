package br.com.lucasromagnoli.cashcontrol.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author github.com/lucasromagnoli
 * @since 10/2020
 */
@Repository
interface OriginRepository extends JpaRepository<Origin, Integer> {
    boolean existsByNameIgnoreCase(String name);

}
