package com.llxs.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassRequest {

    /** 要使用优惠券的商品 ID */
    private long goodsId;

    /** 优惠券 ID */
    private long templateId;
}
