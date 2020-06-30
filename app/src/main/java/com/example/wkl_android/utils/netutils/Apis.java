package com.example.wkl_android.utils.netutils;

import com.example.wkl_android.seckill.bean.SpikeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Apis {
    //用户根据code查询秒杀
    @GET("goods/kill/findListByCode/3")
    Observable<SpikeBean> findListByCode();

}
