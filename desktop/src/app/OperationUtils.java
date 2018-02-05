package app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class OperationUtils {

	public static JSONObject putOn(String id,String ip){
		String url = "http://"+ip+":8080/trace_system/distribution/syncPutOn.do";
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	    formparams.add(new BasicNameValuePair("id", id));  
		String rs = HttpUtils.pushMessage(formparams, url);
		JSONObject js = JSONObject.parseObject(rs);
		return js;
	}
	
	public static JSONObject putOff(String id,String ip){
		String url = "http://"+ip+":8080/trace_system/distribution/syncPutOff.do";
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
	    formparams.add(new BasicNameValuePair("id", id));  
		String rs = HttpUtils.pushMessage(formparams, url);
		JSONObject js = JSONObject.parseObject(rs);
		return js;
	}
	
	public static JSONArray getTableData(String ip,String baseid,String type,String name){
		String url = "http://"+ip+":8080/trace_system/distribution/syncGetData.do";
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
		formparams.add(new BasicNameValuePair("type", type));  
		formparams.add(new BasicNameValuePair("name", name));  
		formparams.add(new BasicNameValuePair("baseid", baseid));  
		String rs = HttpUtils.pushMessage(formparams, url);
		JSONArray js = JSONArray.parseArray(rs);
		return js;
	}
	
	public static void main(String[] args) {
		JSONArray ja = getTableData(LoginState.ip, "a917bda3d1284f6a9174b0a8c6f16833", "0", "");
		for (int i = 0; i < ja.size(); i++) {
			System.out.println(ja.get(i).toString());
		}
	}
	
	public static void addTableData(JTable table,String ip,String state,String type,String name){
		JSONArray ja = OperationUtils.getTableData(ip, LoginState.baseid, type, name);
		 if(null!=ja){
			 DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
			 for (int i = 0; i < ja.size(); i++) {
				 JSONObject js = JSONObject.parseObject(ja.get(i).toString());
				 tableModel.addRow(new Object[]{js.get("distribution_id"),js.get("hh") ,js.get("kind"),js.get("price"),
						 js.get("yps"),js.get("yps") ,js.get("createtime"),state});
			}
		 }
	}
	
}
