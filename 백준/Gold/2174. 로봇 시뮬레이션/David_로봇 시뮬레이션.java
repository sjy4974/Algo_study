import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static int A;
	public static int B;
	public static int N;
	public static int M;
	public static int[][] map;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Map<Integer, String[]> robot = new HashMap<>();
		map = new int[A][B];
		
		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().trim().split(" ");
			int a = Integer.parseInt(temp[0])-1;
			int b = Integer.parseInt(temp[1])-1;
			map[a][b] = 1;
			temp[0] = String.valueOf(a);
			temp[1] = String.valueOf(b);
			robot.put(i, temp);
		}
		String result = "OK";

		out: for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int robotIdx = Integer.parseInt(st.nextToken()) - 1;
			String[] temp = robot.get(robotIdx);
			String command = st.nextToken();
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				if (command.equals("L")) {
					if (temp[2].equals("N")) {
						temp[2] = "W";
					} else if (temp[2].equals("W")) {
						temp[2] = "S";
					} else if (temp[2].equals("S")) {
						temp[2] = "E";
					} else {
						temp[2] = "N";
					}
				} else if (command.equals("R")) {
					if (temp[2].equals("N")) {
						temp[2] = "E";
					} else if (temp[2].equals("E")) {
						temp[2] = "S";
					} else if (temp[2].equals("S")) {
						temp[2] = "W";
					} else {
						temp[2] = "N";
					}
				} else {
					int nx = 0;
					int ny = 0;
					if (temp[2].equals("N")) {
						nx = Integer.parseInt(temp[0]);
						ny = Integer.parseInt(temp[1])+1;
					} else if (temp[2].equals("W")) {
						nx = Integer.parseInt(temp[0])-1;
						ny = Integer.parseInt(temp[1]);
					} else if (temp[2].equals("S")) {
						nx = Integer.parseInt(temp[0]);
						ny = Integer.parseInt(temp[1])-1;
					} else {
						nx = Integer.parseInt(temp[0])+1;
						ny = Integer.parseInt(temp[1]);
					}

					if (nx < 0 || ny < 0 || nx >= A || ny >= B) {
						result = "Robot " + (robotIdx + 1) + " crashes into the wall";
						break out;
					}
					if (map[nx][ny] == 1) {
						for (int idx : robot.keySet()) {
							String[] r = robot.get(idx);
							int x = Integer.parseInt(r[0]);
							int y = Integer.parseInt(r[1]);
							if (nx == x && ny == y) {
								result = "Robot " + (robotIdx + 1) + " crashes into robot " + (idx+1);
								break out;
							}
						}
					}
					map[nx][ny] = 1;
					temp[0] = String.valueOf(nx);
					temp[1] = String.valueOf(ny);
					robot.put(robotIdx, temp);
				}
			}

		}
		System.out.println(result);
	}

}
