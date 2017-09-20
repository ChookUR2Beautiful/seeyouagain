package com.xmniao.xmn.core.manor.service;

import com.xmniao.xmn.core.manor.entity.ManorFlowerBranch;
import com.xmniao.xmn.core.manor.entity.TManorInfo;
import com.xmniao.xmn.core.manor.entity.treant.Node;
import com.xmniao.xmn.core.manor.entity.treant.Text;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yang.qiang on 2017/8/2.
 */
public class TreanUtils {

    public static Node insertNode(Node parentNode, Node subNode){
        List<Node> children = parentNode.getChildren();
        if (children == null){
            children = new ArrayList<>();
            parentNode.setChildren(children);
        }
//        Node subNode = new Node(branch.getId(), new Text("(" + branch.getUid() + ")" + branch.getNname(), branch.getUname()));
        children.add(subNode);
        return subNode;
    }

    /** 将 Branch 转换成 List<Node>, 并缓存到Buffer */
    public static List<Node> convertNodes(HashMap<String, Node> nodeBuffer, LinkedList<ManorFlowerBranch> branchs) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (ManorFlowerBranch branch : branchs) {
            Node node = new Node(branch.getId(), new Text("(" + branch.getUid() + ")" + branch.getNname(), branch.getUname())
            ,true);
            node.setParentId(branch.getParentId());
            nodeBuffer.put(node.getHtmlId(),node);
            nodes.add(node);
        }
        return nodes;
    }

    public static List<Node> convertNodesByRelation(HashMap<String, Node> nodeBuffer, LinkedList<BursEarningsRelation> allUsers, HashMap<Integer, TManorInfo> manorMap) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (BursEarningsRelation user : allUsers) {
            Node node = new Node(user.getUid()+"", new Text("(" + user.getUid() + ")" + user.getNname(), user.getUname()), true);
            node.setParentId(user.getParentUid()+"");

            TManorInfo manorInfo = manorMap.get(user.getUid());
            if (manorInfo == null) {
                node.setHtmlClass("manor-unactivated");
            }else {
                node.setHtmlClass("manor-activated");
            }


            nodeBuffer.put(node.getHtmlId(),node);
            nodes.add(node);
        }
        return nodes;
    }
    /** 组装Node */
    public static void packagingNode(HashMap<String, Node> nodeBuffer, List<Node> nodes) {
        ArrayList<Node> noParent = new ArrayList<>();

        for (Node node : nodes) {
            Node parentNode = nodeBuffer.get(node.getParentId());
            if (parentNode==null){
                noParent.add(node);
            } else {
                insertNode(parentNode,node);
            }
        }


        for (Node node : noParent) {
            Node parentNode = nodeBuffer.get(node.getParentId());
            if (parentNode!=null){
                insertNode(parentNode,node);
            }
        }
    }


}
