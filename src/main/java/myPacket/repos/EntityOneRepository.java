package myPacket.repos;

import myPacket.entity.EntityOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityOneRepository extends JpaRepository<EntityOne, Integer> {

    @Query("SELECT COUNT(*) FROM EntityOne WHERE symbol = :sym")
    int symbolCount(@Param("sym") String sym);

    @Query("SELECT e FROM EntityOne e WHERE e.symbol = :sym")
    EntityOne getBySymbol(@Param("sym") String sym);
}