package br.com.lucasromagnoli.cashcontrol.origin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OriginRepository extends JpaRepository<Origin, Integer> {
    boolean existsByNameIgnoreCase(String name);

}
