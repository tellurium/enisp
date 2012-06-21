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

import prefuse.data.Graph;
import prefuse.data.Node;
import cn.yestar.DBHelper;

@SuppressWarnings("serial")
public class IncreaseNodeDialog extends JDialog {
	public static final String INCREASE_TITLE = "Insert";
	public Graph mGraph;
	
	public IncreaseNodeDialog(Frame frame, Graph graph) {
		super(frame, INCREASE_TITLE, true);
		mGraph = graph;
		invalidateUIElements();
	}

	private void invalidateUIElements() {
		JPanel d_info = new JPanel();
		d_info.setBorder(javax.swing.BorderFactory.createTitledBorder("Company Information"));
		d_info.setLayout(new GridLayout(8,2, 30, 30));
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
        Label com_row = new Label();
        com_row.setText("Edges:     ");
        final TextField d_cname = new TextField();
        final TextField d_cadd = new TextField();
        final TextField d_cweb = new TextField();
        final TextField d_cemail = new TextField();
        final TextField d_ctime = new TextField();
        final TextField d_ctel = new TextField();
        final TextField d_row = new TextField();
        
        Button cancelBtn = new Button();
        cancelBtn.setLabel("Cancel");
        cancelBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				IncreaseNodeDialog.this.dispose();
			}
        });
        Button increaseBtn = new Button();
        increaseBtn.setLabel("Increase");
        increaseBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] edgesStr = d_row.getText().trim().split(",");
				for(String edgeStr: edgesStr) {
					try {
						Integer.parseInt(edgeStr);
					} catch (NumberFormatException ex) {
						d_row.setText("请输入数字并以逗号分割");
						return;
					}
				}
				Node newNode = mGraph.addNode();
				newNode.setString(1, d_cname.getText());
				newNode.setString(2, d_cadd.getText());
				newNode.setString(3, d_cweb.getText());
				newNode.setString(4, d_cemail.getText());
				newNode.setString(5, d_ctime.getText());
				newNode.setString(6, d_ctel.getText());
				
				// 存入新的节点，并得到id
				DBHelper.addNodeToDB(newNode);
				
				if(edgesStr.length <= 0) return;
				for(String edgeStr: edgesStr) {
					int edgeRow = Integer.parseInt(edgeStr);
					Node n = mGraph.getNode(edgeRow - 1);
					if(n == null) continue;
					DBHelper.addEdgeToDB(mGraph.addEdge(newNode, n));
				}
				
				IncreaseNodeDialog.this.dispose();
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
        d_info.add(com_row);
        d_info.add(d_row);
        d_info.add(cancelBtn);
        d_info.add(increaseBtn);
		this.getContentPane().add(d_info);
		this.setSize(300, 500);
		this.setLocationRelativeTo(null);
	}
}
