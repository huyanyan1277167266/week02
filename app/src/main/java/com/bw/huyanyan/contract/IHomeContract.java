package com.bw.huyanyan.contract;

import com.bw.huyanyan.base.IBaseView;

/*
 *@Auther:cln
 *@Date: 2020/3/2
 *@Time:13:59
 *@Description:
 * */public interface IHomeContract {


    public interface IView{
         void onSuccess(String str);
         void onFrailure(String str);
     }
     public interface IPresenter{
         void getList(String url);
     }
     public interface IModel{
         void getList(String url,ListICallBack iCallBack);
         public interface ListICallBack{
             void onSuccess(String str);
             void onFrailure(String str);

         }     }
}
