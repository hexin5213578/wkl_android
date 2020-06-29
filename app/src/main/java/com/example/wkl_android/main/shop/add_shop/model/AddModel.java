package com.example.wkl_android.main.shop.add_shop.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.add_shop.model.bean.AddShop;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/9/009
 */
public class AddModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    public void submit(String companyName, String companyPhone, String businessDepositBank,
                       String businessPaymentCode, String linkman, String linkmanPhone,
                       String taxpayerCode, String invoiceTitle, String mallBusinessNum,
                       String imgUrl, String businessName, String companyLocation,
                       String companyAddress, String fileName, String postCode, String companyUrl,
                       String companyIntroduce, String companyEmail, String businessGrade,
                       String businessClassify, String sellClassify, ICallBack callBack) {
        String url = C.BASE_URL + "/business/saveBusinessApplication";
        AddShop dataBean = new AddShop();
        dataBean.setBusinessClassify("123");
        dataBean.setBusinessDepositBank("11");
        dataBean.setBusinessGrade("123");
        dataBean.setBusinessName("11");
        dataBean.setBusinessPaymentCode("11");
        dataBean.setCompanyAddress("11");
        dataBean.setCompanyEmail("11");
        dataBean.setCompanyIntroduce("11");
        dataBean.setCompanyLocation("11");
        dataBean.setCompanyName("11");
        dataBean.setCompanyUrl("11");
        dataBean.setCompanyPhone("11");
        dataBean.setInvoiceTitle("11");
        dataBean.setLinkman("11");
        dataBean.setLinkmanPhone("11");
        dataBean.setMallBusinessNum("11");
        dataBean.setPostcode("11");
        dataBean.setTaxpayerCode("11");
        dataBean.setBusinessImageUrl("111");
        dataBean.setBusinessImageName("111");
        dataBean.setBusinessCategory("123");
        String json = new Gson().toJson(dataBean);
        HttpUtils.getInstance().doPostJson(url, json, Common.getToken(), this, callBack);
    }

    public void submit(ICallBack callBack) {
        String url = C.BASE_URL + "/business/business/saveBusinessApplication";
        AddShop dataBean = new AddShop();
        dataBean.setBusinessClassify("123");
        dataBean.setBusinessDepositBank("11");
        dataBean.setBusinessGrade("123");
        dataBean.setBusinessName("11");
        dataBean.setBusinessPaymentCode("11");
        dataBean.setCompanyAddress("11");
        dataBean.setCompanyEmail("11");
        dataBean.setCompanyIntroduce("11");
        dataBean.setCompanyLocation("11");
        dataBean.setCompanyName("11");
        dataBean.setCompanyUrl("11");
        dataBean.setCompanyPhone("11");
        dataBean.setInvoiceTitle("11");
        dataBean.setLinkman("11");
        dataBean.setLinkmanPhone("11");
        dataBean.setMallBusinessNum("11");
        dataBean.setPostcode("11");
        dataBean.setTaxpayerCode("11");
        dataBean.setBusinessImageUrl("111");
        dataBean.setBusinessImageName("111");
        dataBean.setBusinessCategory("123");
        String json = new Gson().toJson(dataBean);
        HttpUtils.getInstance().doPostJson(url, json, Common.getToken(), this, callBack);
    }
}
