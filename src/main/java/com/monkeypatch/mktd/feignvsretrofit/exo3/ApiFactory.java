package com.monkeypatch.mktd.feignvsretrofit.exo3;

import com.monkeypatch.mktd.feignvsretrofit.exo1.MonkeyApi;
import com.monkeypatch.mktd.feignvsretrofit.exo1.MonkeyImpl;
import com.monkeypatch.mktd.feignvsretrofit.exo1.MonkeyRetrofitApi;
import com.monkeypatch.mktd.feignvsretrofit.exo1.model.Monkey;
import com.squareup.moshi.Moshi;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

class ApiFactory {

    static MonkeyApi buildMonkeyApi(String url) {
        return new MonkeyImpl(buildApi(MonkeyRetrofitApi.class, url));
    }

    private static <T> T buildApi(Class<T> clazz, String url) {

//        Moshi moshi = new Moshi.Builder()
//                .add(new MonkeyAdapter()).build();
//        return buildApi(clazz, url, MoshiConverterFactory.create(moshi));
        return buildApi(clazz, url, MoshiConverterFactory.create());
    }

    private static <T> T buildApi(Class<T> clazz, String url, Converter.Factory converter) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(converter)
                .build()
                .create(clazz);

    }


    public static void main(String[] args) {


        buildMonkeyApi("http://defi3.mktd.monkeypatch.io/").getMonkeys(1);
    }
}
