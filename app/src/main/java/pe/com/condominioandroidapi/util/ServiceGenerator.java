package pe.com.condominioandroidapi.util;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS);

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constant.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit.Builder builderLogin = new Retrofit.Builder()
            .baseUrl(Constant.API_BASE_URL_LOGIN)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass, final String apiAccessToken) {
        AccessToken accessToken = new AccessToken(apiAccessToken);
        return createService(serviceClass, accessToken);
    }
    public static <S> S createServiceLogin(Class<S> serviceClass, final String apiAccessToken) {
        AccessToken accessToken = new AccessToken(apiAccessToken);
        return createServiceLogin(serviceClass, accessToken);
    }

    public static <S> S createService(Class<S> serviceClass, final AccessToken accessToken) {
        if (accessToken != null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient
                    .addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();

                            Request.Builder requestBuilder = original.newBuilder()
                                    ;

                            Request request = requestBuilder.build()
                                    ;
                            return chain.proceed(request);
                        }
                    });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
    public static <S> S createServiceLogin(Class<S> serviceClass, final AccessToken accessToken) {
        if (accessToken != null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient
                    .addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();

                            Request.Builder requestBuilder = original.newBuilder()
                                    ;

                            Request request = requestBuilder.build()
                                    ;
                            return chain.proceed(request);
                        }
                    });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builderLogin.client(client).build();
        return retrofit.create(serviceClass);
    }

    private static Gson buildGson()
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        return gson;
    }
}
