package com.xx.request;

import lombok.Data;

/**
 * @Author: shuqingzhou
 * @Date: 2020/11/2 10:02
 * @Description: 冲正拉取手续费服务费流水的接口
 */
@Data
public class InvoiceTransWashedRequest {

    public final static String COMMAND_SERVICE_NAME = "invoiceTransWashedRpc";

    /**开票流水ID*/
    private String transId;
    /**商户号*/
    private String merchNo;
    /**流水交易起始日期yyyyMMdd*/
    private String beginTransDate;
    /**流水交易截止日期yyyyMMdd*/
    private String endTransDate;
}
