/**
 * ����: QQ��¼����
 */
package com.qq.client.view;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import com.qq.client.model.QqClientUser;
import com.qq.common.*;
import java.awt.event.*;
import com.qq.client.tools.*;
public class QqClientLogin extends JFrame implements ActionListener {

	//���山������Ҫ�����
	JLabel jbl1;
	//�����в���Ҫ�����
	//�в�������JPanel����һ��ѡ�����
	JTabbedPane jtp;
	JPanel jp2, jp3, jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4; 
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1, jp2_jcb2;
	//�����ϲ�����Ҫ�����
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub.e
		QqClientLogin qqClientLogin=new QqClientLogin();
	}
	
	public QqClientLogin()
	{
		//������
		jbl1=new JLabel(new ImageIcon("image/tou.gif"));
		
		//�����в�
		jp2=new JPanel(new GridLayout(3,3));
		
		jp2_jbl1=new JLabel("QQ����",JLabel.CENTER);
		jp2_jbl2=new JLabel("QQ����",JLabel.CENTER);
		jp2_jbl3=new JLabel("��������",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("�������뱣��",JLabel.CENTER);
		jp2_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("�����¼");
		jp2_jcb2=new JCheckBox("��ס����");
		
		//�ѿռ䰴��˳����뵽jp2��
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		
		//����ѡ�����
		jtp=new JTabbedPane();
		jtp.add("QQ����",jp2);
		jp3=new JPanel();
		jtp.add("�ֻ�����",jp3);
		jp4=new JPanel();
		jtp.add("�����ʼ�",jp4);
		
		
		//�����ϲ�
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("image/denglu.gif"));
		//��Ӧ�û������¼
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/quxiao.gif"));
		jp1_jb3=new JButton(new ImageIcon("image/xiangdao.gif"));
		
		//��������ť�ŵ�jp1��
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		this.add(jbl1, "North");
		this.add(jtp, "Center");
		//��jp1�ŵ��ϲ�
		this.add(jp1,"South");
		this.setIconImage((new ImageIcon("image/qqdfsdf.gif").getImage()));
		this.setSize(350,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//����û������¼
		if(arg0.getSource()==jp1_jb1)
		{
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(jp2_jtf.getText().trim());  //trim�ǳ�ȥ��ʼ���β�Ŀո�
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(qqClientUser.checkUser(u))
			{
				try {
					//����QQList����
					QqFriendList qqList=new QqFriendList(u.getUserId());
					//��QQlist���뵽ManageQqFriendList��hashmap��
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqList);
					
					//����һ�������
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerTherad(u.getUserId()).getS().getOutputStream());
					
					//��һ���������ߺ����б�Message
					Message m=new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					//ָ����Ҫ��qq�ŵĺ��ѵ����
					m.setSender(u.getUserId());
					oos.writeObject(m);	
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				//�رյ���¼����
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "�û����������");
			}
		}
	}

}
