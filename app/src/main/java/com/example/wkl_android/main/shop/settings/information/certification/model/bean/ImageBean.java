package com.example.wkl_android.main.shop.settings.information.certification.model.bean;

/**
 * Created by szx
 * on 2020/1/9/009
 */
public class ImageBean {

    /**
     * code : 0x10041
     * message : 图片上传成功
     * state : true
     * imageUrl : D:/cache/11/14/img-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27.jpg
     * netWorkUrl : http://192.168.0.111/image/11/14/img-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27.jpg
     */

    private String code;
    private String message;
    private boolean state;
    private String imageUrl;
    private String netWorkUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNetWorkUrl() {
        return netWorkUrl;
    }

    public void setNetWorkUrl(String netWorkUrl) {
        this.netWorkUrl = netWorkUrl;
    }
}
