package server;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SunXianping on 2016/7/29 0029.
 */
public class TestMobile {
    private static OkHttpClient client1 = new OkHttpClient().newBuilder()
        .cookieJar(new CookieJar() {
        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            cookieStore.put(url, cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    }).build();
    private static Gson gson = new Gson();
    private static final String ENCRYPT_URL = "http://localhost:5000/";

    private String cookie ;

    private String getCid() throws IOException {
        Request request = new Request.Builder().url(ENCRYPT_URL+"?type=cid").build();
        Response response = client1.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return null;
        }
    }

    private String getPwd(String pwd) throws IOException {
        Request request = new Request.Builder().url(ENCRYPT_URL+"?type=pwd&param="+pwd).build();
        Response response = client1.newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            System.out.println(result);
            return result;
        } else {
            return null;
        }
    }

    public void login(String telNum,String pwd) throws IOException {
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
        System.out.println("json = "+json);

        RequestBody requestBody = RequestBody.create(MediaType.parse("JSON"), json);


        Request request = new Request.Builder().url("https://clientaccess.10086.cn/biz-orange/LN/uamlogin/login")
                //todo response解压
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Content-Type", "application/Json")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Content-Encoding", "UTF-8")
                .post(requestBody)
                .build();

        System.out.println("request = "+request.toString());

        Response response = client1.newCall(request).execute();
        String contentLength = response.header("Content-Length");

        ResponseBody body = response.peekBody(Long.parseLong(contentLength));
        System.out.println("new body = " + body.string());
//        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//        byte[] b = response.body().bytes(); //获取数据的bytes
//        String info = new String(b, "utf-8");
//        System.out.println("Response  response:          " + info);
//        Map map = gson.fromJson(response.body().charStream(),new TypeToken<HashMap>(){}.getType());
        System.out.println("response = " + response.body().string());
//        System.out.println(response.body().string());

    }


    /**
     * 发验证码
     * @param telNum
     * @throws IOException
     */
    public void sendMsgLogin(String telNum) throws IOException {

//        {
//            "cid": "lzte60fTxZoayKbS49UjwCiDlrpz3cJKdviiNxyUqL93NdTkd9U657aRgn/AXgUbcRNoRiLQvKVB/oQWmJKpEwCQBBIJ6UQoar5F7SAeXJ8FmWYFf7jpqMcbcxMWG5l1",
//                "cv": "2.3.0",
//                "en": "0",
//                "reqBody": {
//                  "cellNum": "13821033039"
//                  },
//            "sn": "EVA-TL00",
//                "sp": "1080x1812",
//                "st": "1",
//                "sv": "6.0",
//                "t": "4464a45168685d2a5a4ebc8ecaa918c7"
//        }
        //cid
        String cid = getCid();


        Map<String, Object> rootMap = new HashMap<>();
        rootMap.put("cid",cid);
        rootMap.put("cv","2.3.0");
        rootMap.put("en","0");
        rootMap.put("sn","EVA-TL00");
        rootMap.put("sp", "1080x1812");
        rootMap.put("st","1");
        rootMap.put("sv", "6.0");
        rootMap.put("t","4464a45168685d2a5a4ebc8ecaa918c7");

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("cellNum", telNum);
        rootMap.put("reqBody", reqMap);

        RequestBody requestBody = RequestBody.create(MediaType.parse("JSON"), gson.toJson(rootMap));


        Request request = new Request.Builder().url("https://clientaccess.10086.cn/biz-orange/LN/uamrandcode/sendMsgLogin")
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Content-Type", "application/Json")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Content-Encoding", "UTF-8")
                .post(requestBody)
                .build();

        Response response = client1.newCall(request).execute();
        System.out.println(response.body().string());

    }


    public void getTmpIdentCode(String telNum,String pwd,String smspwd) throws IOException {
//        {
//            "cid": "lzte60fTxZoayKbS49UjwCiDlrpz3cJKdviiNxyUqL93NdTkd9U657aRgn/AXgUbcRNoRiLQvKVB/oQWmJKpEwCQBBIJ6UQoar5F7SAeXJ8FmWYFf7jpqMcbcxMWG5l1",
//                "cv": "2.3.0",
//                "en": "0",
//                "reqBody": {
//            "businessCode": "01",
//                    "cellNum": "13821033039",
//                    "passwd": "hjGsw9r6/7bmEpYBiHLrsS60vv7MP4/+YyPcjRHmmHuvB/Lc58awkNIg9qVPzuyc/saSMDflPPMf1Qh7cuipNzm2UbEQwyrDybfkcsl0REP+ksweiwAKvkklFh7n+7+9HvN7kHXNFiEqES9c7xw4sYKaCN6VBaRZpIqZudPUHWQ=",
//                    "smsPasswd": "238710"
//        },
//            "sn": "EVA-TL00",
//                "sp": "1080x1812",
//                "st": "1",
//                "sv": "6.0",
//                "t": "f82a76bc484c60254358b5df12091c2c"
//        }


        String cid = getCid();
        String ccPasswd = getPwd(pwd);

        Map<String, Object> rootMap = new HashMap<>();
        rootMap.put("cid",cid);
        rootMap.put("cv","2.3.0");
        rootMap.put("en","0");
        rootMap.put("sn","EVA-TL00");
        rootMap.put("sp", "1080x1812");
        rootMap.put("st","1");
        rootMap.put("sv", "6.0");
        rootMap.put("t","4464a45168685d2a5a4ebc8ecaa918c7");

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("cellNum", telNum);
        reqMap.put("passwd", ccPasswd);
        reqMap.put("smsPasswd", smspwd);

        rootMap.put("reqBody", reqMap);

        RequestBody requestBody = RequestBody.create(MediaType.parse("JSON"), gson.toJson(rootMap));


        Request request = new Request.Builder().url("https://clientaccess.10086.cn/biz-orange/LN/tempIdentCode/getTmpIdentCode")
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Content-Type", "application/Json")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Content-Encoding", "UTF-8")
                .post(requestBody)
                .build();

        Response response = client1.newCall(request).execute();
        System.out.println(response.body().string());

    }

    public static void main(String[] args) {
        TestMobile test = new TestMobile();
        try {
            test.login("13821033039","102426");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.sendMsgLogin("13821033039");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
