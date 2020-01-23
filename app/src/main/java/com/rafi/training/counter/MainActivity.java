package com.rafi.training.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String STATE_COUNT = "state_count";
    private final String STATE_LAST_ORIENTATION = "state_last_orientation";
    //pembuatan objec component
    TextView tvCount;
    Button btnCount, btnReset;
    int count = 0; //tipe data primitif
    //Integer count = 0; //tipe data reference

    int last_orientation = 1; // 1 for portrait
    private Bundle outstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//bind activity ke layout yang akan ditampilkan

        //binding component view
        tvCount = findViewById(R.id.tv_count);
        btnCount = findViewById(R.id.btn_count);
        btnReset = findViewById(R.id.btn_reset);

        if(getResources().getConfiguration().orientation == 1){ // jika orientasi saat ini adalah portrait
            last_orientation = 2; // last nya adalah landscape
        }

        if(savedInstanceState != null){ //cek apakah ada state yang tersimpan ?
            count = savedInstanceState.getInt(STATE_COUNT);
            last_orientation = savedInstanceState.getInt(STATE_LAST_ORIENTATION);
            if(count >= 1000){
                //set font size
                if(last_orientation == Configuration.ORIENTATION_LANDSCAPE){
                    //if last is landscape and now is portrait
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.smaller_text_tv_count));
                }
                else{
                    //if last is portrait and now is lands
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.default_text_tv_count_land));
                }
            }
            else{
                if(last_orientation == Configuration.ORIENTATION_LANDSCAPE){
                    //if last is landscape and now is portrait
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.default_text_tv_count));
                }
                else{
                    //if last is portrait and now is lands
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.default_text_tv_count_land));
                }
            }
            tvCount.setText(String.valueOf(count));
        }

        //cara 1 untuk set OnClick pada component
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something here
                count++;
                if(count == 1000) {
                    if(last_orientation == Configuration.ORIENTATION_LANDSCAPE){
                        //if last is landscape and now is portrait
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.smaller_text_tv_count));
                }
                    else{
                        //if last is portrait and now is lands
                        tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.default_text_tv_count_land));
                    }

                }
                tvCount.setText(String.valueOf(count));

                //tvCount.setText(count_toString());

                Log.i("COUNT IS : ",String.valueOf(count));

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;

                if(last_orientation == Configuration.ORIENTATION_LANDSCAPE){
                    //if last is landscape and now is portrait
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.default_text_tv_count));
                }
                else{
                    //if last is portrait and now is lands
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.default_text_tv_count_land));
                }
                tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.default_text_tv_count));

                tvCount.setText(String.valueOf(count));
            }
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outstate);
        //menyimpan state
        outstate.putInt(STATE_COUNT,count);
        outState.putInt(STATE_LAST_ORIENTATION,getResources().getConfiguration().orientation);
    }
}
