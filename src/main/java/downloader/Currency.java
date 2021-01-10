package downloader;


public class Currency {
    private final String code;
    private final double buyingRate;
    private final double sellingRate;
    private final double avarageRate;

    public Currency(String code, double buyingRate, double sellingRate) {
        this.code = code;
        this.buyingRate = buyingRate;
        this.sellingRate = sellingRate;
        this.avarageRate = (buyingRate + sellingRate)/2;
    }

    public String getCode() {
        return code;
    }

    public double getBuyingRate() {
        return buyingRate;
    }

    public double getSellingRate() {
        return sellingRate;
    }

    public double getAvarageRate() {
        return avarageRate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", buyingRate=" + buyingRate +
                ", sellingRate=" + sellingRate +
                ", avarageRate=" + avarageRate +
                "}\n";
    }
}
