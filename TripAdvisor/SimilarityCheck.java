import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Union Find - Collect synomous words all into same group, support quick lookup
 * */
class UnionFind {
    int[] parent;
    public UnionFind(int n){
        parent = new int[n];
        for(int i = 0; i < n;i++){
            parent[i] = i;
        }
    }
    public int find(int x){
        while(this.parent[x] != x){
            x = this.parent[x];
        }
        return x;
    }
    public void union(int x,int y){
        this.parent[find(x)] = find(y);
    }
}

/**
 * Class to initialize the whole union find map and do actual union on the similar word group.
 * return
 */
public class SimilarityCheck {

    /**
     * Method to set union find, and allocate the words to its mapping parents
     * @param words from the the tuple currently parsing.
     * @param group from the same word group
     * @return word after mapping to parent.
     * ex. [I love dog] -> [I love animal] if dog and animal are in the same group, and animal is the parent of whole group
     *
     */

    public List<String> checkSentencesSimilar(String[] words, String[][] group) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        int index = 0;
        for(int i = 0; i < group.length;i++){
            for (int j = 0; j < group[i].length; j++) {
                if(!map.containsKey(group[i][j])) {
                    map.put(group[i][j], index++);
                }
            }
        }

        UnionFind wordgroup = new UnionFind(map.size());

        for (int i = 0; i < group.length; i++) {
            for (int j = 0; j < group[i].length - 1; j++) {
                int firstSet = map.get(group[i][j]);
                int secondSet = map.get(group[i][j + 1]);
                wordgroup.union(firstSet, secondSet);
            }
        }

        List<String> parentResult = new LinkedList<>();
        for(int i = 0; i < words.length; i++){
            if (map.containsKey(words[i])) {
                parentResult.add(String.valueOf(wordgroup.find(map.get(words[i]))));
            } else {
                parentResult.add(words[i]);
            }
        }
        return parentResult;
    }
}
