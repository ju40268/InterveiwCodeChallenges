class product {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        // 1 1 2 6
        int right = 1;
        for(int i = nums.length-1; i >= 0; i--) {
            res[i] = res[i]*right;
            right *= nums[i];
            // 1 4 12 12
        }
        return res;
    }
}