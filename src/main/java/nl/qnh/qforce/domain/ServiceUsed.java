package nl.qnh.qforce.domain;

public enum ServiceUsed {
	SEARCH(1), GET(2);
	int id ;
	ServiceUsed(int i) {
		this.id=i;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
}
