package com.djs.one.api;

import com.djs.one.bean.AddToShoppingCarBean;
import com.djs.one.bean.Address;
import com.djs.one.bean.CreateOrderBean;
import com.djs.one.bean.ImageVerifyCode;
import com.djs.one.bean.LoginToken;
import com.djs.one.bean.MyOrdersBean;
import com.djs.one.bean.OSSBean;
import com.djs.one.bean.OrderDetailBean;
import com.djs.one.bean.PayCallBackBean;
import com.djs.one.bean.PhoneCheckCode;
import com.djs.one.bean.ProductBean;
import com.djs.one.bean.ProductDetailBannerBean;
import com.djs.one.bean.ProductDetailBean;
import com.djs.one.bean.ProductList;
import com.djs.one.bean.ShoppingCarItems;
import com.djs.one.bean.SuccessfulMode;
import com.djs.one.bean.SuccessfulModeBean;
import com.djs.one.bean.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    //https://lvyou.baidu.com/pictravel/photo/view/4cbde9f540b8f5398e39fb49
    @GET("meituApi?")
    Call<ProductList> getProducts(@Query("page")String page);

    //获取OSS授权
    @GET("api/kit/ossauth?")
    Call<OSSBean> ossauth(@Header("Authorization") String authorization);

    //获取图形验证码
    @GET("captcha/api?")
    Call<ImageVerifyCode> getImageVerifyCode();

    //发送短信验证码api/kit/send_cell_checkcode
    @POST("api/kit/send_cell_checkcode?")
    Call<PhoneCheckCode> sendPhoneCheckCode(@Query("mobile")String mobile,
                                            @Query("imgCode")String imgCode,
                                            @Query("imgCodeKey")String imgCodeKey);

    //登录
    @POST("api/auth/login?")
    Call<LoginToken> login(@Query("mobile")String mobile,
                           @Query("checkcode")String checkcode,
                           @Query("agreement")String agreement,
                           @Query("deviceType")String deviceType,
                           @Query("deviceToken")String deviceToken);

    //获取登录用户信息
    @GET("api/user/profile?")
    Call<UserProfile> getUser(@Header("Authorization") String authorization);

    //登录
    @POST("api/auth/updateProfile?")
    Call<SuccessfulMode> updateProfile(@Header("Authorization") String authorization,
                                       @Query("nickname")String nickname,
                                       @Query("avatar")String avatar,
                                       @Query("gender")String gender,
                                       @Query("birthday")String birthday);

    //注销
    @POST("api/auth/logout?")
    Call<SuccessfulMode> logout(@Header("Authorization") String authorization);

    //地址管理
    @GET("api/user/addresses?")
    Call<Address> getMyAddresses(@Header("Authorization") String authorization,
                                 @Query("pageSize") String pageSize);

    //新增地址
    @POST("api/user/createAddress?")
    Call<SuccessfulMode> createAddress(@Header("Authorization") String authorization,
                                       @Query("name")String name,
                                       @Query("mobile") String mobile,
                                       @Query("area") String area,
                                       @Query("address") String address,
                                       @Query("default") String isDefaultAddress);

    //删除地址
    @POST("api/user/deleteAddress?")
    Call<SuccessfulMode> deleteAddress(@Header("Authorization") String authorization,
                                       @Query("id")String id);

    //更新地址
    @POST("api/user/updateAddress?")
    Call<SuccessfulMode> updateAddress(@Header("Authorization") String authorization,
                                       @Query("id") String id,
                                       @Query("name")String name,
                                       @Query("mobile") String mobile,
                                       @Query("area") String area,
                                       @Query("address") String address,
                                       @Query("default") String isDefaultAddress);

    //获取商品列表
    @GET("api/items?")
    Call<ProductBean> getProductList(@Query("pageSize") String pageSize,
                                     @Query("page") String page,
                                     @Query("saleTimeSortType") String saleTimeSortType,//按上架时间排序，1 倒序 2 升序，默认 1
                                     @Query("priceSortType") String priceSortType);//按商品价格排序，1 倒序 2 升序，默认 2

    //商品详情页 Banner
    @GET("api/item/banners?")
    Call<ProductDetailBannerBean> getProductDetailBanner(@Query("itemId") String itemId);

    //商品详情页
    @GET("api/item/detail?")
    Call<ProductDetailBean> getProductDetail(@Query("itemId") String itemId);

    //商品规格详情
//    @GET("api/item/sku?")
//    Call<ProductDetailBean.DataBean.SkusBean> getProductSKU(@Query("itemId") String itemId,
//                                                            @Query("skuId") String skuId);


    //商品收藏与取消收藏
    @POST("api/item/collect?")
    Call<SuccessfulModeBean> collect(@Header("Authorization") String authorization,
                                     @Query("itemId")String itemId);

    //我的收藏
    @GET("api/user/collects?")
    Call<ProductBean> getMyCollects(@Header("Authorization") String authorization,
                                 @Query("pageSize") String pageSize,
                                @Query("page") String page);




    // ========== 购物车 ===========

    //将商品添加到购物车
    @POST("api/cart/addItem?")
    Call<AddToShoppingCarBean> addItem(@Header("Authorization") String authorization,
                                       @Query("itemId")String itemId,
                                       @Query("skuId") String skuId,
                                       @Query("quantity") String quantity);

    //购物车商品列表
    @GET("api/cart/items?")
    Call<ShoppingCarItems> shoppingCarItems(@Header("Authorization") String authorization,
                                            @Query("pageSize") String pageSize,
                                            @Query("page") String page);

    //添加或减少购物车中的商品购买数量
    @POST("api/cart/quantity?")
    Call<SuccessfulMode> changeQuantity(@Header("Authorization") String authorization,
                                       @Query("cartId")String cartId,
                                       @Query("type") String type);

    //将商品移除购物车
    @POST("api/cart/remove?")
    Call<SuccessfulMode> removeShoppingCar(@Header("Authorization") String authorization,
                                            @Query("cartId")String cartId);

    // ========== 订单 ===========

    //创建订单
    @POST("api/trade/order?")
    Call<CreateOrderBean> createOrder(@Header("Authorization") String authorization,
                                      @Query("skus")String skus);

    //获取订单详情
    @GET("api/trade/info?")
    Call<OrderDetailBean> orderInfo(@Header("Authorization") String authorization,
                                    @Query("trade_no")String trade_no);

    //订单支付接口 收获地址ID 支付方式：1 支付宝 2 微信
    @POST("api/trade/pay?")
    Call<PayCallBackBean> pay(@Header("Authorization") String authorization,
                              @Query("addressId")String addressId,
                              @Query("trade_no")String trade_no,
                              @Query("payType")String payType);

    //我的订单列表 订单状态type： 1 待支付, 2 已支付/待发货, 3 已发货/待收货, 4 已完成， 默认4
    @GET("api/my/orders?")
    Call<MyOrdersBean> myOrders(@Header("Authorization") String authorization,
                                 @Query("type")String type,
                                @Query("pageSize")String pageSize,
                                @Query("page")String page);
}
