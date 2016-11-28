package com.lbriceno.dragonsflight.model;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by luis_ on 11/27/2016.
 */
public class CurrencyJob extends Job {
    public static final int PRIORITY = 1;
    String currency;
    public CurrencyJob(String currency) {
        super(new Params(PRIORITY).requireNetwork().persist());
        this.currency = currency;
    }

    @Override
    public void onAdded() {
        // job added to queue
    }

    @Override
    public void onRun() throws Throwable {
        // do the work
        Api.getInstance().getCurrencyTransformation(currency);

    }

    @Override
    protected void onCancel() {
        // clean up
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
