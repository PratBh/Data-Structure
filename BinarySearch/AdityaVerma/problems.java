package adityaVerma;

public class Problems {

	//Given a sorted array arr[] of n elements, write a function to search a given element x in arr[].
	
	int binarySearch(int[] arr,int element,int start,int end) {
		int mid;
		while(start<=end) {
			mid=start+(end-start)/2;
			if(arr[mid]==element)
				return mid;
			if(arr[mid]>element)
				end=mid-1;
			else
				start=mid+1;
		}
		return -1;
	}

//	BINARY SEARCH ON REVERSE SORTED ARRAY:
	int binarySearchReverse(int[] arr,int element,int start,int end) {
		int mid;
		while(start<=end) {
			mid=start+(end-start)/2;
			if(arr[mid]==element)
				return mid;
			if(arr[mid]<element)
				end=mid-1;
			else
				start=mid+1;
		}
		return -1;
	}
	
//	Given a sorted array of numbers, find if a given number ‘key’ is present in the array. 
//	Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.
	
	int agnosticSearch(int [] arr,int element) {
		if(arr.length==1)
			if(arr[0]==element)
				return 0;
			else
				return -1;
		if(arr[0]>arr[1])
			return binarySearchReverse(arr, element,0,arr.length-1);
		else
			return binarySearch(arr, element,0,arr.length-1);
	}
	
//	FIND FIRST AND LAST POSITIONS OF AN ELEMENT IN A SORTED ARRAY:
//
//		Given a sorted array with possibly duplicate elements, the task is to find indexes of first and last occurrences of an element x in the given array.
//
//		Example:
//
//		Input : arr[] = {1, 3, 5, 5, 5, 5 ,67, 123, 125}    
//		        x = 5
//		Output : First Occurrence = 2
//		         Last Occurrence = 5
	
	void firstANdLastOccurance(int [] arr,int element){
		System.out.println("1st occurance: "+firstOccurance(arr, element));
		System.out.println("Last occurance: "+lastOccurance(arr, element));
	}
	
	int firstOccurance(int [] arr,int element) {
		int start=0,end=arr.length-1,res=-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(arr[mid]==element)
			{
				res=mid;
				end=mid-1;
			}
			else if(arr[mid]>element)
				end=mid-1;
			else
				start=mid+1;
		}
		return res;
	}
	
	int lastOccurance(int [] arr,int element) {
		int start=0,end=arr.length-1,res=-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(arr[mid]==element)
			{
				res=mid;
				start=mid+1;
			}
			else if(arr[mid]>element)
				end=mid-1;
			else
				start=mid+1;
		}
		return res;
	}
	
//	COUNT NUMBER OF OCURRENCES(or frequency) IN A SORTED ARRAY:
//
//		Given a sorted array arr[] and a number x, write a function that counts the occurrences of x in arr[]. Expected time complexity is O(Logn)
//		Examples:
//
//		  Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 2
//		  Output: 4 // x (or 2) occurs 4 times in arr[]
//
//		  Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 3
//		  Output: 1 
	
	int countOccurance(int [] arr,int element) {
		int count =0;
		if(lastOccurance(arr, element)!=-1 && firstOccurance(arr, element)!=-1)
			count=lastOccurance(arr, element)-firstOccurance(arr, element)+1;
		System.out.println(count);
		return count;
	}
	
//	Find the Rotation Count in Rotated Sorted array
//	Consider an array of distinct numbers sorted in increasing order. The array has been rotated (clockwise) k number of times. Given such an array, find the value of k.
//
//	Examples:
//
//	Input : arr[] = {15, 18, 2, 3, 6, 12}
//	Output: 2
//	Explanation : Initial array must be {2, 3,
//	6, 12, 15, 18}. We get the given array after 
//	rotating the initial array twice.
//
//	Input : arr[] = {7, 9, 11, 12, 5}
//	Output: 4
//
//	Input: arr[] = {7, 9, 11, 12, 15};
//	Output: 0
	
	int numberOfRotation(int [] arr) {
		int min=0,start=0,end=arr.length-1;
		while(start<=end) {
			if(arr[start]<=arr[end])
 				return start;
			int mid=start+(end-start)/2;
			int next=(mid+1)%arr.length;
			int prev=(mid+arr.length-1)%arr.length;
			if(arr[mid]<arr[prev]&& arr[mid]<arr[next]) {
				min=mid;//index of the minimum element;
				break;
			}
			else if(arr[start]<=arr[mid])
				start=mid+1;
			else if (arr[end]>=arr[mid])
				end=mid-1;
		}
		System.out.println(min);
		return min;
	}
