import java.util.stream.IntStream;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int sum = IntStream.of(piles).sum();
        double avg = (double)sum/(double)h;
        int answer = (int)avg;
        if (avg % 1.0 != 0.0){
            answer = (int)avg + 1;
        }
        return answer;
    }
}