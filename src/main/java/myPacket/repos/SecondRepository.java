package myPacket.repos;

import jakarta.transaction.Transactional;
import myPacket.entity.EntityTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondRepository extends JpaRepository<EntityTwo, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE EntityTwo SET" +
            " result = :newResult," +
            " num_1 = :newNum_1," +
            " num_2 = :newNum_2" +
            " WHERE symbol_id = :symbol_id")
    void updateEntityTwo(@Param("newResult") String newResult,
                         @Param("newNum_1") int newNum_1,
                         @Param("newNum_2") int newNum_2,
                         @Param("symbol_id") String sym_id);
}
