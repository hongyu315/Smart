package hongyu315.com.smart2.api;

import hongyu315.com.smart2.bean.Address;
import hongyu315.com.smart2.bean.ImageVerifyCode;
import hongyu315.com.smart2.bean.LoginToken;
import hongyu315.com.smart2.bean.PhoneCheckCode;
import hongyu315.com.smart2.bean.ProductBean;
import hongyu315.com.smart2.bean.ProductDetailBannerBean;
import hongyu315.com.smart2.bean.ProductDetailBean;
import hongyu315.com.smart2.bean.ProductList;
import hongyu315.com.smart2.bean.SuccessfulMode;
import hongyu315.com.smart2.bean.SuccessfulModeBean;
import hongyu315.com.smart2.bean.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    //https://lvyou.baidu.com/pictravel/photo/view/4cbde9f540b8f5398e39fb49
    @GET("meituApi?")
    Call<ProductList> getProducts(@Query("page")String page);

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
                           @Query("agreement")String agreement);

    //获取登录用户信息
    @GET("api/user/profile?")
    Call<UserProfile> getUser(@Header("Authorization") String authorization);

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
}
