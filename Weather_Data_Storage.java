import java.util.*;

class DateRecord {
    int day, month, year;

    DateRecord(int d, int m, int y) {
        day = d;
        month = m;
        year = y;
    }
}

class WeatherRecord {
    DateRecord date;
    String city;
    double temperature;

    WeatherRecord(int d, int m, int y, String c, double t) {
        date = new DateRecord(d, m, y);
        city = c;
        temperature = t;
    }
}

class WeatherDataSystem {
    private List<String> cities;
    private List<Integer> years;
    private double[][] tempData;  // 2D array [year][city]
    private final double SENTINEL = -9999; // For sparse data

    WeatherDataSystem(List<Integer> yrs, List<String> cts) {
        years = yrs;
        cities = cts;
        tempData = new double[years.size()][cities.size()];

        // Initialize with sentinel values
        for (int i = 0; i < years.size(); i++) {
            Arrays.fill(tempData[i], SENTINEL);
        }
    }

    void insert(int year, String city, double temp) {
        int r = getYearIndex(year);
        int c = getCityIndex(city);
        if (r != -1 && c != -1) {
            tempData[r][c] = temp;
        }
    }

    void remove(int year, String city) {
        int r = getYearIndex(year);
        int c = getCityIndex(city);
        if (r != -1 && c != -1) {
            tempData[r][c] = SENTINEL;
        }
    }

    double retrieve(int year, String city) {
        int r = getYearIndex(year);
        int c = getCityIndex(city);
        if (r != -1 && c != -1) {
            return tempData[r][c];
        }
        return SENTINEL;
    }

    void rowMajorAccess() {
        for (int i = 0; i < years.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                System.out.println(years.get(i) + " - " + cities.get(j) + ": " + tempData[i][j]);
            }
        }
    }

    void columnMajorAccess() {
        for (int j = 0; j < cities.size(); j++) {
            for (int i = 0; i < years.size(); i++) {
                System.out.println(years.get(i) + " - " + cities.get(j) + ": " + tempData[i][j]);
            }
        }
    }

    private int getYearIndex(int year) {
        return years.indexOf(year);
    }

    private int getCityIndex(String city) {
        return cities.indexOf(city);
    }
}

public class Weather_Data_Storage {
    public static void main(String[] args) {
        List<Integer> years = Arrays.asList(2023, 2024, 2025);
        List<String> cities = Arrays.asList("Delhi", "Mumbai", "Chennai");

        WeatherDataSystem wds = new WeatherDataSystem(years, cities);

        wds.insert(2023, "Delhi", 32.5);
        wds.insert(2024, "Mumbai", 29.0);

        System.out.println("Delhi 2023 Temp: " + wds.retrieve(2023, "Delhi"));

        System.out.println("\nRow Major Access:");
        wds.rowMajorAccess();

        System.out.println("\nColumn Major Access:");
        wds.columnMajorAccess();
    }
}
