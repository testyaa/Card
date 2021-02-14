package com.llxs.passbook.passbook.service;

import com.llxs.passbook.passbook.vo.PassRequest;
import com.llxs.passbook.passbook.vo.Response;

import java.io.IOException;

public interface PassOperatorService {

    /**
     * 获取优惠券
     */
    Response gainPassTempate(long templateId);

    /**
     * 使用优惠券
     */
    Response usePassTemplate(PassRequest request) throws IOException;

}
