package com.example.sstv.deal.restController;

import com.example.sstv.common.Data;
import com.example.sstv.deal.Exchange;
import com.example.sstv.deal.Service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/exchange/*")
public class ExchangeRestController {
    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    public ExchangeRestController(ExchangeService exchangeService){this.exchangeService= exchangeService;}

    @GetMapping(value = "getExchangeList/{userId}")
    public Data getExchangeList(@PathVariable String userId)throws Exception {
        System.out.println("결제 구매 내역");

        Data data = new Data("success", exchangeService.getExchangeList(userId));
        System.out.println(data+"getExchangeList데이터");
        return data;
    }
    @GetMapping(value = "getExchangeRequestList/{userId}")
    public Data getExchangeRequestList(@PathVariable String userId)throws Exception {
        System.out.println("결제 구매 내역");

        Data data = new Data("success", exchangeService.getExchangeRequestList(userId));
        System.out.println(data+"getExchangeList데이터");
        return data;
    }

    @PostMapping(value = "addExchange")
    public Data addExchange(@RequestBody Exchange exchange){

        System.out.println("환전하기");
        LocalDateTime currentDataTime  = LocalDateTime.now();
        exchangeService.addExchange(currentDataTime);
        Data data = new Data("success", "환전하기");
        return  data;
    }
    @PostMapping (value ="exchangeAcc")
    public Data exchangeAcc(@RequestBody Exchange exchange) throws Exception {
        exchangeService.exchangeAcc(exchange);
        Data data = new Data("success", "환전신청심사");
        return data;
    }
}