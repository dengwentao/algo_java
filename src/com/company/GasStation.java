package com.company;
import java.util.*;

/**
 * Created by wedeng on 2/5/15.
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

 You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 You begin the journey with an empty tank at one of the gas stations.

 Return any one of the starting gas station's index if you can travel around the circuit once starting from there, otherwise return -1.
 */

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length==0 || gas.length != cost.length)
            return -1;

        int index = 0;

        while(true) {
            int result = 0;
            for(int i=index; i<gas.length; i++) {
                result += gas[i] - cost[i];
                if(result < 0) {
                    index = i+1;
                    cost[cost.length-1] -= result;
                    break;
                }
            }
            //we come here in 3 cases: 1) finished one loop with result>=0; 2) finished one loop with result<0 3) in the middle with result<0;
            if(result >= 0)
                return index;
            if(index==gas.length)
                return -1;
        }
    }

    public void test() {
        int[] gas = {3, 5, 8, 1, 7, 2, 4, 8};
        int[] cost = {5, 2, 7, 9, 2, 6, 1, 6};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}

public class GasStation {
    public static void main(String[] args) {
        // write your code here
        Solution sol = new Solution();
        sol.test();
    }
}
