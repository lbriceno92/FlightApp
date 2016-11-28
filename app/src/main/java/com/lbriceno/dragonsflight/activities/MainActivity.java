package com.lbriceno.dragonsflight.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lbriceno.dragonsflight.R;
import com.lbriceno.dragonsflight.adapter.FlighsRecyclerAdapter;
import com.lbriceno.dragonsflight.model.entities.Flight;
import com.lbriceno.dragonsflight.tools.DividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, TextView.OnEditorActionListener {

    private RealmResults<Flight> result;

    @Bind(R.id.flights_recycler)
    public RecyclerView mRecycler;
    @Bind(R.id.min)
    public EditText min;
    @Bind(R.id.max)
    public EditText max;

    private FlighsRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        result = Realm.getDefaultInstance().where(Flight.class).findAll().sort("price", Sort.ASCENDING);

        result.addChangeListener(new RealmChangeListener<RealmResults<Flight>>() {
            @Override
            public void onChange(RealmResults<Flight> element) {
                mRecycler.getAdapter().notifyDataSetChanged();
            }
        });

        min.setOnEditorActionListener(this);
        max.setOnEditorActionListener(this);


        adapter = new FlighsRecyclerAdapter(this, result, true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycler.setAdapter(adapter);
        mRecycler.setHasFixedSize(true);
        mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (newText.isEmpty())
            result = Realm.getDefaultInstance().where(Flight.class).findAll().sort("price", Sort.ASCENDING);

        result = Realm.getDefaultInstance().where(Flight.class).contains("inbound.destination", newText, Case.INSENSITIVE).findAllSorted("price", Sort.ASCENDING);
        adapter.updateData(result);
        return false;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        double minValue = min.getText().toString().isEmpty() ? 0 : Double.parseDouble(min.getText().toString());
        double maxValue = max.getText().toString().isEmpty() ? Double.MAX_VALUE : Double.parseDouble(max.getText().toString());

        result = Realm.getDefaultInstance().where(Flight.class).between("price", minValue, maxValue).findAllSorted("price", Sort.ASCENDING);
        adapter.updateData(result);

        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        return false;
    }
}
