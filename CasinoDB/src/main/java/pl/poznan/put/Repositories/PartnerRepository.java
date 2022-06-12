package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Partner;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query("select p, c.name from Partner p, Casino c where p.id_cas = c.id_cas")
     List<Object[]> findPartners();

    @Transactional
    @Modifying
    @Query(value = "call NowyWspol(:name, :id, :serv, :cost, :ends)", nativeQuery = true)
    void addPartner(@Param("name") String name, @Param("id") Long id, @Param("serv") String serv, @Param("cost") Double cost, @Param("ends") Date ends);

    @Transactional
    @Modifying
    @Query("update Partner p set p.name = :name, p.service = :service, p.cost = :cost, p.ends = :ends where p.id = :id")
    void updatePartner(@Param("name") String name, @Param("service") String service, @Param("cost") Double cost, @Param("ends") Date ends, @Param("id") Long id);
}
