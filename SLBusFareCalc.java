import java.util.ArrayList;
import java.util.Scanner;

class FeeStage {
    private String name;

    public FeeStage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Route {
    private String number;
    private ArrayList<FeeStage> stages;

    public Route(String number) {
        this.number = number;
        this.stages = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public void addStage(FeeStage stage) {
        stages.add(stage);
    }

    public ArrayList<FeeStage> getStages() {
        return stages;
    }

    public int getIndexOfStage(String stageName) {
        for (int i = 0; i < stages.size(); i++) {
            if (stages.get(i).getName().equals(stageName)) {
                return i + 1;
            }
        }
        return 0;
    }
}

class FareCalculator {
    private static final double[] fareReference = {0.00, 30.00, 38.00, 50.00, 61.00, 73.00, 84.00, 96.00, 99.00};

    public static double calculateFare(int originIndex, int destinationIndex) {
        double fare = fareReference[Math.abs(destinationIndex - originIndex)];
        return fare;
    }
}

public class SLBusFareCalc {
    public static void main(String args[]) {
        FeeStage nittambuwa = new FeeStage("Nittambuwa");
        FeeStage kalalpitiya = new FeeStage("Kalalpitiya");
        FeeStage pasyala = new FeeStage("Pasyala");
        FeeStage kajugama = new FeeStage("Kajugama");
        FeeStage radawadunna = new FeeStage("Radawadunna");
        FeeStage weweldeniya = new FeeStage("Weweldeniya");
        FeeStage danowita = new FeeStage("Danowita");
        FeeStage dummaladeniya = new FeeStage("Dummaladeniya");
        FeeStage warakapola = new FeeStage("Warakapola");

        ArrayList<Route> routes = new ArrayList<>();
        Route route674003 = new Route("670");
        route674003.addStage(nittambuwa);
        route674003.addStage(kalalpitiya);
        route674003.addStage(pasyala);
        route674003.addStage(kajugama);
        route674003.addStage(radawadunna);
        route674003.addStage(weweldeniya);
        route674003.addStage(danowita);
        route674003.addStage(dummaladeniya);
        route674003.addStage(warakapola);
        routes.add(route674003);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the route");
        for (Route route : routes) {
            System.out.println("- " + route.getNumber());
        }
		System.out.println("");
        String routeNumber = scanner.nextLine();

        Route selectedRoute = null;

        for (Route route : routes) {
            if (route.getNumber().equals(routeNumber)) {
                selectedRoute = route;
                break;
            }
        }

        System.out.println("\nSelect the origin");

        for (FeeStage stage : selectedRoute.getStages()) {
            System.out.println("- " + stage.getName());
        }
		System.out.println("");

        String origin = scanner.nextLine();

        System.out.println("\nSelect the destination");

        for (FeeStage stage : selectedRoute.getStages()) {
            System.out.println("- " + stage.getName());
        }
		System.out.println("");
		
		String destination = scanner.nextLine();

        int originIndex = selectedRoute.getIndexOfStage(origin);
        int destinationIndex = selectedRoute.getIndexOfStage(destination);

        double fare = FareCalculator.calculateFare(originIndex, destinationIndex);

        System.out.println("\nBus fare between " + origin + " and " + destination + " is Rs. " + String.format("%.2f", fare));

        scanner.close();
    }
}
