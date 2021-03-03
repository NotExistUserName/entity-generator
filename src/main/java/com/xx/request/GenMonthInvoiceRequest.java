package com.xx.request;

public class GenMonthInvoiceRequest {

    public final static String COMMAND_SERVICE_NAME = "genMonthInvoiceRpc";

    /** 格式 yyyy-MM-dd 生成该天的上一周期汇总 */
    private String date;
    /**商户号*/
    private String merchNo;
    /**只更新标志*/
    private String updateOnly;


    public String getUpdateOnly() {
        return updateOnly;
    }

    public void setUpdateOnly(String updateOnly) {
        this.updateOnly = updateOnly;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }
}
