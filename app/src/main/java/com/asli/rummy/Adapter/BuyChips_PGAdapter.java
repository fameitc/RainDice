package com.asli.rummy.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asli.rummy.R;
import com.phonepe.intent.sdk.api.UPIApplicationInfo;

import java.util.List;

public class BuyChips_PGAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<UPIApplicationInfo> upiAppList;
    private PGSelection pgSelection;

    public BuyChips_PGAdapter(Context context, List<UPIApplicationInfo> upiAppList, PGSelection pgSelection) {
        this.context = context;
        this.upiAppList = upiAppList;
        this.pgSelection = pgSelection;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_buychips_pg, parent, false);
        return new PGViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PGViewHolder) holder).bind(upiAppList.get(position), pgSelection);
    }

    @Override
    public int getItemCount() {
        return upiAppList.size();
    }

    static class PGViewHolder extends RecyclerView.ViewHolder {

        TextView txtPGName;

        public PGViewHolder(@NonNull View itemView) {
            super(itemView);

            txtPGName = itemView.findViewById(R.id.txtPGName);
        }

        void bind(UPIApplicationInfo upiApp, PGSelection pgSelection) {
            txtPGName.setText(upiApp.getApplicationName());
            txtPGName.setOnClickListener(v -> pgSelection.onPGSelection(upiApp));
        }
    }

    public interface PGSelection {
        void onPGSelection(UPIApplicationInfo upiInfo);
    }
}
