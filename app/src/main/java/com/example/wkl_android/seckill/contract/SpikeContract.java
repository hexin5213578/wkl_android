package com.example.wkl_android.seckill.contract;

import com.example.wkl_android.base.all.BaseView;
import com.example.wkl_android.seckill.bean.SpikeBean;

public class SpikeContract {
    public interface  iView extends BaseView{
        public void onGetSuccess(SpikeBean spikeBean);
        public  void onGetError(String msg);
    }
    public interface  iPresenter{
        public void doGetSpike();
    }
    public interface IModel{
        public void doGetSpike(doGetSpikeCallBack callBack);
        public interface doGetSpikeCallBack{
            void onGetSuccess(SpikeBean spikeBean);
            void onGetError(String msg);
        }
    }
}
