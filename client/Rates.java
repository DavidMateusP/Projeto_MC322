package client;

public enum Rates {
    VERY_GOOD(10),
    GOOD(8),
    OK(6),
    BAD(4), 
    VERY_BAD(2);

    double value;
    Rates(double value){
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }
}
