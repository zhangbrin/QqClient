/**
 * ����ǿͻ������������ĺ�̨
 */
package com.qq.client.model;
import java.util.*;
import java.net.*;
import java.io.*;
import com.qq.client.tools.*;
import com.qq.common.Message;
import com.qq.client.model.*;
import com.qq.common.*;
public class QqClientConServer {

	public  Socket s;
	
	//���͵�һ������
	public boolean sendLoginInfoToServer(Object o)
	{
		boolean b=false;
		try {
			System.out.println(  ((User)o).getUserId() + " calling server...");
			s=new Socket("127.0.0.1", 9999);
			
			//ͨ��socket�����ͻ��˵���������󣬴��ݸ�������-->����o
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			//ͨ��socket�����ͻ��˵�����������,��ȡserver�˷����Ķ�������ms
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			
			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals("1"))
			{
				//����һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(s);
				//�������߳�
				ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);
				ccst.start();
				b=true;
			}else{
				//�ر�
				s.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
				
		}
		return b;
	}
	
	
	public void SendInfoToServer(Object o)
	{
		try {
			Socket s=new Socket("127.0.0.1", 9999);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
				
		}
	}
}
