package com.example.wkl_android.seckill.model;

import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.seckill.contract.SpikeContract;
import com.example.wkl_android.utils.netutils.NetUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SpikeModel implements SpikeContract.IModel {
    @Override
    public void doGetSpike(doGetSpikeCallBack callBack) {
        NetUtils.getInstance().getApis().findListByCode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpikeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpikeBean spikeBean) {
                        if (callBack != null) {
                            callBack.onGetSuccess(spikeBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack != null) {
                            callBack.onGetError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
