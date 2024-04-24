package Heap;

public class Test {

	public static void main(String[] args) {
		int [][] mat = {{1,2,3},{4,5,6},{7,8,9}};
		mat = Test.transform(mat);
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat[0].length;j++) {
				System.out.print(mat[i][j]);
			}
			System.out.println();
		}
	}
	
	public static int[][] transform(int[][] mat){
		int m=mat.length,n=mat[0].length;
		boolean [][] visited = new boolean[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j]) {
					if(i==j) {
						visited[i][j]=true;
					}else {
						int temp = mat[i][j];
						mat[i][j]=mat[j][i];
						mat[j][i]=temp;
						visited[i][j]=true;
						visited[j][i]=true;
					}
				}
			}
		}
		return mat;
	}

}
