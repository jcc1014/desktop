package app;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtils {
	
	public static String pushMessage(List<NameValuePair> formparams,String postUrl) {  
		String resData = null;  
	    //创建HttpClientBuilder    
	    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
	    CloseableHttpClient httpclient = httpClientBuilder.build();  
	    HttpPost httppost = new HttpPost(postUrl.toString());  
	    UrlEncodedFormEntity uefEntity;  
	    try {  
	        uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
	        httppost.setEntity(uefEntity);  
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间  
	        httppost.setConfig(requestConfig);  
	        CloseableHttpResponse response = httpclient.execute(httppost);  
	        try {  
	        HttpEntity entity = response.getEntity();  
	        if (entity != null) {  
	            resData = EntityUtils.toString(entity, "UTF-8");  
	        }  
	        } finally {  
	        response.close();  
	        }  
	    } catch (ClientProtocolException e) {  
	        e.printStackTrace();  
	    } catch (UnsupportedEncodingException e1) {  
	        e1.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        // 关闭连接,释放资源      
	        try {  
	        httpclient.close();  
	        } catch (IOException e) {  
	        e.printStackTrace();  
	        }  
	    }  
	    return resData;  
	}  
	
	public static void main(String[] args) {
		String username = "lsx";
		String password = "000000"; 
		String url = "http://localhost:8080/trace_system/baseInfo/syncLogin.do";
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	    formparams.add(new BasicNameValuePair("username", username));  
	    formparams.add(new BasicNameValuePair("password", password));
		String rs = pushMessage(formparams, url);
		JSONObject js = JSONObject.parseObject(rs);
		System.out.println(js.get("code"));
	}

}
