package com.bw.huyanyan.model;

import com.bw.huyanyan.contract.IHomeContract;
import com.bw.huyanyan.utlie.VolleyUtile;

/*
 *@Auther:cln
 *@Date: 2020/3/2
 *@Time:14:03
 *@Description:
 * */public class IHomePageModel implements IHomeContract.IModel {
    @Override
    public void getList(String url, final ListICallBack iCallBack) {
        VolleyUtile.getInstance().getjson(url, new VolleyUtile.ICallBack() {
            @Override
            public void onSuccess(String json) {
                iCallBack.onSuccess(json);
            }

            @Override
            public void onError(String msg) {
                iCallBack.onFrailure(msg);
            }
        });
    }
}
