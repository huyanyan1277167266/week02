package com.bw.huyanyan.presenter;

import com.bw.huyanyan.activity.MainActivity;
import com.bw.huyanyan.contract.IHomeContract;
import com.bw.huyanyan.model.IHomePageModel;

/*
 *@Auther:cln
 *@Date: 2020/3/2
 *@Time:14:03
 *@Description:
 * */public class IHomePagePreshenter implements IHomeContract.IPresenter {
     IHomePageModel mModel;
     IHomeContract.IView iView;

    public IHomePagePreshenter(IHomePageModel Model, IHomeContract.IView iView) {
        mModel = Model;
        this.iView = iView;
    }



    @Override
    public void getList(String url) {
        mModel.getList(url, new IHomeContract.IModel.ListICallBack() {
            @Override
            public void onSuccess(String str) {
                    iView.onSuccess(str);
            }

            @Override
            public void onFrailure(String str) {
                iView.onFrailure(str);
            }
        });
    }
}
