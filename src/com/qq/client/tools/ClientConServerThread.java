/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�
 * ������շ���������Ϣ�ͷ��͸���������Ϣ
 */
package com.qq.client.tools;

import java.io.*;
import java.net.*;
import com.qq.common.*;
import com.qq.client.tools.*;
import com.qq.client.view.*;

public class ClientConServerThread extends Thread {

	private Socket s;

	// �ر��߳̿���
	private boolean stop = false;

	public void setThreadStop(boolean b) {
		this.stop = b;
	}

	// ����Socket���г�ʼ���ͻ������ӷ��������߳�
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

				//��ȡ��������������Ϣ
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				m = (Message) ois.readObject();

				//��ͨ��Ϣ�Ĵ���
				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// �Ѵӷ������õ�����Ϣ��ʾ������ʾ�Ľ���
					QqChat qqChat = ManageQqChat.getQqChat(m.getGetter() + "" + m.getSender());
					// ��ʾ��Ϣ
					qqChat.showMessage(m);
				//���������û�����Ϣ����	
				} else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)) {
					System.out.println("�ͻ���"+m.getGetter() +"���ܵ����ߺ����б�:" + m.getCon());
//					String con = m.getCon();
//					String friends[] = con.split(" ");
					String getter = m.getGetter();
					// �޸���Ӧ�ĺ����б�
					QqFriendList qqFriendList = ManageQqFriendList.getQqFriend(getter);

					// ���º����б�
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
