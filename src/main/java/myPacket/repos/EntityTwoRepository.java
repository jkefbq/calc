package myPacket.repos;

import myPacket.entity.EntityOne;
import myPacket.entity.EntityTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityTwoRepository extends JpaRepository<EntityTwo, Integer> {

    @Query("SELECT e FROM EntityTwo e WHERE e.entityOne = :entityOne")
    EntityTwo getBySymbolId(@Param("entityOne") EntityOne entityOne);
}
