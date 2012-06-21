package cn.yestar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import prefuse.data.Graph;
import prefuse.data.Node;

public class Utils {
	
	/**
	 * 计算度数(Degree)：一个结点的边数  
	 */
	public static int calculateDegree(Node node) {
		return node.getDegree();
	}
	
	/**
	 * 计算总体中心度(Global Centrality)：一个结点在整个网络中与其他各节点的距离之和
	 */
	public static int calculateGlobalCentrality(Node node) {
		// 记录节点是否被访问过的集合
		Set<Node> visitedNodeSet = new HashSet<Node>();
		
		return _calculateGlobalCentrality(visitedNodeSet, node, 1);
	}
	
	/**
	 * 计算占结构洞位置数量(Number of Structural Holes)：
	 * A结点与B结点、C结点都有边，而B、C之间没有边，则称A占据了一个结构洞位置。
	 * 统计一个结点占据结构洞位置的数量。
	 */
	@SuppressWarnings("rawtypes")
	public static int calculateNumberOfStructuralHoles(Node node) {
		int nodeDegree = node.getDegree();
		if (nodeDegree < 2) return 0;		
		// 最大结构洞数目nodeDegree * (nodeDegree - 1) / 2
		int result = (nodeDegree * (nodeDegree - 1)) / 2;		
		Graph graph = node.getGraph();	
		// 记录节点是否被访问过的映射
		Map<Node, Boolean> visitedNodeMap = new HashMap<Node, Boolean>();	
		// Step 1: 获取所有周边节点
		Iterator nodeIterator = node.neighbors();
		while(nodeIterator.hasNext()) {
			Node nextNode = (Node)nodeIterator.next();
			if (visitedNodeMap.containsKey(nextNode)) continue;
			visitedNodeMap.put(nextNode, false);
		}		
		// Step 2: 遍历周边节点，查看两两之间是否有边， 如果有边，result - 1 
		for(Node tmpNode: visitedNodeMap.keySet()) {
			visitedNodeMap.put(tmpNode, true);
			for(Node nestedTmpNode: visitedNodeMap.keySet()) {
				if (tmpNode == nestedTmpNode || visitedNodeMap.get(nestedTmpNode)) {
					continue;
				}
				if (graph.getEdge(tmpNode, nestedTmpNode) != null) {
					result--;
				}
			}
		}
		return result;
	}
	
	/**
	 * 计算冗余度(Redundancy):一个点所在的个体网成员中其他点的平均度数（连接到该点的线不计算在内）
	 */
	@SuppressWarnings("rawtypes")
	public static float calculateRedundacy(Node node) {
		float result = 0;		
		int degree = node.getDegree();		
		Iterator nodeIterator = node.neighbors();
		while(nodeIterator.hasNext()) {
			Node tmpNode = (Node)nodeIterator.next();
			result += tmpNode.getDegree();
		}
		return (result - degree) / degree;
	}
	
	/**
	 * 计算有效规模(Effective Size):就是结点的度数减去冗余度
	 */
	public static float calculateEffectiveSize(Node node) {
		return (float)node.getDegree() - calculateRedundacy(node); 
	}
	
	/**
	 * 计算两节点之间的可达性
	 */
	public static boolean calculateAccessibility(Node sourceNode, Node targetNode) {
		// 记录节点是否被访问过的集合
		Set<Node> visitedNodeSet = new HashSet<Node>();
		return _calculateAccessibility(sourceNode, targetNode, visitedNodeSet);
	}
	
	/**
	 * 广度优先遍历整个图，迭代计算总体中心度
	 */
	@SuppressWarnings("rawtypes")
	private static int _calculateGlobalCentrality(Set<Node> visitedSet, Node curNode, int distance) {
		if (visitedSet.contains(curNode)) return 0;
		if (curNode.getDegree() == 0) return 0;
		int result = 0;
		visitedSet.add(curNode);
		Iterator nodeIterator = curNode.neighbors();
		while(nodeIterator.hasNext()) {
			Node nextNode = (Node)nodeIterator.next();
			if (visitedSet.contains(nextNode)) continue;
			result += (_calculateGlobalCentrality(visitedSet, nextNode, distance + 1) + distance);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	private static boolean _calculateAccessibility(Node sourceNode, Node targetNode, Set<Node> visitedNodeSet) {
		// step 1: 把源节点标记为已经被访问
		visitedNodeSet.add(sourceNode);
		
		// step 2: 检测两个节点是否是同一个节点，若是则可达
		if (sourceNode == targetNode) return true;
		
		// step 3: 检测源节点和目标节点是否是孤立的节点，若是，则不可达
		if (sourceNode.getDegree() <= 0 || targetNode.getDegree() <= 0) return false;
		
		// step 4: 对非孤立源节点进行广度遍历
		Iterator nodeIterator = sourceNode.neighbors();
		while(nodeIterator.hasNext()) {
			Node tempSourceNode = (Node) nodeIterator.next();
			
			// step 5: 检查邻接节点是否被访问过，如果被访问过，则跳过继续查看下一个节点
			if (visitedNodeSet.contains(tempSourceNode)) continue;
			
			// step 6: 假如源节点的某个邻居节点与目标节点可达，则返回true
			if (_calculateAccessibility(tempSourceNode, targetNode, visitedNodeSet)) return true;
			
			// 否则继续遍历
		}
		
		return false;
	}
}