//	Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
//
//		[4,5,6,7,0,1,2] if it was rotated 4 times.
//		[0,1,2,4,5,6,7] if it was rotated 7 times.
//		Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
//
//		Given the sorted rotated array nums, return the minimum element of this array.
//
//		 
//
//		Example 1:
//
//		Input: nums = [3,4,5,1,2]
//		Output: 1
//		Explanation: The original array was [1,2,3,4,5] rotated 3 times.
//		Example 2:
//
//		Input: nums = [4,5,6,7,0,1,2]
//		Output: 0
//		Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
//		Example 3:
//
//		Input: nums = [11,13,15,17]
//		Output: 11
//		Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
//		 
//
//		Constraints:
//
//		n == nums.length
//		1 <= n <= 5000
//		-5000 <= nums[i] <= 5000
//		All the integers of nums are unique.
//		nums is sorted and rotated between 1 and n times.
	//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/solution/
	
	public int findMinAfterRotation(int[] nums) {
        if(nums.length==1)
        	return nums[0];
        int start=0,end=nums.length;
        if(nums[start]<nums[end])
        	return nums[start];//here there is no rotation
        
        while(start<=end) {
        	int mid=start+(end-start)/2;
        	if(nums[mid]>nums[mid+1])//here the mid+1 is the inflation point
        		return nums[mid+1];
        	
        	if(nums[mid-1]>nums[mid])// if the mid element is lesser than its previous element then mid element is the smallest
        		return nums[mid];
        	
        	 // if the mid elements value is greater than the 0th element this means
            // the least value is still somewhere to the right as we are still dealing with elements
            // greater than nums[0]
        	if(nums[mid]>nums[0])
        		start=mid+1;
        	else
        		end=mid-1;
        }
        return -1;
    }
	
//	FIND AN ELEMENT IN A ROTATED SORTED ARRAY:
//
//		Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//		(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
//
//		You are given a target value to search. If found in the array return its index, otherwise return -1.
//
//		You may assume no duplicate exists in the array.
//
//		Your algorithm's runtime complexity must be in the order of O(log n).
//
//		Example:
//
//		Input: nums = [4,5,6,7,0,1,2], target = 0
//		Output: 4
	
	int findElementInRotatedArr(int[] arr,int element) {
		int inflationIndex=numberOfRotation(arr);
		if(arr[inflationIndex]==element)
			return inflationIndex;
		int right=binarySearch(arr, element, 0, inflationIndex-1);
		int left=binarySearch(arr, element, inflationIndex+1, arr.length-1);
		if(right==-1&&left==-1)
			return -1;//the element is not present
		
		if(right==-1)
			return left;
		return right;
	}
	
//	SEARCH IN A NEARLY SORTED ARRAY:
//
//		Given an array which is sorted, but after sorting some elements are moved to either of the adjacent positions, 
//	i.e., arr[i] may be present at arr[i+1] or arr[i-1]. Write an efficient function to search an element in this array. 
//	Basically the element arr[i] can only be swapped with either arr[i+1] or arr[i-1].
//
//		For example consider the array {2, 3, 10, 4, 40}, 4 is moved to next position and 10 is moved to previous position.
//
//		Example :
//		Input: arr[] =  {10, 3, 40, 20, 50, 80, 70}, key = 40
//		Output: 2 
//		Output is index of 40 in given array
	
	int bsNearlySortedArr(int[] arr,int element) {
		int start=0,end=arr.length-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(arr[mid]==element)
				return mid;
			if(mid-1>=0 && arr[mid-1]==element)
				return mid-1;
			if(mid+1<=arr.length-1 && arr[mid+1]==element)
				return mid+1;
			if(arr[mid]>element)
				end=mid-2;
			else
				start=mid+2;
		}
		return -1;
	}
	
//	FIND FLOOR OF AN ELEMENT IN A SORTED ARRAY:
//
//		Given a sorted array and a value x, the floor of x is the largest element in array smaller than or equal to x.
//		Write efficient functions to find floor of x.
//
//		Example:
//
//		Input : arr[] = {1, 2, 8, 10, 10, 12, 19}, x = 5
//		Output : 2
//		2 is the largest element in arr[] smaller than 5.
	
	int findFloor(int [] arr,int elemnt) {
		int start=0,end=arr.length-1,res=-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(arr[mid]==elemnt) {
				res=mid;
				break;
			}
			if(arr[mid]>elemnt)
				end=mid-1;
			else if(arr[mid]<elemnt) {
				res=mid;
				start=mid+1;
			}
		}
		return arr[res];
	}
	
