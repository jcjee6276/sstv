package com.example.sstv.deal.restController;

import com.example.sstv.common.Data;
import com.example.sstv.deal.Purchase;
import com.example.sstv.deal.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {
    private PurchaseService purchaseService;

    @Autowired
    public PurchaseRestController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping(value = "getPurchaseList/{userId}")
    public Data getPurchaseList(@PathVariable String userId) {
        List<Purchase> purchaseList = purchaseService.getPurchaseList(userId);

        Data data = new Data("success", purchaseList);
        return data;
    }

    @PostMapping(value = "addPurchase/{userId}")
    public Data addPurchase(@PathVariable String userId, @RequestBody Purchase purchase) {
        purchase.setUserId(userId);

        String ImpUid = purchase.getImpUid();
        String merchantUid = purchase.getMerchantUid();
        purchaseService.addPurchase(purchase);
        System.out.println("addPurchas rest"+userId+purchase);
        Data data = new Data("success", "결제하기");
        return data;
    }
    @PostMapping(value = "savePaymentHistory")
    public Data savePaymentHistory(@RequestBody Purchase purchase) {
        purchaseService.addPurchase(purchase);
        Data data = new Data("success", "Payment history saved");
        return data;
    }


}
