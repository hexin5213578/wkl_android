package com.example.wkl_android.seckill.contract;

import com.example.wkl_android.base.all.BaseView;
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
    }
    interface IPresenter{
        void doGetSeckill();
    }
    interface IModel{
        void doGetSeckill(doGetSeeckillCallBack callBack);
        interface doGetSeeckillCallBack{
            void onGetSuccess(SpikeBean spikeBean);
            void onGetError(String msg);
        }
    }
}
