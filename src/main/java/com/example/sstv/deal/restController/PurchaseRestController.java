package com.example.sstv.deal.restController;

import com.example.sstv.common.Data;
import com.example.sstv.deal.Purchase;
import com.example.sstv.deal.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    public PurchaseRestController(PurchaseService purchaseService){this.purchaseService= purchaseService;}

    @GetMapping(value = "getPurchaseList/{paymentNo}")
    public Data getPurchaseList(@PathVariable int paymentNo)throws Exception {
        System.out.println("결제 구매 내역");
        String userId = purchaseService.getPurchaseList(paymentNo).getUserId();
        System.out.println(userId);
        Data data = new Data("success", purchaseService.getPurchaseList(paymentNo));
        System.out.println(data+"getPurchaseList데이터");
        return data;
    }

    @PostMapping(value ="addPurchase")
    public Data addPurchase(@RequestBody Purchase purchase){
        System.out.println("결제하기");

        purchaseService.addPurchase(purchase);
        Data data = new Data("success", "결제하기");
        return data;
    }
}