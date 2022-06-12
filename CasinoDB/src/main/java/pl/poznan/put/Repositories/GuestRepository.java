package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Guest;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    @Query("select g, c.name from Guest g, Casino c")
    List<Object[]> findGuests();

    @Transactional
    @Modifying
    @Query(value="call NowyGosc(:name, :id, :res)", nativeQuery = true)
    void addGuest(@Param("name") String name, @Param("id") Long id, @Param("res") Date res);

    @Transactional
    @Modifying
    @Query("update Guest g set g.name = :name, g.reserved = :reserved, g.winnings = :winnings where g.id = :id")
    void updateGuest(@Param("name") String name, @Param("reserved") Date reserved, @Param("winnings") Double winnings, @Param("id") Long id);

    @Query(value = "select Dostepnosc(:date, :id) from dual", nativeQuery = true)
    boolean availability(@Param("date") Date date, @Param("id") Long id);
}
