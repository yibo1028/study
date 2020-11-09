package com.yibo.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * @Description:
 * @Author: Yibo Liu
 * @Date: 2020-11-09 11:16
 */
public class LinkedList {
    public Node reverse(Node node) {
        if (node == null) {
            return null;
        }
        Node preNode = node;
        Node curNode = node.getNext();
        Node nextNode;
        while (curNode != null) {
            nextNode = curNode.getNext();
            curNode.setNext(preNode);
            preNode = curNode;
            curNode = nextNode;
        }
        node.setNext(null);
        return preNode;
    }

    public void print(Node node) {
        System.out.println(JSON.toJSONString(node));
        while (node != null) {
            String key = node.getKey();
            String value = node.getValue();
            Node next = node.getNext();
            System.out.println("key=" + key + ",value=" + value + ",nextKey=" + (next == null ? null : next.getKey()));
            node = node.getNext();
        }
    }

    public Node getNode() {
        Node node = new Node();
        node.setKey("" + 0);
        node.setValue("" + 0);
        Node preNode = node;
        for (int i = 1; i < 5; i++) {
            Node nextNode = new Node();
            nextNode.setKey("" + i);
            nextNode.setValue("" + i);
            preNode.setNext(nextNode);
            preNode = nextNode;
        }
        return node;
    }
}
