package vn.com.zalopay.demozpdk.ZPDKListener;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import vn.com.zalopay.demozpdk.Constant.ConstantCode;
import vn.com.zalopay.demozpdk.Constant.PaymentErrorCode;
import vn.com.zalopay.demozpdk.Helper.Dialog;
import vn.zalopay.sdk.ZaloPayErrorCode;
import vn.zalopay.sdk.ZaloPayListener;
import vn.zalopay.sdk.ZaloPaySDK;


public class MerchantListener implements ZaloPayListener {
    private Context mContext;

    public MerchantListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onPaymentSucceeded(String transactionId, String transToken) {
        @SuppressLint("DefaultLocale")
        Spanned mes = Html.fromHtml(String.format("onPaymentSuccess: <br><br> On successful with: <br>- transactionId: <b> %s  </b> <br> - transtoken: <b>  %s </b> ", transactionId, transToken));
        Dialog.showNaturalDialog(mContext, "Payment Success", mes, "Yes");
        Log.d(ConstantCode.TAG, "onSuccess: On successful with transactionId: " + transactionId + "- transtoken: %s" + transToken);
    }

    @Override
    public void onPaymentError(ZaloPayErrorCode zaloPayErrorCode, int paymentErrorCode, String zpTransToken) {
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
            Log.d(ConstantCode.TAG, "onError: <br> <b> <i> ZaloPay App not install on this Device. </i> </b>");
        } else if (zaloPayErrorCode == ZaloPayErrorCode.PAYMENT_ERROR && paymentErrorCode == PaymentErrorCode.USER_CANCEL.getValue()) {
            @SuppressLint("DefaultLocale")
            Spanned mes = Html.fromHtml("<b> Payment error <b> : User click back return to app.");
            Dialog.showNaturalDialog(mContext, "Payment Error", mes, "Yes");
            Log.d(ConstantCode.TAG, String.valueOf(mes));
        } else {
            @SuppressLint("DefaultLocale")
            Spanned mes = Html.fromHtml(String.format("onPaymentError: <br> <br> payment error with: <br> - error: <b> %s </b> <br> - paymentErrorCode: <b> %d </b> <br> - zpTransToken: <b> %s </b>", zaloPayErrorCode, paymentErrorCode, zpTransToken));
            Dialog.showNaturalDialog(mContext, "Payment Error", mes, "Yes");
            Log.d(ConstantCode.TAG, String.valueOf(mes));
        }
    }
}