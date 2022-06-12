package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Guest;
import pl.poznan.put.Entities.Review;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select g.name, r from Guest g, Review r where g.id = r.id_g")
    List<Object[]> findReviews();

    @Query("select distinct g from Guest g where g.id not in (select r.id_g from Review r)")
    List<Guest> findValid();

    @Transactional
    @Modifying
    @Query(value = "call NowaRec(:desc, :id, :star)", nativeQuery = true)
    void addReview(@Param("desc") String desc, @Param("id") Long id, @Param("star") Integer star);

    @Transactional
    @Modifying
    @Query("update Review r set r.desc = :desc, r.stars = :stars where r.id = :id")
    void updateReview(@Param("desc") String desc, @Param("stars") Integer stars, @Param("id") Long id);
}
