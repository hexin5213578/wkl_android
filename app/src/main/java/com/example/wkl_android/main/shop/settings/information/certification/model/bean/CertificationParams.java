package com.example.wkl_android.main.shop.settings.information.certification.model.bean;

/**
 * Created by szx
 * on 2020/1/3/003
 */
public class CertificationParams {
    /**
     * cidCard : string
     * cimageUrlFalse : string
     * cimageUrlTrue : string
     * cname : string
     * createDate : 2020-01-09T01:45:42.148Z
     * createUserId : 0
     * reviseDate : 2020-01-09T01:45:42.148Z
     * reviseUserId : 0
     * userId : 0
     */

    private String cidCard;
    private String cimageUrlFalse;
    private String cimageUrlTrue;
    private String cname;

    public String getCidCard() {
        return cidCard;
    }

    public void setCidCard(String cidCard) {
        this.cidCard = cidCard;
    }

    public String getCimageUrlFalse() {
        return cimageUrlFalse;
    }

    public void setCimageUrlFalse(String cimageUrlFalse) {
        this.cimageUrlFalse = cimageUrlFalse;
    }

    public String getCimageUrlTrue() {
        return cimageUrlTrue;
    }

    public void setCimageUrlTrue(String cimageUrlTrue) {
        this.cimageUrlTrue = cimageUrlTrue;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
