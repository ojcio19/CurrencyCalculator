package downloader;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadHelper {

    DownloadHelper(){ }

    public void downloadBanksData(){
        String AliorAddress = "https://www.aliorbank.pl/exchange-rates/print/csv?tableNumber=20201231155824&content=206fb8d8-89e5-4ee9-9756-01320da68cbc";
        String mBankAddress = "https://www.mbank.pl/ajax/currency/getCSV/?id=3&date=2020-12-31%2016:30:00&lang=pl";
        String NBPAddress = "http://api.nbp.pl/api/exchangerates/tables/C/";
        String nestBank = "https://nestbank.pl/dla-ciebie-i-rodziny/przydatne-informacje/kursy-walut";

        getData(AliorAddress,"AliorBank" + ".csv");
        getData(mBankAddress,"mBank" + ".csv");
        getData(NBPAddress,"NBP" + ".txt");
        getData(nestBank,"nestBank" + ".html");

        //getData(SantanderBank,"SantanderBank" + ".html");     //HTML
        //getData(BnpParibas,"BNP_Paribas");                    //PDF
        //getData(GetinBank,"GetinBank" + ".html");             //HTML

    }

    private void getData(String addressCSV, String bankName){
        DownloadHelper helper = new DownloadHelper();
        try {
            helper.downloadFile(addressCSV, bankName);
        }catch (MalformedURLException e){
            System.out.println(e.toString());
        }
    }

    private void downloadFile(String fromURLaddress, String toFilename) throws MalformedURLException {
        URL url = new URL(fromURLaddress);

        try (BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
             FileOutputStream fileOS = new FileOutputStream(toFilename)) {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            System.out.println("Error downloading file:" +  toFilename +
                    " from:" + fromURLaddress);
        }
    }
}

