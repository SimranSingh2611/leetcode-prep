// 355. Design Twitter
// https://leetcode.com/problems/design-twitter/
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
