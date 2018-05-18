package com.noteplaces.testmidtrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.midtrans.sdk.corekit.callback.TransactionCallback;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.TransactionResponse;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;


/**
 * documentation midtrans: https://mobile-docs.midtrans.com/pdf/android_midtrans_mobile_doc.pdf
 **/
public class MainActivity extends AppCompatActivity {

    public static String CLIENT_KEY = "Mid-client-QtVBI2YYoEYWdDbz";
    public static String BASE_URL = "https://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SdkUIFlowBuilder.init()
                .setClientKey(CLIENT_KEY) // client_key is mandatory
                .setContext(MainActivity.this) // context is mandatory
                .setTransactionFinishedCallback(new TransactionFinishedCallback() {
                    @Override
                    public void onTransactionFinished(TransactionResult result) {
                        // Handle finished transaction here.
                        Log.wtf("test", "result: " + result.getSource());
                    }
                }) // set transaction finish callback (sdk callback)
                .setMerchantBaseUrl(BASE_URL) //set merchant url (required)
                .enableLog(true) // enable sdk log (optional)
                .setColorTheme(new CustomColorTheme("#FFE51255", "#B61548", "#FFE51255")) // set theme. it will replace theme on snap theme on MAP ( optional)
                .buildSDK();
        //TransactionRequest transactionRequest = new TransactionRequest()

        MidtransSDK.getInstance().paymentUsingMandiriBillPay("", "", new TransactionCallback() {
            @Override
            public void onSuccess(TransactionResponse transactionResponse) {
                Log.e("onSuccess", "=> " + transactionResponse.getBank());
            }

            @Override
            public void onFailure(TransactionResponse transactionResponse, String s) {
                Log.e("onFailure", "=> " + transactionResponse.getApprovalCode());
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("onError", "=> " + throwable.getMessage());
            }
        });


    }
}
