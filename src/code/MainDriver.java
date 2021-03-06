package code;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainDriver {
    public static void main(String[] args) throws IOException {

        int noOfSamples = 20;

        ArrayList<BufferedImage> dataset = new ArrayList<>();

        /*
        Takes input of 20 samples

        Index 1 - 10 images are GREEN
        Index 11 - 20 images are SNOW

        */

        String samplePath = "src/dataset/all_sample_2/s";

        for (int i = 1; i <= noOfSamples; i++) {
            String tempSamplePath = samplePath + i + ".jpg";

            File tempFile = new File(tempSamplePath);
            BufferedImage tempImage = ImageIO.read(tempFile);
            dataset.add(tempImage);
        }

        /*

        Takes test image to find it's nearest neighbor

        test_image 1, 4 -> SNOW
        test_image 2, 3 -> GREEN

        */
        String testPath = "src/dataset/test/test_image_4.jpg";

        File testFile = new File(testPath);
        BufferedImage testImage = ImageIO.read(testFile);

        KNN_Trainer knn_trainer_Tool = new KNN_Trainer(testImage,dataset,3);

        knn_trainer_Tool.showValuesOfKnn();
        System.out.println("\nTest data belongs to: " +knn_trainer_Tool.getCluster() + " \n ");

        knn_trainer_Tool.setK(5);
        knn_trainer_Tool.showValuesOfKnn();
        System.out.println("\nTest data belongs to: " +knn_trainer_Tool.getCluster() + " \n ");

    }
}
