package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Restaurant;

import javax.transaction.Transactional;

import java.util.List;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    @Query("select r, c from Restaurant r, Casino c where r.id_cas = c.id_cas")
    List<Object[]> findRestaurants();

    @Transactional
    @Modifying
    @Query("update Restaurant r set r.name = :name, r.income = :income where r.id = :id")
    void updateRestaurant(@Param("name") String name, @Param("income") Double income, @Param("id") Long id);

    @Query(value = "call NowaRest(:name, :id)", nativeQuery = true)
    void addRest(@Param("name") String name, @Param("id") Long id);


}
