package com.bw.huyanyan.utlie;




import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/*
 *@Auther:cln
 *@Date: 2020/3/2
 *@Time:13:31
 *@Description:
 * */public class VolleyUtile {
//     //RequestQueue mQueue;
//     public VolleyUtile(){
//       //  mQueue= Volley.newRequestQueue(App.CONTEXT_RESTRICTED);
//     }
//     private static class SingleInsetence{
//         private static SingleInsetence INSETENCE=new VolleyUtile.SingleInsetence();
//     }
//
//    public static SingleInsetence getInstance() {
//        return SingleInsetence.INSETENCE;
//    }
//    //判断网络
//    public boolean iswork(Context context){
//         ConnectivityManager cm= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = cm.getActiveNetworkInfo();
//        if (info!=null){
//            return true;
//        }
//        return false;
//    }
//    //创建接口
//    public interface ICallBack{
//         void onSuccess(String json);
//         void onError(String msg);
//    }
//    public void doGet(String url, final ICallBack iCallBack){
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                iCallBack.onSuccess(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                iCallBack.onError(error.getMessage());
//            }
//        }){
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                String a;
////                StringRequest stringRequest1 = new StringRequest("UTF-8");
//                return super.parseNetworkResponse(response);
//            }
//        };
//        mQueue.add(stringRequest);
//    }
    private static VolleyUtile volleyUtile=new VolleyUtile();

    private VolleyUtile() {
    }

    public static VolleyUtile getInstance() {
        return volleyUtile;
    }
    public boolean iswork(Context context){
       ConnectivityManager cm= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }
    public Handler handler=new Handler();
    public interface ICallBack{
        void onSuccess(String json);
        void onError(String msg);
    }
    public void getjson(final String json, final ICallBack iCallBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(json);
                  HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                  conn.setRequestMethod("GET");
                  conn.setConnectTimeout(5000);
                  conn.setReadTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = conn.getInputStream();
                        int len=0;
                        StringBuilder sb = new StringBuilder();
                        byte[] bytes = new byte[1024];
                        while ((len=inputStream.read(bytes))!=-1){
                            String s = new String(bytes, 0, len);
                            sb.append(s);
                        }
                        final String json = sb.toString();
                        inputStream.close();
                        Log.i("xxx",json);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iCallBack.onSuccess(json);
                            }
                        });
                    }else{
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                iCallBack
                                        .onError("获取失败");
                            }
                        });
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iCallBack.onError(e.toString());
                        }
                    });
                }
            }
        }).start();
    }
}
