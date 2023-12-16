package KiemTra;

public interface TourManager {
	public void addTour(Tour t);
	public void delTour(Tour t);
	public int searchTour(String name);
	public int searchTour(double price);
	public void sortedTour();
	public int getTotalTour();
	
}
