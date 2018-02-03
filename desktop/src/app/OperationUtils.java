package app;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;

public class OperationUtils {

	public static JSONObject putOn(String id,String ip){
		String url = "http://"+ip+":8080/trace_system/baseInfo/syncPutOn.do";
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	    formparams.add(new BasicNameValuePair("id", id));  
		String rs = HttpUtils.pushMessage(formparams, url);
		JSONObject js = JSONObject.parseObject(rs);
		return js;
	}
	
	public static JSONObject putOff(String id,String ip){
		String url = "http://"+ip+":8080/trace_system/baseInfo/syncPutOff.do";
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	    formparams.add(new BasicNameValuePair("id", id));  
		String rs = HttpUtils.pushMessage(formparams, url);
		JSONObject js = JSONObject.parseObject(rs);
		return js;
	}
	
	
	
}
