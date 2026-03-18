package Пр;

interface IReport {
    String generate();
}

class SalesReport implements IReport {
    public String generate() {
        return "Отчет по продажам:\nЗаказ1 - $100\nЗаказ2 - $200\nЗаказ3 - $150";
    }
}

class UserReport implements IReport {
    public String generate() {
        return "Отчет по пользователям:\nПользователь1\nПользователь2\nПользователь3";
    }
}

abstract class ReportDecorator implements IReport {
    protected IReport report;

    public ReportDecorator(IReport report) {
        this.report = report;
    }

    public String generate() {
        return report.generate();
    }
}

class DateFilterDecorator extends ReportDecorator {
    private String fromDate;
    private String toDate;

    public DateFilterDecorator(IReport report, String fromDate, String toDate) {
        super(report);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String generate() {
        return report.generate() + "\n[Фильтр по дате: " + fromDate + " - " + toDate + "]";
    }
}

class SortingDecorator extends ReportDecorator {
    private String criteria;

    public SortingDecorator(IReport report, String criteria) {
        super(report);
        this.criteria = criteria;
    }

    public String generate() {
        return report.generate() + "\n[Сортировка по: " + criteria + "]";
    }
}

class CsvExportDecorator extends ReportDecorator {
    public CsvExportDecorator(IReport report) {
        super(report);
    }

    public String generate() {
        return "CSV ФОРМАТ:\n" + report.generate().replace("\n", ", ");
    }
}

class PdfExportDecorator extends ReportDecorator {
    public PdfExportDecorator(IReport report) {
        super(report);
    }

    public String generate() {
        return "PDF ФОРМАТ:\n" + report.generate();
    }
}

class AmountFilterDecorator extends ReportDecorator {
    private double minAmount;

    public AmountFilterDecorator(IReport report, double minAmount) {
        super(report);
        this.minAmount = minAmount;
    }

    public String generate() {
        return report.generate() + "\n[Фильтр по сумме > " + minAmount + "]";
    }
}

public class ReportApp {
    public static void main(String[] args) {

        IReport report = new SalesReport();

        report = new DateFilterDecorator(report, "2025-01-01", "2025-12-31");
        report = new SortingDecorator(report, "amount");
        report = new AmountFilterDecorator(report, 100);
        report = new CsvExportDecorator(report);

        System.out.println(report.generate());

        System.out.println("\n-----------------\n");

        IReport userReport = new UserReport();
        userReport = new PdfExportDecorator(userReport);

        System.out.println(userReport.generate());
    }
}
