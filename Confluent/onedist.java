class onedist {
    public boolean isOneEditDistance(String s, String t) {
        int count = 0, index1 = 0, index2 = 0;
        while(index1 < s.length() && index2 < t.length()) {
            if(s.charAt(index1) != t.charAt(index2)) {
                if(count == 1) return false;
                if(s.length() < t.length()) {// insert a char in S
                    index2++;
                } else if(s.length() > t.length()) { // delete a char in S
                    index1++;
                } else {
                    index1++;
                    index2++;
                }
                count++;
            } else {
                index1++;
                index2++;
            }
        }
        if(count == 1 && index1 == s.length() && index2 == t.length())
            return true;
        if(count == 0 && (s.length()-index1 == 1 || t.length()-index2 == 1))
            return true;
        return false;
    }
}