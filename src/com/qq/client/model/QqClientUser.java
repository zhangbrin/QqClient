/**
 * ��������Ƿ���ȷ
 */
package com.qq.client.model;
import com.qq.common.*;
import java.net.*;
import java.io.*;

public class QqClientUser {
	
	public boolean checkUser(User u)
	{
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
}
