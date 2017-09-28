package com.item1024.pojo;

public class SystemOption {
    private Integer webId;

    private String webName;

    private String webImage;

    private String webKey;

    private String webRecordnum;

    private String webCopyright;

    private String webMaskword;

    private Double integralUserule;

    private Double integralAddrule;

    private Double integralMinusrule;

    private Integer saleActornum;

    private Double orderExpiretime;

    private Double couponExpiretime;

    private Double couponWarntime;

    private Double billFee;

    public Integer getWebId() {
        return webId;
    }

    public void setWebId(Integer webId) {
        this.webId = webId;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName == null ? null : webName.trim();
    }

    public String getWebImage() {
        return webImage;
    }

    public void setWebImage(String webImage) {
        this.webImage = webImage == null ? null : webImage.trim();
    }

    public String getWebKey() {
        return webKey;
    }

    public void setWebKey(String webKey) {
        this.webKey = webKey == null ? null : webKey.trim();
    }

    public String getWebRecordnum() {
        return webRecordnum;
    }

    public void setWebRecordnum(String webRecordnum) {
        this.webRecordnum = webRecordnum == null ? null : webRecordnum.trim();
    }

    public String getWebCopyright() {
        return webCopyright;
    }

    public void setWebCopyright(String webCopyright) {
        this.webCopyright = webCopyright == null ? null : webCopyright.trim();
    }

    public String getWebMaskword() {
        return webMaskword;
    }

    public void setWebMaskword(String webMaskword) {
        this.webMaskword = webMaskword == null ? null : webMaskword.trim();
    }

    public Double getIntegralUserule() {
        return integralUserule;
    }

    public void setIntegralUserule(Double integralUserule) {
        this.integralUserule = integralUserule;
    }

    public Double getIntegralAddrule() {
        return integralAddrule;
    }

    public void setIntegralAddrule(Double integralAddrule) {
        this.integralAddrule = integralAddrule;
    }

    public Double getIntegralMinusrule() {
        return integralMinusrule;
    }

    public void setIntegralMinusrule(Double integralMinusrule) {
        this.integralMinusrule = integralMinusrule;
    }

    public Integer getSaleActornum() {
        return saleActornum;
    }

    public void setSaleActornum(Integer saleActornum) {
        this.saleActornum = saleActornum;
    }

    public Double getOrderExpiretime() {
        return orderExpiretime;
    }

    public void setOrderExpiretime(Double orderExpiretime) {
        this.orderExpiretime = orderExpiretime;
    }

    public Double getCouponExpiretime() {
        return couponExpiretime;
    }

    public void setCouponExpiretime(Double couponExpiretime) {
        this.couponExpiretime = couponExpiretime;
    }

    public Double getCouponWarntime() {
        return couponWarntime;
    }

    public void setCouponWarntime(Double couponWarntime) {
        this.couponWarntime = couponWarntime;
    }

    public Double getBillFee() {
        return billFee;
    }

    public void setBillFee(Double billFee) {
        this.billFee = billFee;
    }
}