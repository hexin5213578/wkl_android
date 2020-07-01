package com.example.wkl_android.seckill.model;

import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.seckill.contract.SeckillContract;
import com.example.wkl_android.utils.netutils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: wkl_android
 * @Package: com.example.wkl_android.seckill.model
 * @ClassName: SeckillModel
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/7/1 13:56
 */
public class SeckillModel implements SeckillContract.IModel {
    @Override
    public void doGetSeckill(doGetSeeckillCallBack callBack) {
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
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
