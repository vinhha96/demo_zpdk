package vn.com.zalopay.demozpdk.ZPDKListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import vn.com.zalopay.demozpdk.Constant.PaymentErrorCode;
import vn.com.zalopay.demozpdk.MainActivity;
import vn.zalopay.sdk.ZaloPayErrorCode;
import vn.zalopay.sdk.ZaloPayListener;
import vn.zalopay.sdk.ZaloPaySDK;

public class MerchantListener implements ZaloPayListener {
    private String TAG = "Merchant Callback";
    private Context mContext;

    public MerchantListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onPaymentSucceeded(String transactionId) {
        @SuppressLint("DefaultLocale")
        String mes = String.format("onPaymentSuccess: On successful with transactionId: %s]", transactionId);
        new AlertDialog.Builder(mContext)
                .setTitle("Payment Success")
                .setMessage(mes)
                .setNegativeButton("Yes", null).show();
        Log.d(TAG, "onSuccess: On successful with transactionId:" + transactionId);
    }

    @Override
    public void onPaymentError(ZaloPayErrorCode zaloPayErrorCode, int paymentErrorCode) {
        if (zaloPayErrorCode == ZaloPayErrorCode.ZALO_PAY_NOT_INSTALLED) {
            new AlertDialog.Builder(mContext)
                    .setTitle("Error Payment")
                    .setMessage("ZaloPay App not install on this Device.")
                    .setPositiveButton("Open Market", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ZaloPaySDK.getInstance().navigateToStore(mContext);
                        }
                    })
                    .setNegativeButton("Back", null).show();
            Log.d(TAG, "onError: ZaloPay App not install on this Device.");
        } else if (zaloPayErrorCode == ZaloPayErrorCode.PAYMENT_ERROR && paymentErrorCode == PaymentErrorCode.USER_CANCEL.getValue()) {
            @SuppressLint("DefaultLocale")
            String mes = "Payment error: User click back return to app.";
            new AlertDialog.Builder(mContext)
                    .setTitle("Error Payment")
                    .setMessage(mes)
                    .setNegativeButton("Yes", null).show();
            Log.d(TAG, mes);
        } else {
            @SuppressLint("DefaultLocale")
            String mes = String.format("onPaymentError: payment error with [error: %s, paymentErrorCode: %d]", zaloPayErrorCode, paymentErrorCode);
            new AlertDialog.Builder(mContext)
                    .setTitle("Error Payment")
                    .setMessage(mes)
                    .setNegativeButton("Yes", null).show();
            Log.d(TAG, mes);
        }
    }
}