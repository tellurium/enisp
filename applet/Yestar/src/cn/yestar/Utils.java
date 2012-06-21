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
	 * �������(Degree)��һ�����ı���  
	 */
	public static int calculateDegree(Node node) {
		return node.getDegree();
	}
	
	/**
	 * �����������Ķ�(Global Centrality)��һ��������������������������ڵ�ľ���֮��
	 */
	public static int calculateGlobalCentrality(Node node) {
		// ��¼�ڵ��Ƿ񱻷��ʹ��ļ���
		Set<Node> visitedNodeSet = new HashSet<Node>();
		
		return _calculateGlobalCentrality(visitedNodeSet, node, 1);
	}
	
	/**
	 * ����ռ�ṹ��λ������(Number of Structural Holes)��
	 * A�����B��㡢C��㶼�бߣ���B��C֮��û�бߣ����Aռ����һ���ṹ��λ�á�
	 * ͳ��һ�����ռ�ݽṹ��λ�õ�������
	 */
	@SuppressWarnings("rawtypes")
	public static int calculateNumberOfStructuralHoles(Node node) {
		int nodeDegree = node.getDegree();
		if (nodeDegree < 2) return 0;		
		// ���ṹ����ĿnodeDegree * (nodeDegree - 1) / 2
		int result = (nodeDegree * (nodeDegree - 1)) / 2;		
		Graph graph = node.getGraph();	
		// ��¼�ڵ��Ƿ񱻷��ʹ���ӳ��
		Map<Node, Boolean> visitedNodeMap = new HashMap<Node, Boolean>();	
		// Step 1: ��ȡ�����ܱ߽ڵ�
		Iterator nodeIterator = node.neighbors();
		while(nodeIterator.hasNext()) {
			Node nextNode = (Node)nodeIterator.next();
			if (visitedNodeMap.containsKey(nextNode)) continue;
			visitedNodeMap.put(nextNode, false);
		}		
		// Step 2: �����ܱ߽ڵ㣬�鿴����֮���Ƿ��бߣ� ����бߣ�result - 1 
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
	 * ���������(Redundancy):һ�������ڵĸ�������Ա���������ƽ�����������ӵ��õ���߲��������ڣ�
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
	 * ������Ч��ģ(Effective Size):���ǽ��Ķ�����ȥ�����
	 */
	public static float calculateEffectiveSize(Node node) {
		return (float)node.getDegree() - calculateRedundacy(node); 
	}
	
	/**
	 * �������ڵ�֮��Ŀɴ���
	 */
	public static boolean calculateAccessibility(Node sourceNode, Node targetNode) {
		// ��¼�ڵ��Ƿ񱻷��ʹ��ļ���
		Set<Node> visitedNodeSet = new HashSet<Node>();
		return _calculateAccessibility(sourceNode, targetNode, visitedNodeSet);
	}
	
	/**
	 * ������ȱ�������ͼ�����������������Ķ�
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
		// step 1: ��Դ�ڵ���Ϊ�Ѿ�������
		visitedNodeSet.add(sourceNode);
		
		// step 2: ��������ڵ��Ƿ���ͬһ���ڵ㣬������ɴ�
		if (sourceNode == targetNode) return true;
		
		// step 3: ���Դ�ڵ��Ŀ��ڵ��Ƿ��ǹ����Ľڵ㣬���ǣ��򲻿ɴ�
		if (sourceNode.getDegree() <= 0 || targetNode.getDegree() <= 0) return false;
		
		// step 4: �Էǹ���Դ�ڵ���й�ȱ���
		Iterator nodeIterator = sourceNode.neighbors();
		while(nodeIterator.hasNext()) {
			Node tempSourceNode = (Node) nodeIterator.next();
			
			// step 5: ����ڽӽڵ��Ƿ񱻷��ʹ�����������ʹ��������������鿴��һ���ڵ�
			if (visitedNodeSet.contains(tempSourceNode)) continue;
			
			// step 6: ����Դ�ڵ��ĳ���ھӽڵ���Ŀ��ڵ�ɴ�򷵻�true
			if (_calculateAccessibility(tempSourceNode, targetNode, visitedNodeSet)) return true;
			
			// �����������
		}
		
		return false;
	}
}
