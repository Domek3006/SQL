package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Game;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;


@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    @Query("select g, c.name from Game g, Casino c where c.id_cas = g.id_cas")
    List<Object[]> findGames();

    @Transactional
    @Modifying
    @Query(value = "call NowaGra(:name, :typ, :id)", nativeQuery = true)
    void addGame(@Param("name") String name, @Param("typ") String typ, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Game g set g.name = :name, g.type = :type, g.income = :income where g.id = :id")
    void updateGame(@Param("name") String name, @Param("type") String type, @Param("income") Double income, @Param("id") Long id);
}
