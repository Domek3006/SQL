package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Bar;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BarRepository extends JpaRepository<Bar,Long> {
    @Transactional
    @Modifying
    @Query(value = "call NowyBar(:name, :id)", nativeQuery = true)
    void addBar(@Param("name") String name, @Param("id") Long id);

    @Query("select b, c from Bar b, Casino c where b.id_cas = c.id_cas")
    List<Object[]> findBars();

    @Transactional
    @Modifying
    @Query("update Bar b set b.name = :name where b.id_bar = :id")
    void updateBar(@Param("id") Long id, @Param("name") String name);
}
