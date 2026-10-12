/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd encode each string as its length, a separator, and its payload.
 * - The decoder consumes exactly that length, so empty strings and separators inside the
 *   payload stay unambiguous.
 * Time complexity: O(C) for either operation, where C is the encoded character count.
 * Space complexity: O(C) output/buffer storage for either operation.
 */

import java.util.ArrayList;
import java.util.List;

// LeetCode's required class. Length-prefix framing handles empty strings and
// arbitrary separator characters in O(C) encode/decode time.
class Codec {
    private static final char SEPARATOR = '#';

    public String encode(List<String> strings) {
        StringBuilder encoded = new StringBuilder();
        for (String value : strings) {
            encoded.append(value.length())
                    .append(SEPARATOR)
                    .append(value);
        }
        return encoded.toString();
    }

    public List<String> decode(String encoded) {
        List<String> result = new ArrayList<>();
        int cursor = 0;

        while (cursor < encoded.length()) {
            int separatorIndex = cursor;
            while (encoded.charAt(separatorIndex) != SEPARATOR) {
                separatorIndex++;
            }

            int length = Integer.parseInt(encoded.substring(cursor, separatorIndex));
            int valueStart = separatorIndex + 1;
            int valueEnd = valueStart + length;
            result.add(encoded.substring(valueStart, valueEnd));
            cursor = valueEnd;
        }
        return result;
    }
}

// Follow-up: escape '/' as '//' and terminate each string with '/:'. This also
// runs in O(C), but the length-prefix format is usually simpler to validate.
class EscapingCodec {
    public String encode(List<String> strings) {
        StringBuilder encoded = new StringBuilder();
        for (String value : strings) {
            for (int i = 0; i < value.length(); i++) {
                char character = value.charAt(i);
                if (character == '/') {
                    encoded.append('/');
                }
                encoded.append(character);
            }
            encoded.append("/:");
        }
        return encoded.toString();
    }

    public List<String> decode(String encoded) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < encoded.length(); i++) {
            char character = encoded.charAt(i);
            if (character != '/') {
                current.append(character);
                continue;
            }

            char escapedOrTerminator = encoded.charAt(++i);
            if (escapedOrTerminator == '/') {
                current.append('/');
            } else {
                result.add(current.toString());
                current.setLength(0);
            }
        }
        return result;
    }
}
