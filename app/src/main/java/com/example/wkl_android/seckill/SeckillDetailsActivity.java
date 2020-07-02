package com.example.wkl_android.seckill;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.wkl_android.R;
import com.example.wkl_android.base.all.BaseAvtivity;
import com.example.wkl_android.base.all.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.common.CustomDialog;
import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.good.model.GoodsSelDialog;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.good.ui.evaluate.GoodsEvaluateActivity;
import com.example.wkl_android.main.MainActivity;
import com.example.wkl_android.main.shop.address.location.LocationAddressActivity;
import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.seckill.contract.SeckillContract;
import com.example.wkl_android.seckill.presenter.SeckillPresenter;
import com.example.wkl_android.shop_street.shop_home.ui.activity.ShopHomeActivity;
import com.example.wkl_android.widget.web.NoScrollWebView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sunfusheng.util.Utils;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.GetMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.wkl_android.base.app.BaseApp.APP;
import static com.example.wkl_android.common.Common.APP_ID;
import static com.example.wkl_android.common.Common.APP_ID_QQ;

/**
 * @ProjectName: wkl_android
 * @Package: com.example.wkl_android.seckill
 * @ClassName: SeckillDetailsActivity
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/7/1 17:31
 */
public class SeckillDetailsActivity extends BaseAvtivity implements CustomDialog.OnCenterItemClickListener,SeckillContract.IView,View.OnClickListener {
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.iv_back)
    LinearLayout ivBack;
    @BindView(R.id.iv_share)
    LinearLayout ivShare;
    @BindView(R.id.iv_more)
    LinearLayout ivMore;
    @BindView(R.id.zhekoujia)
    TextView zhekoujia;
    @BindView(R.id.yuanjia)
    TextView yuanjia;
    @BindView(R.id.salecount)
    TextView salecount;
    @BindView(R.id.hour)
    TextView tvhour;
    @BindView(R.id.second)
    TextView tvsecond;
    @BindView(R.id.minute)
    TextView tvminute;
    @BindView(R.id.jiangjia)
    ImageView jiangjia;
    @BindView(R.id.guanzhu)
    ImageView guanzhu;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_youhui)
    TextView tvYouhui;
    @BindView(R.id.tv_manjian)
    TextView tvManjian;
    @BindView(R.id.youhuidetails)
    ImageView youhuidetails;
    @BindView(R.id.tv_yixuan)
    TextView tvYixuan;
    @BindView(R.id.yixuandetails)
    ImageView yixuandetails;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.songzhidetails)
    ImageView songzhidetails;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_count_pingjia)
    TextView tvCountPingjia;
    @BindView(R.id.iv_touxiang1)
    SimpleDraweeView ivTouxiang1;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.tv_data1)
    TextView tvData1;
    @BindView(R.id.tv_pingjia1)
    TextView tvPingjia1;
    @BindView(R.id.rc1)
    RecyclerView rc1;
    @BindView(R.id.iv_touxiang2)
    SimpleDraweeView ivTouxiang2;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.tv_data2)
    TextView tvData2;
    @BindView(R.id.tv_pingjia2)
    TextView tvPingjia2;
    @BindView(R.id.rc2)
    RecyclerView rc2;
    @BindView(R.id.tv_seemore)
    TextView tvSeemore;
    @BindView(R.id.iv_dianputouxiang)
    ImageView ivDianputouxiang;
    @BindView(R.id.tv_dianpuname)
    TextView tvDianpuname;
    @BindView(R.id.iv_haopinglv)
    TextView ivHaopinglv;
    @BindView(R.id.bt_goindianpu)
    Button btGoindianpu;
    @BindView(R.id.kefu)
    TextView kefu;
    @BindView(R.id.dianpu)
    TextView dianpu;
    @BindView(R.id.shopcar)
    TextView shopcar;
    @BindView(R.id.tv_AddShoppingCart)
    TextView tvAddShoppingCart;
    @BindView(R.id.tv_Buy)
    TextView tvBuy;
    @BindView(R.id.webview)
    NoScrollWebView webView;
    @BindView(R.id.tv_goodsname)
    TextView tvgoodsname;
    @BindView(R.id.tv_daojishi)
    TextView tvdaojishi;
    @BindView(R.id.over)
    TextView over;
    private long time;
    private long time1;
    private CountDownTimer timer;
    GoodsSelDialog dialog;
    private String id;
    private boolean ischecked = true;
    private BasePresenter basePresenter;
    private PopupWindow popupWindow;
    private CustomDialog custom;
    private Tencent tencent;
    private IWXAPI api;
    @Override
    protected int getResId() {
        return R.layout.activity_seckilldetails;
    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        ArrayList<SpikeBean.DataBean> list = intent.getParcelableArrayListExtra("list");

        if (list!=null && list.size()>0){
            //商品ID
            String killId = list.get(0).getKillId();

            SpikeBean.DataBean dataBean = list.get(0);
            tvgoodsname.setText(dataBean.getSkuTitle());
            zhekoujia.setText(""+dataBean.getKillPrice());
            yuanjia.setText(""+dataBean.getKillPriceRaw());
            //中划线
            yuanjia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            //计算已售
            int killStockRaw = dataBean.getKillStockRaw();
            int killStock = dataBean.getKillStock();
            int a = killStockRaw-killStock;
            salecount.setText("已售"+a);
            Glide.with(this).load(dataBean.getSkuImage()).error(R.mipmap.goods_grid).into(ivImage);

            String killOverTime = dataBean.getKillOverTime();
            //24小时制
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                time = simpleDateFormat.parse(killOverTime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");
            Date curDate =  new Date(System.currentTimeMillis());
            //获取当前时间
            String   str   =   formatter.format(curDate);
            try {
                time1 = simpleDateFormat.parse(str).getTime();
                Log.d("xxx", time1 +"");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long timestamp =   time-time1;
            if(timestamp>0){
                timer = new CountDownTimer(timestamp, 1000) {
                    @Override
                    public void onTick(long l) {
                        long day = l/(1000*60*60*24);
                        long hour = (l - day*(1000*60*60*24))/(1000*60*60);
                        long minute = (l-day*(1000*60*60*24)-hour*(1000*60*60))/(1000*60);
                        long second = (l-day*(1000*60*60*24)-hour*(1000*60*60)-minute*(1000*60))/1000;
                        tvhour.setText(hour+"");
                        tvsecond.setText(minute+"");
                        tvminute.setText(second+"");
                    }
                    @Override
                    public void onFinish() {

                    }
                };
                timer.start();
            }else{
                //处理倒计时结束
                tvdaojishi.setVisibility(View.GONE);
                over.setVisibility(View.VISIBLE);
                tvhour.setVisibility(View.GONE);
                tvsecond.setVisibility(View.GONE);
                tvminute.setVisibility(View.GONE);
            }



            Log.d("hmy", Common.getToken());
            Log.d("hmy",dataBean.getKillId()+"");
            basePresenter = getPresenter();
            //加载商品详情
            if(basePresenter instanceof SeckillPresenter){
                ((SeckillPresenter) basePresenter).doGetGoodsDetails(" http://39.100.87.173:30001/goods/goodspu/findSpuToPreview/"+dataBean.getSkuId()+"/"+"0");
            }
            //webview加载商品详情
            //webview处理
            WebSettings webSettings = webView.getSettings();//获取webview设置属性
            //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
            webSettings.setJavaScriptEnabled(true);//支持js
            webSettings.setUseWideViewPort(true); // 关键点
            webSettings.setLoadWithOverviewMode(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                webSettings.setMediaPlaybackRequiresUserGesture(false);
            }
            webSettings.setAllowFileAccess(true); // 允许访问文件
            webSettings.setBuiltInZoomControls(false); // 不显示放大缩小
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容
            webSettings.setSupportZoom(false); // 不可以缩放wv_detail.setVerticalScrollBarEnabled(false);
            webView.setVerticalScrollbarOverlay(false);
            webView.setHorizontalScrollBarEnabled(false);
            //默认字体，屏幕密度*18
            webSettings.setDefaultFontSize((int) (Utils.getDisplayMetrics(this).density * 18));
            webView.setHorizontalScrollbarOverlay(false);

            webSettings.setDefaultTextEncodingName("UTF-8");
            webView.setWebViewClient(new MyWebViewClient());
        }
        ivBack.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        ivMore.setOnClickListener(this);

        youhuidetails.setOnClickListener(this);
        yixuandetails.setOnClickListener(this);
        songzhidetails.setOnClickListener(this);

        jiangjia.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
        tvSeemore.setOnClickListener(this);
        btGoindianpu.setOnClickListener(this);

        kefu.setOnClickListener(this);
        dianpu.setOnClickListener(this);
        shopcar.setOnClickListener(this);
        tvAddShoppingCart.setOnClickListener(this);
        tvBuy.setOnClickListener(this);


// Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
// 其中APP_ID是分配给第三方应用的appid，类型为String。
// 其中Authorities为 Manifest文件中注册FileProvider时设置的authorities属性值
        tencent = Tencent.createInstance(Common.APP_ID_QQ, this.getApplicationContext());

// 1.4版本:此处需新增参数，传入应用程序的全局context，可通过activity的getApplicationContext方法获取

    }

    @Override
    public void OnCenterItemClick(CustomDialog dialog, View view) {
        switch (view.getId()){
            case R.id.l1:
                final Bundle params = new Bundle();
                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
                params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");
                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/news/1.html");
                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
                if (!tencent.isQQInstalled(this)) {
                    Toast.makeText(this, "您还未安装qq", Toast.LENGTH_SHORT).show();
                    return;
                }
                params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试应用222222");
                tencent.shareToQQ(this, params, new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Toast.makeText(SeckillDetailsActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Toast.makeText(SeckillDetailsActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(SeckillDetailsActivity.this, "取消分享", Toast.LENGTH_SHORT).show();
                    }
                });
            break;
            case R.id.l2:
                Toast.makeText(this, "点击了2", Toast.LENGTH_SHORT).show();
                regToWx();
                if (!api.isWXAppInstalled()) {
                    Toast.makeText(this, "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    String text = "12346";
//初始化一个 WXTextObject 对象，填写分享的文本内容
                    WXTextObject textObj = new WXTextObject();
                    textObj.text = text;

//用 WXTextObject 对象初始化一个 WXMediaMessage 对象
                    WXMediaMessage msg = new WXMediaMessage();
                    msg.mediaObject = textObj;
                    msg.description = text;

                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.message = msg;
//调用api接口，发送数据到微信
                    api.sendReq(req);

                }
                break;
            default:
                break;
        }
    }
    private void regToWx() {
        String appId = Common.APP_ID;
        api = WXAPIFactory.createWXAPI(this, appId, false);
        api.registerApp(appId);
    }
    public class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            videoReset();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void imgReset() {
        String javascript = "javascript:function ResizeImages() {" +
                "var maxwidth = document.body.clientWidth;" +
                "for(i=0;i <document.images.length;i++){" +
                " var myimg = document.images[i];" +
                "if(myimg.width > maxwidth){" +
                "myimg.width = maxwidth;" +
                "}" +
                "}" +
                "}";
        webView.loadUrl(javascript);
        webView.loadUrl("javascript:ResizeImages();");

    }

    private void videoReset() {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('video'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var video = objs[i];   " +
                "    video.style.width = '100%'; video.style.height = 'auto';  " +
                "}" +
                "})()");
    }
    @Override
    protected BasePresenter initPresenter() {
        return new SeckillPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:

                    custom = new CustomDialog(this,R.layout.dialog_custom,new int[]{R.id.l1,R.id.l2});
                    custom.setOnCenterItemClickListener((CustomDialog.OnCenterItemClickListener)this);
                    custom.show();

                break;
            case R.id.iv_more:
                if(ischecked){
                    LayoutInflater inflater= LayoutInflater.from( this );
                    View myview=inflater.inflate(R.layout.popwindow,null);//引用自定义布局
                    //后面是像素大小
                    popupWindow = new PopupWindow( myview,160,200 );
                    myview.findViewById(R.id.back_homepage).setOnClickListener( new View.OnClickListener() {//获取布局里面按钮
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();//点击按钮对话框消失
                        }
                    } );
                    myview.findViewById(R.id.shopcar).setOnClickListener( new View.OnClickListener() {//获取布局里面按钮
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();//点击按钮对话框消失
                        }
                    } );
                    myview.findViewById(R.id.share).setOnClickListener( new View.OnClickListener() {//获取布局里面按钮
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();//点击按钮对话框消失
                        }
                    } );
                    popupWindow.showAsDropDown(ivMore);
                    ischecked = false;
                }else{
                    popupWindow.dismiss();
                    ischecked=true;
                }
                break;
            case R.id.youhuidetails:

                break;
            case R.id.yixuandetails:

                break;
            case R.id.songzhidetails:
                startActivityForResult(new Intent(APP, LocationAddressActivity.class), 100);
                break;
            case R.id.jiangjia:

                break;
            case R.id.guanzhu:

                break;
            case R.id.tv_seemore:
                Intent intent =new Intent(APP, GoodsEvaluateActivity.class);
                intent.putExtra("skuid" , id);
                startActivity(intent);
                break;
            case R.id.bt_goindianpu:
                startActivity(new Intent(APP, ShopHomeActivity.class));
                break;
            case R.id.kefu:
                Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dianpu:
                startActivity(new Intent(APP, ShopHomeActivity.class));
                break;
            case R.id.shopcar:

                break;
            case R.id.tv_AddShoppingCart:
                dialog.show(getSupportFragmentManager(), "1");
                break;
            case R.id.tv_Buy:

                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (requestCode == 100) {
                if (data == null) {
                    return;
                }
                String name = data.getStringExtra("name");
                tvLocation.setText(name);
            }
        }
    }

    @Override
    public void onGetSuccess(SpikeBean spikeBean) {

    }

    @Override
    public void onGetError(String msg) {

    }

    @Override
    public void onGetGoodsDetailsSuccess(GoodsBean goodsBean) {
        GoodsBean.DataBean data = goodsBean.getData();
        if(data!=null){
            Log.d("hmy",data.getBrandId());

            tvDianpuname.setText(data.getBusinessName());
            Glide.with(this).load(data.getBusinessImage()).apply(RequestOptions.circleCropTransform()).into(ivDianputouxiang);
            dialog = new GoodsSelDialog(data);
            id = data.getMasterGoodsStaticPreviewSkuVO().getSkuId();


            webView.loadData(Common.translationHtml(goodsBean.getData().getSpuDetailRichText()), "text/html; charset=UTF-8", null);
        }
    }

    @Override
    public void onGetGoodsDetailsError(String msg) {

    }
}
