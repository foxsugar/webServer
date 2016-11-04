package server;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SunXianping on 2016/7/29 0029.
 */
public class TestOkHttp {
    static OkHttpClient client = new OkHttpClient();
    static OkHttpClient client1 = new OkHttpClient();
    static Gson gson = new Gson();


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



    private String getCid() throws IOException {
        Request request = new Request.Builder().url("http://localhost:5000/?type=cid").build();
        Response response = client1.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return null;
        }
    }

    private String getPwd(String pwd) throws IOException {
        Request request = new Request.Builder().url("http://localhost:5000/?type=pwd&param="+pwd).build();
        Response response = client1.newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            System.out.println(result);

            return result;
        } else {
            return null;
        }
    }

    private void login(String telNum,String pwd) throws IOException {
        //cid
        String cid = getCid();
        //pwd
        String ccPasswd = getPwd(pwd);
        Map<String, Object> rootMap = new HashMap<>();
        rootMap.put("cid",cid);
        rootMap.put("cv","2.3.0");
        rootMap.put("en","3");
        rootMap.put("sn","EVA-TL00");
        rootMap.put("sp","1080x1812");
        rootMap.put("st","1");
        rootMap.put("sv","6.0");
        rootMap.put("t","");

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("cellNum",telNum);
        reqMap.put("sendSmsFlag","1");
        reqMap.put("ccPasswd",ccPasswd);

        rootMap.put("reqBody", reqMap);


        //to json
        String json = gson.toJson(rootMap);

        RequestBody requestBody = RequestBody.create(MediaType.parse("JSON"), json);


        Request request = new Request.Builder().url("https://clientaccess.10086.cn/biz-orange/LN/uamlogin/login")
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Content-Type", "application/Json")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Content-Encoding", "UTF-8")
                .post(requestBody)
                .build();

        System.out.println(request.toString());

        Response response = client1.newCall(request).execute();
//        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//        byte[] b = response.body().bytes(); //获取数据的bytes
//        String info = new String(b, "utf-8");
//        System.out.println("Response  response:          " + info);
//        Map map = gson.fromJson(response.body().charStream(),new TypeToken<HashMap>(){}.getType());
        System.out.println(response.body().string());
//        System.out.println(response.body().string());

    }

    public static void main(String[] args) {

        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        File file = new File("d://testCache");
        Cache cache = new Cache(file, cacheSize);

        client = new OkHttpClient().newBuilder()
                .cache(cache)
                .build();


        client1 = new OkHttpClient().newBuilder()
                .build();


        TestOkHttp test = new TestOkHttp();
        try {
            test.login("13821033039","102426");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
