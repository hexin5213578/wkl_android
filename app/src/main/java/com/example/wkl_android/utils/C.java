package com.example.wkl_android.utils;

import android.widget.ScrollView;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.example.wkl_android.WholesaleMarketDetail.bean.BannerInfo;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.bean.FindList;

public class C {
    // 发版
//    private static final boolean RELEASE = true;
//    public static final boolean LOG_SWITCH = false;

    // 测试
    private static final boolean RELEASE = false;
    public static final boolean LOG_SWITCH = true;

    public static final String REGISTER;

    public static final String USER;
    public static final String UPLOAD_IMG;
    public static final String BASE_URL;
    public static final String ADD_SHOP;
    public static final String BASE_IMAGE_URL;
    public static final String GET_SLIDE;
    public static final String GET_PLATE;
    /**
     * 获取一级分类
     */
    public static final String GET_FIRST_TYPE;
    public static final String GET_CATEGORY;
    public static final String BUSINESS_MESSAGE;
    public static final String SEARCH;
    public static final String IS_EXIST;
    public static final String GET_GOODS_DETAIL;
    public static final String GET_SHOP_LIST;
    public static final String ADD_TO_SHOP_CARD;
    public static final String EDIT_SHOP_CARD;
    public static final String DELETE_SHOP_CARD;
    public static final String CREATE_ORDER;
    public static final String ADDRESS_SAVE;
    public static final String SET_DEFAULT_ADDRESS;
    public static final String HOME_PAGE;
    public static final String GOODS_COMMENT;
    public static final String DEL_ADDRESS;
    public static final String ADDRESS_EDIT;
    public static final String MARKET_CLASSIFY;
    public static final String SUBMIT_ORDER;
    public static final String WECHAT_PAY;
    public static final String ALI_PAY;
    public static final String ORDER_LIST;
    public static final String ORDER_DETAIL;
    public static final String CANCEL_ORDER;
    public static final String UP_FILE;
    public static final String ORDER_CHECK;
    public static final String SEARCH_SHOP;
    public static final String SHOP_DETAIL;
    public static final String SHOP_SAVE;
    public static final String CANCEL_SHOP_SAVE;
    public static final String COMMENT_ORDER;
    public static final String MY_COUPON;
    public static final String MARKET_HOME;
    public static final String GET_SAVE_GOODS;
    public static final String COLLECT_GOODS;
    public static final String DELECT_COLLECT;
    public static final String COLLECTION_SHOP;
    public static final  String GET_SECKILL_SHOP;

    /**
     * 默认地址
     */
    public static final String GET_DEFAULT_ADDRESS;

    static {
        BASE_URL = "http://39.100.87.173:30001";
        REGISTER = "http://39.100.87.173:22001";
        BASE_IMAGE_URL = "http://39.100.87.173";
        USER = BASE_URL + "/user";
        UPLOAD_IMG = BASE_URL + "/upload/upload/image";
        GET_SLIDE = BASE_URL + "/goods/slideshow/findList";
        GET_PLATE = BASE_URL + "/goods/plate/findList";
        GET_FIRST_TYPE = BASE_URL + "/goods/goodsclassify/findList";
        GET_CATEGORY = BASE_URL + "/goods/goodsclassify/findByPid"; //查询分类
        SEARCH = BASE_URL + "/search/search/";//商品搜索
        ADD_SHOP = BASE_URL + "/business/business/saveBusinessApplication";
        BUSINESS_MESSAGE = BASE_URL + "/business/business/findBusinessApplicationByUser";
        IS_EXIST = BASE_URL + "/business/business/findBusinessByUserIsExist";
        GET_GOODS_DETAIL = BASE_URL + "/goods/goodspu/findSpuToPreview/";
        GET_SHOP_LIST = BASE_URL + "/goods/shopCar/findByUserId";
        ADD_TO_SHOP_CARD = BASE_URL + "/goods/shopCar/save";
        EDIT_SHOP_CARD = BASE_URL + "/goods/shopCar/edit";
        DELETE_SHOP_CARD = BASE_URL + "/goods/shopCar/delete";
        CREATE_ORDER = BASE_URL + "/order/createOrder";
        ADDRESS_SAVE = BASE_URL + "/user/address/save";
        ADDRESS_EDIT = BASE_URL + "/user/address/edit";
        GET_DEFAULT_ADDRESS = BASE_URL + "/user/address/findByUser";
        SET_DEFAULT_ADDRESS = BASE_URL + "/user/address/setDefault";
        HOME_PAGE = BASE_URL + "/goods/homePage/findHomePage";
        GOODS_COMMENT = BASE_URL + "/order/goodsEstimate/findListBySkuId/";
        DEL_ADDRESS = BASE_URL + "/user/address/delete";
        MARKET_CLASSIFY = BASE_URL + "/business/wholesaleMarketClassify/findMarketClassifyForApp";
        SUBMIT_ORDER = BASE_URL + "/order/submitOrder";
        WECHAT_PAY = BASE_URL + "/pay/weChatSign";
        ALI_PAY = BASE_URL + "/pay/aliPaySign";
        ORDER_LIST = BASE_URL + "/order/findOrderByUser/";
        ORDER_DETAIL = BASE_URL + "/order/findPayOrderVOById/";
        CANCEL_ORDER = BASE_URL + "/pay/cancelPayNoOrder";
        UP_FILE = BASE_URL + "/user/upload/image";
        ORDER_CHECK = BASE_URL + "/order/findOrderByPay/";
        SEARCH_SHOP = BASE_URL + "/search/searchBusiness/";
        SHOP_DETAIL = BASE_URL + "/business/business/findBusinessVOById/";
        SHOP_SAVE = BASE_URL + "/user/userShopFavorite/save";
        CANCEL_SHOP_SAVE = BASE_URL + "/user/userGoodsFavorite/delete";
        COMMENT_ORDER = BASE_URL + "/order/orderEstimate/save";
        MY_COUPON = BASE_URL + "/user/userCoupon/findList/";
        MARKET_HOME = BASE_URL + "/business/wholesaleMarket/findMarketHomePage/";
        GET_SAVE_GOODS = BASE_URL + "/user/userGoodsFavorite/findList/";
        COLLECT_GOODS = BASE_URL + "/user/userGoodsFavorite/saveOrDelete";
        DELECT_COLLECT = BASE_URL + "/user/userGoodsFavorite/deleteList";
        COLLECTION_SHOP = BASE_URL + "/user/userShopFavorite/findList/";
        GET_SECKILL_SHOP = BASE_URL+"/goods/kill/findListByCode/3";
    }
}
