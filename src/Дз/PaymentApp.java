package Дз;

interface IPaymentProcessor {
    void processPayment(double amount);
}

class PayPalPaymentProcessor implements IPaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Обработка платежа PayPal: $" + amount);
    }
}

class StripePaymentService {
    public void makeTransaction(double totalAmount) {
        System.out.println("Транзакция Stripe: $" + totalAmount);
    }
}

class StripePaymentAdapter implements IPaymentProcessor {
    private StripePaymentService stripeService;

    public StripePaymentAdapter(StripePaymentService stripeService) {
        this.stripeService = stripeService;
    }

    public void processPayment(double amount) {
        stripeService.makeTransaction(amount);
    }
}

class CryptoPaymentService {
    public void sendCrypto(double amount) {
        System.out.println("Крипто-платеж отправлен: $" + amount);
    }
}

class CryptoPaymentAdapter implements IPaymentProcessor {
    private CryptoPaymentService cryptoService;

    public CryptoPaymentAdapter(CryptoPaymentService cryptoService) {
        this.cryptoService = cryptoService;
    }

    public void processPayment(double amount) {
        cryptoService.sendCrypto(amount);
    }
}

public class PaymentApp {
    public static void main(String[] args) {

        IPaymentProcessor paypal = new PayPalPaymentProcessor();
        paypal.processPayment(100);

        IPaymentProcessor stripe = new StripePaymentAdapter(new StripePaymentService());
        stripe.processPayment(200);

        IPaymentProcessor crypto = new CryptoPaymentAdapter(new CryptoPaymentService());
        crypto.processPayment(300);
    }
}
