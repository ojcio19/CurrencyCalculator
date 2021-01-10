package downloader.Parsers;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVReader;

import downloader.Currency;
import downloader.DownloadHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BankParser{
    private final HashMap<String, Currency> currencies = new HashMap<>();
    private final String[] definedCodes =  {"PLN","USD","EUR","GBP","CHF","CZK","NOK"};

    public BankParser(){}

    public void readFromCSV(String filename) throws IOException {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(filename))
                .withCSVParser(csvParser)
                .withSkipLines(3)
                .build()){
            if(filename.equals("AliorBank.csv"))    //AliorBank
                fillCurrencyCSV(reader.readAll(),3,4); //get List<String[]>
            else if(filename.equals("mBank.csv")){  //mBank
                fillCurrencyCSV(reader.readAll(),4,5);
            }
        }
    }

    public void readFromTXT(String filename) throws IOException {
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator('{')
                .build();
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(filename))
                .withCSVParser(csvParser)
                .build()){
            fillCurrencyTxt(reader.readAll());
        }
    }

    public void readFromHTML(String filename) throws IOException{
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator('<')
                .build();
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(filename))
                .withCSVParser(csvParser)
                .build()){
            fillCurrencyHTML(reader.readAll());
        }
    }

    private void fillCurrencyHTML(List<String[]> document){ //todo refactor this algorithm
        ArrayList<String> notFormatedLines = new ArrayList<>();
        document.forEach(lines->notFormatedLines.addAll(Arrays.asList(lines)));
        int search = 0;
        String wantedCode="PLN";
        double buy = 1.0;
        double sell = 1.0;
        int indexFrom = 0, indexTo = 0, i =0;
        for (String line:notFormatedLines) {
            if(line.contains("class=\"PortletExchangeRatesBig__currency-table\">")) {
                indexFrom = i;
            }else if(line.contains("icon-spinner")){
                indexTo = i;
            }
            i++;
        }
        for (int j = indexFrom; j < indexTo; j++) {
            String [] line = notFormatedLines.get(j).replace(",",".").split("\\s+");
            for (String word: line) {
                for (String code:definedCodes) {
                    if (word.equals(code)) {
                        search = 21;
                        wantedCode=code;
                        break;
                    }
                }
            }
            search--;

            if (search == 12){
                buy = Double.parseDouble(line[1]);
            }else if(search == 1){
                sell = Double.parseDouble(line[1]);
            }
            if (!currencies.containsKey(wantedCode) && search==0) {
                currencies.put(wantedCode, new Currency(wantedCode, buy , sell));
            }
        }
        currencies.put("PLN",new Currency("PLN", 1.0,1.0));
    }

    private void fillCurrencyTxt(List<String[]> document){
        ArrayList<String> currency = new ArrayList<>();
        document.forEach(lines->currency.addAll(Arrays.asList(lines)));

        for (String element:currency) {
            String [] line = element
                    .replace(" ", "_")
                    .replace(":", "")
                    .replace(",", "")
                    .replace("\"", " ")
                    .replace("}", "")
                    .split("\\s+");
            for (String str:line) {
                for (String code:definedCodes) {
                    if (str.matches(code) && !currencies.containsKey(code)) {
                        currencies.put(code, new Currency(code, Double.parseDouble(line[5]),
                                    Double.parseDouble(line[7])));
                    }
                }
            }
        }
        currencies.put("PLN",new Currency("PLN", 1.0,1.0));
    }

    private void fillCurrencyCSV(List<String[]> document, int buyPosition, int sellPosition) {

        for (String[] wholeline:document) {
            for (String element:wholeline) {
                for (String code:definedCodes) {

                    String pattern = ".*" + code + "$";
                    if(element.matches(pattern) && !currencies.containsKey(code)){
                        currencies.put(code,new Currency(code, Double.parseDouble(wholeline[buyPosition]),
                                Double.parseDouble(wholeline[sellPosition])));
                    }
                }
            }
        }
        currencies.put("PLN",new Currency("PLN", 1,1));
    }

    public HashMap<String, Currency> getCurrencies() {
        return currencies;
    }

    public Currency getCurrencyByCode(String code){
        return currencies.get(code);
    }

    public String[] getDefinedCodes() {
        return definedCodes;
    }

    @Override
    public String toString() {
        return "BankParser{" +
                "numberOfCurrencies=" + currencies.size() + "\n" +
                "definedCodes=" + Arrays.toString(definedCodes) +
                '}';
    }
}
