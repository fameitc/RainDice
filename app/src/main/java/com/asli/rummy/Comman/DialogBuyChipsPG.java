package com.asli.rummy.Comman;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.asli.rummy.Adapter.BuyChips_PGAdapter;
import com.asli.rummy.R;
import com.asli.rummy.Utils.Functions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.phonepe.intent.sdk.api.UPIApplicationInfo;

import java.util.List;

public class DialogBuyChipsPG extends BottomSheetDialogFragment {

    private Context context;
    private List<UPIApplicationInfo> upiApps;

    private BuyChips_PGAdapter.PGSelection pgSelection;

    public DialogBuyChipsPG(Context context, List<UPIApplicationInfo> upiApps, BuyChips_PGAdapter.PGSelection pgSelection) {
        this.context = context;
        this.upiApps = upiApps;
        this.pgSelection = pgSelection;
    }

    public void showDialogPG() {
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.setContentView(R.layout.dialog_buychips_pg);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme; //style id
        dialog.setTitle("Title...");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View imgclose = (View) dialog.findViewById(R.id.ivClose);
        RecyclerView recyclerUPIOptions = dialog.findViewById(R.id.recyclerUPIOptions);
        imgclose.setOnClickListener(view -> dialog.dismiss());
        recyclerUPIOptions.setItemAnimator(new DefaultItemAnimator());
        recyclerUPIOptions.setAdapter(new BuyChips_PGAdapter(context, upiApps, upiInfo -> {
            Log.d("Rain Dice", upiInfo.getPackageName());
            pgSelection.onPGSelection(upiInfo);
            dialog.dismiss();
        }));
        dialog.setCancelable(true);
        dialog.show();
        Functions.setDialogParams(dialog);
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
}
