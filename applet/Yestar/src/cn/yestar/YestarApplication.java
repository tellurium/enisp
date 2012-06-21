package cn.yestar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.GroupAction;
import prefuse.action.ItemAction;
import prefuse.action.RepaintAction;
import prefuse.action.animate.ColorAnimator;
import prefuse.action.animate.PolarLocationAnimator;
import prefuse.action.animate.QualityControlAnimator;
import prefuse.action.animate.VisibilityAnimator;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.FontAction;
import prefuse.action.layout.CollapsedSubtreeLayout;
import prefuse.action.layout.graph.RadialTreeLayout;
import prefuse.activity.SlowInSlowOutPacer;
import prefuse.controls.ControlAdapter;
import prefuse.controls.DragControl;
import prefuse.controls.FocusControl;
import prefuse.controls.HoverActionControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Table;
import prefuse.data.Tuple;
import prefuse.data.event.TupleSetListener;
import prefuse.data.query.SearchQueryBinding;
import prefuse.data.search.PrefixSearchTupleSet;
import prefuse.data.search.SearchTupleSet;
import prefuse.data.tuple.DefaultTupleSet;
import prefuse.data.tuple.TupleSet;
import prefuse.render.AbstractShapeRenderer;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.ui.JFastLabel;
import prefuse.util.ui.JSearchPanel;
import prefuse.util.ui.UILib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.visual.sort.TreeDepthItemSorter;
import cn.yestar.view.IncreaseNodeDialog;
import cn.yestar.view.NodeCalculationPanel;
import cn.yestar.view.NodeInfoPanel;

@SuppressWarnings("serial")
public class YestarApplication extends Display {
	
    private static final String tree = "tree";                 //定义一棵树    
    private static final String treeNodes = "tree.nodes";      //树的结点
    private static final String treeEdges = "tree.edges";      //树的边
    private static final String linear = "linear";             //直线
    
    private LabelRenderer m_nodeRenderer;            //结点渲染
    private EdgeRenderer m_edgeRenderer;             //边渲染
    
    private static String m_label = "label";                //文字
    private static String m_image = "image";
 
    public NodeInfoPanel mNodeInfoPanel;
    public NodeCalculationPanel mNodeCalculationPanel;
    
    static Node curNode;
    static Graph curGraph;
    static Visualization curVis;
    static JFrame curFrame;
    
    public YestarApplication(Graph g, String label) {         //产生一个新的空虚拟文字空间
        super(new Visualization());
        m_label = label;                                         

        // -- set up visualization --
        m_vis.add(tree, g);                                //加入一棵树
        m_vis.setInteractive(treeEdges, null, false);      
        
        // -- set up renderers --设置渲染属性
        m_nodeRenderer = new LabelRenderer(m_label, m_image);       
        m_nodeRenderer.setRenderType(AbstractShapeRenderer.RENDER_TYPE_FILL);
        m_nodeRenderer.setHorizontalAlignment(Constants.CENTER);
        m_nodeRenderer.setRoundedCorner(8,8);
        m_nodeRenderer.setMaxImageDimensions(100,100);
        
        m_nodeRenderer.getImageFactory().preloadImages(m_vis.items(),"image");
        
        // 设置图片显示的位置
        m_nodeRenderer.setImagePosition(Constants.TOP);
        m_edgeRenderer = new EdgeRenderer();
        
        //装载渲染
        DefaultRendererFactory rf = new DefaultRendererFactory(m_nodeRenderer);
        rf.add(new InGroupPredicate(treeEdges), m_edgeRenderer);
        m_vis.setRendererFactory(rf);       
               
        // -- set up processing actions --
        
        // colors
        ItemAction nodeColor = new NodeColorAction(treeNodes);
        ItemAction textColor = new TextColorAction(treeNodes);
        m_vis.putAction("textColor", textColor);
        
        ItemAction edgeColor = new ColorAction(treeEdges,
                VisualItem.STROKECOLOR, ColorLib.rgb(200,200,200));   //设置边的颜色
        
        FontAction fonts = new FontAction(treeNodes, 
                FontLib.getFont("Tahoma", 10));         //设置字的大小
        fonts.add("ingroup('_focus_')", FontLib.getFont("Tahoma", 11));//设置选中后字体的大小
        
        // recolor
        ActionList recolor = new ActionList();
        recolor.add(nodeColor);
        recolor.add(textColor);
        m_vis.putAction("recolor", recolor);
        
        // repaint重绘
        ActionList repaint = new ActionList();
        repaint.add(recolor);
        repaint.add(new RepaintAction());
        m_vis.putAction("repaint", repaint);
        
        // animate paint change 激活重绘
        ActionList animatePaint = new ActionList(400);
        animatePaint.add(new ColorAnimator(treeNodes));
        animatePaint.add(new RepaintAction());
        m_vis.putAction("animatePaint", animatePaint);
        
        // create the tree layout action 创建树的布局
        // 控制显示的节点的半径大小
        RadialTreeLayout treeLayout = new RadialTreeLayout(tree, 90);
        // treeLayout.setAngularBounds(-Math.PI/2, Math.PI);
        m_vis.putAction("treeLayout", treeLayout);
        
        CollapsedSubtreeLayout subLayout = new CollapsedSubtreeLayout(tree);
        m_vis.putAction("subLayout", subLayout);
        
        // create the filtering and layout     创建过滤与布局
        ActionList filter = new ActionList();
        filter.add(new TreeRootAction(tree));
        filter.add(fonts);
        filter.add(treeLayout);
        filter.add(subLayout);
        filter.add(textColor);
        filter.add(nodeColor);
        filter.add(edgeColor);
        m_vis.putAction("filter", filter);
        
        // animated transition    激活转移
        ActionList animate = new ActionList(1250);
        animate.setPacingFunction(new SlowInSlowOutPacer());
        animate.add(new QualityControlAnimator());
        animate.add(new VisibilityAnimator(tree));
        animate.add(new PolarLocationAnimator(treeNodes, linear));
        animate.add(new ColorAnimator(treeNodes));
        animate.add(new RepaintAction());
        m_vis.putAction("animate", animate);        //显示转移的过程
        m_vis.alwaysRunAfter("filter", "animate");  
        
        // ------------------------------------------------
        
        // initialize the display  初始化显示
        setSize(600,600);
        setItemSorter(new TreeDepthItemSorter());
        addControlListener(new DragControl());
        addControlListener(new ZoomToFitControl());
        addControlListener(new ZoomControl());
        addControlListener(new PanControl());
        addControlListener(new FocusControl(1, "filter"));
        addControlListener(new HoverActionControl("repaint"));
        
        // ------------------------------------------------
        
        // filter graph and perform layout
        m_vis.run("filter");
        
        // maintain a set of items that should be interpolated linearly
        // this isn't absolutely necessary, but makes the animations nicer
        // the PolarLocationAnimator should read this set and act accordingly
        m_vis.addFocusGroup(linear, new DefaultTupleSet());
        m_vis.getGroup(Visualization.FOCUS_ITEMS).addTupleSetListener(
            new TupleSetListener() {
                public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
                    TupleSet linearInterp = m_vis.getGroup(linear);
                    if ( add.length < 1 ) return; linearInterp.clear();
                    for ( Node n = (Node)add[0]; n!=null; n=n.getParent() )
                        linearInterp.addTuple(n);
                }
            }
        );
        
        //设置搜索元组
       SearchTupleSet search = new PrefixSearchTupleSet();   
        m_vis.addFocusGroup(Visualization.SEARCH_ITEMS, search);
        search.addTupleSetListener(new TupleSetListener() {
            public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
                m_vis.cancel("animatePaint");
                m_vis.run("recolor");
                m_vis.run("animatePaint");
            }
        });
        
        // ....
        curVis = m_vis;
        curGraph = g;
    }
    
    // ------------------------------------------------------------------------
    
    public static void main(String argv[]) {
        UILib.setPlatformLookAndFeel();
        
        JFrame frame = new JFrame("Yestar Application");  
        
        curFrame = frame;
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(demo());
        
        // setup menu
        MenuBar bar = new MenuBar();
        frame.setMenuBar(bar);
        Menu Add = new Menu("Insert");
        MenuItem insert = new MenuItem("Insert");
        insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new IncreaseNodeDialog(curFrame, curGraph);
				dialog.setVisible(true);
				curVis.run("filter");
				System.out.println("Insert menu bar is clicked");
			}
        });
        Add.add(insert);
        bar.add(Add);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static JPanel demo() {
         return demo(DBHelper.getDataFromDB(), "enterprisename");
    }
    
    public static JPanel demo(final Graph g, final String label) {
    	curGraph = g;
    	g.addColumn(m_image, "CONCAT('logopic/',id,'.jpg')");
        // create a new radial tree view  创建一个新的树
        final YestarApplication gview = new YestarApplication(g, label);
        gview.setBorder(javax.swing.BorderFactory.createTitledBorder("TreeView"));
        Visualization vis = gview.getVisualization();
        
        // create a search panel for the tree map  创建搜索功能
        SearchQueryBinding sq = new SearchQueryBinding(
             (Table)vis.getGroup(treeNodes), label,
             (SearchTupleSet)vis.getGroup(Visualization.SEARCH_ITEMS));
        JSearchPanel search = sq.createSearchPanel();
        search.setShowResultCount(true);
        search.setBorder(BorderFactory.createEmptyBorder(5,5,4,0));
        search.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 11));
        
        final JFastLabel title = new JFastLabel("                 ");//设置左下角结点放大功能
        title.setPreferredSize(new Dimension(350, 20));
        title.setVerticalAlignment(SwingConstants.BOTTOM);
        title.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        title.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 16));
        
        Box box = new Box(BoxLayout.X_AXIS);
        
        box.add(Box.createHorizontalStrut(10));
        box.add(title);
        box.add(Box.createHorizontalGlue());
        box.add(search);
        box.add(Box.createHorizontalStrut(3));
             
        gview.mNodeInfoPanel = new NodeInfoPanel(curFrame, curVis, curGraph, curNode);
        
        gview.mNodeCalculationPanel = new NodeCalculationPanel(curFrame, curVis, curGraph);
        
        Box rightpart = new Box(BoxLayout.Y_AXIS);
        rightpart.add(gview.mNodeInfoPanel);     
        rightpart.add(gview.mNodeCalculationPanel);
        
        gview.addControlListener(new ControlAdapter() {       //监听鼠标位置消息
            public void itemEntered(VisualItem item, MouseEvent e) {
                if ( item.canGetString(label) )
                    title.setText(item.getString(label));
            }
            public void itemExited(VisualItem item, MouseEvent e) {
                title.setText(null);
            }
            public void itemClicked(VisualItem item, MouseEvent e) {
            	if(gview.mNodeInfoPanel.cname == null || gview.mNodeInfoPanel.cweb ==null 
            			|| gview.mNodeInfoPanel.cadd==null || gview.mNodeInfoPanel.cemail ==null
            			|| gview.mNodeInfoPanel.ctime ==null || gview.mNodeInfoPanel.ctel ==null) return;
            	
            	curGraph = g;
            	curNode = g.getNode(item.getRow());
            	gview.mNodeInfoPanel.mNode = curNode;
            	
            	gview.mNodeInfoPanel.cname.setText(Snippet.getShortenString(curNode.getString(1)));
            	gview.mNodeInfoPanel.cadd.setText(Snippet.getShortenString(curNode.getString(2)));
            	gview.mNodeInfoPanel.cweb.setText(Snippet.getShortenString(curNode.getString(3)));
            	gview.mNodeInfoPanel.cemail.setText(Snippet.getShortenString(curNode.getString(4)));
            	gview.mNodeInfoPanel.ctime.setText(Snippet.getShortenString(curNode.getString(5)));
            	gview.mNodeInfoPanel.ctel.setText(Snippet.getShortenString(curNode.getString(6)));
            	
            	gview.mNodeCalculationPanel.cnum.setText(curNode.getDegree() + "");
            	gview.mNodeCalculationPanel.cglobal_centrality.setText("" + Utils.calculateGlobalCentrality(curNode));
            	gview.mNodeCalculationPanel.cnum_structral_holes.setText("" + Utils.calculateNumberOfStructuralHoles(curNode));
            	gview.mNodeCalculationPanel.credundancy.setText("" + Utils.calculateRedundacy(curNode));
            	gview.mNodeCalculationPanel.ceffective_size.setText("" + Utils.calculateEffectiveSize(curNode));
            	gview.mNodeCalculationPanel.cnum.setText(curNode.getDegree() + "");

            	gview.mNodeCalculationPanel.updateAccPanel(curNode);
            } 
        });
     
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(gview, BorderLayout.CENTER);
        panel.add(box, BorderLayout.SOUTH);
        panel.add(rightpart,BorderLayout.EAST);
              
        Color BACKGROUND = Color.white;
        Color FOREGROUND = Color.DARK_GRAY;
        UILib.setColor(panel, BACKGROUND, FOREGROUND);
        
        return panel;
    }
    
    public static class YestarControlAdapter extends ControlAdapter {
    	
    }
    
    // ------------------------------------------------------------------------
    
    /**
     * Switch the root of the tree by requesting a new spanning tree
     * at the desired root
     */
    public static class TreeRootAction extends GroupAction {  //结点移动算法
        public TreeRootAction(String graphGroup) {
            super(graphGroup);
        }
        @SuppressWarnings("rawtypes")
		public void run(double frac) {
            TupleSet focus = m_vis.getGroup(Visualization.FOCUS_ITEMS);
            if ( focus==null || focus.getTupleCount() == 0 ) return;
            
            Graph g = (Graph)m_vis.getGroup(m_group);
            Node f = null;
            Iterator tuples = focus.tuples();
            while (tuples.hasNext() && !g.containsTuple(f=(Node)tuples.next()))
            {
                f = null;
            }
            if ( f == null ) return;
            g.getSpanningTree(f);
        }
    }
    
    /**
     * Set node fill colors
     */
    public static class NodeColorAction extends ColorAction {  //结点颜色变化
        public NodeColorAction(String group) {
            super(group, VisualItem.FILLCOLOR, ColorLib.rgba(255,255,255,0));
            add("_hover", ColorLib.gray(220,230));     //鼠标移上后背景
            add("ingroup('_search_')", ColorLib.rgb(255,190,190)); //搜索匹配后的背景颜色
            add("ingroup('_focus_')", ColorLib.rgb(198,229,229));//结点移动后的默认颜色
        }
                
    } // end of inner class NodeColorAction
    
    /**
     * Set node text colors
     */
    public static class TextColorAction extends ColorAction {  //鼠标移上后字的颜色
        public TextColorAction(String group) {
            super(group, VisualItem.TEXTCOLOR, ColorLib.gray(0));
            add("_hover", ColorLib.rgb(255,0,0));   
        }
    } // end of inner class TextColorAction
    
} // end of class RadialGraphView