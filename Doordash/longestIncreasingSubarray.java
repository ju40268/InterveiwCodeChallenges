import java.util.*; 
  
class longestIncreasingSubarray { 
      
    // function to find the length of longest 
    // increasing contiguous subarray 
    public static int lenOfLongIncSubArr(int arr[], 
                                            int n) 
    { 
        // 'max' to store the length of longest 
        // increasing subarray 
        // 'len' to store the lengths of longest 
        // increasing subarray at diiferent  
        // instants of time 
        int max = 1, len = 1; 
           
        // traverse the array from the 2nd element 
        for (int i=1; i<n; i++) 
        { 
            // if current element if greater than 
            // previous element, then this element  
            // helps in building up the previous  
            // increasing subarray encountered 
            // so far 
            if (arr[i] > arr[i-1]) 
                len++; 
            else
            { 
                // check if 'max' length is less  
                // than the length of the current  
                // increasing subarray. If true, 
                // than update 'max' 
                if (max < len)     
                    max = len; 
                       
                // reset 'len' to 1 as from this  
                // element again the length of the  
                // new increasing subarray is being  
                // calculated     
                len = 1;     
            }     
        } 
           
        // comparing the length of the last 
        // increasing subarray with 'max' 
        if (max < len) 
            max = len; 
           
        // required maximum length 
        return max; 
    } 

    public static void printLogestIncSubArr(int arr[],  int n) { 
        // 'max' to store the length of longest 
        // increasing subarray 
        // 'len' to store the lengths of longest 
        // increasing subarray at diiferent  
        // instants of time 
        int max = 1, len = 1, maxIndex = 0; 
           
        // traverse the array from the 2nd element 
        for (int i = 1; i < n; i++) 
        { 
            // if current element if greater than  
            // previous element, then this element  
            // helps in building up the previous  
            // increasing subarray encountered 
            // so far 
            if (arr[i] > arr[i-1]) 
                len++; 
            else
            { 
                // check if 'max' length is less 
                // than the length of the current 
                // increasing subarray. If true,  
                // then update 'max' 
                if (max < len)     
                { 
                    max = len; 
                       
                    // index assign the starting  
                    // index of longest increasing 
                    // contiguous subarray.    
                    maxIndex = i - max; 
                } 
                       
                // reset 'len' to 1 as from this 
                // element again the length of the 
                // new increasing subarray is  
                // being calculated     
                len = 1;     
            }     
        } 
           
        // comparing the length of the last 
        // increasing subarray with 'max' 
        if (max < len) 
        {  
            max = len; 
            maxIndex = n - max; 
        } 
       
        // Print the elements of longest  
        // increasing contiguous subarray.  
        for (int i = maxIndex; i < max+maxIndex; i++) 
            System.out.print(arr[i] + " "); 
    } 
      
    /* Driver program to test above function */
    public static void main(String[] args)  
    { 
         int arr[] = {5, 6, 3, 5, 7, 8, 9, 1, 2}; 
            int n = arr.length; 
            System.out.println("Length = " + 
                      lenOfLongIncSubArr(arr, n)); 
            printLogestIncSubArr(arr, n); 
          
        } 
    } 
    