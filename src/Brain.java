import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by bramreth on 4/16/17.
 */
public class Brain implements Serializable {
    private int[] likelyAscii;
    private int total;
    public Brain(){
        likelyAscii = new int[95];
        for(int x = 0; x < 95; x++){
            likelyAscii[x] = 1;
            total++;
        }
    }
    public void updateLikelihood(int commonLetter){
        if(likelyAscii[commonLetter] < 40){
            likelyAscii[commonLetter]++;
            total++;
        }else{
            for(int x = 0; x < 95; x++){
                if(likelyAscii[x] > 1 && x != commonLetter){
                    if(likelyAscii[commonLetter] > 30){
                        switch (new Random().nextInt(8)){
                            case 0:
                                likelyAscii[x]--;
                                total--;
                            default:
                                break;
                        }
                    }else if(likelyAscii[commonLetter] > 20){
                        switch (new Random().nextInt(4)){
                            case 0:
                                likelyAscii[x]--;
                                total--;
                            default:
                                break;
                        }
                    }else if(likelyAscii[commonLetter] > 10){
                        switch (new Random().nextInt(2)){
                            case 0:
                                likelyAscii[x]--;
                                total--;
                            default:
                                break;
                        }
                    }else {
                        likelyAscii[x]--;
                        total--;
                    }
                }
            }
        }
    }
    public int[] getLikelyAscii(){
        return likelyAscii;
    }

    public int getTotal() {
        return total;
    }

    public void saveBrain() {
        File brain = new File("brain.txt");
        try {
            FileOutputStream fileStream = new FileOutputStream(brain);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(brain);
            objectStream.close();
            fileStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printBrain(){
        for(int x = 0; x < 95; x++){
            char temp = (char) (x+32);
            if(likelyAscii[x]>5) {
                System.out.println("ascii: " + temp + " value: " + likelyAscii[x]);
            }
        }
    }
}
