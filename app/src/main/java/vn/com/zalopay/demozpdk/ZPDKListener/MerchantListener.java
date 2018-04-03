package vn.com.zalopay.demozpdk.ZPDKListener;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import vn.zalopay.sdk.ZaloPayErrorCode;
import vn.zalopay.sdk.ZaloPayListener;

public class MerchantListener implements ZaloPayListener {
    private String TAG = "Merchant Callback";
    private Context mContext;

    public MerchantListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onPaymentSucceeded(String transactionId) {
        Log.d(TAG, "onSuccess: On successful with transactionId:" + transactionId);
    }

    @Override
    public void onPaymentError(ZaloPayErrorCode zaloPayErrorCode, int paymentErrorCode) {
        if (zaloPayErrorCode == ZaloPayErrorCode.ZALO_PAY_NOT_INSTALLED) {
            new AlertDialog.Builder(mContext)
                    .setTitle("Error Payment")
                    .setMessage("ZaloPay App not install on this Device.")
                    .setNegativeButton("Yes", null).show();
            Log.d(TAG, "onError: ZaloPay App not install on this Device.");
        } else {
            @SuppressLint("DefaultLocale")
            String mes = String.format("onPaymentError: payment error with [error: %s, paymentError: %d]", zaloPayErrorCode, paymentErrorCode);
            new AlertDialog.Builder(mContext)
                    .setTitle("Error Payment")
                    .setMessage(mes)
                    .setNegativeButton("Yes", null).show();
            Log.d(TAG, mes);
        }
    }
}