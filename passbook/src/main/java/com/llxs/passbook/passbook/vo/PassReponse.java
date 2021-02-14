package com.llxs.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassReponse {

    /** 打折的商品ID */
    private long goodsId;

    /** 具体折扣（80=八折，75=七五折） */
    private int discount;
}
