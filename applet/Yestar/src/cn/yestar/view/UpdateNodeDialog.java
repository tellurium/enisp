package cn.yestar.view;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;

import cn.yestar.DBHelper;

import prefuse.data.Node;

@SuppressWarnings("serial")
public class UpdateNodeDialog extends JDialog {
	public static final String UPDATE_TITLE = "Update";
	
	public NodeInfoPanel mNodeInfoPanel;
	public Node mNode;
	
	public UpdateNodeDialog(Frame frame, NodeInfoPanel nodeInfoPanel, Node node) {
		super(frame, UPDATE_TITLE, true);
		mNodeInfoPanel = nodeInfoPanel;
		mNode = node;
		
		setUpUIElements();
	}

	private void setUpUIElements() {
		JPanel d_info = new JPanel();
		d_info.setBorder(javax.swing.BorderFactory.createTitledBorder("Company Information"));
		d_info.setLayout(new GridLayout(7,2, 30, 30));
        Label com_name = new Label();
        com_name.setText("Name:     ");
        Label com_add = new Label();       
        com_add.setText("Address:     ");
        Label com_web = new Label();
        com_web.setText("Official Website:     ");
        Label com_email = new Label();
        com_email.setText("Email:     ");
        Label com_time = new Label();
        com_time.setText("Establishment Time:     ");
        Label com_tel = new Label();
        com_tel.setText("Telephone:     ");
        final TextField d_cname = new TextField();
        d_cname.setText(mNode.getString(1));
        final TextField d_cadd = new TextField();
        d_cadd.setText(mNode.getString(2));
        final TextField d_cweb = new TextField();
        d_cweb.setText(mNode.getString(3));
        final TextField d_cemail = new TextField();
        d_cemail.setText(mNode.getString(4));
        final TextField d_ctime = new TextField();
        d_ctime.setText(mNode.getString(5));
        final TextField d_ctel = new TextField();
        d_ctel.setText(mNode.getString(6));
        Button cancelBtn = new Button();
        cancelBtn.setLabel("Cancel");
        cancelBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateNodeDialog.this.dispose();
			}
        });
        Button updateBtn = new Button();
        updateBtn.setLabel("Update");
        updateBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mNode == null || mNode.getGraph() == null) return;
				mNode.setString(1, d_cname.getText());
				mNode.setString(2, d_cadd.getText());
				mNode.setString(3, d_cweb.getText());
				mNode.setString(4, d_cemail.getText());
				mNode.setString(5, d_ctime.getText());
				mNode.setString(6, d_ctel.getText());
				
				UpdateNodeDialog.this.dispose();
				
				DBHelper.updateNodeInDB(mNode);
				
				// update application ui
				mNodeInfoPanel.cname.setText(mNode.getString(1));
				mNodeInfoPanel.cadd.setText(mNode.getString(2));
				mNodeInfoPanel.cweb.setText(mNode.getString(3));
				mNodeInfoPanel.cemail.setText(mNode.getString(4));
				mNodeInfoPanel.ctime.setText(mNode.getString(5));
				mNodeInfoPanel.ctel.setText(mNode.getString(6));
			}
        });
        
        d_info.add(com_name);
        d_info.add(d_cname);
        d_info.add(com_add);
        d_info.add(d_cadd);
        d_info.add(com_web);
        d_info.add(d_cweb);
        d_info.add(com_email);
        d_info.add(d_cemail);
        d_info.add(com_time);
        d_info.add(d_ctime);
        d_info.add(com_tel);
        d_info.add(d_ctel);
        d_info.add(cancelBtn);
        d_info.add(updateBtn);
		this.getContentPane().add(d_info);
		this.setSize(300, 500);
		this.setLocationRelativeTo(null);
	}
}
