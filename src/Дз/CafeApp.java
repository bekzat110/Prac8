package Дз;

interface Beverage {
    String getDescription();
    double cost();
}

class Espresso implements Beverage {
    public String getDescription() {
        return "Эспрессо";
    }

    public double cost() {
        return 2.0;
    }
}

class Tea implements Beverage {
    public String getDescription() {
        return "Чай";
    }

    public double cost() {
        return 1.5;
    }
}

class Latte implements Beverage {
    public String getDescription() {
        return "Латте";
    }

    public double cost() {
        return 3.0;
    }
}

class Mocha implements Beverage {
    public String getDescription() {
        return "Мокко";
    }

    public double cost() {
        return 3.5;
    }
}

abstract class BeverageDecorator implements Beverage {
    protected Beverage beverage;

    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription();
    }

    public double cost() {
        return beverage.cost();
    }
}

class Milk extends BeverageDecorator {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return beverage.getDescription() + ", Молоко";
    }

    public double cost() {
        return beverage.cost() + 0.5;
    }
}

class Sugar extends BeverageDecorator {
    public Sugar(Beverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return beverage.getDescription() + ", Сахар";
    }

    public double cost() {
        return beverage.cost() + 0.2;
    }
}

class WhippedCream extends BeverageDecorator {
    public WhippedCream(Beverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return beverage.getDescription() + ", Взбитые сливки";
    }

    public double cost() {
        return beverage.cost() + 0.7;
    }
}

class Caramel extends BeverageDecorator {
    public Caramel(Beverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return beverage.getDescription() + ", Карамель";
    }

    public double cost() {
        return beverage.cost() + 0.6;
    }
}

class Chocolate extends BeverageDecorator {
    public Chocolate(Beverage beverage) {
        super(beverage);
    }

    public String getDescription() {
        return beverage.getDescription() + ", Шоколад";
    }

    public double cost() {
        return beverage.cost() + 0.8;
    }
}

public class CafeApp {
    public static void main(String[] args) {

        Beverage drink1 = new Espresso();
        drink1 = new Milk(drink1);
        drink1 = new Sugar(drink1);

        System.out.println(drink1.getDescription() + " = $" + drink1.cost());

        Beverage drink2 = new Latte();
        drink2 = new Caramel(drink2);
        drink2 = new WhippedCream(drink2);
        drink2 = new Chocolate(drink2);

        System.out.println(drink2.getDescription() + " = $" + drink2.cost());

        Beverage drink3 = new Mocha();
        drink3 = new Milk(drink3);
        drink3 = new Milk(drink3);
        drink3 = new Sugar(drink3);

        System.out.println(drink3.getDescription() + " = $" + drink3.cost());
    }
}
