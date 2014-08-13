package test;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@SuppressWarnings("unchecked")
public class JsonTransfor {

	public JsonTransfor() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 特别为Ext.Ajax.request所需的json准备
	 * Ext.Ajax.request方式所需的json 可以存在\" 及值外围的""
	 * */
	public static String getAjaxJson(Map<?,?> map){
		String jsonStr = null;
		try{
			JSONObject jsonObject = JSONObject.fromObject(map);
			jsonStr = jsonObject.toString();
		}catch(Exception e){
			System.out.println(JsonTransfor.class+":Exception");
		}
		return jsonStr;
	}//{"loginresult":"loginSuccess","userinfo":"{\"phonenumber\":\"189\",\"authority\":\"sxfj\",\"username\":\"sxfj\",\"principal\":\"Lin\",\"company\":\"绍兴纺机集团\",\"password\":\"123456\"}"}
	
	//一下的方法都为store.load()所需的json格式准备
	/**
	 * getStoreSingleDataJson(Map<String, Object> map)
	 * 产生单一json对象(即一组数据)--->{'machineType':'ABCD','machineNumber':'0001'}
	 * 传入一个map对象
	 * **/
	public static String getStoreSingleDataJson(Map<String, Object> map){  
		String result = null;
		JSONObject ja = JSONObject.fromObject(map);
		result = ja.toString().replaceAll("\"", "\'");
		return result;
	}	
	/**
	 * getStoreMultiDataJson(List<Map<String, Object>> list)
	 * 产生多个Json对象构成的数组---> [{'machineType':'ABCD','machineNumber':'0001'},  {'machineType':'ABCD','machineNumber':'0001'}]
	 * 传入一个list<Map>对象(包含多个map对象)
	 * **/
	public static String getStoreMultiDataJson(List<Map<String, Object>> list){  
		String result = null;
		ArrayList<JSONObject> joList = new ArrayList<>();  //用来存JsonObject对象的列表
		Iterator<?> i = list.iterator();
		while(i.hasNext()){
			Map<String,Object> map = (Map<String,Object>)i.next();
			JSONObject jo = JSONObject.fromObject(map);
			joList.add(jo);
		}
		JSONArray ja = JSONArray.fromObject(joList);
		result = ja.toString().replaceAll("\"", "\'");
		return result;
	}
	
	/**
	 * getStoreWholeJson(Map<String,Object> map)
	 * 本质和getAjaxJson函数一样
	 * 该函数特别用于用上述方法产生的json对象的整合(为了去掉值上面的引号)
	 * **/
	public static String getStoreWholeJson(Map<String,Object> map){   
		String result = null;
		JSONObject jo = JSONObject.fromObject(map);
		result = jo.toString().replaceAll("\"", "");
		return result;
	}//{userinfo:{'phonenumber':'189','authority':'sxfj','username':'sxfj','principal':'Lin','company':'绍兴纺机集团','password':'123456'},success:true}
	
/*	public static void main(String[] args){
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("username", "yy");
		map1.put("password", "yy");
		map1.put("identity", "yy");
		map1.put("company","yy");
		map1.put("phonenumber", "yy");	
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("username", "yyy");
		map2.put("password", "yyy");
		map2.put("identity", "yyy");
		map2.put("company","yyy");
		map2.put("phonenumber", "yyy");
		List<Map<String,Object>> list = new ArrayList<>();
		list.add(map1);
		list.add(map2);
		String s = getStoreDataJson(list);
		System.out.println(s);
		//[{'phonenumber':'yy','identity':'yy','username':'yy','company':'yy','password':'yy'},
		// {'phonenumber':'yyy','identity':'yyy','username':'yyy','company':'yyy','password':'yyy'}]
		Map<String,Object> map = new HashMap<>();
		map.put("data", s);
		map.put("success", true);
		String json = getStoreWholeJson(map);
		System.out.println("json--->"+json);
	}  */
}
