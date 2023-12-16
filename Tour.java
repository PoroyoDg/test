package KiemTra;



public class Tour extends Product implements TourManager{
	private String tour_number_dates;
	private String tour_transport;
	
	public Tour() {
		super();
	}

	

	@Override
	public String toString() {
	
		return "So ngay di: " + tour_number_dates + "PTVC:" +tour_transport + super.toString();
	}



	public Tour(String tour_number_dates, String tour_transport,String product_id, String product_name, int product_price, int product_total) {
		super(product_id, product_name, product_price, product_total);
		this.tour_number_dates = tour_number_dates;
		this.tour_transport = tour_transport;
	}

	

	public String getTour_number_dates() {
		return tour_number_dates;
	}



	public void setTour_number_dates(String tour_number_dates) {
		this.tour_number_dates = tour_number_dates;
	}



	public String getTour_transport() {
		return tour_transport;
	}



	public void setTour_transport(String tour_transport) {
		this.tour_transport = tour_transport;
	}



	@Override
	public double getTotalPrice() {
		return (super.getProduct_price()*super.getProduct_total())*1.1
			 ;
	}



	@Override
	public void addTour(Tour t) {
		Main.listTour.add(t);
	}



	@Override
	public void delTour(Tour t) {
		Main.listTour.remove(t);
	}



	@Override
	public int searchTour(String name) {
		for (int i=0; i<Main.listTour.size(); i++) {
			Tour t = Main.listTour.get(i);
			if(t.getProduct_name() == name) {
				return i;
			}
		}
		return -1;
	}



	@Override
	public int searchTour(double price) {
		for (int i=0; i<Main.listTour.size(); i++) {
			Tour t = Main.listTour.get(i);
			if(t.getProduct_price() == price) {
				int a = 0;
			
			}
		}
		return -1;
	}



	@Override
	public void sortedTour() {
		
	}



	@Override
	public int getTotalTour() {
		int sum = 0;
		for (int i=0; i<Main.listTour.size(); i++) {
			Tour t = Main.listTour.get(i);
			sum += t.getProduct_price();
		};
		return sum;
	}
	
}
