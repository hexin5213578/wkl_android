package com.example.wkl_android.base;


import com.example.wkl_android.BuildConfig;
import com.example.wkl_android.dialog.CommDialog;
import com.example.wkl_android.utils.SPUtils;

public interface Constant {

    boolean isDebug = BuildConfig.DEBUG;

    String LOG_TAG_HTTP = "log_tag_http";
    String LOG_TAG_WEB = "log_tag_web";
    String ANDROID = "Android";
    String MONEY_ZERO = "0.00";
    String NUMBER_ZERO = "0";
    String DATE_FORMAT_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
    String DATE_FORMAT_HH_mm_ss = "HH:mm:ss";
    String DATE_FORMAT_mm_FEN_ss_MIAO = "m分s秒";
    String DATE_FORMAT_dd_TIAN_HH_SHI_mm_FEN_ss_MIAO = "d天H小时m分s秒";
    String DATE_FORMAT_mm_FEN = "m分钟";
    String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd HH小时mm分ss秒";
    String HTTP = "http";
    String HTTPS = "https";

    class HTTP_CODE {
        public static final int SUCCESS = 200;
        public static final int NEED_LOGIN = 1000;
    }

    class FRAGMENT_TAG {
        public static final String HOME = "tag_home";
        public static final String SEARCH = "tag_categoty";
        public static final String SHOP_CAR = "tag_shop_car";
        public static final String MY = "tag_my";
        public static final String VIP_CENTER = "tag_vip_center";
        public static final String DIALOG_COMM = CommDialog.class.getSimpleName();

    }


    class HTTP_PARAMS {
        //用户session	string
        public static final String AUTH_SESSION = "auth_session";
        //渠道	string	Y	官方下载传:bfzl，其他是第三方渠道号
        public static final String CHANNEL = "channel";
        //请求客户端类型	Integer	android; 3
        public static final String CLIENT_TYPE = "client_type";
        //客户端系统版本	string
        public static final String CLIENT_VERSION = "client_version";
        //客户端唯一id	string
        public static final String DVC_ID = "dvc_id";
        //参数	string
        public static final String PARAMS = "params";
        //uninx时间戳	Integer
        public static final String TIMESTAMP = "timestamp";
        //版本号	Integer
        public static final String VERSION = "version";
        //加密串
        public static final String SIGN = "sign";

        public static final String KEY = "key";

        public static final String MY = "bfzl_eef44512c30d8";
    }


    class FRAGMENT_NAME {
        public static final String HOME = "首页";
        public static final String SEARCH = "搜索";
        public static final String SHOP_CAR = "购物车";
        public static final String MY = "我的";
        public static final String VIP_CENTER = "会员";
    }

    class INTENT_KEY {
        public static final String TAG = "tag";
        public static final String TARGET = "target";
        public static final String DATA = "data";
        public static final String TITLE = "title";
        public static final String DESC = "desc";
        public static final String URL = "url";
        public static final String IMAGE_URL = "image_url";
        public static final String NAME = "name";
        public static final String GOODS = "goods";
        public static final String ID = "id";
        public static final String SESSION = "session";
        public static final String FROM = "from";
        public static final String TYPE = "type";
        public static final String VERSION = "version";
        public static final String CONTENT = "content";
        public static final String EXTRA = "extra";
        public static final String POSITION = "position";
        public static final String OFFSET = "offset";
        public static final String INFO = "info";
        public static final String PAGE = "page";
        public static final String SPU = "spu";
        public static final String SKU = "sku";
        public static final String BRAND = "brand";
        public static final String CATEGORY = "category";
        public static final String SEARCH = "search";
        public static final String PAGE_TYPE = "page_type";
    }

    class TARGET {
        public static final String WX_AUTH = "wx_auth";
    }

    class TARGET_PAGE {
        public static final int FINISH = 0;
        public static final int MAIN_MY = 1;
        public static final int WEB = 2;
    }

    /**
     * H5 JS交互
     */
    class H5_JS {
        public static final String SCHEME_BFZL = "bfzl";
        public static final String NEED_SESSION = "need_session";
        public static final String WX_PAY = "wx_pay_action";
        public static final String WX_SHARE = "wx_share_action";
        public static final String SAVE_PIC = "save_pic_action";
        public static final String GOODSDETAIL = "goodsdetail";
        public static final String BRAND = "brand";
        public static final String SUCCESS = "1";
        public static final String FAIL = "0";
        public static final String SHARE = "share";
        public static final String HTML = "/html";
    }

