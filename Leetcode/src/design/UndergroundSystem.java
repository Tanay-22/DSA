package design;

import java.util.HashMap;
import java.util.Map;

class UndergroundSystem
{
    private Map<Integer, Passenger> passengerMap;
    private Map<String, Route> routeMap;

    public UndergroundSystem()
    {
        this.passengerMap = new HashMap<>();
        this.routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t)
    {
        passengerMap.putIfAbsent(id, new Passenger(stationName, t));
    }

    public void checkOut(int id, String stationName, int t)
    {
        Passenger passenger = passengerMap.getOrDefault(id, null);
        if (passenger == null)
            return;

        passenger.checkOut(stationName, t);
        String routeKey = passenger.checkInLocation + ":" + passenger.checkOutLocation;
        Route route = routeMap.getOrDefault(routeKey,
                new Route(passenger.checkInLocation, passenger.checkOutLocation));
        route.addTrip(passenger.checkInTime, passenger.checkOutTime);
        routeMap.put(routeKey, route);
        passengerMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation)
    {
        return routeMap.get(startStation + ":" + endStation).getAvgTime();
    }
}

class Route
{
    String startStation;
    String endStation;
    int trips;
    long totalTime;

    public Route(String startStation, String endStation)
    {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public void addTrip(int startTime, int endTime)
    {
        trips++;
        totalTime += endTime - startTime;
    }

    public double getAvgTime()
    {
        return 1.0 * totalTime / trips;
    }
}

class Passenger
{
    int checkInTime;
    int checkOutTime;
    String checkInLocation;
    String checkOutLocation;

    public Passenger(String checkInLocation, int chekInTime)
    {
        this.checkInLocation = checkInLocation;
        this.checkInTime = checkInTime;
    }

    public void checkOut(String checkOutLocation, int chekOutTime)
    {
        this.checkOutLocation = checkOutLocation;
        this.checkOutTime = checkOutTime;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */