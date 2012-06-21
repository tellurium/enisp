package cn.yestar.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import cn.yestar.Utils;

import prefuse.Visualization;
import prefuse.data.Graph;
import prefuse.data.Node;

@SuppressWarnings("serial")
public class NodeCalculationPanel extends JPanel implements ActionListener {
	public JLabel cnum = new JLabel();
    public JLabel cglobal_centrality = new JLabel();
    public JLabel cnum_structral_holes = new JLabel();
    public JLabel credundancy = new JLabel();
    public JLabel ceffective_size = new JLabel();
    
    public JComboBox<Integer> nodeList;
    public JLabel accLabel;
    public JPanel accPanel;
    public NodeComboBoxRenderer nodeComboBoxRenderer;
	
	public Node mNode;
	public Visualization mVis;
	public Frame mFrame;
	public Graph mGraph;
	 
	 
	public NodeCalculationPanel(Frame frame, Visualization vis, Graph graph){
		mFrame = frame;
		mVis = vis;
		mGraph = graph;
		 
		setupUIQuota();
	}

	@SuppressWarnings("unchecked")
	private void setupUIQuota() {
		this.setBorder(javax.swing.BorderFactory.createTitledBorder("Company Relationship"));
	    this.setLayout(new GridLayout(6,2));
	    Label num = new Label();
	    num.setText("Number of Degree : ");
	    Label global_centrality = new Label();
	    global_centrality.setText("Global centrality: ");
	    Label num_structral_holes = new Label();
	    num_structral_holes.setText("Num of structural hole: ");
	    Label redundancy = new Label();
	    redundancy.setText("Redundancy: ");
	    Label effective_size = new Label();
	    effective_size.setText("Effective Size: ");
	    Label accessibility = new Label();
	    accessibility.setText("Accessibility: ");
	     
	    // 可达性视图
	    accPanel = new JPanel();
	    accPanel.setLayout(new GridLayout(1, 2));
	    nodeComboBoxRenderer = new NodeComboBoxRenderer();
	    nodeComboBoxRenderer.setPreferredSize(new Dimension(30, 20));
	    
	    int nodeCount = mGraph.getNodeCount();
	    Integer[] intArray = new Integer[nodeCount];
	    for	(int i = 0; i < nodeCount; i++) {
	    	intArray[i] = new Integer(i);
	    }
	    
	    nodeList = new JComboBox<Integer>(intArray);
	    nodeList.setRenderer(nodeComboBoxRenderer);
	    nodeList.addActionListener(this);
	    nodeList.setMaximumRowCount(15);
	    accPanel.add(nodeList);
	    accLabel = new JLabel("None", JLabel.CENTER);
	    accPanel.add(accLabel);
	    
	    this.add(num);
	    this.add(cnum);
	    this.add(global_centrality);
	    this.add(cglobal_centrality);
	    this.add(num_structral_holes);
	    this.add(cnum_structral_holes);
	    this.add(redundancy);
	    this.add(credundancy);
	    this.add(effective_size);
	    this.add(ceffective_size);
	    this.add(accessibility);
	    this.add(accPanel);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (mNode == null) return;
		int selectedIndex = ((Integer) nodeList.getSelectedItem()).intValue();
		accLabel.setText(Utils.calculateAccessibility(mNode, mGraph.getNode(selectedIndex)) + "");
	}
	
	public void updateAccPanel(Node node) {
		if (node == null) return;
		mNode = node;
		accPanel.repaint();
	}
	
	@SuppressWarnings("rawtypes")
	class NodeComboBoxRenderer extends JLabel implements ListCellRenderer {
		
		public NodeComboBoxRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if(isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			
			if(value == null) return this;
			
			int selectedIndex = ((Integer) value).intValue();
			
			Node tmpNode = (Node) mGraph.getNode(selectedIndex);
			// System.out.println(tmpNode.getString(1));
			setText(tmpNode.getString(1));
			
			return this;
		}
	}
}
