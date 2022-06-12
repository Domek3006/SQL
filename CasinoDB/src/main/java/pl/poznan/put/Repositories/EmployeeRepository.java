package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Employee;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("select e, c from Employee e, Casino c where e.id_cas = c.id_cas")
    List<Object[]> findEmployees();

    @Transactional
    @Modifying
    @Query(value = "call NowyPrac(:name, :id, :func, :ends, :sal)", nativeQuery = true)
    void addEmployee(@Param("name") String name, @Param("id") Long id, @Param("func") String func, @Param("ends") Date ends, @Param("sal") Double sal);

    @Transactional
    @Modifying
    @Query("update Employee emp set emp.name = :name, emp.salary = :salary, emp.function = :function, emp.ends = :ends where emp.id = :id")
    void updateEmployee(@Param("name") String name, @Param("salary") Double salary, @Param("function") String function, @Param("ends") Date ends, @Param("id") Long id);

}
