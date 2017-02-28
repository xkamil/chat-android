package pl.com.kamilwrobel.czater.Api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static final String BASE_URL = "https://chat-api2.herokuapp.com";

    private static Retrofit builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <T> T createService(Class<T> serviceClass) {
        return builder.create(serviceClass);
    }

    public static CzaterApi getChaterApi(){
        return createService(CzaterApi.class);
    }
}
