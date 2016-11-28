package com.lbriceno.dragonsflight.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lbriceno.dragonsflight.R;
import com.lbriceno.dragonsflight.databinding.FlightRecyclerItemBinding;
import com.lbriceno.dragonsflight.model.entities.Flight;
import com.squareup.picasso.Picasso;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by luis_ on 11/26/2016.
 */
public class FlighsRecyclerAdapter extends RealmRecyclerViewAdapter<Flight, FlighsRecyclerAdapter.FlightViewHolder> {
    Context mContext;

    public FlighsRecyclerAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Flight> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
        mContext = context;

    }

    @Override
    public FlightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FlightRecyclerItemBinding binder = FlightRecyclerItemBinding.inflate(inflater);
        return new FlightViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(FlightViewHolder holder, int position) {
        Flight flight = getItem(position);
        holder.bind(flight);
        Picasso.with(context).load(flight.getOutbound().getAirlineImage()).into(holder.mBinder.flightInbound);
        Picasso.with(context).load(flight.getInbound().getAirlineImage()).into(holder.mBinder.flightOutbound);
    }

    class FlightViewHolder extends RecyclerView.ViewHolder {

        private final FlightRecyclerItemBinding mBinder;

        public FlightViewHolder(FlightRecyclerItemBinding binder) {
            super(binder.getRoot());
            mBinder = binder;
        }

        public void bind(Flight item) {
            mBinder.setModel(item);
        }
    }
}
