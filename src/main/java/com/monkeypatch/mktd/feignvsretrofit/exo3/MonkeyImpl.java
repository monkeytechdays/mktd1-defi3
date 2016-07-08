package com.monkeypatch.mktd.feignvsretrofit.exo3;

import com.monkeypatch.mktd.feignvsretrofit.exo1.MonkeyApi;
import com.monkeypatch.mktd.feignvsretrofit.exo1.MonkeyRetrofitApi;
import com.monkeypatch.mktd.feignvsretrofit.exo1.model.Monkey;
import com.monkeypatch.mktd.feignvsretrofit.exo1.model.Page;

import java.io.IOException;


public class MonkeyImpl implements MonkeyApi {
    private MonkeyRetrofitApi api;

    public MonkeyImpl(MonkeyRetrofitApi api) {
        this.api = api;
    }

    @Override
    public Page<Monkey> getMonkeys(int page) {
        try {
            return api.getMonkeys(page).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Monkey getMonkeyByName(String name) {
        try {
            return api.getMonkeyByName(name).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Monkey createMonkey(Monkey monkey) {
        try {
            return api.createMonkey(monkey).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMonkey(String id) {

        try {
            api.deleteMonkey(id).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
