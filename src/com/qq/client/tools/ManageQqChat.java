/**
 * ����ǹ����û�����������
 */
package com.qq.client.tools;
import java.util.*;
import com.qq.client.view.*;
public class ManageQqChat {
	private static HashMap hm=new HashMap<String, QqChat>();
	
	
	
	//����
	public static void addQqChat(String loginIaAnFriendId,QqChat qqChat)
	{
		hm.put(loginIaAnFriendId, qqChat);
	}
	
	
	
	//ȡ��
	public static QqChat getQqChat(String loginIaAnFriendId)
	{
		return (QqChat)hm.get(loginIaAnFriendId);
	}
	
}
