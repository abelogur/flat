package com.artur.belogur.flatclient;

import com.artur.belogur.Flat;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.List;

@Slf4j
public class FlatClient {

    private static final MediaType MEDIA_TYPE = MediaType.get("application/x-www-form-urlencoded; charset=UTF-8");

    private final OkHttpClient client = new OkHttpClient();
    private final Request request;

    public FlatClient() {
        RequestBody requestBody = new FormBody.Builder()
                .add("sql1", "")
                .add("sql2", "2|")
                .add("sql3", "0")
                .add("sql4", "150")
                .add("sql5", "0")
                .add("sql6", "16000000")
                .add("sor1", "0")
                .add("sor2", "0")
                .build();

        this.request = new Request.Builder()
                .url("http://yb.doseanstroj.ru/system/module-sample_list.php")
                .post(requestBody)
                .build();
    }

    @SneakyThrows
    public List<Flat> getFlats() {
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        return new FlatParser(response.body().string()).parseFlats();
    }
}
