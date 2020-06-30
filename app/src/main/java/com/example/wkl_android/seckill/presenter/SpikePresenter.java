package com.example.wkl_android.seckill.presenter;

import com.example.wkl_android.base.all.BasePresenter;
import com.example.wkl_android.base.all.BaseView;
import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.seckill.contract.SpikeContract;
import com.example.wkl_android.seckill.model.SpikeModel;
import com.example.wkl_android.utils.netutils.NetUtils;

public class SpikePresenter extends BasePresenter implements SpikeContract.iPresenter {
    private SpikeModel mModel;
    public SpikePresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        mModel = new SpikeModel();
    }

    @Override
    public void doGetSpike() {
        mModel.doGetSpike(new SpikeContract.IModel.doGetSpikeCallBack() {
            @Override
            public void onGetSuccess(SpikeBean spikeBean) {
                BaseView view = getView();
                if(view instanceof SpikeContract.iView){
                    ((SpikeContract.iView) view).onGetSuccess(spikeBean);
                }
            }

            @Override
            public void onGetError(String msg) {
                BaseView view = getView();
                if(view instanceof SpikeContract.iView){
                    ((SpikeContract.iView) view).onGetError(msg);
                }
            }
        });
    }
}
