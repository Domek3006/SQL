package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Dish;
import pl.poznan.put.Entities.Drink;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Drink> {
    @Query("select distinct d from Drink d")
    List<Drink> findDistinct();

    @Query("select d from Drink d where d.id_cas = :cas and d.id_bar = :rest")
    List<Drink> findDrinks(@Param("cas") Long cas, @Param("rest") Long rest);

    @Transactional
    @Modifying
    @Query("update Drink dr set dr.name = :name where dr.name = :namee")
    void updateDrink(@Param("name") String name, @Param("namee") String namee);

    @Transactional
    @Modifying
    @Query(value = "insert into drinki (nazwa, id_bar, id_kas) values (:name, :rest, :cas)", nativeQuery = true)
    void addDrink(@Param("name") String name, @Param("rest") Long rest, @Param("cas") Long cas);
}
