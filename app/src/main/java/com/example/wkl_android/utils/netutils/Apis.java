package com.example.wkl_android.utils.netutils;

import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.seckill.bean.GoodsCommentBean;
import com.example.wkl_android.seckill.bean.SpikeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface Apis {
    //用户根据code查询秒杀
    @GET("goods/kill/findListByCode")
    Observable<SpikeBean> findListByCode();

    //查询商品详情
    @GET()
    Observable<GoodsBean> findGoodDetails(@Url String url);

    //查询商品评价
    @GET()
    Observable<GoodsCommentBean> findGoodsComment(@Url String url, @Header("token")String token);
}