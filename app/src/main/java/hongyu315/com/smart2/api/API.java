package hongyu315.com.smart2.api;

import hongyu315.com.smart2.bean.ProductList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    //https://lvyou.baidu.com/pictravel/photo/view/4cbde9f540b8f5398e39fb49
    @GET("meituApi?")
    Call<ProductList> getProducts(@Query("page")String page);
}
