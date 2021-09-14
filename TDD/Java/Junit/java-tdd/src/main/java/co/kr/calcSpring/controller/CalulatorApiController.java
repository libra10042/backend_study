package co.kr.calcSpring.controller;

import co.kr.calcSpring.component.Calculator;
import co.kr.calcSpring.dto.Req;
import co.kr.calcSpring.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class CalulatorApiController {


    @Autowired
    private Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y){

        return calculator.sum(x, y);
    }

    @GetMapping("/minus")
    public int minus(@RequestParam int x, @RequestParam int y){
        return calculator.minus(x, y);
    }
    
    @PostMapping("/minus2")
    public Res minus(@RequestBody Req req){
        int result = calculator.minus(req.getX1(), req.getY2());

        System.out.println(result);

        Res res = new Res();
        res.setResult(result);
        res.setResponse(new Res.Body());
        return res;
    }
}
