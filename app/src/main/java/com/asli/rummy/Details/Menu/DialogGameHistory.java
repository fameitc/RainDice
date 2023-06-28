package com.asli.rummy.Details.Menu;

import static android.content.Context.MODE_PRIVATE;
import static com.asli.rummy.Activity.Homepage.MY_PREFS_NAME;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.ANIMAL_ROULLETE;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.Andhar_Bahar;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.CAR_ROULLETE;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.COLOR_PREDICTION;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.DRAGON_TIGER;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.JACKPOT_3PATTI;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.RUMMY;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.RUMMY_DEAL;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.RUMMY_POOL;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.SEVEN_UP_DOWN;
import static com.asli.rummy.MyAccountDetails.MyWinnigmodel.TEEN_PATTI;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asli.rummy.Interface.ApiRequest;
import com.asli.rummy.Interface.Callback;
import com.asli.rummy.MyAccountDetails.MyWinnigmodel;
import com.asli.rummy.MyAccountDetails.MyWinningAdapte;
import com.asli.rummy.MyFlowLayout;
import com.asli.rummy.R;
import com.asli.rummy.RedeemCoins.RedeemModel;
import com.asli.rummy.SampleClasses.Const;
import com.asli.rummy.Utils.Functions;
import com.asli.rummy.Utils.SharePref;
import com.asli.rummy.Utils.Variables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DialogGameHistory {

    Dialog alert;
    Context context;
    MyFlowLayout tabLayout;
    TextView nofound;
    ProgressBar progressBar;
    RecyclerView rec_winning;
    MyWinningAdapte myWinningAdapte;
    int[] gamelist = {
            R.string.teenpatti,
            R.string.rummy,
            R.string.rummy_pool,
            R.string.rummy_deal,
            R.string.andharbahar,
            R.string.dragontiger,
            R.string.sevenupdown,
            R.string.jackpotteenpatti,
            R.string.animal_roulette,
            R.string.car_roulette,
            R.string.color_prediction,
    };

    public DialogGameHistory(Context context) {
        this.context = context;
        alert = Functions.DialogInstance(context);
        alert.setContentView(R.layout.dialog_historygame);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tabLayout = alert.findViewById(R.id.lnrTabs);
        nofound = alert.findViewById(R.id.txtnotfound);
        progressBar = alert.findViewById(R.id.progressBar);
        rec_winning = alert.findViewById(R.id.rec_winning);
        rec_winning.setLayoutManager(new LinearLayoutManager(context));
        tabLayout.removeAllViews();
        int count = 0;
        for (int i = 0; i < gamelist.length; i++) {

            if(!SharePref.getIsTeenpattiVisible() && i == 0)
            {
                continue;
            }
            if(!SharePref.getIsRummyVisible()&& i == 1)
            {
                continue;
            }
            if(!Variables.RUMMPOOL_SHOW && i == 2) continue;
            if(!Variables.RUMMYDEAL_SHOW && i == 3) continue;

            if(!SharePref.getIsAndharBaharVisible()&& i == 4)
            {
                continue;
            }
            if(!SharePref.getIsDragonTigerVisible()&& i == 5)
            {
                continue;
            }

            if(!Variables.SEVENUPSDOWN_SHOW && i == 6) continue;
            if(!Variables.JACKPOTGAME_SHOW && i == 7) continue;
            if(!Variables.ANIMALROULETE_SHOW && i == 8) continue;
            if(!Variables.CARROULETE_SHOW && i == 9) continue;
            if(!Variables.COLOR_PREDICTION && i == 10) continue;

            CreateTabsLayout(count, gamelist[i]);
            count++;
        }


        alert.findViewById(R.id.imgclosetop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void CreateTabsLayout(final int position, int name) {
        final View view = Functions.CreateDynamicViews(R.layout.item_gamehistory_tabs,tabLayout,context);
        String strtitle = context.getString(name);
        view.setTag("" + strtitle);

        TextView title = view.findViewById(R.id.tvGameRecord);

        title.setText(strtitle);


//        if (position == 0) {
//            title.setTextColor(context.getResources().getColor(R.color.white));
//            title.setBackground(context.getResources().getDrawable(R.drawable.d_orange_corner));
//            CALL_API_TEENPATII();
//        } else {
//            title.setTextColor(context.getResources().getColor(R.color.black));
//            title.setBackground(context.getResources().getDrawable(R.drawable.d_white_corner));
//        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPagerPostion(strtitle);
            }
        });

        if(position == 0)
            setPagerPostion(strtitle);
    }

    private void setPagerPostion(String name) {
        for (int i = 0; i < tabLayout.getChildCount() ; i++) {

            View view  = tabLayout.getChildAt(i);
            TextView title = view.findViewById(R.id.tvGameRecord);

            if(Functions.getStringFromTextView(title).equalsIgnoreCase(name))
            {
                title.setTextColor(context.getResources().getColor(R.color.black));
                title.setBackground(context.getResources().getDrawable(R.drawable.btn_yellow_discard));
                String teen_patti = Functions.getString(context,gamelist[0]);
                String rummy = Functions.getString(context,gamelist[1]);
                String rummy_pool = Functions.getString(context,gamelist[2]);
                String rummy_deal = Functions.getString(context,gamelist[3]);
                String andharbahar = Functions.getString(context,gamelist[4]);
                String dragontiger = Functions.getString(context,gamelist[5]);
                String sevenupDown = Functions.getString(context,gamelist[6]);

                String jackpotteenpatti = Functions.getString(context,gamelist[7]);
                String animal_roulette = Functions.getString(context,gamelist[8]);
                String car_roulette = Functions.getString(context,gamelist[9]);
                String color_prediction = Functions.getString(context,gamelist[10]);

                if(name.equalsIgnoreCase(teen_patti))
                {
                    CALL_API_TEENPATII();
                }
                else if(name.equalsIgnoreCase(rummy))
                {
                    CALL_API_RUMMY();
                }
                else if(name.equalsIgnoreCase(rummy_pool))
                {
                    CALL_API_RUMMY_POOL();
                }
                else if(name.equalsIgnoreCase(rummy_deal))
                {
                    CALL_API_RUMMY_DEAL();
                }
                else if(name.equalsIgnoreCase(andharbahar))
                {
                    CALL_API_getANDHAR_BAHAR();
                }
                else if(name.equalsIgnoreCase(dragontiger))
                {
                    CALL_API_getDragonTigerHistory();
                }
                else if(name.equalsIgnoreCase(sevenupDown))
                {
                    CALL_API_getSEVEN_UP_HISTORY();
                }
                else if(name.equalsIgnoreCase(jackpotteenpatti))
                {
                    CALL_API_getjackpotteenpatti();
                }
                else if(name.equalsIgnoreCase(animal_roulette))
                {
                    CALL_API_getanimal_roulette();
                }else if(name.equalsIgnoreCase(car_roulette))
                {
                    CALL_API_getcar_roulette();
                }
                else if(name.equalsIgnoreCase(color_prediction))
                {
                    CALL_API_getcolor_prediction();
                }
            } else {
                title.setTextColor(context.getResources().getColor(R.color.white));
                title.setBackground(context.getResources().getDrawable(R.drawable.d_white_corner));
            }

        }
    }



    public void show(){
        alert.show();
        Functions.setDialogParams(alert);
    }
    public void dismiss(){alert.dismiss();}

    ArrayList<MyWinnigmodel> myWinnigmodelArrayList;

    private void CALL_API_TEENPATII(){

        HideProgressBar(false);
        NoDataVisible(false);
        myWinnigmodelArrayList = new ArrayList<>();

        HashMap<String, String> params = new HashMap<String, String>();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id",prefs.getString("user_id", ""));

        ApiRequest.Call_Api(context, Const.TEENPATTI_GAMELOG_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                try {
                    JSONObject jsonObject = new JSONObject(resp);

                    String code = jsonObject.getString("code");

                    if(code.equals("200"))
                    {
                        JSONArray TeenPattiGameLog = jsonObject.optJSONArray("TeenPattiGameLog");
                        if(TeenPattiGameLog != null)
                        {
                            for (int i = 0; i < TeenPattiGameLog.length() ; i++) {

                                JSONObject ListObject= TeenPattiGameLog.getJSONObject(i);
                                MyWinnigmodel usermodel = new MyWinnigmodel();

                                usermodel.id = ListObject.optString("game_id");
                                usermodel.table_id = ListObject.optString("game_id");
                                usermodel.amount = ListObject.optString("winning_amount");
                                usermodel.invest = ListObject.optInt("invest",0);
                                usermodel.winner_id = ListObject.optString("winner_id");
                                usermodel.added_date = ListObject.optString("added_date");
                                usermodel.game_type = TEEN_PATTI;
                                usermodel.ViewType = RedeemModel.GAME_LIST;

                                myWinnigmodelArrayList.add(usermodel);
                            }
                        }

                        Collections.reverse(myWinnigmodelArrayList);
                    }
                    else {
                        NoDataVisible(true);
                    }

                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);



                } catch (JSONException e) {
                    e.printStackTrace();
                    NoDataVisible(true);
                }

                HideProgressBar(true);
            }
        });
    }

    private void CALL_API_RUMMY(){

        HideProgressBar(false);
        NoDataVisible(false);
        myWinnigmodelArrayList = new ArrayList<>();

        HashMap<String, String> params = new HashMap<String, String>();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id",prefs.getString("user_id", ""));

        ApiRequest.Call_Api(context, Const.RUMMY_GAMELOG_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                try {
                    JSONObject jsonObject = new JSONObject(resp);

                    String code = jsonObject.getString("code");

                    if(code.equals("200"))
                    {
                        JSONArray RummyGameLog = jsonObject.optJSONArray("RummyGameLog");
                        if(RummyGameLog != null)
                        {
                            for (int i = 0; i < RummyGameLog.length() ; i++) {

                                JSONObject ListObject= RummyGameLog.getJSONObject(i);
                                MyWinnigmodel usermodel = new MyWinnigmodel();

                                usermodel.id = ListObject.optString("game_id");
                                usermodel.table_id = ListObject.optString("game_id");

                                int win_amount = ListObject.optInt("amount",0);
                                if(win_amount > 0)
                                    usermodel.amount = ListObject.optString("amount");

                                usermodel.invest = ListObject.optInt("amount",0);
                                usermodel.winner_id = ListObject.optString("winner_id");
                                usermodel.added_date = ListObject.optString("added_date");
                                usermodel.game_type = RUMMY;
                                usermodel.ViewType = RedeemModel.GAME_LIST;

                                myWinnigmodelArrayList.add(usermodel);
                            }
                        }

                        Collections.reverse(myWinnigmodelArrayList);
                    }
                    else {
                        NoDataVisible(true);
                    }

                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);



                } catch (JSONException e) {
                    e.printStackTrace();
                    NoDataVisible(true);
                }

                HideProgressBar(true);
            }
        });
    }

    private void CALL_API_RUMMY_POOL(){

        HideProgressBar(false);
        NoDataVisible(false);
        myWinnigmodelArrayList = new ArrayList<>();

        HashMap<String, String> params = new HashMap<String, String>();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id",prefs.getString("user_id", ""));

        ApiRequest.Call_Api(context, Const.RUMMY_POOL_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                try {
                    JSONObject jsonObject = new JSONObject(resp);

                    String code = jsonObject.getString("code");

                    if(code.equals("200"))
                    {
                        JSONArray RummyGameLog = jsonObject.optJSONArray("GameLog");
                        if(RummyGameLog != null)
                        {
                            for (int i = 0; i < RummyGameLog.length() ; i++) {

                                JSONObject ListObject= RummyGameLog.getJSONObject(i);
                                MyWinnigmodel usermodel = new MyWinnigmodel();

                                usermodel.id = ListObject.optString("game_id");
                                usermodel.table_id = ListObject.optString("game_id");

                                int win_amount = ListObject.optInt("amount",0);
                                if(win_amount > 0)
                                    usermodel.amount = ListObject.optString("amount");

                                usermodel.invest = ListObject.optInt("amount",0);
                                usermodel.winner_id = ListObject.optString("winner_id");
                                usermodel.added_date = ListObject.optString("added_date");
                                usermodel.game_type = RUMMY_POOL;
                                usermodel.ViewType = RedeemModel.GAME_LIST;

                                myWinnigmodelArrayList.add(usermodel);
                            }
                        }

                        Collections.reverse(myWinnigmodelArrayList);
                    }
                    else {
                        NoDataVisible(true);
                    }

                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);



                } catch (JSONException e) {
                    e.printStackTrace();
                    NoDataVisible(true);
                }

                HideProgressBar(true);
            }
        });
    }

    private void CALL_API_RUMMY_DEAL(){

        HideProgressBar(false);
        NoDataVisible(false);
        myWinnigmodelArrayList = new ArrayList<>();

        HashMap<String, String> params = new HashMap<String, String>();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id",prefs.getString("user_id", ""));

        ApiRequest.Call_Api(context, Const.RUMMY_DEAL_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                try {
                    JSONObject jsonObject = new JSONObject(resp);

                    String code = jsonObject.getString("code");

                    if(code.equals("200"))
                    {
                        JSONArray RummyGameLog = jsonObject.optJSONArray("GameLog");
                        if(RummyGameLog != null)
                        {
                            for (int i = 0; i < RummyGameLog.length() ; i++) {

                                JSONObject ListObject= RummyGameLog.getJSONObject(i);
                                MyWinnigmodel usermodel = new MyWinnigmodel();

                                usermodel.id = ListObject.optString("game_id");
                                usermodel.table_id = ListObject.optString("game_id");

                                int win_amount = ListObject.optInt("amount",0);
                                if(win_amount > 0)
                                    usermodel.amount = ListObject.optString("amount");

                                usermodel.invest = ListObject.optInt("amount",0);
                                usermodel.winner_id = ListObject.optString("winner_id");
                                usermodel.added_date = ListObject.optString("added_date");
                                usermodel.game_type = RUMMY_DEAL;
                                usermodel.ViewType = RedeemModel.GAME_LIST;

                                myWinnigmodelArrayList.add(usermodel);
                            }
                        }

                        Collections.reverse(myWinnigmodelArrayList);
                    }
                    else {
                        NoDataVisible(true);
                    }

                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);



                } catch (JSONException e) {
                    e.printStackTrace();
                    NoDataVisible(true);
                }

                HideProgressBar(true);
            }
        });
    }


    private void CALL_API_getANDHAR_BAHAR() {

        HideProgressBar(false);
        NoDataVisible(false);
        myWinnigmodelArrayList.clear();

        HashMap<String, String> params = new HashMap<String, String>();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id",prefs.getString("user_id", ""));

        ApiRequest.Call_Api(context, Const.GETHISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                try {
                    JSONObject jsonObject = new JSONObject(resp);

                    String code = jsonObject.getString("code");

                    if(code.equals("200"))
                    {

                        JSONArray arraygame_dataa = jsonObject.optJSONArray("GameLog");
                        if(arraygame_dataa != null)
                        {
                            for (int i = 0; i < arraygame_dataa.length(); i++) {
                                JSONObject welcome_bonusObject = arraygame_dataa.getJSONObject(i);

                                MyWinnigmodel model = new MyWinnigmodel();
                                model.setId(welcome_bonusObject.getString("id"));
                                model.setAnder_baher_id(welcome_bonusObject.getString("ander_baher_id"));
                                model.setAdded_date(welcome_bonusObject.getString("added_date"));
                                model.setAmount(welcome_bonusObject.getString("amount"));
                                model.setWinning_amount(welcome_bonusObject.getString("winning_amount"));
                                model.amount = welcome_bonusObject.optString("winning_amount");
                                model.invest = welcome_bonusObject.optInt("amount",0);
                                model.setBet(welcome_bonusObject.getString("bet"));
                                model.setRoom_id(welcome_bonusObject.getString("room_id"));
                                model.game_type = Andhar_Bahar;
                                model.ViewType = RedeemModel.GAME_LIST;
                                myWinnigmodelArrayList.add(model);

                            }
                        }

                        Collections.reverse(myWinnigmodelArrayList);
                    }
                    else {
                        NoDataVisible(true);
                    }

                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);



                } catch (JSONException e) {
                    e.printStackTrace();
                    NoDataVisible(true);
                }

                HideProgressBar(true);
            }
        });


    }


    private void CALL_API_getDragonTigerHistory() {

        HideProgressBar(false);
        NoDataVisible(false);

        myWinnigmodelArrayList.clear();

        HashMap params = new HashMap();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id", prefs.getString("user_id", ""));
        params.put("token", prefs.getString("token", ""));

        ApiRequest.Call_Api(context, Const.DRAGON_TIGER_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");

                        if (code.equalsIgnoreCase("200")) {


                            JSONArray arraygame_dataa = jsonObject.optJSONArray("GameLog");
                            if(arraygame_dataa != null)
                            {
                                for (int i = 0; i < arraygame_dataa.length(); i++) {
                                    JSONObject welcome_bonusObject = arraygame_dataa.getJSONObject(i);

                                    MyWinnigmodel model = new MyWinnigmodel();
                                    model.setId(welcome_bonusObject.optString("id"));
                                    model.setAnder_baher_id(welcome_bonusObject.optString("ander_baher_id"));
                                    model.setAdded_date(welcome_bonusObject.optString("added_date"));
                                    model.setAmount(welcome_bonusObject.optString("amount"));
                                    model.setWinning_amount(welcome_bonusObject.optString("winning_amount"));
                                    model.amount = welcome_bonusObject.optString("winning_amount");
                                    model.invest = welcome_bonusObject.optInt("amount",0);
                                    model.setBet(welcome_bonusObject.optString("bet"));
                                    model.setRoom_id(welcome_bonusObject.optString("room_id"));
                                    model.game_type = DRAGON_TIGER;
                                    model.ViewType = RedeemModel.GAME_LIST;
                                    myWinnigmodelArrayList.add(model);

                                }
                            }


                        }

                        Collections.reverse(myWinnigmodelArrayList);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);

                    if(myWinnigmodelArrayList.size() <= 0)
                        NoDataVisible(true);
                    HideProgressBar(true);

                }

            }
        });

    }

    private void CALL_API_getSEVEN_UP_HISTORY() {

        HideProgressBar(false);
        NoDataVisible(false);

        myWinnigmodelArrayList.clear();

        HashMap params = new HashMap();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id", prefs.getString("user_id", ""));
        params.put("token", prefs.getString("token", ""));

        ApiRequest.Call_Api(context, Const.SEVEN_UP_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");

                        if (code.equalsIgnoreCase("200")) {


                            JSONArray arraygame_dataa = jsonObject.optJSONArray("GameLog");
                            if(arraygame_dataa != null)
                            {
                                for (int i = 0; i < arraygame_dataa.length(); i++) {
                                    JSONObject welcome_bonusObject = arraygame_dataa.getJSONObject(i);

                                    MyWinnigmodel model = new MyWinnigmodel();
                                    model.setId(welcome_bonusObject.optString("id"));
                                    model.setAnder_baher_id(welcome_bonusObject.optString("ander_baher_id"));
                                    model.setAdded_date(welcome_bonusObject.optString("added_date"));
                                    model.setAmount(welcome_bonusObject.optString("amount"));
                                    model.setWinning_amount(welcome_bonusObject.optString("winning_amount"));
                                    model.amount = welcome_bonusObject.optString("winning_amount");
                                    model.invest = welcome_bonusObject.optInt("amount",0);
                                    model.setBet(welcome_bonusObject.optString("bet"));
                                    model.setRoom_id(welcome_bonusObject.optString("room_id"));
                                    model.game_type = SEVEN_UP_DOWN;
                                    model.ViewType = RedeemModel.GAME_LIST;
                                    myWinnigmodelArrayList.add(model);

                                }
                            }


                        }

                        Collections.reverse(myWinnigmodelArrayList);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);

                    if(myWinnigmodelArrayList.size() <= 0)
                        NoDataVisible(true);
                    HideProgressBar(true);

                }

            }
        });

    }


    private void CALL_API_getjackpotteenpatti() {

        HideProgressBar(false);
        NoDataVisible(false);

        myWinnigmodelArrayList.clear();

        HashMap params = new HashMap();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id", prefs.getString("user_id", ""));
        params.put("token", prefs.getString("token", ""));

        ApiRequest.Call_Api(context, Const.JACKPOT_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");

                        if (code.equalsIgnoreCase("200")) {


                            JSONArray arraygame_dataa = jsonObject.optJSONArray("GameLog");
                            if(arraygame_dataa != null)
                            {
                                for (int i = 0; i < arraygame_dataa.length(); i++) {
                                    JSONObject welcome_bonusObject = arraygame_dataa.getJSONObject(i);

                                    MyWinnigmodel model = new MyWinnigmodel();
                                    model.setId(welcome_bonusObject.optString("id"));
                                    model.setAnder_baher_id(welcome_bonusObject.optString("ander_baher_id"));
                                    model.setAdded_date(welcome_bonusObject.optString("added_date"));
                                    model.setAmount(welcome_bonusObject.optString("amount"));
                                    model.setWinning_amount(welcome_bonusObject.optString("winning_amount"));
                                    model.amount = welcome_bonusObject.optString("winning_amount");
                                    model.invest = welcome_bonusObject.optInt("amount",0);
                                    model.setBet(welcome_bonusObject.optString("bet"));
                                    model.setRoom_id(welcome_bonusObject.optString("room_id"));
                                    model.game_type = JACKPOT_3PATTI;
                                    model.ViewType = RedeemModel.GAME_LIST;
                                    myWinnigmodelArrayList.add(model);

                                }
                            }


                        }

                        Collections.reverse(myWinnigmodelArrayList);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);

                    if(myWinnigmodelArrayList.size() <= 0)
                        NoDataVisible(true);
                    HideProgressBar(true);

                }

            }
        });

    }

    private void CALL_API_getcolor_prediction() {
        HideProgressBar(false);
        NoDataVisible(false);

        myWinnigmodelArrayList.clear();

        HashMap params = new HashMap();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id", prefs.getString("user_id", ""));
        params.put("token", prefs.getString("token", ""));

        ApiRequest.Call_Api(context, Const.COLOR_PREDICTION_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");

                        if (code.equalsIgnoreCase("200")) {


                            JSONArray arraygame_dataa = jsonObject.optJSONArray("GameLog");
                            if(arraygame_dataa != null)
                            {
                                for (int i = 0; i < arraygame_dataa.length(); i++) {
                                    JSONObject welcome_bonusObject = arraygame_dataa.getJSONObject(i);

                                    MyWinnigmodel model = new MyWinnigmodel();
                                    model.setId(welcome_bonusObject.optString("id"));
                                    model.setAnder_baher_id(welcome_bonusObject.optString("ander_baher_id"));
                                    model.setAdded_date(welcome_bonusObject.optString("added_date"));
                                    model.setAmount(welcome_bonusObject.optString("amount"));
                                    model.setWinning_amount(welcome_bonusObject.optString("winning_amount"));
                                    model.amount = welcome_bonusObject.optString("winning_amount");
                                    model.invest = welcome_bonusObject.optInt("amount",0);
                                    model.setBet(welcome_bonusObject.optString("bet"));
                                    model.setRoom_id(welcome_bonusObject.optString("room_id"));
                                    model.game_type = COLOR_PREDICTION;
                                    model.ViewType = RedeemModel.GAME_LIST;
                                    myWinnigmodelArrayList.add(model);

                                }
                            }


                        }

                        Collections.reverse(myWinnigmodelArrayList);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);

                    if(myWinnigmodelArrayList.size() <= 0)
                        NoDataVisible(true);
                    HideProgressBar(true);

                }

            }
        });

    }

    private void CALL_API_getcar_roulette() {
        HideProgressBar(false);
        NoDataVisible(false);

        myWinnigmodelArrayList.clear();

        HashMap params = new HashMap();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id", prefs.getString("user_id", ""));
        params.put("token", prefs.getString("token", ""));

        ApiRequest.Call_Api(context, Const.CAR_ROULETTE_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");

                        if (code.equalsIgnoreCase("200")) {


                            JSONArray arraygame_dataa = jsonObject.optJSONArray("GameLog");
                            if(arraygame_dataa != null)
                            {
                                for (int i = 0; i < arraygame_dataa.length(); i++) {
                                    JSONObject welcome_bonusObject = arraygame_dataa.getJSONObject(i);

                                    MyWinnigmodel model = new MyWinnigmodel();
                                    model.setId(welcome_bonusObject.optString("id"));
                                    model.setAnder_baher_id(welcome_bonusObject.optString("ander_baher_id"));
                                    model.setAdded_date(welcome_bonusObject.optString("added_date"));
                                    model.setAmount(welcome_bonusObject.optString("amount"));
                                    model.setWinning_amount(welcome_bonusObject.optString("winning_amount"));
                                    model.amount = welcome_bonusObject.optString("winning_amount");
                                    model.invest = welcome_bonusObject.optInt("amount",0);
                                    model.setBet(welcome_bonusObject.optString("bet"));
                                    model.setRoom_id(welcome_bonusObject.optString("room_id"));
                                    model.game_type = CAR_ROULLETE;
                                    model.ViewType = RedeemModel.GAME_LIST;
                                    myWinnigmodelArrayList.add(model);

                                }
                            }


                        }

                        Collections.reverse(myWinnigmodelArrayList);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);

                    if(myWinnigmodelArrayList.size() <= 0)
                        NoDataVisible(true);
                    HideProgressBar(true);

                }

            }
        });

    }

    private void CALL_API_getanimal_roulette() {
        HideProgressBar(false);
        NoDataVisible(false);

        myWinnigmodelArrayList.clear();

        HashMap params = new HashMap();
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        params.put("user_id", prefs.getString("user_id", ""));
        params.put("token", prefs.getString("token", ""));

        ApiRequest.Call_Api(context, Const.ANIMAL_ROULETTE_HISTORY, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");

                        if (code.equalsIgnoreCase("200")) {


                            JSONArray arraygame_dataa = jsonObject.optJSONArray("GameLog");
                            if(arraygame_dataa != null)
                            {
                                for (int i = 0; i < arraygame_dataa.length(); i++) {
                                    JSONObject welcome_bonusObject = arraygame_dataa.getJSONObject(i);

                                    MyWinnigmodel model = new MyWinnigmodel();
                                    model.setId(welcome_bonusObject.optString("id"));
                                    model.setAnder_baher_id(welcome_bonusObject.optString("ander_baher_id"));
                                    model.setAdded_date(welcome_bonusObject.optString("added_date"));
                                    model.setAmount(welcome_bonusObject.optString("amount"));
                                    model.setWinning_amount(welcome_bonusObject.optString("winning_amount"));
                                    model.amount = welcome_bonusObject.optString("winning_amount");
                                    model.invest = welcome_bonusObject.optInt("amount",0);
                                    model.setBet(welcome_bonusObject.optString("bet"));
                                    model.setRoom_id(welcome_bonusObject.optString("room_id"));
                                    model.game_type = ANIMAL_ROULLETE;
                                    model.ViewType = RedeemModel.GAME_LIST;
                                    myWinnigmodelArrayList.add(model);

                                }
                            }


                        }

                        Collections.reverse(myWinnigmodelArrayList);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    myWinningAdapte = new MyWinningAdapte(context,myWinnigmodelArrayList);
                    rec_winning.setAdapter(myWinningAdapte);

                    if(myWinnigmodelArrayList.size() <= 0)
                        NoDataVisible(true);
                    HideProgressBar(true);

                }

            }
        });

    }


    private void NoDataVisible(boolean visible){

        nofound.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void HideProgressBar(boolean gone){
        progressBar.setVisibility(!gone ? View.VISIBLE : View.GONE);
    }

}
