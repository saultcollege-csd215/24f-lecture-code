package java._02_data_calculations_actions.problems;

import java.util.List;

public class P2 {

    private record Weather(int timestamp, double temp, double humidity, double windSpeed, double windDirection) {
    }

    // Try to break this into smaller calculations (pure functions)
    public static void main(String[] args) {

        var weatherHistory = List.of(
                new Weather(1695659030+86400*0, 32.0, 0.5, 10.0, 0.0),
                new Weather(1695659030+86400*1, 27.1, 0.4, 12.0, 230.0),
                new Weather(1695659030+86400*2, 30.0, 0.6, 8.0, 180.0),
                new Weather(1695659030+86400*3, 28.0, 0.5, 9.0, 90.0),
                new Weather(1695659030+86400*4, 29.0, 0.5, 11.0, 270.0),
                new Weather(1695659030+86400*5, 31.0, 0.5, 10.0, 0.0),
                new Weather(1695659030+86400*6, 26.0, 1.0, 16.0, 95.0),
                new Weather(1695659030+86400*7, 25.0, 0.9, 15.0, 180.0),
                new Weather(1695659030+86400*8, 24.0, 0.8, 14.0, 270.0),
                new Weather(1695659030+86400*9, 23.0, 0.7, 13.0, 0.0),
                new Weather(1695659030+86400*10, 22.0, 0.6, 12.0, 90.0),
                new Weather(1695659030+86400*11, 21.0, 0.5, 11.0, 180.0),
                new Weather(1695659030+86400*12, 20.0, 0.4, 10.0, 270.0),
                new Weather(1695659030+86400*13, 19.0, 0.3, 9.0, 0.0)
        );

        // Find the hottest day
        var hottestDay = weatherHistory.get(0);
        for ( var weather : weatherHistory ) {
            if ( weather.temp() > hottestDay.temp() ) {
                hottestDay = weather;
            }
        }
        System.out.println("Hottest day: " + hottestDay.timestamp());
    }
}
