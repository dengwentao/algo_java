package com.leetcode;
import java.util.*;

/**
 * Created by wentaod on 8/17/16.
 */
public class Twitter {

    static final int TOTAL = 100;
    static int time = 0;

    static class Post {
        int ts = 0;
        int id;
        Post(int id) {
            ts = time++;
            this.id = id;
        }
    }

    static class User {
        boolean[] follows = new boolean[TOTAL];
        LinkedList<Post> posts = new LinkedList<>();
    }

    static class Pair {
        Iterator<Post> it; // point to post of a user
        Post post; // post
        Pair(Iterator<Post> it, Post post) {
            this.it = it;
            this.post = post;
        }
    }

    User[] users = new User[TOTAL];

    /** Initialize your data structure here. */
    public Twitter() {
        for (int i=0; i<users.length; i++)
            users[i] = new User();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        users[userId].posts.addFirst(new Post(tweetId));
        if (users[userId].posts.size() > 10)
            users[userId].posts.removeLast();
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(TOTAL, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return p2.post.ts - p1.post.ts;
            }
        });

        for (int i=0; i<TOTAL; i++) {
            if (i==userId || users[userId].follows[i]) {
                Iterator<Post> it = users[i].posts.listIterator();
                if (it.hasNext()) {
                    Post post = it.next();
                    pq.offer(new Pair(it, post));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i=0; i<10 && !pq.isEmpty(); i++) {
            Pair p = pq.poll();
            result.add(p.post.id);
            if (p.it.hasNext()) {
                Post post = p.it.next();
                pq.offer(new Pair(p.it, post));
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId)
            return;
        users[followerId].follows[followeeId] = true;
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId)
            return;
        users[followerId].follows[followeeId] = false;
    }

    public static void main(String args[]) {
        Twitter obj = new Twitter();
        obj.postTweet(3,0);
        obj.postTweet(4,9);
        obj.postTweet(3,3);
        obj.postTweet(2,6);
        obj.postTweet(4,8);
        obj.postTweet(3,7);
        System.out.println(obj.getNewsFeed(3));
        obj.follow(3,4);
        obj.follow(3,2);
        System.out.println(obj.getNewsFeed(3));
        obj.unfollow(3,2);
        System.out.println(obj.getNewsFeed(3));
     }
}

