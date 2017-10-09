package com.wuxufanhua.customprogressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wuxufanhua.customprogressview.view.CustomProgressView;

public class MainActivity extends AppCompatActivity {

    private CustomProgressView mCustomProgressView;
    private SeekBar mSeekBar;
    private TextView mTextView;
    private String text = "当前进度为%1$s/%2$s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomProgressView = (CustomProgressView) findViewById(R.id.cpv_iamge);
        mSeekBar = (SeekBar) findViewById(R.id.sb_progress);
        mTextView = (TextView) findViewById(R.id.tv_progress);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCustomProgressView.setProgress(progress);
                mTextView.setText(String.format(text, progress, mSeekBar.getMax()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