//	CEILING OF AN ELEMENT IN A SORTED ARRAY:
//
//		Given a sorted array and a value x, the ceiling of x is the smallest element in array greater than or equal to x,
//		and the floor is the greatest element smaller than or equal to x. Assume than the array is sorted in non-decreasing order. 
//		Write efficient functions to find floor and ceiling of x.
//
//		Examples :
//
//		For example, let the input array be {1, 2, 8, 10, 10, 12, 19}
//		For x = 0:    floor doesn't exist in array,  ceil  = 1
//		For x = 1:    floor  = 1,  ceil  = 1
//		For x = 5:    floor  = 2,  ceil  = 8
//		For x = 20:   floor  = 19,  ceil doesn't exist in array
	
	int findCeil(int [] arr,int element) {
		int start=0,end=arr.length-1,res=-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(arr[mid]==element) {
				res=mid;
				break;
			}
			if(arr[mid]>element) {
				res=mid;
				end=mid-1;
			}
			else if(arr[mid]<element)
				start=mid+1;
		}
		return arr[res];
	}
	
	//Given an array of letters sorted in ascending order, find the smallest letter in the the array which is greater than a given key letter.
	
	char findNextAlp(char[] arr,char key) {
		int start=0,end=arr.length-1;
		char res='#';
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(arr[mid]<=key)
				start=mid+1;
			else
			{
				res=arr[mid];
				end=mid-1;
			}
		}
		return res;
	}
	
	//Suppose you have a sorted array of infinite numbers, how would you search an element in the array
	
	int findElementInfinteArr(int[]arr,int key) {
		int start=0,end=1;
		while(arr[end]<key) {
			start=end;
			end=end*2;
		}
		
		return binarySearch(arr, key, start, end);
	}
	
//	Given an infinite sorted array consisting 0s and 1s. The problem is to find the index of first ‘1’ in that array.
//	As the array is infinite, therefore it is guaranteed that number ‘1’ will be present in the array.
//
//	Example:
//
//	Input : arr[] = {0, 0, 1, 1, 1, 1} 
//	Output : 2
	
	int firstOccuranceInfinite(int[] arr) {
		int start=0,end=1,res=-1;
		while(arr[end]<1) {
			start=end;
			end=end*2;
		}
		while(start<=end) {
			int mid=start+(end-start)/2; 
			if(arr[mid]==1) {
				res=mid;
				end=mid-1;
			}
			else if(arr[mid]<1)
				start=mid+1;
		}
		return res;
	}
	
	//Given a sorted array, find the element in the array which has minimum difference with the given number.
	int findElementMinDiff(int [] arr,int key) {
		int start=0,end=arr.length-1;
		while(start<=end){
			int mid=start+(end-start)/2; 
			if(arr[mid]==key)
				return arr[mid];
			if(arr[mid]<key)
				start=mid+1;
			else
				end=mid-1;
		}
		if(Math.abs(key-arr[start])<Math.abs(key-arr[end]))
			return arr[start];
		else
			return arr[end];
	}
	
//	FIND PEAK ELEMENT IN AN ARRAY:
//
//		A peak element is an element that is greater than its neighbors.
//
//		Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
//
//		The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
//		You may imagine that nums[-1] = nums[n] = -∞.
//
//		Example :
//
//		Input: nums = [1,2,3,1]
//		Output: 2
//		Explanation: 3 is a peak element and your function should return the index number 2.
	
	int peakElementIndex(int[]arr) {
		int start=0,end=arr.length-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(mid>0 && mid<arr.length-1) {
				if(arr[mid]>arr[mid-1]&& arr[mid]>arr[mid+1])
					return mid;
				if(arr[mid]<arr[mid+1])
					start=mid+1;
				else if(arr[mid-1]>arr[mid])
					end=mid-1;
			}
			else if(mid==0) {
				if(arr[mid]>arr[mid+1])
					return mid;
				else
					start=mid+1;
			}
			else if(mid==arr.length-1) {
				if(arr[mid]>arr[mid-1])
					return mid;
				else
					end=mid-1;
			}
		}
		return -1;
	}
	
