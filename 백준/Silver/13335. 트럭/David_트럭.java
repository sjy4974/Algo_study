import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Truck {
    int weight;
    int time;

    Truck(int weight, int time) {
        this.weight = weight;
        this.time = time;
    }
}
public class Main {
    public static int N, W, L;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        Queue<Integer> trucks = new LinkedList<>();
        Queue<Truck> bridge = new LinkedList<>();
        int time = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            trucks.offer(Integer.parseInt(st.nextToken()));
        }

        bridge.offer(new Truck(trucks.poll(), W));

        while (true) {
            time++;
            int sum = 0;
            for(Truck t : bridge) {
                sum += t.weight;
                t.time--;
            }
//            if(time == 6) {
//                System.out.println(bridge.size());
//                System.out.println(bridge.peek().weight);
//                System.out.println(bridge.peek().time);
//            }
            if(!bridge.isEmpty() && bridge.peek().time == 0){
                sum -= bridge.peek().weight;
//                if(time == 6) {
//                    System.out.println("ㅇㅇㅇㅇㅇ");
//                }
                bridge.poll();
            }
//            if(time == 6) {
//                System.out.println("dd"+sum);
//            }
            if(!trucks.isEmpty()){
                if(sum + trucks.peek() <= L){
                    bridge.offer(new Truck(trucks.poll(), W));
                }
            }

            if(bridge.isEmpty() && trucks.isEmpty()) break;

        }
        System.out.println(time+1);

    }
}