package vn.com.zalopay.demozpdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.com.zalopay.demozpdk.ZPDKListener.MerchantListener;
import vn.zalopay.sdk.ZaloPaySDK;

public class MainActivity extends AppCompatActivity {

    Button mButtonPayOrder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonPayOrder = (Button) findViewById(R.id.btn_payorder);
        mButtonPayOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZaloPaySDK.getInstance().payOrder(MainActivity.this, "ZPTransToken", new MerchantListener(MainActivity.this));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ZaloPaySDK.getInstance().onActivityResult(requestCode, resultCode, data);
    }
}
