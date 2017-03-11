import java.util.ArrayList;
import java.util.Random;

/**
 * Created by bramreth on 3/10/17.
 */
public class TalkingTony {
    private String tempGeneration= "";
    private String target;
    private int fitness, topFitness, gen = 0;
    public TalkingTony(String target){
        this.target = target;
        for(int x = 0; x < target.length(); x++) {
            tempGeneration += ( (char) (32 + (new Random()).nextInt(95)) );
        }
        String topGen = tempGeneration;
        while(fitness < target.length()*3){
            tempGeneration = mutate(topGen, fitness);
            fitness = calcFitness(tempGeneration);
            if(fitness > topFitness){
                topGen = tempGeneration;
                topFitness = fitness;
                gen++;
                System.out.println("Gen: " + (gen+1) + " | Fitness: " + fitness + " | " + tempGeneration);
            }
        }
    }
    private int calcFitness(String tempGeneration){
        int fitnessResult = 0;
        for(int x = 0; x < tempGeneration.length(); x++) {
            if((int)tempGeneration.charAt(x) == (int) target.charAt(x)){
                fitnessResult += 3;
            }else if((int)tempGeneration.charAt(x) < ((int) target.charAt(x) + 5) &&
                    (int)tempGeneration.charAt(x) > ((int) target.charAt(x) - 5)){
                fitnessResult += 2;
            }else if((int)tempGeneration.charAt(x) < ((int) target.charAt(x) + 10) &&
                    (int)tempGeneration.charAt(x) > ((int) target.charAt(x) - 10)) {
                fitnessResult += 1;
            }
        }
        return fitnessResult;
    }

    private String mutate(String target, int rate){
        String creation = "";
        for(int x = 0; x < target.length(); x++) {
            Random r = new Random();
            if(r.nextInt(target.length()*3)<rate) {
                creation += target.charAt(x);
            }else{
                creation += (char) (new Random().nextInt(126) + 1);
            }
        }
        return creation;
    }
}