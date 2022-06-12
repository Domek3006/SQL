package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Dish;
import pl.poznan.put.Entities.DishId;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, DishId> {

    @Query("select distinct g from Dish g")
    List<Dish> findDistinct();

    @Query("select d from Dish d where d.id_cas = :cas and d.id_rest = :rest")
    List<Dish> findDishes(@Param("cas") Long cas, @Param("rest") Long rest);

    @Transactional
    @Modifying
    @Query(value = "insert into dania (nazwa, id_rest, id_kas) values (:name, :rest, :cas)", nativeQuery = true)
    void addDish(@Param("name") String name, @Param("rest") Long rest, @Param("cas") Long cas);

    @Transactional
    @Modifying
    @Query("update Dish di set di.name = :name, di.popularity = :popularity where di.name = :namee")
    void updateDish(@Param("name") String name, @Param("popularity") Integer popularity, @Param("namee") String namee);
}
