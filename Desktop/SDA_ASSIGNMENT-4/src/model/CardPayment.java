package model;

public class CardPayment extends Payment {
    private String cardHolder;
    private String cardLast4;
    private boolean validated;

    public CardPayment(int paymentId, String cardHolder, String cardLast4) {
        super(paymentId);
        this.cardHolder = cardHolder;
        this.cardLast4 = cardLast4;
        this.validated = false;
    }

    @Override
    public boolean process(double amount) {
        if (amount > 0 && validateCard()) {
            setAmount(amount);
            setPaymentStatus("Paid");
            setTransactionRef("CARD-" + cardLast4);
            return true;
        }
        markFailed("Card validation failed");
        return false;
    }

    public boolean validateCard() {
        validated = cardHolder != null && cardLast4 != null && cardLast4.length() == 4;
        return validated;
    }

    public String getReceipt() {
        return "Card receipt: " + getTransactionRef() + ", Rs. " + getAmount();
    }

    public String getCardHolder() { return cardHolder; }
    public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }
    public String getCardLast4() { return cardLast4; }
    public void setCardLast4(String cardLast4) { this.cardLast4 = cardLast4; }
    public boolean isValidated() { return validated; }
}
