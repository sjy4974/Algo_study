import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
       long answer = Long.MAX_VALUE;
        Arrays.sort(times);
        long start = times[0];
        long end = (long)times[times.length-1] * (long)n;
        long mid = 0;
        long sum = 0;
        while(start<=end) {
            mid = (start+end) / 2;
            sum = 0;
            for(int time : times) {
                sum += mid/time;
            }
            if(sum >= n) {
                answer = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return answer;
    }
    /*
    이분탐색의 범위.. 가장 적게 걸리는 시간 ~ 가장 오래걸리는 시간
    가장 적게 걸리는시간 = times[0]
    가장 오래걸리는시간 = 정렬된 times 배열의 마지막 원소 * n (인원수)
    mid = 주어진 시간

    주어진 시간 (mid) 동안 심사한 사람의 수가
    심사 받아야 할 사람의 수인 n보다 같거나 큰 경우에는
    시간이 충분했으므로 주어진 시간을 줄임 (mid를 기준으로 왼쪽을 탐색).
    end를 다시 설정하고 (end = mid-1) 탐색 재시작

    만약 주어진 시간 (mid) 동안 심사한 사람의 수가
    심사 받아야 할 사람의 수인 n보다 작은 경우
    주어진 시간동안에 모든 사람을 심사할 수 없었던 경우이므로
    mid를 기준으로 오른쪽에서 다시 탐색
    start를 다시 설정 ( start = mid+1) 하고 탐색 재시작
    */
}