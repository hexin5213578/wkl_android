package com.example.wkl_android.forget;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public interface IForgetView extends IBaseView {
  void showBtnTextAndEnabled(String btnText, boolean b);

  void handleRegisterSuccess();
  }
