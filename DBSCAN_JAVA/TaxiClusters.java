import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mikhail Kamara 300190412
 */
class TaxiClusters {
    
    public static void main(String args[]) {
        String line = "";
        String delimiter = ",";
        ArrayList<Taxi> taxis = new ArrayList<>();
        int counter = 0;


        try {
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String name = "";
            int minPts = 0;
            double eps = 0;

            if (args.length > 0){
                name = args[0];
                minPts = Integer.parseInt(args[1]);
                eps = Double.parseDouble(args[2]);
            } else {
                name = "yellow_tripdata_2009-01-15_1hour_clean.csv";
                minPts = 5;
                eps = 0.0003;
            }

            BufferedReader br = new BufferedReader(new FileReader(name));

            br.readLine();

            while ((line = br.readLine()) != null){
                String[] tokens = line.split(delimiter);
                GPScoord pickup_Location = new GPScoord(Double.parseDouble(tokens[8]), Double.parseDouble(tokens[9]));
                GPScoord dropoff_Location = new GPScoord(Double.parseDouble(tokens[12]), Double.parseDouble(tokens[13]));
                TripRecord tripRecord = new TripRecord(pickup_Location, dropoff_Location, tokens[4], Float.parseFloat(tokens[7]));
                TripInfo tripInfo = new TripInfo(Integer.parseInt(tokens[6]), tokens[14], Float.parseFloat(tokens[15]), 
                Float.parseFloat(tokens[16]), tokens[6], Float.parseFloat(tokens[18]), Float.parseFloat(tokens[19]), Float.parseFloat(tokens[20]));

                Taxi taxi = new Taxi(counter, tokens[3], tripInfo, tripRecord, tokens[10], tokens[11]);
                Map<Taxi, Taxi.labelStatus> test = new HashMap<>();
                test.put(taxi, Taxi.labelStatus.NOISE);
                taxis.add(taxi);
                counter++;
            }

            br.close();

            // ArrayList<ArrayList<ArrayList<Taxi>>> tArrayLists = new ArrayList<>();
            // double N = 1;

            // for (int i = 0; i < N; i++) {
            //     tArrayLists.add(new ArrayList<>());
            //     for (int j = 0; j < N; j++) {
            //         tArrayLists.get(i).add(new ArrayList<>());
            //         for (Taxi pt : taxis) {
            //             double incx = (40.8 - 40.7) / N;
            //             double incy = (-73.93d - (-74)) / N;

            //             var lon = pt.getTripRecord().getPickup_Location().getLatitude();
            //             var lat = pt.getTripRecord().getPickup_Location().getLongitude();
            //             //				if (pt.long >= minPt.long+float64(i)*incx-eps) && (pt.long < minPt.long+float64(i+1)*incx+eps) && (pt.lat >= minPt.lat+float64(j)*incy-eps) && (pt.lat < minPt.lat+float64(j+1)*incy+eps) {

            //             if ((lon >= 40.7+ (double)i * incx-eps) && (lon < 40.7+ (double)(i+1) * incx+eps) && (lat >= -74d+ (double)j * incy-eps) && (lat < -74d+ (double)(j+1) * incy+eps)) {
            //                 tArrayLists.get(i).get(j).add(pt);
            //             }
            //         }
            //     }
            // }

            // for (int i = 0; i < N; i++) {
            //     for (int j = 0; j < N; j++) {
            //         Cluster cluster = new Cluster(minPts, eps);
            //         cluster.DBSCAN(tArrayLists.get(i).get(j));
            //         System.out.println("ID:" + i*10000000+j*1000000 + " nbCluster " + cluster.clusters.size()+ " nbPts: " + tArrayLists.get(i).get(j).size());
            //     }
            // }


            Cluster cluster = new Cluster(minPts, eps);
            cluster.DBSCAN(taxis);
            cluster.writeOutput();
        
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}