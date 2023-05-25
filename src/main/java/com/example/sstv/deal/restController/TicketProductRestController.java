package com.example.sstv.deal.restController;


import com.example.sstv.common.Data;
import com.example.sstv.deal.Service.TicketProductService;
import com.example.sstv.deal.TicketProduct;
import com.example.sstv.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketProduct/*")
public class TicketProductRestController {



    @Autowired
    public TicketProductService ticketProductService;

    @Autowired
    public TicketProductRestController(TicketProductService ticketProductService){this.ticketProductService = ticketProductService;}

    @GetMapping(value = "getTicketProductAdminList/{ticketProductNo}")
    public Data getTicketProductAdminList(@PathVariable int ticketProductNo) {

        System.out.println("상품등록(이용권)");

        Data data = new Data("success", ticketProductService.getTicketProductAdminList(ticketProductNo));
        System.out.println(data);
        return data;
    }
    @PostMapping(value = "addTicketProductAdmin")
    public Data addTicketProductAdmin(@RequestBody TicketProduct ticketProduct){

        System.out.println("상품등록하기");

        ticketProductService.addTicketProductAdmin(ticketProduct);
        Data data = new Data("success", "상품등록하기");
        return  data;
    }
    @PostMapping(value = "removeTicketAdmin")
    public Data removeTicketAdmin(@RequestBody TicketProduct ticketProduct){

        ticketProductService.removeTicketProductAdmin(ticketProduct);
        Data data = new Data("success", "상품삭제하기");
        return data;
    }
}