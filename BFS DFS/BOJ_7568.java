import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[] dx={1,0,-1,0,0,0};
    static int[] dy={0,1,0,-1,0,0};
    static int[] dz={0,0,0,0,1,-1};
    static int[][][] board;
    static int[][][] dist;
    static int H,N,M; // 높이, 행, 열 (높이, 세로, 가로)

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][N][M];
        dist = new int[H][N][M];

        int rawTomato = 0;

        Deque<int[]> q = new ArrayDeque<>();
        
        for (int h=0; h<H; h++) {
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<M; j++) {
                    dist[h][i][j] = -1; // 기본 dist 값 -1 초기화
                    board[h][i][j] = Integer.parseInt(st.nextToken());
                    if (board[h][i][j] == 0) rawTomato++;

                    if (board[h][i][j] == 1) { // 익은 토마토
                        q.add(new int[] {h,i,j});
                        dist[h][i][j] = 0;
                    }

                    if (board[h][i][j] == -1) { // 빈 곳
                        dist[h][i][j] = 0; // dist -1 은 아직 안익은 토마토 -> 마지막에 안익은 토마토 있나 확인하려고
                    }
                }
            }
        }

        // 저장될 때부터 모든 토마토 익으면
        if (rawTomato == 0) {
            System.out.println(0);
            return;
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for (int dir=0; dir<6; dir++) {
                int nx = cur[1] + dx[dir];
                int ny = cur[2] + dy[dir];
                int nz = cur[0] + dz[dir];

                if (nx<0 || ny<0 || nz<0 || nx>=N || ny>=M || nz>=H) continue;
                if (board[nz][nx][ny] != 0) continue;
                if (dist[nz][nx][ny] >= 0) continue;

                q.add(new int[] {nz,nx,ny});
                dist[nz][nx][ny] = dist[cur[0]][cur[1]][cur[2]]+1;
            }
        }

        // bfs 끝

        int result = 0;
        
        for (int h=0; h<H; h++) {
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    result = Math.max(result, dist[h][i][j]);
                    if (dist[h][i][j] == -1) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(result);

    }
    
}