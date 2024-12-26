/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = new ArrayList<>();
        int maxWeight = 0;

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Thêm danh sách items");
            System.out.println("2. Xóa item");
            System.out.println("3. Giải quyết bài toán cái túi");
            System.out.println("4. Thoát");
            System.out.print("Chọn một chức năng: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1: // Thêm danh sách items
                    System.out.print("Nhập số lượng items: ");
                    int n = scanner.nextInt();
                    
                    for (int i = 0; i < n; i++) {
                        System.out.print("Nhập weight của item " + (i + 1) + ": ");
                        int weight = scanner.nextInt();
                        System.out.print("Nhập value của item " + (i + 1) + ": ");
                        int value = scanner.nextInt();
                        items.add(new Item(weight, value));
                    }
                    
                    System.out.print("Nhập maxWeight: ");
                    maxWeight = scanner.nextInt();
                    
                    System.out.println("Đã thêm danh sách items.");
                    break;

                case 2: // Xóa item
                    System.out.println("\n1. Xóa một item");
                    System.out.println("2. Xóa tất cả items");
                    System.out.print("Chọn một phương án: ");
                    int deleteChoice = scanner.nextInt();

                    if (deleteChoice == 1) {
                        System.out.print("Nhập chỉ số của item cần xóa (bắt đầu từ 0): ");
                        int index = scanner.nextInt();
                        if (index >= 0 && index < items.size()) {
                            items.remove(index);
                            System.out.println("Đã xóa item tại vị trí " + index + ".");
                        } else {
                            System.out.println("Chỉ số không hợp lệ.");
                        }
                    } else if (deleteChoice == 2) {
                        items.clear();
                        System.out.println("Đã xóa tất cả items.");
                    } else {
                        System.out.println("Lựa chọn không hợp lệ.");
                    }
                    break;

                case 3: // Giải quyết bài toán cái túi
                    if (items.isEmpty() || maxWeight == 0) {
                        System.out.println("Danh sách items rỗng hoặc maxWeight chưa được đặt.");
                        break;
                    }
                    
                    GeneticAlgorithm ga = new GeneticAlgorithm(items, maxWeight);
                    KnapSack bestKnapsack = ga.run();
                    
                    System.out.println("Best fitness: " + bestKnapsack.fitness);
                    System.out.print("Selected items: ");
                    for (int i = 0; i < bestKnapsack.genes.length; i++) {
                        if (bestKnapsack.genes[i]) {
                            System.out.print("(" + items.get(i).weight + ", " + items.get(i).value + ") ");
                        }
                    }
                    System.out.println();
                    break;

                case 4: // Thoát
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }
}

