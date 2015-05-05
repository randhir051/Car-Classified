package com.project.cars;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by randhir on 19/4/15.
 */
public class FilterFragment extends DialogFragment {

    Spinner spinner1,spinner2;
    ArrayAdapter<String> adapter1, adapter2;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.filter_dialog, null);

        spinner1 = (Spinner) layout.findViewById(R.id.spinner1);
        spinner2 = (Spinner) layout.findViewById(R.id.spinner2);

        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };
        adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        adapter1.addAll(getResources().getStringArray(R.array.years));
        adapter1.add("Year ?");
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(adapter1.getCount());


        adapter2.addAll(getResources().getStringArray(R.array.models));
        adapter2.add("Company ?");
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(adapter2.getCount());

        builder.setTitle("Filter").setView(layout)
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (spinner1.getSelectedItemId() == adapter1.getCount() && spinner2.getSelectedItemId() == adapter2.getCount()) {
                            Toast.makeText(getActivity(), "Atleast select one filter", Toast.LENGTH_LONG).show();
                        }
                        else {
                            ArrayList<Car> data = new ArrayList<>();
                            int size= ((MainActivity) getActivity()).cars.size();
                            for (int j = 0; j < size; j++) {
                                if (((MainActivity) getActivity()).cars.get(j).year.equals(spinner1.getSelectedItem().toString())||
                                        ((MainActivity) getActivity()).cars.get(j).make.equals(spinner2.getSelectedItem().toString())) {
                                    data.add(((MainActivity) getActivity()).cars.get(j));
                                }
                            }
                            ((MainActivity) getActivity()).cars.clear();
                            ((MainActivity) getActivity()).cars = data;
                            ((MainActivity) getActivity()).myAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Clear Filters", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ((MainActivity) getActivity()).myAdapter.notifyDataSetChanged();
                    }
                });

        return builder.create();

    }
}
