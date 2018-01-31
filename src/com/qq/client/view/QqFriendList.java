/**
 * �ҵĺ����б�����İ���ˣ���������
 */
package com.qq.client.view;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;
import com.qq.client.tools.*;
import com.qq.client.model.*;
import com.qq.common.*;
public class QqFriendList extends JFrame implements ActionListener, MouseListener,WindowListener 
{

	//�����һ�ſ�Ƭ
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	String owner;
	//����ڶ��ſ�Ƭ��İ���ˣ�
	
	JPanel jpmsr1, jpmsr2, jpmsr3;
	JButton jpmsr_jb1, jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel []jbls;
	//������JFrame ���ó�CardLayout
	CardLayout cl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqFriendList qqFriendList=new QqFriendList();
	}
	

	
	
	
	//�������ߵĺ��ѵ����
	public void updateFriend(Message m)
	{
		String onLineFriend[]=m.getCon().split(" "); //���ر�����ָ����ָ�֮����ַ�������
		for(int i=0; i<onLineFriend.length; i++)
		{
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}
	
	public QqFriendList(String ownerId)
	{
		this.owner=ownerId;
		//�����һ�ſ�Ƭ(��ʾ�����б�
		jphy_jb1=new JButton("�ҵĺ���");
		jphy_jb2=new JButton ("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton ("������");
		jphy1=new JPanel(new BorderLayout());
		//�ٶ���50������
		jphy2=new JPanel(new GridLayout(50,1,4,4));  //4��ʾ�м����м��
		
		//��jphy2,��ʼ��50������
		jbls=new JLabel[50];
		
		for(int i=0; i<jbls.length; i++)
		{
			//��ʾ����
			jbls[i]=new JLabel (i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jbls[i].setEnabled(false);
			if(jbls[i].getText().equals(ownerId))
			{
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
		}
			
		
		jphy3=new JPanel(new GridLayout(2,1));
		//�������������뵽jphy3��
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		
		jsp1=new JScrollPane(jphy2);
		
		
		//��jphy1�ĳ�ʼ��
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		
		
		
		//����ڶ��ſ�Ƭ
		
		jpmsr_jb1=new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton ("İ����");
		jpmsr_jb3=new JButton ("������");
		jpmsr1=new JPanel(new BorderLayout());
		//�ٶ���20������
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));  //4��ʾ�м����м��
		
		//��jphy2,��ʼ��20��İ����
		JLabel []jbls2=new JLabel[20];
		
		for(int i=0; i<jbls2.length; i++)
		{
			//��ʾ����
			jbls2[i]=new JLabel (i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
		}
			
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//�������������뵽jphy3��
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		
		jsp2=new JScrollPane(jpmsr2);
		
		
		//��jphy1�ĳ�ʼ��
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		
		
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		//�ڴ�����ʾ�Լ��ı��
		this.setTitle(ownerId);
		this.setSize(150,400);
		this.setVisible(true);
		this.addWindowListener(this);
	}

	
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jphy_jb2)
		{
			cl.show(this.getContentPane(),"2");
		}else if(arg0.getSource()==jpmsr_jb1)
		{
			cl.show(this.getContentPane(), "1");
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//��Ӧ�û�˫�����¼����õ����ѵı��
		if(arg0.getClickCount()==2)  //��ʾ˫��
		{
			//�õ��ú��ѵı��
			String friendNo=((JLabel)arg0.getSource()).getText();
			QqChat qqChart=new QqChat(this.owner,friendNo);
			
			//�����������뵽�����൱��
			ManageQqChat.addQqChat(this.owner+""+friendNo, qqChart);
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.red);
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.black);
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}




//windows�����ļ���������
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
//		// TODO Auto-generated method stub
//		ManageClientConServerThread.getClientConServerTherad(this.owner).setThreadStop(true);
//		try {
//			ManageClientConServerThread.getClientConServerTherad(this.owner).getSocket().close();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		ManageClientConServerThread.delClientConServerThread(this.owner);
//		System.out.println("window has been shunDonw,brin-----3");
		
		//����������͹ر�socket����Ϣ
		Message m=new Message();
		m.setMesType(MessageType.message_login_fail);
		m.setSender(this.owner);
		m.setSendTime(new java.util.Date().toString());
		//���͸�������
		try {
			ManageClientConServerThread.getClientConServerTherad(this.owner).setThreadStop(true);
			ObjectOutputStream oos=new ObjectOutputStream
			(ManageClientConServerThread.getClientConServerTherad(this.owner).getS().getOutputStream());
			oos.writeObject(m);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}



	
	
	
	
	
	
	
	
	
	
	
	
	


}

