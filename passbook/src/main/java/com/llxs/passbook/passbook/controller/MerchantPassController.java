package com.llxs.passbook.passbook.controller;

import com.llxs.passbook.passbook.service.MerchantsPassService;
import com.llxs.passbook.passbook.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passbook")
public class MerchantPassController {

    @Autowired
    private MerchantsPassService merchantsPassService;

    /**
     * 根据 merchantId 获取相应商户的所有优惠券
     */
    @GetMapping("/passtemplateList/{merchantId}")
    Response getMerchantPassInfo(@PathVariable("merchantId") int merchantId) {
        return merchantsPassService.getMerchantPassInfo(merchantId);
    }
}
