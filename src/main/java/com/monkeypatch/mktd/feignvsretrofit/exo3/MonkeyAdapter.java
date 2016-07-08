package com.monkeypatch.mktd.feignvsretrofit.exo3;

import com.monkeypatch.mktd.feignvsretrofit.exo1.model.Monkey;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonkeyAdapter {
    
   // static String reg = "{\"id\":\"(.*)\",\"name\":\"(.*)\",\"age\":(\\d*),\"gender\":\"(MALE|FEMALE)\",\"raceId\":\"(.*)\"}";
   // static Pattern pattern = Pattern.compile(reg);
    
    @ToJson
    String toJson(Monkey monkey) {
        return "";
    }

    @FromJson
    Monkey fromJson(String json) {
        
       // Matcher matcher = pattern.matcher(json);
        return null;
    }
}