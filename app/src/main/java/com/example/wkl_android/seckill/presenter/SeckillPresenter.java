package com.example.wkl_android.seckill.presenter;

import com.example.wkl_android.base.all.BasePresenter;
import com.example.wkl_android.base.all.BaseView;
import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.seckill.contract.SeckillContract;
import com.example.wkl_android.seckill.model.SeckillModel;

/**
 * @ProjectName: wkl_android
 * @Package: com.example.wkl_android.seckill.presenter
 * @ClassName: SeckillPresenter
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/7/1 13:57
 */
public class SeckillPresenter extends BasePresenter implements SeckillContract.IPresenter {
    private SeckillModel mModel;
    public SeckillPresenter(BaseView baseView) {
        super(baseView);
    }

    @Override
    protected void initModel() {
        mModel = new SeckillModel();
    }

    @Override
    public void doGetSeckill() {
        mModel.doGetSeckill(new SeckillContract.IModel.doGetSeeckillCallBack() {
            @Override
            public void onGetSuccess(SpikeBean spikeBean) {
                BaseView baseView = getView();
                if(baseView instanceof SeckillContract.IView){
                    ((SeckillContract.IView) baseView).onGetSuccess(spikeBean);
                }
            }

            @Override
            public void onGetError(String msg) {
                BaseView baseView = getView();
                if(baseView instanceof SeckillContract.IView){
                    ((SeckillContract.IView) baseView).onGetError(msg);
                }
            }
        });
    }

    @Override
    public void doGetGoodsDetails(String url) {
        mModel.doGetGoodsDetails(url, new SeckillContract.IModel.doGetGoodsDetailsCallBack() {
            @Override
            public void onGetGoodsDetailsSuccess(GoodsBean goodsBean) {
                BaseView baseView = getView();
                if(baseView instanceof SeckillContract.IView){
                    ((SeckillContract.IView) baseView).onGetGoodsDetailsSuccess(goodsBean);
                }
            }

            @Override
            public void onGetGoodsDetailsError(String msg) {
                BaseView baseView = getView();
                if(baseView instanceof SeckillContract.IView){
                    ((SeckillContract.IView) baseView).onGetGoodsDetailsError(msg);
                }
            }
        });
    }


}