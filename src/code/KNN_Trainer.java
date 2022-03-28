package code;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

public class KNN_Trainer {
    BufferedImage test;
    ArrayList<BufferedImage> dataset;

    ArrayList<Double> valuesOfKNN;
    ArrayList<Boolean> selected;
    private final int maxGreenIndex = 5;
    private final int maxSnowIndex = 9;
    private int k;
    private String cluster;

    public KNN_Trainer(BufferedImage test, ArrayList<BufferedImage> dataset, int k) {
        this.test = test;
        this.dataset = dataset;
        this.valuesOfKNN = new ArrayList<>();
        this.selected = new ArrayList<>();
        this.k = k;
        this.cluster = "";
        calculateValuesOfKNN();
        analyse_cluster();
    }

    void setK(int k){
        this.k = k;
        analyse_cluster();
    }

    private void calculateValuesOfKNN() {
        ImageComparator tool;
//        tool.setTest(test);
        for (BufferedImage train:dataset) {
            tool = new ImageComparator(train, test);
            tool.calculateKNN();
            valuesOfKNN.add(tool.getKNN_val());
            selected.add(false);
        }
    }

    @Override
    public String toString() {
        return "KNN_Trainer{" +
                "test=" + test + "\n" +
                ", dataset=" + dataset + "\n" +
                ", valuesOfKNN=" + valuesOfKNN + "\n" +
                '}';
    }

    public void analyse_cluster() {
//        int k = 3;
        ArrayList<Double> sample = new ArrayList<>();

        for (Double d:valuesOfKNN) {
            sample.add(d);
        }

        ArrayList<Integer> snow = new ArrayList<>();
        ArrayList<Integer> green = new ArrayList<>();

        int bestIndex;
        for (int i = 0; i < k; i++) {
            bestIndex = getBestIndex(sample);
//            System.out.println(bestIndex);
            if(bestIndex < maxGreenIndex){
                green.add(bestIndex);
            }else if(bestIndex < maxSnowIndex){
                snow.add(bestIndex);
            }
            sample.set(bestIndex-1, -1.0);
        }
        if(snow.size() > green.size()){
            this.cluster = "Snow";
//            return "Snow";
        }else{
            this.cluster = "Green";
//            return "Green";
        }
    }

    private int getBestIndex(ArrayList<Double> sample) {
//        System.out.println(sample.toString());
        int index = Integer.MAX_VALUE;
        double val = Double.MAX_VALUE;
//        int i = 0;
        for (int i = 0; i < sample.size(); i++) {
            if(sample.get(i) == -1.0)
                continue;
            if(sample.get(i) < val){
                index = i;
                val = Math.min(val, sample.get(i));
            }
        }
        selected.set(index, true);
        return index+1;
    }

    public String getCluster() {
        return cluster;
    }

    void showValuesOfKnn(){
        for (int i = 0; i < valuesOfKNN.size(); i++) {
            if(i == 0)
                System.out.println("\nGreen Category:");
            if(i == 4)
                System.out.println("\nSnow Category:");
            System.out.println("Image -> "+ (i+1) + " : " + valuesOfKNN.get(i) +"\t "+ " " + selected.get(i) + " ");

        }
    }

}
