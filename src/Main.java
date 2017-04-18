
public class Main {

    public static void main(String[] args) {
        int numOfRuns = 10;
        long[] resultArray = new long[numOfRuns];
        long[] resultArray2 = new long[numOfRuns];
        int bestGen = 10000000;
        long worstGen = 0;
        int bestGen2 = 10000000;
        long worstGen2 = 0;
        Brain bestBrain = new Brain();
        for(int x = 0; x < numOfRuns; x++){
            TalkingTony attempt = new TalkingTony("hello world!");
            resultArray[x] = (long)attempt.getGen();
            if(attempt.getGen() < bestGen){
                bestGen = attempt.getGen();
                bestBrain = attempt.getTonysBrain();
            }
            if(attempt.getGen() > worstGen){
                worstGen = attempt.getGen();
            }
        }
        for(int x = 0; x < numOfRuns; x++){
            TalkingTony attempt = new TalkingTony("aaaaaaaaaaaa");
            resultArray2[x] = (long)attempt.getGen();
            if(attempt.getGen() < bestGen2){
                bestGen2 = attempt.getGen();
                bestBrain = attempt.getTonysBrain();
            }
            if(attempt.getGen() > worstGen2){
                worstGen2 = attempt.getGen();
            }
        }
        System.out.println("best attempt: " + bestGen + "\nbest attempt 2: " + bestGen2);
        System.out.println("worst attempt: " + worstGen + "\nworst attempt 2: " + worstGen2);
        bestBrain.printBrain();
        System.out.println(TeliText.graph(resultArray,resultArray2,worstGen,10));
    }
}
