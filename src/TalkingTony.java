import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;

/**
 * Created by bramreth on 3/10/17.
 */
public class TalkingTony {
    private String tempGeneration= "";
    private String target;
    private int fitness, topFitness, gen = 0;
    private Brain tonysBrain;
    public TalkingTony(String target){
        loadBrain();
        this.target = target;
        tonysBrain.updateForLength(target.length());
        for(int x = 0; x < target.length(); x++) {
             tempGeneration += generateAscii(x);
        }
        String topGen = tempGeneration;
        while(fitness < target.length()*3){
            tempGeneration = mutate(topGen, fitness);
            fitness = calcFitness(tempGeneration);
            if(fitness > topFitness){
                learn(topGen, tempGeneration);
                topGen = tempGeneration;
                topFitness = fitness;
                //gen++;
                System.out.println("Gen: " + (gen+1) + " | Fitness: " + fitness + " | " + tempGeneration);
            }
            gen++;
        }
        tonysBrain.saveBrain();
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
                creation += generateAscii(x);
            }
        }
        return creation;
    }

    private void loadBrain(){
        File load = new File("brain.txt");
        try {
            FileInputStream fileStream = new FileInputStream(load);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            tonysBrain = (Brain) objectStream.readObject();
        } catch (Exception e) {
            tonysBrain = new Brain();
        }
    }
    private char generateAscii(int position){
        int randChar = new Random().nextInt(tonysBrain.getTotal(position));
        int x = 0;
        for(int y = 32; y < 127; y++){
            x+=tonysBrain.getLikelyAscii(position)[y-32];
            if(randChar < x){
                return (char) y;
            }
        }
        return  ( (char) (32 + (new Random()).nextInt(95)) );
    }
    private void learn(String oldGen, String newGen){
        for(int x = 0; x < oldGen.length(); x++){
            if(oldGen.charAt(x) != newGen.charAt(x)){
                tonysBrain.updateLikelihood((int) newGen.charAt(x)-32, x);
            }
        }
    }

    public int getGen() {
        return gen;
    }

    public Brain getTonysBrain() {
        return tonysBrain;
    }
}
