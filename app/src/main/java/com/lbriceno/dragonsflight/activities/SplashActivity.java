package com.lbriceno.dragonsflight.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lbriceno.dragonsflight.R;
import com.lbriceno.dragonsflight.model.Api;
import com.lbriceno.dragonsflight.model.CurrencyJob;
import com.lbriceno.dragonsflight.model.FlightsJob;
import com.path.android.jobqueue.BaseJob;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.di.DependencyInjector;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements Api.OnModelUpdate {
    @Bind(R.id.splash_text)
    TextView mSplashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ButterKnife.bind(this);

        Configuration configuration = new Configuration.Builder(getApplicationContext()).build();
        JobManager manager = new JobManager(this,configuration);
        manager.addJob(new CurrencyJob("USD"));
        manager.addJob(new CurrencyJob("GBP"));
        manager.addJob(new CurrencyJob("JPY"));
        manager.start();

        Api.getInstance().getFlights(this);
    }

    @Override
    public void updateUI(String message, boolean finish) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        mSplashText.setText(message);
        if (finish) {
            startActivity(intent);
            finish();
        }
    }
}
