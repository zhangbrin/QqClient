/**
 * �������������Ľ���
 * ��Ϊ�ͻ���Ҫ���ڶ�ȡ��״̬��������ǰ�������һ���߳�
 */
package com.qq.client.view;
import java.awt.*;
import java.awt.event.*;
import com.qq.common.*;
import javax.swing.*;
import com.qq.client.model.*;
import java.io.*;
import com.qq.client.tools.*;
public class QqChat extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	QqChart QQ=new QqChart("1");
	}

	public 	QqChat(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("����");
		jb.addActionListener(this);
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		//���ô��ڵ�ͼ��
		this.setTitle(ownerId+"���ں�"+friend+"����:");
		this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
		this.setSize(300,200);
		this.setVisible(true);
		
		
	}
	
	//дһ������������ʾ��Ϣ
	public void showMessage(Message m)
	{
		String info=m.getSender()+"��"+m.getGetter()+m.getCon()+"\r\n";
		this.jta.append(info); // ׷�ӵ��ı�����
	}
	

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			//����û�����˷���
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			//���͸�������
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerTherad(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				jtf.setText(null);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}
	
//	
//	public void run() {
//		// TODO Auto-generated method stub
//		while(true)
//		{
//			try {
//				//���û����Ϣ��ȡ�͵ȴ�
//				ObjectInputStream ois=new ObjectInputStream(QqClientConServer.s.getInputStream());
//				Message m=(Message)ois.readObject();
//				//��ʾ���ı���
//				
//				String info=m.getSender()+"��"+m.getGetter()+m.getCon()+"\r\n";
//				this.jta.append(info); // ׷�ӵ��ı�����
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			//��ȡ
//			
//		}
}

