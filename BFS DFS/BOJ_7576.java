import java.io.*;
import java.util.*;

public class BOJ_7576 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int [][] board;
    static int [][] dist;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};
    static int M,N;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int [N][M];
        dist = new int [N][M];

        Deque<int[]> q = new ArrayDeque<>();
        int rawTomato = 0;

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                dist[i][j] = -1; // 기본 -1 초기화
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) { // 익은 토마토
                    dist[i][j] = 0;
                    q.add(new int[] {i, j}); // 큐에 추가
                }
                if (board[i][j] == -1) {
                    dist[i][j] = 0; // 빈 벽은 앞으로 접근 안할 것임
                }
                if (board[i][j] == 0) {
                    rawTomato++;
                }
            }
        }

        // 원래부터 토마토가 전부 익어있으면 0 출력
        if (rawTomato == 0) {
            System.out.println(0);
            return;
        }


        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for (int dir=0; dir<4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if (nx<0 || ny<0 || nx>= N || ny>= M) continue; // boundary check
                if (board[nx][ny] != 0) continue; // 익지 않은 토마토에만 이동 가능
                if (dist[nx][ny] >= 0 ) continue; // dist 가 0 이상이면 이미 방문했거나, 빈벽

                q.add(new int[] {nx,ny});
                dist[nx][ny] = dist[cur[0]][cur[1]]+1;
            }
        }

        //bfs 끝

        int result = 0;

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                result = Math.max(result, dist[i][j]);
                if (dist[i][j] == -1) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(result);
    }
}