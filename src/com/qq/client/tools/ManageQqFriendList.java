/**
 * ������ѣ���������������
 */
package com.qq.client.tools;
import java.util.*;
import java.io.*;
import com.qq.client.view.*;
public class ManageQqFriendList {
	
	private static HashMap hm=new HashMap<String, QqFriendList>();
	
	
	//����
	public static void addQqFriendList(String qqid, QqFriendList qqFriendList)
	{
		hm.put(qqid, qqFriendList);
	}
	
	
	
	//��ȡ
	public static QqFriendList getQqFriend(String qqId)
	{
		return (QqFriendList)hm.get(qqId);
	}
	
	
	
}
