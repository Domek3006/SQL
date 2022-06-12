package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Enforcer;

import java.util.List;

@Repository
public interface EnforcerRepository extends JpaRepository<Enforcer,Long> {
    @Query("select e, c.name from Enforcer, Employee e, Casino c")
    List<Object[]> findEnforcers();
}