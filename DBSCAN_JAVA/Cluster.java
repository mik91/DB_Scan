import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Mikhail Kamara 300190412
 */
public class Cluster {
    private int minPoints;
    private double eps;
    private StringBuilder finalOutput = new StringBuilder();
    public ArrayList<LinkedHashSet<Taxi>> clusters;

    public Cluster(int minPoints, double eps) {
        this.minPoints = minPoints;
        this.clusters = new ArrayList<>();
        setEps(eps);
    }

    public int getMinPoints() {

        if (minPoints < 0) {
            throw new IllegalArgumentException("The minimum numbers of points need to be a positive number");
        }

        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) throws IllegalArgumentException {

        if (eps < 0) {
            throw new IllegalArgumentException("The radius of the neighborhood need to be a positive number");
        }

        this.eps = eps;
    }

    /**
     * DBSCAN algo to find all clusters
     * @param taxis ArrayList of taxi
     */
    public void DBSCAN(ArrayList<Taxi> taxis) {
        for (int i = 0; i < taxis.size(); i++) {
            Taxi current = taxis.get(i);

            if (current.getStatus() != Taxi.labelStatus.UNDEFINED)
                continue;

            ArrayList<Taxi> neighbors = rangeQuery(current, taxis);

            if (neighbors.size() < minPoints) {
                taxis.get(i).setStatus(Taxi.labelStatus.NOISE);
                continue;
            }

            ArrayList<Taxi> seedSet = new ArrayList<>(neighbors);
            current.setStatus(Taxi.labelStatus.PART_CLUSTER);

            LinkedHashSet<Taxi> cluster = new LinkedHashSet<>();
            cluster.add(current);

            expandCluster(taxis, seedSet, cluster);
        }
    }

    /**
     * Expand the cluster
     * @param taxis list of taxis
     * @param seedSet seedSet with the neighbors 
     * @param cluster current cluster
     */
    private void expandCluster(ArrayList<Taxi> taxis, ArrayList<Taxi> seedSet, LinkedHashSet<Taxi> cluster) {
        for (int j = 0; j < seedSet.size(); j++) {
            Taxi currentSeedSet = seedSet.get(j);

            if (currentSeedSet.getStatus() == Taxi.labelStatus.NOISE) {
                currentSeedSet.setStatus(Taxi.labelStatus.BORDER);
                cluster.add(currentSeedSet);
            }

            if (currentSeedSet.getStatus() != Taxi.labelStatus.UNDEFINED) continue;
            
            cluster.add(currentSeedSet);
            currentSeedSet.setStatus(Taxi.labelStatus.PART_CLUSTER);

            ArrayList<Taxi> n = rangeQuery(currentSeedSet, taxis);
            
            if (n.size() >= minPoints) {
                seedSet = mergeSeedSet(seedSet, n);
            }
        }
        clusters.add(cluster);
    }

    /**
     * Write output to the file
     */
    public void writeOutput() throws FileNotFoundException {
        finalOutput.append("Custer ID,Longitude,Latitude,Number of points");
        finalOutput.append("\n");

        for (int i = 0; i < clusters.size(); i++) {
            double lonAverage = 0;
            double latAverage = 0;

            finalOutput.append(i+1);
            finalOutput.append(",");

            for (Taxi taxi : clusters.get(i)) {
                lonAverage += taxi.getTripRecord().getPickup_Location().getLongitude();
                latAverage += taxi.getTripRecord().getPickup_Location().getLatitude();
            }

            finalOutput.append(lonAverage / clusters.get(i).size());
            finalOutput.append(",");
            finalOutput.append(latAverage / clusters.get(i).size());
            finalOutput.append(",");
            finalOutput.append(clusters.get(i).size());
            finalOutput.append("\n");
        }

        PrintWriter writer = new PrintWriter("output.csv");
        writer.write(finalOutput.toString());
        writer.close();
        
    }

    /**
     * Find all neighbors to the current Taxi
     * @param current current taxi
     * @param taxis ArrayList of taxis
     * @return list of neighbors
     */
    private ArrayList<Taxi> rangeQuery(Taxi current, ArrayList<Taxi> taxis) {
        ArrayList<Taxi> neighbors = new ArrayList<>();

        for (int i = 0; i < taxis.size(); i++) {
            Taxi newTaxi = taxis.get(i);

            double distance = distance(current.getTripRecord().getPickup_Location(),
                    newTaxi.getTripRecord().getPickup_Location());

            if (current != newTaxi && distance <= eps) {
                neighbors.add(newTaxi);
            }
        }

        return neighbors;
    }

    /**
     * Merge two list
     * @param taxi first list
     * @param taxi2 second list
     * @return merge of the two list
     */
    private ArrayList<Taxi> mergeSeedSet(ArrayList<Taxi> taxi, ArrayList<Taxi> taxi2) {
        LinkedHashSet<Taxi> setTaxi = new LinkedHashSet<>(taxi);
        setTaxi.addAll(taxi2);

        return new ArrayList<>(setTaxi);
    }

    /**
     * Calculate distance between two coordinates
     * @param gpsCoord1 first coordinate
     * @param gpsCoord2 second coordinate
     * @return distance between the two coordinates
     */
    private double distance(GPScoord gpsCoord1, GPScoord gpsCoord2) {
        double x = gpsCoord1.getLongitude() - gpsCoord2.getLongitude();
        double y = gpsCoord1.getLatitude() - gpsCoord2.getLatitude();

        x *= x;
        y *= y;

        double sum = x + y;
        return Math.sqrt(sum);
    }

}
