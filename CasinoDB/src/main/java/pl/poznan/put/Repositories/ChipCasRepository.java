package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Chip_Cas;

import javax.transaction.Transactional;

@Repository
public interface ChipCasRepository extends JpaRepository<Chip_Cas, Long> {
    @Transactional
    @Modifying
    @Query("delete from Chip_Cas chc where chc.id_cas = :id")
    void deleteWithCasino(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("delete from Chip_Cas chc where chc.id_chip = :id")
    void deleteWithChip(@Param("id") Long id);
}