    class H5_PATH {
        public static final String INVITE_SHARE = "inviteshare";
        public static final String PAYMENT = "payment";
        public static final String NOVICE_BOOK = "novicebook";
        public static final String NEWS = "news";
        public static final String CLASSROOM = "classroom";
        public static final String HELP = "webview/shop/help";
        public static final String COOPERATION = "webview/shop/cooperation";
        public static final String PLUS_MY = "webview/plus/my";
    }

    /**
     * H5邀请类型
     */
    class H5_INVITE_TYPE {
        // 1为邀请创建团队、团队组建完成去支付
        public static final String CREATE_TEAM = "1";
        // 2为邀请加入团队、
        public static final String JOIN_TEAM = "2";
        // 3为邀请成为VIP
        public static final String INVITE_BE_VIP = "3";
        // 4为成为VIP去支付
        public static final String BE_VIP = "4";
        // 5为邀请消费会员
        public static final String INVITE_CONSUME = "5";
    }

    /**
     * H5拼接参数Key
     */
    class H5_PARAMS {
        public static final String INVITE_CODE = "invite_code";
        // 1为直接分享htmlurl地址，2为分享小程序卡片(要满足platform=1)
        public static final String TYPE = "type";
        public static final String NAME = "name";
        public static final String USER_TOKEN = "user_token";
        public static final String USER_AVATAR = "user_avatar";
        public static final String ORDER_ID = "order_id";
        public static final String PREPAY_ID = "prepayId";
        public static final String TIME_STAMP = "timeStamp";
        public static final String NONCE_STR = "nonceStr";
        public static final String SIGN = "sign";
        public static final String PIC = "pic";
        public static final String SKU = "sku";
        public static final String SPU = "spu";
        public static final String BRANDID = "brandId";
        // 1为微信
        public static final String PLATFORM = "platform";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String THUMB_IMAGE = "thumbImage";
        //低版本微信网页链接
        public static final String MINI_WEB_PAGE_URL = "miniwebpageUrl";
        //小程序页面的路径
        public static final String PATH = "path";
        //小程序username 小程序ID
        public static final String USER_NAME = "userName";
        public static final String INVITE_SIGN = "invite_sign";
    }

    class PLATFORM {
        public static final int WX = 1;
    }

    class SP_NAME {
        public static final String USER = "user";
        public static final String SETTING = "setting";
        public static final String SYSTEM = "system";
        public static final String CART = "cart";
        public static final String HOME = "home";
        public static final String GOODS = "goods";
        public static final String HISTORY = "history";
        public static final String DEPOSIT = "deposit";
    }

    class SP_KEY {
        public static final String FIRST_LAUNCH = "first_launch";
        public static final String API_ENV = "api_env";
        public static final String AUTH_SESSION = "auth_session";
        public static final String SPREAD_USER_INFO = "spread_user_info";
        public static final String USER_INFO = "user_info";
        public static final String USER_ID = "user_id";
        public static final String UUID = "uuid";
        public static final String NOTCH_PARAMS = "notch_params";
        public static final String NOTIFY_TIP = "notify_tip";
        public static final String PROV_CITY_VERSION = "prov_city_version";
        public static final String VERSION_INFO = "version_info";
        public static final String CART_NUM = "cart_num";
        public static final String HOME_CACHE = "home_cache";
        public static final String DEFAULT_SPEC = "default_spec";
        public static final String SPU = "spu";
        public static final String LAST_SKU = "last_sku";
        public static final String SKU = "sku";
        public static final String SEARCH = "search";
        public static final String DEPOSIT_PHONE = "deposit_phone";
        public static final String INVITE_SIGN = "invite_sign";
    }

    class WX {
        public static final String APPID = "wx4df431b6c5f636e7";
        public static final String SECRET = "3b2b942dc95dce9dafdc3b651a1cd7c4";
        public static final String PACKAGE = "Sign=WXPay";
        public static final String PARTNER_ID = "1516853871";
        public static final String MINI_NAME = "gh_880b7ca49895";
    }

    class DIR {
        public static final String PARENT = "BaiFenZhiLiu";
        public static final String APK = "apk";
        public static final String PICTURE = "picture";
    }

    class FILE_NAME {
        public static final String PROV_CITY_DATA = "prov_city_data.json";
        public static final String PIC_PRE = "bfzl";
    }

    class RECEIVER_ACTION {
        public static final String WX_LOGIN_SUCCESS = "wx_login_success";
        public static final String WX_LOGIN_FAIL = "wx_login_fail";
        public static final String WX_SHARE_SUCCESS = "wx_share_success";
        public static final String WX_SHARE_FAIL = "wx_share_fail";
        public static final String WX_PAY_SUCCESS = "wx_pay_success";
        public static final String WX_PAY_FAIL = "wx_pay_fail";
    }

