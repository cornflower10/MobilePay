package com.qingmang.api;


import com.qingmang.moudle.entity.BaseEntity;
import com.qingmang.moudle.entity.LoginEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by xiejingbao on 2017/5/18.
 */

public interface ApiService {

    /**
     * 登录
     * @param userName
     * @param passWd
     * @return
     */
    @FormUrlEncoded
    @POST("regist.php")
    Observable<BaseEntity<LoginEntity>> login(@Field("phone") String userName,
                                              @Field("password") String passWd
                                              );

    /**
     * 消费
     * @param
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("regist.php")
    Observable<String> consume(@Field("merchantId") String merchantId,
                               @Field("payCode") String payCode,
                               @Field("transAmt") double transAmt);

}
