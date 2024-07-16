/*
package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Twitter
{
    static int tweetCount = 0;

    private class User
    {
        int id;
        Set<Integer> following;
        Tweet tweetHead;

        public User(int id)
        {
            this.id = id;
            following = new HashSet<>();
            tweetHead = null;
        }

        public void follow(int id)
        {
            this.following.add(id);
        }

        public void unfollow(int id)
        {
            this.following.remove(id);
        }

        public void post(int tweetId)
        {
            Tweet tweet = new Tweet(tweetId);
            tweet.next = tweetHead;
            tweetHead = tweet;
        }
    }

    private class Tweet
    {
        int id;
        int time;
        Tweet next;

        public Tweet(int id)
        {
            this.id = id;
            this.time = tweetCount++;
            this.next = null;
        }
    }

    HashMap<Integer, User> users;

    public Twitter()
    {
        this.users = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId)
    {
        if(!this.users.containsKey(userId))
        {
            User user = new User(userId);
            this.users.put(userId, user);
        }
        this.users.get(userId).post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId)
    {

    }

    public void follow(int followerId, int followeeId)
    {
        if(!this.users.containsKey(followeeId))
        {
            User user = new User(followeeId);
            this.users.put(followeeId, user);
        }
        if(!this.users.containsKey(followerId))
        {
            User user = new User(followerId);
            this.users.put(followerId, user);
        }
        users.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId)
    {
        if(!users.containsKey(followerId) || followerId==followeeId)
            return;
        users.get(followerId).unfollow(followeeId);
    }
}

*/

