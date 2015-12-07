package com.leetcode;

/**
 * Reverse Words in a String
 *
 * Created by wentaod on 12/3/15.
 */
public class ReverseWordsInString {
    static public class Solution {
        public String reverseWords(String s) {
            if(s==null || s.isEmpty())
                return s;

            s = reverse(s);
            String[] arr = s.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<arr.length; i++) {
                if(!arr[i].isEmpty()) {
                    sb.append(reverse(arr[i]));
                    if(i != arr.length-1)
                        sb.append(' ');
                }
            }
            return sb.toString();
        }

        // reverse a char array in [start, end]
        String reverse(String s) {
            char[] arr = s.toCharArray();
            for(int i=0, j=arr.length-1; i<j; i++, j--) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            return String.valueOf(arr);
        }

        public void test() {
            System.out.println("*"+reverseWords("  the sky is  blue  ")+"*");
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
