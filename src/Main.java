
public class Main {

    public static void main(String[] args) {
        Brain goodBrain;
        int bestGen = 10000000;
        for(int x = 0; x < 1; x++){
            TalkingTony attempt = new TalkingTony("I think therefore I am");
            if(attempt.getGen() < bestGen){
                bestGen = attempt.getGen();
            }
        }
        System.out.println("best attempt: " + bestGen);
    }
}
