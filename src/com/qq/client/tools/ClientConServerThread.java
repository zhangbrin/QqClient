/**
 * 这是客户端和服务器端保持通讯的线程
 * 负责接收服务器的消息和发送给服务器消息
 */
package com.qq.client.tools;

import java.io.*;
import java.net.*;
import com.qq.common.*;
import com.qq.client.tools.*;
import com.qq.client.view.*;

public class ClientConServerThread extends Thread {

	private Socket s;

	// 关闭线程开关
	private boolean stop = false;

	public void setThreadStop(boolean b) {
		this.stop = b;
	}

	// 传入Socket进行初始化客户端连接服务器的线程
	public ClientConServerThread(Socket s) {
		this.s = s;
	}

	Message m;

	public Socket getSocket() {
		return this.s;
	}

	public void run() {
		while (!this.stop) {
			try {

				//读取服务器发来的消息
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				m = (Message) ois.readObject();

				//普通消息的处理
				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// 把从服务器得到的消息显示到该显示的界面
					QqChat qqChat = ManageQqChat.getQqChat(m.getGetter() + "" + m.getSender());
					// 显示消息
					qqChat.showMessage(m);
				//更新在线用户的消息处理	
				} else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)) {
					System.out.println("客户端"+m.getGetter() +"接受到在线好友列表:" + m.getCon());
//					String con = m.getCon();
//					String friends[] = con.split(" ");
					String getter = m.getGetter();
					// 修改相应的好友列表
					QqFriendList qqFriendList = ManageQqFriendList.getQqFriend(getter);

					// 更新好友列表
					if (qqFriendList != null) {
						qqFriendList.updateFriend(m);
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
}
