package com.example.wkl_android.main.shop.address.main.model.bean;

import java.util.List;

/**
 * Created by szx
 * on 2020/4/19/019
 */
public class AddressListBean {

    /**
     * code : 0x10035
     * data : [{"addressArea":"23测试","addressDetail":"34.728629974822255,112.44498399999993","addressId":"17","addressReDetail":"河南省洛阳市老城区洛阳宏进农副产品国际物流中心","consignee":"张阳阳","isDefault":false,"phoneNumber":"15729108393","userId":6},{"addressArea":"测试","addressDetail":"34.728969974782956,112.44687899999994","addressId":"19","addressReDetail":"河南省洛阳市老城区S243(洛吉快速路)","consignee":"张阳阳","isDefault":false,"phoneNumber":"15729108393","userId":6},{"addressArea":"哈哈","addressDetail":"34.728969974782956,112.44687899999994","addressId":"20","addressReDetail":"河南省洛阳市老城区S243(洛吉快速路)","consignee":"测试默认地址","isDefault":true,"phoneNumber":"15729108393","userId":6}]
     * mesg : 成功
     * status : true
     */

    private String code;
    private String mesg;
    private boolean status;
    private List<Address> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Address> getData() {
        return data;
    }

    public void setData(List<Address> data) {
        this.data = data;
    }
}
