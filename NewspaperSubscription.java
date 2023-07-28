import java.util.ArrayList;
import java.util.List;

class NewspaperSubscription {
    String name;
    double[] prices;

    public NewspaperSubscription(String name, double[] prices) {
        this.name = name;
        this.prices = prices;
    }
}

class SubscriptionCalculator {

    public static List<List<String>> getCombinations(List<NewspaperSubscription> newspapers, double budget) {
        List<List<String>> result = new ArrayList<>();
        findCombinations(newspapers, budget, 0, new ArrayList<>(), result);
        return result;
    }

    private static void findCombinations(List<NewspaperSubscription> newspapers, double budget,
                                         int index, List<String> currentCombination, List<List<String>> result) {
        if (budget == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        if (budget < 0 || index >= newspapers.size()) {
            return;
        }

        NewspaperSubscription newspaper = newspapers.get(index);
        for (int i = 0; i < newspaper.prices.length; i++) {
            if (budget >= newspaper.prices[i]) {
                currentCombination.add(newspaper.name);
                findCombinations(newspapers, budget - newspaper.prices[i], index + 1, currentCombination, result);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<NewspaperSubscription> newspapers = new ArrayList<>();
        newspapers.add(new NewspaperSubscription("TOI", new double[]{3, 3, 3, 3, 3, 5, 6}));
        newspapers.add(new NewspaperSubscription("Hindu", new double[]{2.5, 2.5, 2.5, 2.5, 2.5, 4, 4}));
        newspapers.add(new NewspaperSubscription("ET", new double[]{4, 4, 4, 4, 4, 4, 10}));
        newspapers.add(new NewspaperSubscription("BM", new double[]{1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5}));
        newspapers.add(new NewspaperSubscription("HT", new double[]{2, 2, 2, 2, 2, 4, 4}));

        double budget = 40;
        List<List<String>> combinations = getCombinations(newspapers, budget);

        for (List<String> combination : combinations) {
            System.out.println(combination);
        }
    }
}