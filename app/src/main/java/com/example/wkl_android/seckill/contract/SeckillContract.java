package com.example.wkl_android.seckill.contract;

import com.example.wkl_android.base.all.BaseView;
import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.seckill.bean.SpikeBean;

/**
 * @ProjectName: wkl_android
 * @Package: com.example.wkl_android.seckill.contract
 * @ClassName: SeckillContract
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/7/1 13:54
 */
public interface SeckillContract {
    interface IView extends BaseView{
        void onGetSuccess(SpikeBean spikeBean);
        void onGetError(String msg);

        void onGetGoodsDetailsSuccess(GoodsBean goodsBean);
        void onGetGoodsDetailsError(String msg);
    }
   interface IPresenter{
        void doGetSeckill();
        void doGetGoodsDetails(String url);
    }
    interface IModel{
        void doGetSeckill(doGetSeeckillCallBack callBack);
        interface doGetSeeckillCallBack{
            void onGetSuccess(SpikeBean spikeBean);
            void onGetError(String msg);
        }
        void doGetGoodsDetails(String url,doGetGoodsDetailsCallBack callBack);
        interface doGetGoodsDetailsCallBack{
            void onGetGoodsDetailsSuccess(GoodsBean goodsBean);
            void onGetGoodsDetailsError(String msg);
        }
    }
}
