package campusconnect.interfaces;

import campusconnect.exceptions.InvalidPaymentException;

public interface Payable {
    double calculateDue();
    void makePayment(double amount) throws InvalidPaymentException;
}
