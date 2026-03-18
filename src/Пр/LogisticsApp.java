package Пр;

interface IInternalDeliveryService {
    void deliverOrder(String orderId);
    String getDeliveryStatus(String orderId);
    double calculateCost(double weight);
}

class InternalDeliveryService implements IInternalDeliveryService {

    public void deliverOrder(String orderId) {
        System.out.println("Внутренняя доставка для заказа: " + orderId);
    }

    public String getDeliveryStatus(String orderId) {
        return "Статус внутренней доставки: Доставлено";
    }

    public double calculateCost(double weight) {
        return weight * 2;
    }
}

class ExternalLogisticsServiceA {
    public void shipItem(int itemId) {
        System.out.println("Сервис A отправляет товар: " + itemId);
    }

    public String trackShipment(int shipmentId) {
        return "Сервис A отслеживание: В пути";
    }

    public double getPrice(double weight) {
        return weight * 3;
    }
}

class ExternalLogisticsServiceB {
    public void sendPackage(String packageInfo) {
        System.out.println("Сервис B отправляет: " + packageInfo);
    }

    public String checkPackageStatus(String code) {
        return "Сервис B статус: Доставлено";
    }

    public double computeCost(double weight) {
        return weight * 4;
    }
}

class ExternalLogisticsServiceC {
    public void dispatch(String data) {
        System.out.println("Сервис C отправка: " + data);
    }

    public String status(String id) {
        return "Сервис C статус: В ожидании";
    }

    public double price(double weight) {
        return weight * 5;
    }
}

class LogisticsAdapterA implements IInternalDeliveryService {
    private ExternalLogisticsServiceA service = new ExternalLogisticsServiceA();

    public void deliverOrder(String orderId) {
        try {
            service.shipItem(Integer.parseInt(orderId));
        } catch (Exception e) {
            System.out.println("Ошибка в адаптере A: " + e.getMessage());
        }
    }

    public String getDeliveryStatus(String orderId) {
        return service.trackShipment(Integer.parseInt(orderId));
    }

    public double calculateCost(double weight) {
        return service.getPrice(weight);
    }
}

class LogisticsAdapterB implements IInternalDeliveryService {
    private ExternalLogisticsServiceB service = new ExternalLogisticsServiceB();

    public void deliverOrder(String orderId) {
        service.sendPackage(orderId);
    }

    public String getDeliveryStatus(String orderId) {
        return service.checkPackageStatus(orderId);
    }

    public double calculateCost(double weight) {
        return service.computeCost(weight);
    }
}

class LogisticsAdapterC implements IInternalDeliveryService {
    private ExternalLogisticsServiceC service = new ExternalLogisticsServiceC();

    public void deliverOrder(String orderId) {
        service.dispatch(orderId);
    }

    public String getDeliveryStatus(String orderId) {
        return service.status(orderId);
    }

    public double calculateCost(double weight) {
        return service.price(weight);
    }
}

class DeliveryServiceFactory {
    public static IInternalDeliveryService getService(String type) {
        switch (type) {
            case "internal":
                return new InternalDeliveryService();
            case "A":
                return new LogisticsAdapterA();
            case "B":
                return new LogisticsAdapterB();
            case "C":
                return new LogisticsAdapterC();
            default:
                throw new IllegalArgumentException("Неизвестный тип доставки");
        }
    }
}

public class LogisticsApp {
    public static void main(String[] args) {

        IInternalDeliveryService service1 = DeliveryServiceFactory.getService("internal");
        service1.deliverOrder("101");
        System.out.println(service1.getDeliveryStatus("101"));
        System.out.println("Стоимость: " + service1.calculateCost(10));

        System.out.println("\n-----------------\n");

        IInternalDeliveryService service2 = DeliveryServiceFactory.getService("A");
        service2.deliverOrder("102");
        System.out.println(service2.getDeliveryStatus("102"));
        System.out.println("Стоимость: " + service2.calculateCost(10));

        System.out.println("\n-----------------\n");

        IInternalDeliveryService service3 = DeliveryServiceFactory.getService("C");
        service3.deliverOrder("103");
        System.out.println(service3.getDeliveryStatus("103"));
        System.out.println("Стоимость: " + service3.calculateCost(10));
    }
}
