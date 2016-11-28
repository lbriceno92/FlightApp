package com.lbriceno.dragonsflight.model;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by luis_ on 11/27/2016.
 */
public class FlightsJob extends Job {

    public static final int PRIORITY = 2;
    private final Api.OnModelUpdate onModelUpdateListener;

    public FlightsJob(Api.OnModelUpdate onModelUpdateListener) {
        super(new Params(PRIORITY).requireNetwork().persist());
        this.onModelUpdateListener = onModelUpdateListener;
    }

    @Override
    public void onAdded() {
        // job added to queue
    }

    @Override
    public void onRun() throws Throwable {
        // do the work
        Api.getInstance().getFlights(onModelUpdateListener);
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
