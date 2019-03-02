import java.util.*;
public class findAncestor {
    static boolean shareAncestor(String[] idPair, String[][] dataset) {
      if(idPair == null || dataset == null){
        return false;
      }
      HashMap<String, List<String>> relations = new HashMap<String, List<String>>();
      int rows = dataset.length;
      for(int i = 0; i < rows; i++){
        if(relations.containsKey(dataset[i][1])){
          relations.get(dataset[i][1]).add(dataset[i][0]);
          continue;
        }
        List<String> parents = new ArrayList<String>();
        parents.add(dataset[i][0]);
        relations.put(dataset[i][1], parents);
      }
      if(!relations.containsKey(idPair[0]) || !relations.containsKey(idPair[1])){
        return false;
      }
      List<String> parentA = new ArrayList<String>();
      List<String> parentB = new ArrayList<String>();
  
      findParent(idPair[0], parentA, relations);
      findParent(idPair[1], parentB, relations);

      for(String eleA : parentA){
        if(parentB.contains(eleA)){
          return true;
        }
      }
      return false;
    }
  
    static void findParent(String str, List<String> parent, HashMap<String, List<String>> relations){
      if(!relations.containsKey(str)){
        return;
      }
      for(String element : relations.get(str)){
        parent.add(element);
        findParent(element, parent, relations);
      }
  
    }
    public static void main(String[] args) {
      String[] pair = new String[]{"3", "8"};
      String[][] dataset = new String[][]{{"1", "3"}, {"2" , "3"}, {"3", "6"}, {"5", "7"}, {"4", "5"}, {"4", "8"}, {"8", "9"}};
      findAncestor test = new findAncestor();
      boolean result =  shareAncestor(pair, dataset);
      System.out.println(result);
    }
  }
  