package myPacket.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myPacket.entity.EntityTwo;
import myPacket.repos.FirstRepository;
import myPacket.dto.RequestDTO;
import myPacket.dto.ResponseDTO;
import myPacket.entity.EntityOne;
import myPacket.repos.SecondRepository;
import myPacket.service.FirstService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
public class FirstController {

    private final FirstRepository repo1;
    private final SecondRepository repo2;
    private final FirstService service1;
    private final ResponseDTO response;

    @PostMapping("/processingRequest")
    public ResponseDTO calculateAndCreateOrUpdate(@RequestBody RequestDTO request) {
        System.out.println("//////////////////////////////////////////");
        String res = service1.calculateResult(request);

        if ((repo1.hasSymbol(request.getSymbol()) > 0) && (!res.equals("error"))) {
            return updateRequest(request);
        } else if (!res.equals("error")){
            return createRequest(request);
        }
        response.setResult(res);
        return response;
    }

    @PostMapping("/update")
    public ResponseDTO updateRequest(@RequestBody RequestDTO request) {

        String res = service1.calculateResult(request);
        repo2.updateEntityTwo(res, request.getA(), request.getB(), request.getSymbol());
        response.setResult(res);
        log.info("UPDATE");

        return response;
    }

    @PostMapping("/create")
    public ResponseDTO createRequest(@RequestBody RequestDTO request) {
        EntityOne entityOne = new EntityOne();
        EntityTwo entityTwo = new EntityTwo();

        entityOne.setSymbol(request.getSymbol());
        repo1.save(entityOne);

        entityTwo.setSymbol_id(request.getSymbol()); //table two
        entityTwo.setResult(service1.calculateResult(request));
        entityTwo.setNum_1(request.getA());
        entityTwo.setNum_2(request.getB());
        repo2.save(entityTwo);

        response.setResult(service1.calculateResult(request));
        log.info("CREATE");
        return response;
    }
}