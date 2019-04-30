package com.artur.belogur.flatclient;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

@Slf4j
public class FlatClient {

    private static final MediaType MEDIA_TYPE = MediaType.get("application/x-www-form-urlencoded; charset=UTF-8");

    private final OkHttpClient client = new OkHttpClient();
    private final Request request;

    @SneakyThrows
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

    public String getFlats() {
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
