package server;

import okhttp3.CacheControl;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;

/**
 * Created by SunXianping on 2016/7/29 0029.
 */
public class TestOkHttp {
    static OkHttpClient client = new OkHttpClient();


    public void test() throws IOException {
        CacheControl cacheControl = new CacheControl.Builder()
//                .noCache()
//                .noTransform()
                .build();

        Request request = new Request.Builder()
//                .url("http://localhost:8080/webServer/text")
//                .url("http://218.244.137.203:8082/dict_data?code=customer_type")
                .url("http://localhost:8080/webServer/a.do")
//                .url("https://publicobject.com/helloworld.txt")
//                .url("http://www.sina.com.cn/")
                .cacheControl(cacheControl)
                .addHeader("Cache-Control", "max-age=0")
                .build();


        Response response1 = client.newCall(request).execute();
        if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);

        String response1Body = response1.body().string();
        System.out.println("Response 1 response:          " + response1);
        System.out.println("Response 1 cache response:    " + response1.cacheResponse());
        System.out.println("Response 1 network response:  " + response1.networkResponse());

//        request = request.newBuilder().addHeader("If-None-Match", response1.header("ETag")).build();

//        CacheStrategy cacheStrategy = new CacheStrategy.Factory(System.currentTimeMillis(), request, response1).get();
//        System.out.println("Response 1 c:          " + cacheStrategy.cacheResponse);

        Response response2 = client.newCall(request).execute();
        if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);

        String response2Body = response2.body().string();
        System.out.println("Response 2 response:          " + response2);
        System.out.println("Response 2 cache response:    " + response2.cacheResponse());
        System.out.println("Response 2 network response:  " + response2.networkResponse());

//        CacheStrategy cacheStrategy2 = new CacheStrategy.Factory(System.currentTimeMillis(),request,response2).get();
//        System.out.println("Response 2 c:          " + cacheStrategy2.cacheResponse);

        System.out.println("Response 2 equals Response 1? " + response1Body.equals(response2Body));
    }


    public static void main(String[] args) {

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        File file = new File("d://testCache");
        Cache cache = new Cache(file, cacheSize);

        client = new OkHttpClient().newBuilder()
                .cache(cache)
                .build();


        TestOkHttp test = new TestOkHttp();
        try {
            test.test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
