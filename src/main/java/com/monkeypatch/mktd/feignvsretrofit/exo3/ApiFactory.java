package com.monkeypatch.mktd.feignvsretrofit.exo3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.monkeypatch.mktd.feignvsretrofit.exo1.MonkeyApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;

class ApiFactory {

    static MonkeyApi buildMonkeyApi(String url) {


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new AfterburnerModule());

        return Feign
                .builder()
                .decoder(new JacksonDecoder(mapper))
                .client(new OkHttpClient())
                .target(MonkeyApi.class, url + "/monkeys");
    }

}
