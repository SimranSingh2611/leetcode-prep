/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd store each user's tweets newest first and merge those lists when building a feed.
 * - A heap contains one current tweet per candidate user, and each extraction advances only
 *   that user's list.
 * Time complexity: Expected O(1) for postTweet, follow, unfollow; getNewsFeed takes O((F + 10)
 *   log(F + 2)) for F candidate users.
 * Space complexity: O(T + R + U) persistent storage for tweets T, follow relationships R, and
 *   stored user records U; O(F) feed workspace plus at most ten answers.
 */

// 355. Design Twitter
// https://leetcode.com/problems/design-twitter/
// NeetCode 150: https://neetcode.io/problems/design-twitter-feed/question?list=neetcode150
// Twitter is the design class. Self-follow is ignored to avoid duplicate feed entries; historical
// tweets are retained.
import java.util.*;

class Twitter {

    Map<Integer, List<Integer>> userIdToFollowerId;
    Map<Integer, List<Pair<Integer, Integer>>> userIdToTweet;
    int tweetNumber;

    public Twitter() {
        userIdToFollowerId = new HashMap<>();
        userIdToTweet = new HashMap<>();
        tweetNumber = 0;
    }

    public void postTweet(int userId, int tweetId) {
        tweetNumber += 1;

        Pair<Integer, Integer> tweet = new Pair<>(tweetId, tweetNumber);

        userIdToTweet.putIfAbsent(userId, new ArrayList<>());
        userIdToTweet.get(userId).add(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {

        // Max-heap: Most recent tweet at the top
        PriorityQueue<Pair<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.tweetNumber, a.tweetNumber));

        // Add user's own tweets
        for (Pair<Integer, Integer> tweet : userIdToTweet.getOrDefault(userId, Collections.emptyList())) {
            maxHeap.add(tweet);
        }

        // Add tweets from all users being followed
        for (int followeeId : userIdToFollowerId.getOrDefault(userId, Collections.emptyList())) {
            for (Pair<Integer, Integer> tweet : userIdToTweet.getOrDefault(followeeId, Collections.emptyList())) {
                maxHeap.add(tweet);
            }
        }

        // Extract 10 most recent tweets
        List<Integer> newsFeed = new ArrayList<>();

        while (!maxHeap.isEmpty() && newsFeed.size() < 10) {
            newsFeed.add(maxHeap.poll().tweetId);
        }

        return newsFeed;
    }

    public void follow(int followerId, int followeeId) {

        // Cannot follow yourself
        if (followerId == followeeId)
            return;

        userIdToFollowerId.putIfAbsent(followerId, new ArrayList<>());

        List<Integer> followees = userIdToFollowerId.get(followerId);

        // Avoid duplicate followees
        if (!followees.contains(followeeId))
            followees.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (userIdToFollowerId.containsKey(followerId)) {
            userIdToFollowerId.get(followerId)
                .remove(Integer.valueOf(followeeId));
        }
    }

    class Pair<K, V> {
        K tweetId;
        V tweetNumber;

        Pair(K tweetId, V tweetNumber) {
            this.tweetId = tweetId;
            this.tweetNumber = tweetNumber;
        }
    }
}