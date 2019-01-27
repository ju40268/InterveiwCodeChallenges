class atoi {
    public int myAtoi(String str) {
        int index = 0, sign = 1, res = 0;
        // 1. Empty string
        if(str.length() == 0)
            return 0;
        // 2. Remove leading spaces
        while(index < str.length() && str.charAt(index) == ' ')
            index++;
        // 3. Handle sign
        if(index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = (str.charAt(index) == '+' ? 1:-1);
            index++;
        }

        // 4. Convert number
        res = 0;
        while(index < str.length()) {
            int digit = str.charAt(index)-'0';
            if(digit < 0 || digit > 9)
                break;
            try{
            	Math.multiplyExact(res, 10);
            	res *= 10;
            	Math.addExact(res, sign*digit);
            	res += sign*digit;
            } catch(Exception ex){// ArithmeticException e
            	System.err.println("overflow");
            	if(sign == 1)
            		return Integer.MAX_VALUE;
            	else 
            		return Integer.MIN_VALUE;
            }
            index++;
        }
        return res;
    }
}