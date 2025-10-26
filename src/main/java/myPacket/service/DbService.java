package myPacket.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import myPacket.entity.EntityOne;
import myPacket.entity.EntityTwo;
import myPacket.repos.EntityOneRepository;
import myPacket.repos.EntityTwoRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DbService {

    private final EntityOneRepository repo1;
    private final EntityTwoRepository repo2;

    @Transactional
    public void createRecord(String res, String sym, int a, int b) {
        repo2.save(new EntityTwo(a, b, res, repo1.save(new EntityOne(sym))));
        System.out.println("CREATE");
    }

    @Transactional
    public void updateRecord(String res, String sym, int a, int b) {
        EntityOne entityOne = repo1.getBySymbol(sym);//нашли в 'one' запись с нужным символом
        repo2.getBySymbolId(entityOne).updateEntityTwo(res, a, b);//нашли в 'two' запись с таким же entityOne => с нужным символом + обновили
        System.out.println("UPDATE");
    }
}
