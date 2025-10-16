package myPacket.repos;

import jakarta.transaction.Transactional;
import myPacket.entity.EntityOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstRepository extends JpaRepository<EntityOne, Integer> {

    @Query("SELECT COUNT(*) FROM EntityOne WHERE symbol = :sym")
    int hasSymbol(@Param("sym") String sym);
}