    class INVIATION_TYPE {
        public static final int USER = 1;
        public static final int VIP = 2;

    }

    class USER_TYPE {
        // 1消费 2分销
        public static final String CUSTOMER = "1";
        public static final String SPREAD = "2";
    }

    class USER_CUSTOMER_TYPE {
        //1，注册用户；2，会员；3，试用会员
        public static final String REGISTER = "1";
        public static final String COMMON = "2";
        public static final String PROBATION = "3";
    }

    class USER_RANK {
//         ( 1 注册用户  2 消费会员 6 合伙人 7 联合创始人 8 创始人 9 创业团队)
        /**
         * 团队创建者
         */
        public static final String TEAM_CREATOR = "10";
        /**
         * 团队成员
         */
        public static final String TEAM_MEMBER = "9";
        /**
         * 创始人
         */
        public static final String SUPER_PARTNER = "8";
        /**
         * 联合创始人
         */
        public static final String PARTNER = "7";
        /**
         * 合伙人
         */
        public static final String VIP_MEMBER = "6";
        /**
         * 消费会员
         */
        public static final String COMM_MEMBER = "2";
        /**
         * 注册用户
         */
        public static final String COMM = "1";
    }

    class USER_RANK_NAME {
        //        角色id（vip:vip  合伙人:partner  会员:normal  育成合伙人:rear_partner  育成超级合伙人:rear_super_partner  团队:team）
        public static final String VIP = "vip";
        public static final String PARTNER = "partner";
    }

    class UMeng {
        public static final String KEY = "5bf291d3f1f5561648000386";
    }

    class NOTIFICATION_CHANNEL {
        public static final String ID_MAIN = "main";
        public static final String NAME_MAIN = "主要通知";

        public static final String ID_MSG = "msg";
        public static final String NAME_MSG = "消息通知";
    }

    class INVITE_TYPE {
        /**
         * 直接
         */
        public static final int DIRECT = 2;
        /**
         * 间接
         */
        public static final int INDIRECT = 1;
        /**
         * 间接
         */
        public static final int ALL = 0;
    }

    /**
     * WebView功能类型
     */
    class WEB_PAGE_TYPE {

        /**
         * 分享
         */
        public static final int SHARE = 1;
    }

    class PAY_RESULT {
        public static final int SUCCESS = 1;
        public static final int FAIL = 2;
    }

    class SYMBOL {
        public static final String DOUBLE_BAR = "- -";
        public static final String QUEST = "?";
        public static final String EQUALS = "=";
        public static final String ADN = "&";
        public static final String BAR = "-";
        public static final String PLUS = "+";
        public static final String COLON_CN = "：";
        public static final String COLON = ":";
        public static final String PERCENT = "%";
        public static final String BRACKET_LEFT_CN = "（";
        public static final String BRACKET_RIGHT_CN = "）";
        public static final String DOT = ".";
        public static final String COMMA = ",";
        public static final String MULTIPLE = "×";
        public static final String RMB = "¥";
        public static final String ELLIPSIS = "…";
    }

    class ORDER_TAB {
        public static final int All = 0;
        public static final int WAIT_PAY = 1;
        public static final int WAIT_SEND = 2;
        public static final int WAIT_RECEIVE = 3;
        public static final int BACK = 4;
    }

    class ORDER_STATUS {
        // 1：待支付；
        public static final int WAIT_PAY = 1;
        // 2：待发货；
        public static final int WAIT_SEND = 2;
        // 3：部分发货；
        public static final int PART_SEND = 3;
        // 4：已发货；
        public static final int ALL_SEND = 4;
        // 5：已完成；
        public static final int FINISH = 5;
        // 6：已取消；
        public static final int CANCEL = 6;
    }

    class ORDER_TYPE {
        // 0, 普通订单；
        public static final int COMM = 0;
        // 1,一元购；
        public static final int ONE_YUAN_BUY = 1;
        // 2,虚拟订单-购买会员卡；
        public static final int VIRTUAL_MEMBER_CARD = 2;
        // 3,虚拟订单-购买vip；
        public static final int VIRTUAL_VIP = 3;
        // 4,虚拟订单-购买团队合伙人;
        public static final int VIRTUAL_PARTNER = 4;
        // 5,钻石购买
        public static final int DIAMOND_BUY = 5;
        // 7,充值类
        public static final int RECHARGE = 7;
    }

    class GOODS_DETAIL {
        public static final int SPEC = 0;
        public static final int ADDCART = 1;
        public static final int BUYNOW = 2;
        public static final String CASH_BACK = "cash_back";
    }

