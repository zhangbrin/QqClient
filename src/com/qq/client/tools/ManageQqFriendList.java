/**
 * 管理好友，黑名单，界面类
 */
package com.qq.client.tools;
import java.util.*;
import java.io.*;
import com.qq.client.view.*;
public class ManageQqFriendList {
	
	private static HashMap hm=new HashMap<String, QqFriendList>();
	
	
	//加入
	public static void addQqFriendList(String qqid, QqFriendList qqFriendList)
	{
		hm.put(qqid, qqFriendList);
	}
	
	
	
	//获取
	public static QqFriendList getQqFriend(String qqId)
	{
		return (QqFriendList)hm.get(qqId);
	}
	
	
	
}