//	FIND MAXIMUM ELEMENT IN AN BITONIC ARRAY:
//
//		Problem statement:
//		Given a bitonic array find the maximum value of the array. An array is said to be bitonic if it has an increasing sequence of integers 
//		followed immediately by a decreasing sequence of integers.
//
//		Example:
//
//		Input:
//		1 4 8 3 2
//
//		Output:
//		8
	
	int findMaxBitonic(int [] arr) {
		int res=peakElementIndex(arr);
		if(res!=-1)
			return arr[res];
		return 0;
	}
	
//	FIND AN ELEMENT IN BITONIC ARRAY:
//
//		Given a bitonic sequence of n distinct elements, write a program to find a given element x in the bitonic sequence in O(log n) time. 
//		A Bitonic Sequence is a sequence of numbers which is first strictly increasing then after a point strictly decreasing.
//
//		Examples:
//
//		Input :  arr[] = {-3, 9, 8, 20, 17, 5, 1};
//		         key = 20
//		Output : Found at index 3
	
	int findElementBitonic(int [] arr,int element) {
		int inflation=peakElementIndex(arr);
		int max=arr[inflation];
		if(max==element)
			return inflation;
		if(element>max||element<arr[0] && element<arr[arr.length-1])
			return -1;
		int left=binarySearch(arr, element, 0, inflation),right=0;
		if(left==-1) {
			right=binarySearchReverse(arr, element, inflation+1, arr.length-1);
			return right;
		}
		else			
			return left;
			
	}
	
//	SEARCH IN A ROW WISE AND COLUMN WISE SORTED MATRIX:
//
//		Given an n x n matrix and a number x, find the position of x in the matrix if it is present in it. 
//		Otherwise, print “Not Found”. In the given matrix, every row and column is sorted in increasing order. 
//		The designed algorithm should have linear time complexity.
//		Example :
//
//		Input : mat[4][4] = { {10, 20, 30, 40},
//		                      {15, 25, 35, 45},
//		                      {27, 29, 37, 48},
//		                      {32, 33, 39, 50}};
//		              x = 29
//		Output : Found at (2, 1)
	
	int[] findEle2dArr(int [][] mat,int element) {
		int [] res= {-1,-1};
		int m=mat.length,n=mat[0].length,i=0,j=n-1;
		while(i>=0 && i<m && j<=0 && j<n) {
			if(mat[i][j]==element) {
				res[0]=i;
				res[1]=j;
				return res;
			}
			if(mat[i][j]>element)
				j--;
			else
				i++;
		}
		return res;
	}
//	ALLOCATE MINIMUM NUMBER OF PAGES:
//
//		Given number of pages in n different books and m students. The books are arranged in ascending order of number of pages.
//		Every student is assigned to read some consecutive books. 
//		The task is to assign books in such a way that the maximum number of pages assigned to a student is minimum.
//
//		Example :
//
//		Input : pages[] = {12, 34, 67, 90}
//		        m = 2
//		Output : 113
//		Explanation:
//		There are 2 number of students. Books can be distributed 
//		in following fashion : 
//		  1) [12] and [34, 67, 90]
//		      Max number of pages is allocated to student 
//		      2 with 34 + 67 + 90 = 191 pages
//		  2) [12, 34] and [67, 90]
//		      Max number of pages is allocated to student
//		      2 with 67 + 90 = 157 pages 
//		  3) [12, 34, 67] and [90]
//		      Max number of pages is allocated to student 
//		      1 with 12 + 34 + 67 = 113 pages
//
//		Of the 3 cases, Option 3 has the minimum pages = 113. 
	
	int allocatePages(int [] arr,int students) {
		int max=Integer.MIN_VALUE,sum=0,n=arr.length,res=-1;
		if(n<students)
			return res;
		for(int i=0;i<n;i++) {
			max=Math.max(max, arr[i]);
			sum+=arr[i];
		}
		
		int start=max,end=sum;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(isValid(arr, students, n, mid)) {
				res=mid;
				end=mid-1;
			}else
				start=mid+1;
		}
		return res;
	}
	
	boolean isValid(int [] arr,int students,int n,int maxPage) {
		int student=1,sum=0;
		for(int i=0;i<n;i++) {
			sum+=arr[i];
			if(sum>maxPage)
			{
				sum=arr[i];
				student++;
			}
			if(student>students)
				return false;
		}
		return true;
	}
}

