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
    private static class Tweet {
        int id;
        long time;
        Tweet next;

        Tweet(int id, long time, Tweet next) {
            this.id = id;
            this.time = time;
            this.next = next;
        }
    }

    private long clock = 0;
    private final Map<Integer, Tweet> tweets = new HashMap<>();
    private final Map<Integer, Set<Integer>> follows = new HashMap<>();

    public Twitter() {}

    public void postTweet(int userId, int tweetId) {
        tweets.put(userId, new Tweet(tweetId, clock++, tweets.get(userId)));
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> heap = new PriorityQueue<>((a, b) -> Long.compare(b.time, a.time));
        if (tweets.containsKey(userId)) {
            heap.offer(tweets.get(userId));
        }
        for (int id : follows.getOrDefault(userId, Collections.emptySet())) {
            if (tweets.containsKey(id)) {
                heap.offer(tweets.get(id));
            }
        }
        List<Integer> out = new ArrayList<>();
        while (out.size() < 10 && !heap.isEmpty()) {
            Tweet t = heap.poll();
            out.add(t.id);
            if (t.next != null) {
                heap.offer(t.next);
            }
        }
        return out;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            follows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = follows.get(followerId);
        if (set != null) {
            set.remove(followeeId);
        }
    }
}
