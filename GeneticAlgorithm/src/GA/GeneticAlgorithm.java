/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ACER
 */
public class GeneticAlgorithm {
    List<KnapSack> population;
    List<Item> listItem;
    int maxWeight;
    int populationSize = 100;//Số lượng cá thể trong quần thể
    int generations = 100;//Số thế hệ tối đa
    double mutationRate = 0.01;//Tỷ lệ đột biến
    
    public  GeneticAlgorithm(List<Item> listItem, int maxWeight){
        this.listItem = listItem;
        this.maxWeight = maxWeight;
        this.population = new ArrayList<>();//Khởi tạo quần thể ban đầu là rỗng
    }
    
    // Tạo quần thể ban đầu
    public void initializePopulation(){
        for(int i = 0; i< populationSize;i++){
            KnapSack knapSack = new KnapSack(listItem,maxWeight);
            knapSack.randomizeGenes();//Mỗi cá thể (KnapSack) được tạo ngẫu nhiên
            population.add(knapSack);
        }
    }
    
    // Chọn cá thể vượt trội nhất
    public KnapSack selectBestParent() {//Lựa chọn một cá thể tốt nhất để làm cha mẹ
        // Roulette Wheel Selection
        //Tính tổng độ thích nghi (fitness) của tất cả các cá thể.
        int totalFitness = population.stream().mapToInt(k -> k.fitness).sum();
        Random rand = new Random();
        //Chọn ngẫu nhiên một giá trị trong khoảng từ 0 đến tổng độ thích nghi.
        int randomFitness = rand.nextInt(totalFitness);
        int cumulativeFitness = 0;
        //Duyệt qua các cá thể, tính tổng tích lũy cumulativeFitness. 
        //Khi tổng tích lũy vượt qua giá trị ngẫu nhiên, cá thể hiện tại được chọn.
        for (KnapSack knapsack : population) {
            cumulativeFitness += knapsack.fitness;
            if (cumulativeFitness >= randomFitness) {
                return knapsack;
            }
        }
        return population.get(0); // Fallback
    }
    // Lai ghép cá thể con
    //Tạo ra một cá thể con (offspring) bằng cách lai ghép từ hai cá thể cha mẹ.
    public KnapSack crossover(KnapSack parent1, KnapSack parent2) {
        KnapSack offspring = new KnapSack(listItem, maxWeight);
        //Chọn ngẫu nhiên điểm cắt
        Random rand = new Random();
        int crossoverPoint = rand.nextInt(parent1.genes.length);
//Gen từ cha mẹ được ghép vào con theo quy tắc
//Các gen trước điểm cắt lấy từ parent1.
//Các gen sau điểm cắt lấy từ parent2.
        for (int i = 0; i < parent1.genes.length; i++) {
            offspring.genes[i] = (i < crossoverPoint) ? parent1.genes[i] : parent2.genes[i];
        }

        offspring.calculateFitness();
        return offspring;
    }
    // Đột biến một cá thể
    //Tăng tính đa dạng trong quần thể bằng cách đột biến ngẫu nhiên một số gen trong cá thể.
    public void mutate(KnapSack knapsack) {
        //Tạo một số ngẫu nhiên; nếu nhỏ hơn mutationRate, thì gen đó sẽ bị lật (từ true thành false hoặc ngược lại).
        Random rand = new Random();
        for (int i = 0; i < knapsack.genes.length; i++) {
            if (rand.nextDouble() < mutationRate) {
                knapsack.genes[i] = !knapsack.genes[i];
            }
        }
        //Sau khi đột biến, tính toán lại độ thích nghi (fitness) của cá thể.
        knapsack.calculateFitness();
    }

    public KnapSack run() {
        initializePopulation();//Khởi tạo quần thể:

        for (int generation = 0; generation < generations; generation++) {
            List<KnapSack> newPopulation = new ArrayList<>();

            for (int i = 0; i < populationSize; i++) {
                //Chọn cha mẹ tốt nhất 
                KnapSack parent1 = selectBestParent();
                KnapSack parent2 = selectBestParent();
                //Tạo cá thể con bằng cách lai ghép 
                KnapSack offspring = crossover(parent1, parent2);
                //Đột biến con
                mutate(offspring);
                //Thêm cá thể con vào quần thể mới.
                newPopulation.add(offspring);
            }
            //Thay thế quần thể hiện tại bằng quần thể mới.
            population = newPopulation;
        }
        //Sau generations thế hệ, trả về cá thể có độ thích nghi cao nhất.
        return population.stream().max((k1, k2) -> Integer.compare(k1.fitness, k2.fitness)).orElse(null);
    }
}
