
public class Problems {
////	A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. 
//	Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
////
////	The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
////
////	lefti is the x coordinate of the left edge of the ith building.
////	righti is the x coordinate of the right edge of the ith building.
////	heighti is the height of the ith building.
////	You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
////
////	The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the 
//	left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the 
//	skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
////
////	Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] 
//	is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
//https://leetcode.com/problems/the-skyline-problem/
	
	class Building{
		int left;
		int height;
		int right;
		public Building(int left, int height, int right) {
			super();
			this.left = left;
			this.height = height;
			this.right = right;
		}
	}
	
	class strip{
		int left,height;

		public strip(int left, int height) {
			super();
			this.left = left;
			this.height = height;
		}
	}
	
	class skyline{
		strip[] strips;
		int startLoc,count;
		public skyline(int n) {
			this.count=this.startLoc=0;
			this.strips=new strip[n];
		}
		
		void append(strip str) {
			strips[startLoc+count]=str;
			count++;
		}
		
		strip Head() {
			return strips[startLoc];
		}
		
		strip removeHead() {
			strip str=strips[startLoc];
			count--;startLoc++;
			return str;
		}
	}
	
	skyline getSkyline(Building[] buildings,int start,int end) {
		if(start==end) {
			skyline sk=new skyline(2);
			sk.append(new strip(buildings[start].left, buildings[start].height));
			sk.append(new strip(buildings[end].right, 0));
			return sk;
		}
		int mid=(start+end)/2;
		skyline sk1=getSkyline(buildings, start, mid);
		skyline sk2=getSkyline(buildings, mid+1, end);
		return mergeSkyline(sk1, sk2);
	}
	
	skyline mergeSkyline(skyline sk1,skyline sk2) {
		skyline newSk=new skyline(sk1.count+sk2.count);
		int curh1=0,curh2=0;
		while(sk1.count>0 && sk2.count>0) {
			if(sk1.Head().left<sk2.Head().left) {
				int curX=sk1.Head().left;
				curh1=sk1.Head().height;
				int maxH=curh1;
				if(curh2>maxH) maxH=curh2;
				newSk.append(new strip(curX, maxH));
				sk1.removeHead();
			}else {
				int curX=sk2.Head().left;
				curh2=sk2.Head().height;
				int maxH=curh2;
				if(curh1>maxH) maxH=curh1;
				newSk.append(new strip(curX, maxH));
				sk2.removeHead();
			}
		}
		while(sk1.count>0) {
			strip str=sk1.removeHead();
			newSk.append(str);
		}
		while(sk2.count>0) {
			strip str=sk2.removeHead();
			newSk.append(str);
		}
		return newSk;
	}
}

