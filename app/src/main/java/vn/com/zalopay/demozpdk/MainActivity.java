package vn.com.zalopay.demozpdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.zalopay.demozpdk.ZPDKListener.MerchantListener;
import vn.zalopay.sdk.ZaloPaySDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZaloPaySDK.getInstance().payOrder(this, "ZPTransToken", new MerchantListener(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ZaloPaySDK.getInstance().onActivityResult(requestCode, resultCode, data);
    }
}
