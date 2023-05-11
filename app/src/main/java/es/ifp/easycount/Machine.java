package es.ifp.easycount;

public class Machine {
    private String location;
    private String status;
    private String finance;

    public Machine() {
        // Default constructor required for Firebase
    }

    public Machine(String location, String status, String finance) {
        this.location = location;
        this.status = status;
        this.finance = finance;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getFinance() {
        return finance;
    }
}

