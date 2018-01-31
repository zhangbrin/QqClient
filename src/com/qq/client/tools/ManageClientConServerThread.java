/**
 * 管理通讯客户端与服务器保持通讯的线程类
 */
package com.qq.client.tools;
import java.util.*;
public class ManageClientConServerThread {
	
		private static HashMap  hm=new HashMap<String, ClientConServerThread>();
		
		//把创建好的ClientConServerThread放入到HashMap中
		public static void addClientConServerThread(String qqId, ClientConServerThread ccst)
		{
			hm.put(qqId, ccst);
		}
		
		
		//可以通过qqId取得该线程
		public static ClientConServerThread getClientConServerTherad(String qqId)
		{
			return (ClientConServerThread)hm.get(qqId);
		}
		
		
		//根据qqid删除该线程
		public static void delClientConServerThread(String qqId ){
			hm.remove(qqId);
		}
}
