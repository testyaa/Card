package com.llxs.passbook.merchants.service.impl;

import com.alibaba.fastjson.JSON;
import com.llxs.passbook.merchants.constant.Constants;
import com.llxs.passbook.merchants.constant.ErrorCode;
import com.llxs.passbook.merchants.dao.MerchantsDao;
import com.llxs.passbook.merchants.entity.Merchants;
import com.llxs.passbook.merchants.service.MerchantsService;
import com.llxs.passbook.merchants.vo.CreateMerchantsRequest;
import com.llxs.passbook.merchants.vo.PassTemplate;
import com.llxs.passbook.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商户服务接口实现
 * */
@Slf4j
@Service
public class MerchantsServiceImpl implements MerchantsService {

    @Autowired
    private MerchantsDao merchantsDao;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    StringRedisTemplate redisTemplate;


    @Override
    public Response createMerchants(CreateMerchantsRequest request) {

        Response response = new Response();

        ErrorCode errorCode = request.validate(merchantsDao);
        if (errorCode != ErrorCode.SUCCESS) {
            response.setCode(errorCode.getCode());
            response.setMsg(errorCode.getDesc());
        }

        Merchants merchants = request.toMerchants();
        Integer merchantId = merchantsDao.save(merchants).getCid();

        redisTemplate.opsForValue().set(Constants.MERCHANTS_PREFIX + merchantId, JSON.toJSONString(merchants));

        response.setData(merchantId);
        return response;
    }

    @Override
    public Response getMerchantsInfoById(Integer id) {

        Response response = new Response();

        Merchants merchants = merchantsDao.findByCid(id);
        if (null == merchants) {
            response.setCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }

        response.setData(merchants);

        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {

        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);

        if (errorCode != ErrorCode.SUCCESS) {
            response.setCode(errorCode.getCode());
            response.setMsg(errorCode.getDesc());
        } else {
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate
            );
            log.info("DropPassTemplates: {}", passTemplate);
        }
        return response;
    }
}
