public class URL {
    HashMap<String, String> short2long = new HashMap();
    HashMap<String, String> long2short = new HashMap();
    String baseHost = "http://tinyurl.com/";
    String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random rand = new Random();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key;
        do {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 6; i++) {
                sb.append(charSet.charAt(rand.nextInt(charSet.length())));
            }
            key = sb.toString();
        } while(short2long.containsKey(key));
        short2long.put(key, longUrl);
        long2short.put(longUrl, key);
        return baseHost+key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return short2long.get(shortUrl.replace(baseHost, ""));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

// 只用数字的问题
// If I'm asked to encode the same long URL several times, it will get several entries. That wastes codes and memory. 
// People can find out how many URLs have already been encoded. Not sure I want them to know.
// People might try to get special numbers by spamming me with repeated requests shortly before their desired number comes up.
// Only using digits means the codes can grow unnecessarily large. Only offers a million codes with length 6 (or smaller). Using six digits or lower or upper case letters would offer (10+26*2)6 = 56,800,235,584 codes with length 6.
// public class Codec {
//     List<String> res = new ArrayList<String>();
//     // Encodes a URL to a shortened URL.
//     public String encode(String longUrl) {
//         res.add(longUrl);
//         return "" + (res.size() - 1);
//     }

//     // Decodes a shortened URL to its original URL.
//     public String decode(String shortUrl) {
//         return res.get(Integer.parseInt(shortUrl));
//     }
// // }

// http://www.cnblogs.com/grandyang/p/7675140.html
// https://www.educative.io/collection/page/5668639101419520/5649050225344512/5668600916475904