    class TIME_TYPE {
        // 时间类型,
        // 0---不根据时间
        public static final int ALL = 0;
        // 1---每日新增
        public static final int TODAY = 1;
        // 2---每月新增
        public static final int MONTH = 2;
    }

    class VIP_PRIVILEGE_TYPE {
        // banner位展示类型 介绍或用户信息
        public static final String USER_TYPE = "base";
        // 会员权益
        public static final String VIP_RIGHTS = "rights_and_interests";
        // 会员特权 图片标题
        public static final String PRIVILEGE_TITLE = "member_privilege";
        // 出厂价购物
        public static final String FACTORY_PRICE = "factory_price";
        // 钻石专区
        public static final String FREE_BUY = "free_buy";
        // 一元购
        public static final String ONE_YUAN_BUY = "one_yuan_buy";
        // 购物返现
        public static final String CASH_BACK = "cash_back";
        // 邀请
        public static final String INVITE = "invite";
        // 余额抵现
        public static final String BALANCE = "balance";
        // 开通会员按钮
        public static final String OPEN_BUTTON = "open_button";
        // 保留购物差价
        public static final String KEEP_PRICE_DIFF = "price_diff";
        // 单品
        public static final String GOODS_ITEM = "goods_item";
    }

    class VIP_PRIVILEGE_TYPE_INT {
        // banner位展示类型 介绍或用户信息
        public static final int USER_TYPE = 1;
        // 会员权益
        public static final int VIP_RIGHTS = 2;
        // 会员特权 图片标题
        public static final int PRIVILEGE_TITLE = 3;
        // 出厂价购物
        public static final int FACTORY_PRICE = 4;
        // 钻石专区
        public static final int FREE_BUY = 5;
        // 一元购
        public static final int ONE_YUAN_BUY = 6;
        // 购物返现
        public static final int CASH_BACK = 7;
        // 邀请
        public static final int INVITE = 8;
        // 余额抵现
        public static final int BALANCE = 9;
        // 开通会员按钮
        public static final int OPEN_BUTTON = 10;
        // 保留购物差价
        public static final int KEEP_PRICE_DIFF = 11;
        // 单品
        public static final int GOODS_ITEM = 12;
    }

    class SORT_KEY {
        // "price-desc",//排序，默认不传按评分排，
        // 可选 值'price-asc'-按价格升序,
        // 'price-desc'-按价格降序,
        // 'time-asc'-按上架时间升序,
        // 'time-desc'-按上架时间降序,
        // 'amount-asc'-按销量升序,
        // 'amount-desc'-按销量降序

        /**
         * 按价格升序
         */
        public static final String PRICE_ASC = "price-asc";
        /**
         * 按价格降序
         */
        public static final String PRICE_DESC = "price-desc";
        /**
         * 按上架时间升序
         */
        public static final String TIME_ASC = "time-asc";
        /**
         * 按上架时间降序
         */
        public static final String TIME_DESC = "time-desc";
        /**
         * 按销量升序
         */
        public static final String AMOUNT_ASC = "amount-asc";
        /**
         * 按销量降序
         */
        public static final String AMOUNT_DESC = "amount-desc";

    }

    /**
     * 物流状态
     */
    class LOGISTICS_STATUS {
        // 0:在途
        public static final int ON_ROAD = 0;
        // ,1:揽件
        public static final int PACKAGE = 1;
        // ,2:疑难
        public static final int PROBLEM = 2;
        // ,3:签收
        public static final int RECEIVED = 3;
        // ,4:退签
        public static final int RECEIVE_REFUSED = 4;
        // ,5:派件
        public static final int DELIVERY = 5;
        // ,6:退回
        public static final int RETURN = 6;
    }

    /**
     * 优惠券状态 本地用
     */
    class COUPON_STATE {
        /**
         * 可使用
         */
        public static final int CAN_USE = 0;
        /**
         * 已使用
         */
        public static final int USED = 1;
        /**
         * 已过期
         */
        public static final int OVER_TIME = 2;

    }

    /**
     * 优惠券状态 接口参数
     */
    class COUPON_STATE_PARAMS {
        /**
         * 作废
         */
        public static final int ABANDON = 0;
        /**
         * 可使用
         */
        public static final int CAN_USE = 1;
        /**
         * 已使用
         */
        public static final int USED = 2;
        /**
         * 已转赠
         */
        public static final int TRANSFORMED = 3;
        /**
         * 已过期
         */
        public static final int OVER_TIME = 4;

    }

    class GOODS_LIST_TYPE{
        public static final String GOODS_LIST_TYPE = "goods_list_type";
    }
}
