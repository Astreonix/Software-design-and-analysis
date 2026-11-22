package model;

public class WalletPayment extends Payment {
    private String walletProvider;
    private String walletNumber;
    private boolean otpVerified;

    public WalletPayment(int paymentId, String walletProvider, String walletNumber) {
        super(paymentId);
        this.walletProvider = walletProvider;
        this.walletNumber = walletNumber;
        this.otpVerified = false;
    }

    @Override
    public boolean process(double amount) {
        if (amount > 0 && verifyOtp()) {
            setAmount(amount);
            setPaymentStatus("Paid");
            setTransactionRef("WALLET-" + walletProvider);
            return true;
        }
        markFailed("OTP verification failed");
        return false;
    }

    public boolean verifyOtp() {
        otpVerified = walletProvider != null && !walletProvider.isEmpty()
                && walletNumber != null && walletNumber.length() >= 10;
        return otpVerified;
    }

    public String getReceipt() {
        return "Wallet receipt: " + getTransactionRef() + ", Rs. " + getAmount();
    }

    public String getWalletProvider() { return walletProvider; }
    public void setWalletProvider(String walletProvider) { this.walletProvider = walletProvider; }
    public String getWalletNumber() { return walletNumber; }
    public void setWalletNumber(String walletNumber) { this.walletNumber = walletNumber; }
    public boolean isOtpVerified() { return otpVerified; }
}
