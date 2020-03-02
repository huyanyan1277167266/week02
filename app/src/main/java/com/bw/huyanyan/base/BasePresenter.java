package com.bw.huyanyan.base;

import java.lang.ref.WeakReference;

/*
 *@Auther:cln
 *@Date: 2020/3/2
 *@Time:13:54
 *@Description:
 * */public abstract class BasePresenter<V extends IBaseView> {

    private final WeakReference<V> mWeakReference;

    private BasePresenter(V v) {
        mWeakReference = new WeakReference<>(v);
        mPresenter();
    }

    public WeakReference<V> getmWeakReference() {

        if (mWeakReference!=null){
            mWeakReference.get();

        }
        return mWeakReference;
    }

    public abstract V mPresenter();

}
