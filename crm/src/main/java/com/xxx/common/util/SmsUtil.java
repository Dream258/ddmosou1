package com.xxx.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 发送短信工具类  
 */
public class SmsUtil {
	 
	/**
	 * 发送短信
	 * 
	 * @param mobiles
	 *            短信接收人数组
	 * @param content
	 *            短信内容
	 * @param priority
	 *            短信的优先级 1到5 数字越大，优先级越高,发送速度越快.
	 * @return map success(0000：发送成功，0001：发送失败，0002：异常) balance：账号余额
	 *         smsNum：总共发送短信条数
	 */
	public static Map<String, Object> sendSMS(String[] mobiles, String content,
			int priority) {
		Map<String, Object> map = new HashMap<String, Object>();
		int smsNum = 0;// 总条数
		int conNum = 0;// 短信的字数
		int memNum = 0;// 接收短信的人数
		try {
			int i = 0;
			try {
				i=0;
				for(String phone:mobiles){
					mmsSend(phone,content);
					System.out.println(phone+content);
				}
				
			} catch (Exception e) {
				i=-1;
				// TODO: handle exception
				e.printStackTrace();
			}
			if (i == 0) {
				// 成功
				map.put("success", "0000");
				// 计算发送短信的条数
				conNum = countWords(content);
				memNum = mobiles.length;
				if (conNum % 140 > 0) {
					smsNum = (conNum / 140 + 1) * memNum;
				} else {
					smsNum = (conNum / 140) * memNum;
				}
			} else {
				// 失败
				map.put("success", "0001");
			}
			map.put("smsNum", smsNum);
		} catch (Exception e) {
			e.printStackTrace();
			// 发送短信异常
			map.put("success", "0002");
		}
		return map;
	}
	
	/**
	 * 备用通道初始化
	 * @param postData
	 * @param postUrl
	 * @return
	 */
	public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
    
    /**
     * 备用通道单条发送短信 
     * 
     */
    public static boolean  mmsSend(String mobile,String content){
    	try {
        	String PostData = "sname=dljax000&spwd=q1w2e3r4&scorpid=&sprdid=1012888&sdst="+mobile+"&smsg="+content+"【芝麻西瓜】";
        	if(content==null)
        		return false;
        	String ret = SMS(PostData, "http://cf.lmobile.cn/submitdata/Service.asmx/g_Submit");
        	//解析发送结果
        	System.out.println(ret);
        	//解析一下
        	ArrayList<String> strs = new ArrayList<String>();
        	Pattern p = Pattern.compile("<State>0</State>");
            Matcher m = p.matcher(ret);
            while (m.find()) {
            //	System.out.println("......");
                strs.add(m.group(0));            
            } 
        	if(strs.size()>0)
        		return true;
        	else
        		 return false;
    	} catch (Exception e) {
			return false;
		}
    }

	/**
	 * 获取混合字符串的字节数
	 * @param str
	 * @return
	 */
	public static int countWords(String str) {
		if (str == null || str.length() <= 0) {
			return 0;
		}
		int len = 0;
		char c;
		for (int i = str.length() - 1; i >= 0; i--) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
					|| (c >= 'A' && c <= 'Z')) {
				// 字母, 数字
				len++;
			} else {
				if (Character.isLetter(c)) { // 中文
					len += 2;
				} else { // 符号或控制字符
					len++;
				}
			}
		}
		return len;
	}

	/*public static void main(String args[]) {
		SmsUtil smstest = new SmsUtil();
		String[] ArrayPhoneNumber = {"15098871163"};
		Map MapResult = smstest.sendSMS(ArrayPhoneNumber, "感谢您注册芝麻西瓜账号，您的验证码为：666123，1小时内有效，如非本人操作，请忽略本短信。【芝麻西瓜】", 5);
		System.out.println(MapResult.get("smsNum")+"(总条数)");
		System.out.println(MapResult.get("success")+"----->：{0000：发送成功，0001：发送失败，0002：异常)");
	}*/
}
