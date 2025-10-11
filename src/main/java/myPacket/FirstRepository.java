package myPacket;

import jakarta.transaction.Transactional;
import myPacket.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstRepository extends JpaRepository<Request, Integer> {

    @Query("SELECT COUNT(*) FROM Request WHERE symbol = :sym")
    int hasSymbol(@Param("sym") String sym);

    @Modifying
    @Transactional
    @Query("UPDATE Request SET result = :newResult WHERE symbol = :sym")
    void updateRequest(@Param("newResult") String res, @Param("sym") String sym);
}