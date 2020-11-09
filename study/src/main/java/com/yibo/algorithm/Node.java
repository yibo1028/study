package com.yibo.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Yibo Liu
 * @Date: 2020-11-09 11:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    private String key;
    private String value;
    private Node next;
}
