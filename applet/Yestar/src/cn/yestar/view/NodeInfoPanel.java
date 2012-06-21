package cn.yestar.view;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JPanel;

import prefuse.Visualization;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;
import cn.yestar.DBHelper;

@SuppressWarnings("serial")
public class NodeInfoPanel extends JPanel {
	public Label cname = new Label();
    public Label cadd = new Label();
    public Label cweb = new Label();
    public Label cemail = new Label();
    public Label ctime = new Label();
    public Label ctel = new Label();
    
    public Node mNode;
    public Visualization mVis;
    public Frame mFrame;
    public Graph mGraph;
	
	public NodeInfoPanel(Frame frame,Visualization vis, Graph graph, Node node) {
		mVis = vis;
		mFrame = frame;
		mGraph = graph;
		mNode = node;
		
		setUpUIElements();
	}

	private void setUpUIElements() {
		this.setBorder(javax.swing.BorderFactory.createTitledBorder("Company Information"));
		this.setLayout(new GridLayout(6,2));
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
        
//        Button deleteBtn = new Button();
//        deleteBtn.setLabel("Delete");
//        deleteBtn.addActionListener(new ActionListener(){
//			@SuppressWarnings("rawtypes")
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				if(mNode == null) return;
//				Graph curGraph = mNode.getGraph();
//				if(curGraph == null) return;
//				if (DBHelper.deleteNodeInDB(mNode)) {
//					Iterator edgeIterator = mNode.edges();
//					while(edgeIterator.hasNext()) {
//						DBHelper.deleteEdgeInDB((Edge)edgeIterator.next());
//					}
//					curGraph.removeNode(mNode);
//				}
//				mVis.repaint();
//				System.out.println("Delete button is clicked");
//			}
//        });
//        
//        
//        Button updateBtn = new Button();
//        updateBtn.setLabel("Update");
//        updateBtn.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(mNode == null) return;
//				final JDialog dialog = new UpdateNodeDialog(mFrame, NodeInfoPanel.this, mNode);
//				dialog.setVisible(true);
//				System.out.println("Update button is clicked");
//			}
//        });
//        
//        Button addBtn = new Button();
//        addBtn.setLabel("Add");
//        addBtn.addActionListener(new ActionListener() {
//        	@Override
//        	public void actionPerformed(ActionEvent e) {
//        		final JDialog dialog = new IncreaseNodeDialog(mFrame, mGraph);
//				dialog.setVisible(true);
//				mVis.run("filter");
//				System.out.println("Insert menu bar is clicked");
//        	}
//        });
        
        
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(1, 3));
//        panel.add(deleteBtn);
//        panel.add(updateBtn);
        
        
        this.add(com_name);
        this.add(cname);
        this.add(com_add);
        this.add(cadd);
        this.add(com_web);
        this.add(cweb);
        this.add(com_email);
        this.add(cemail);
        this.add(com_time);
        this.add(ctime);
        this.add(com_tel);
        this.add(ctel);
//        this.add(addBtn);
//        this.add(panel);
	}
}
