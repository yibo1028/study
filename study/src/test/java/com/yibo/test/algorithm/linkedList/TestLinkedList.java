package com.yibo.test.algorithm.linkedList;

import com.yibo.algorithm.LinkedList;
import com.yibo.algorithm.Node;
import org.junit.Test;

/**
 * @Description:
 * @Author: Yibo Liu
 * @Date: 2020-11-09 14:19
 */
public class TestLinkedList {
    @Test
    public void test() {
        LinkedList linkedList = new LinkedList();
        Node node = linkedList.getNode();
        linkedList.print(node);
        Node reverseNode = linkedList.reverse(node);
        linkedList.print(reverseNode);
    }
}
