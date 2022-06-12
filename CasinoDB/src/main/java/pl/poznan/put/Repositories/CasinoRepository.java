package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Casino;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CasinoRepository extends JpaRepository<Casino,Long> {
    @Transactional
    @Modifying
    @Query("update Casino c set c.name = :name, c.city = :city, c.seats = :seats where c.id_cas = :id")
    void updateCasino(@Param("name") String name, @Param("city") String city, @Param("seats") Integer seats, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "call NoweKasyno(:name, :city, :seats)", nativeQuery = true)
    void addCasino(@Param("name") String name, @Param("city") String city, @Param("seats") Integer seats);

    @Query("select c.id_cas from Casino c")
    List<Casino> findIds();

    @Query(value = "select dochod(:id) from dual", nativeQuery = true)
    Double income(@Param("id") Long id);

    @Query(value = "select dochod(c.id_kas), * from Kasyna c", nativeQuery = true)
    List<Object[]> findIncoms();

    @Query(value = "select dlug(:id) from dual", nativeQuery = true)
    Integer isDebt(@Param("id") Long id);
}