
public class Main {

    public static void main(String[] args) {
        int bestGen = 10000000;
        for(int x = 0; x < 100; x++){
            TalkingTony attempt = new TalkingTony("abcabcabc");
            if(attempt.getGen() < bestGen){
                bestGen = attempt.getGen();
            }
        }
        System.out.println("best attempt: " + bestGen);
    }
}
