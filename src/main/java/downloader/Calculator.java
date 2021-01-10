package downloader;

import downloader.Parsers.BankParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Calculator {
    private HashMap<String, Currency> aliorBank;
    private HashMap<String, Currency> mBank;
    private HashMap<String, Currency> nbpBank;
    private HashMap<String, Currency> nestBank;


    public Calculator() throws IOException {
        //todo download if needed
        DownloadHelper downloadHelper = new DownloadHelper();
        downloadHelper.downloadBanksData();

        BankParser aliorParser = new BankParser();
        aliorParser.readFromCSV("AliorBank.csv");

        BankParser mbankParser = new BankParser();
        mbankParser.readFromCSV("mBank.csv");

        BankParser nbpParser = new BankParser();
        nbpParser.readFromTXT("NBP.txt");

        BankParser nestParser = new BankParser();
        nestParser.readFromHTML("nestBank.html");

        this.aliorBank = aliorParser.getCurrencies();
        this.mBank = mbankParser.getCurrencies();
        this.nbpBank = nbpParser.getCurrencies();
        this.nestBank = nestParser.getCurrencies();
    }

    public double doExchange(double inputValue, String codeFrom, String codeTo, int bank){
        double sellingRate;
        double buyingRate;
        if(codeTo.equals("PLN") || codeFrom.equals("PLN")){
            String helper = codeFrom;
            codeFrom = codeTo;
            codeTo = helper;
        }
        switch (bank) {
            case 0 -> {     // alior
                buyingRate = aliorBank.get(codeTo).getBuyingRate();
                sellingRate = aliorBank.get(codeFrom).getSellingRate();
            }
            case 1 -> {     // mbank
                sellingRate = mBank.get(codeFrom).getSellingRate();
                buyingRate = mBank.get(codeTo).getBuyingRate();
            }
            case 2 ->{     // nest
                sellingRate = nestBank.get(codeFrom).getSellingRate();
                buyingRate = nestBank.get(codeTo).getBuyingRate();
            }
            case 3 -> {     // NBP
                sellingRate = nbpBank.get(codeFrom).getSellingRate();
                buyingRate = nbpBank.get(codeTo).getBuyingRate();
            }
            default -> throw new IllegalStateException("Unexpected value: " + bank);
        }
        double result = (inputValue*sellingRate)/buyingRate;
        if(buyingRate==1.0 || sellingRate==1.0){
            result = inputValue*buyingRate/sellingRate;
        }
        return Rounder.round(result,2);
    }

    public Currency getCurrency(String code, int fromBank){
        switch(fromBank) {
            case 0 -> {
                return aliorBank.get(code);
            }
            case 1 -> {
                return mBank.get(code);
            }
            case 2 -> {
                return nestBank.get(code);
            }
            case 3 -> {
                return nbpBank.get(code);
            }

            default -> throw new IllegalStateException("Unexpected value: " + fromBank);
        }
    }
    public String[] getCodes(){
        BankParser bankParser = new BankParser();
        return bankParser.getDefinedCodes();
    }
}
