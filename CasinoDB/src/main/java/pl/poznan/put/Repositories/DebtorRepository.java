package pl.poznan.put.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poznan.put.Entities.Debtor;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface DebtorRepository extends JpaRepository<Debtor, Long> {

    @Query("select g.name, d, p.name from Guest g, Debtor d, Partner p where g.winnings < 0 and d.id_part = p.id")
    List<Object[]> findDebtors();

    @Transactional
    @Modifying
    @Query("update Debtor d set d.debt = :debt, d.part = :part, d.next = :next where d.id = :id")
    void updateDebtor(@Param("debt") Double debt, @Param("part") Double part, @Param("next") Date next, @Param("id") Long id);
}
