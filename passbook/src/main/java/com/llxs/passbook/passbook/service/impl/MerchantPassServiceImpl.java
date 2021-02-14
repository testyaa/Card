package com.llxs.passbook.passbook.service.impl;

import com.llxs.passbook.passbook.dao.PassTemplateDao;
import com.llxs.passbook.passbook.entity.PassTemplate;
import com.llxs.passbook.passbook.service.MerchantsPassService;
import com.llxs.passbook.passbook.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantPassServiceImpl implements MerchantsPassService {

    @Autowired
    private PassTemplateDao passTemplateDao;

    @Override
    public Response getMerchantPassInfo(int merchantId) {

        List<PassTemplate> passTemplateList = passTemplateDao.findByMerchantId(merchantId);
        return new Response(passTemplateList);
    }
}
