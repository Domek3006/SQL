package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Chip;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ChipRepository extends JpaRepository<Chip,Long> {
    @Query("select ch, c.name from Chip_Cas chc, Casino c, Chip ch where ch.id = chc.id_chip")
    List<Object[]> findChips();

    @Query("select distinct g from Chip g")
    List<Chip> findDistinct();

    @Transactional
    @Modifying
    @Query(value = "call NowyZet(:value, :color, :size, :real, :id)", nativeQuery = true)
    void addChip(@Param("value") Integer value, @Param("color") String color, @Param("size") String size, @Param("real") Integer real, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Chip ch set ch.value = :val, ch.color = :color, ch.size = :size, ch.real_val = :real_val where ch.id = :id")
    void updateChip(@Param("val") Integer val, @Param("color") String color, @Param("size") String size, @Param("real_val") Integer real_val, @Param("id") Long id);
}