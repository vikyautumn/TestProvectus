package viktoriia.testprovectus;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import viktoriia.testprovectus.data.PostModel;

public interface Example {

    @GET("api/")
    Call<PostModel> getData(@Query("results") int count);
}