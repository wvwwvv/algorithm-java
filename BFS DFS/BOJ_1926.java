import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class BOJ_1926 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    static int[][] board;
    static boolean[][] visited;
    static int n,m;
    static int result=0;
    static int num=0;
    
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<int[]> q = new ArrayDeque<>();
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (board[i][j] == 1 && visited[i][j] == false) {
                    q.add(new int[] {i,j});
                    visited[i][j] = true;
                    num++; // 그림의 개수

                    int area = 1;
                    while(!q.isEmpty()) {
                        int[] cur = q.poll();
                       

                        for (int dir=0; dir<4; dir++) {
                            int nx = cur[0] + dx[dir];
                            int ny = cur[1] + dy[dir];

                            if (nx<0 || ny<0 || nx>= n || ny>=m) continue;
                            if (board[nx][ny] == 0 || visited[nx][ny]== true) continue;

                            q.add(new int[] {nx,ny});
                            visited[nx][ny] = true;
                            area++;
                        }
                    }

                    result = Math.max(result, area);
                }
            }
        }
        System.out.println(num);
        System.out.println(result);

        
    }
}