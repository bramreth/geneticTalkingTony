import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by bramreth on 4/16/17.
 * implement understanding the passage of time
 */
public class Brain implements Serializable {
    private ArrayList<Concept> concept;
    public Brain(){
        concept = new ArrayList<>();
    }
    public void updateForLength(int length){
        while(length > concept.size()){
            concept.add(new Concept());
        }
    }
    public void updateLikelihood(int commonLetter, int position) {
        if(position < concept.size()) {
            concept.get(position).updateLikelihood(commonLetter);
        }else{
            concept.add(new Concept());
        }
    }

    public int[] getLikelyAscii(int position){
        if(position < concept.size()) {
            return concept.get(position).getLikelyAscii();
        }else{
            concept.add(new Concept());
        }
        return null;
    }

    public int getTotal(int position) {
        if (position < concept.size()) {
            return concept.get(position).getTotal();
        }
        return -1;
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
            int i = 0;
            for(Concept thisConcept: concept) {
                i++;
                for(int x = 0; x < 95; x++) {
                    char temp = (char) (x + 32);
                    if (thisConcept.getLikelyAscii()[x] > 1) {
                        System.out.println("concept " + i + ": " + temp + " value: " + thisConcept.getLikelyAscii()[x]);
                    }
                }
            }
    }

}
