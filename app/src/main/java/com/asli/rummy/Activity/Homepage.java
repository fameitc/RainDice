package com.asli.rummy.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.asli.rummy.Interface.ApiRequest;
import com.first_player_games.Api.LudoApiRepository;
import com.first_player_games.Home_Activity;
import com.first_player_games.LocalGame.LocalGame;
import com.first_player_games.LocalGame.VsComputer;
import com.first_player_games.Menu.ComputerGameMenu;
import com.first_player_games.Menu.LocalGameMenu;
import com.first_player_games.OnlineGame.Lobby.RoomCreationActivity;
import com.first_player_games.OnlineGame.Lobby.RoomJoinActivity;
import com.first_player_games.ludoApi.TableMaster;
import com.first_player_games.ludoApi.bottomFragment.LudoActiveTables_BF;
import com.asli.rummy.Adapter.HomegameListAdapter;
import com.asli.rummy.BaseActivity;
import com.asli.rummy.Comman.CommonAPI;
import com.asli.rummy.Comman.DialogRestrictUser;
import com.asli.rummy.Comman.DialogSettingOption;
import com.asli.rummy.Details.GameDetails_A;
import com.asli.rummy.Interface.OnItemClickListener;
import com.asli.rummy.LocationManager.GetLocationlatlong;
import com.asli.rummy.LocationManager.GpsUtils;
import com.asli.rummy.Menu.DialogDailyBonus;
import com.asli.rummy.Menu.DialogReferandEarn;
import com.asli.rummy.Menu.DialogUserRanking;
import com.asli.rummy.MyFlowLayout;
import com.asli.rummy.RedeemCoins.WithdrawalList;
import com.asli.rummy.Statement;
import com.asli.rummy.Utils.Sound;
import com.asli.rummy._AdharBahar.Andhar_Bahar_NewUI;
import com.asli.rummy._AnimalRoulate.AnimalRoulette_A;
import com.asli.rummy._CarRoullete.CarRoullete_A;
import com.asli.rummy._CoinFlip.HeadTail_A;
import com.asli.rummy._ColorPrediction.ColorPrediction;
import com.asli.rummy._DragonTiger.DragonTiger_A;
import com.asli.rummy.Fragments.ActiveTables_BF;
import com.asli.rummy.Fragments.UserInformation_BT;
import com.asli.rummy.Utils.SharePref;
import com.asli.rummy.Utils.Variables;
import com.asli.rummy.SampleClasses.Const;
import com.asli.rummy.Interface.Callback;
import com.asli.rummy.R;
import com.asli.rummy.Utils.Animations;
import com.asli.rummy.Utils.Functions;
import com.asli.rummy._LuckJackpot.LuckJackPot_A;
import com.asli.rummy._Poker.Fragment.PokerActiveTables_BF;
import com.asli.rummy._RedBlack.RedBlackPot_A;
import com.asli.rummy._RummyDeal.DialogDealType;
import com.asli.rummy._RummyDeal.Fragments.RummyDealActiveTables_BF;
import com.asli.rummy._RummyDeal.RummyDealGame;
import com.asli.rummy._RummyPull.Fragments.RummyActiveTables_BF;
import com.asli.rummy._SevenUpGames.SevenUp_A;
import com.asli.rummy._TeenPatti.PublicTable_New;
import com.asli.rummy._Tournament.TourList;
import com.asli.rummy._baccarat.Baccarat_A;
import com.asli.rummy._jhandhiMunda.JhandhiMunda_A;
import com.asli.rummy._rouleteGame.RouleteGame_A;
import com.asli.rummy.account.LoginScreen;
import com.asli.rummy.model.HomescreenModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.phonepe.intent.sdk.api.PhonePe;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.asli.rummy.GAMES.ANDHAR_BAHAR;
import static com.asli.rummy.GAMES.ANIMAL_ROULETTE;
import static com.asli.rummy.GAMES.BACCARAT;
import static com.asli.rummy.GAMES.CAR_ROULETTE;
import static com.asli.rummy.GAMES.COLOUR_PREDICTION;
import static com.asli.rummy.GAMES.CUSTOM_TABLE;
import static com.asli.rummy.GAMES.DEAL_RUMMY;
import static com.asli.rummy.GAMES.DRAGON_TIGER;
import static com.asli.rummy.GAMES.HEAD_TAIL;
import static com.asli.rummy.GAMES.JACKPOT_3PATTI;
import static com.asli.rummy.GAMES.JHANDHI_MUNDA;
import static com.asli.rummy.GAMES.LUDO_GAME_COMPUTER;
import static com.asli.rummy.GAMES.LUDO_GAME_ONLINE;
import static com.asli.rummy.GAMES.LUDO_GAME_PLAY_LOCAL;
import static com.asli.rummy.GAMES.POINT_RUMMY;
import static com.asli.rummy.GAMES.POKER_GAME;
import static com.asli.rummy.GAMES.POOL_RUMMY;
import static com.asli.rummy.GAMES.PRIVATE_RUMMY;
import static com.asli.rummy.GAMES.PRIVATE_TABLE;
import static com.asli.rummy.GAMES.RED_BLACK;
import static com.asli.rummy.GAMES.SEVEN_UP_DOWN;
import static com.asli.rummy.GAMES.TEENPATTI;
import static com.asli.rummy.GAMES.ROULETTE;
import static com.asli.rummy.GAMES.TOURNAMENT;
import static com.asli.rummy.LocationManager.GpsUtils.ENABLE_LOCATION_CODE;
import static com.asli.rummy.Utils.Functions.convertDpToPixel;


public class Homepage extends BaseActivity {
    LocalGameMenu localGameMenu;
    ComputerGameMenu computergamemenu;
    List<String> ban_states = new ArrayList<>();
    Animation animBounce, animBlink;
    public static final String MY_PREFS_NAME = "Login_data";
    ImageView imgLogout, imgshare, imaprofile;
    ImageView imgpublicGame, imgPrivategame, ImgCustomePage, ImgVariationGane, iv_andher;
    private String user_id, name, mobile, profile_pic, referral_code, wallet, game_played, bank_detail, adhar_card, upi;
    private TextView txtName, txtwallet, txtproname;
    LinearLayout lnrbuychips, lnrinvite, lnrmail, lnrsetting, lnrvideo;
    Typeface helvatikaboldround, helvatikanormal;
    public String token = "";
    private String game_for_private, app_version;
    SeekBar sBar;
    SeekBar sBarpri;
    ImageView imgCreatetable, imgCreatetablecustom, imgclosetoppri, imgclosetop;
    int pval = 1;
    int pvalpri = 1;
    Button btnCreatetable;
    Button btnCreatetablepri;
    TextView txtStart, txtLimit, txtwalletchips,
            txtwalletchipspri, txtBootamount, txtPotLimit, txtNumberofBlind,
            txtMaximumbetvalue, tvUserCategory;
    TextView txtStartpri, txtLimitpri, txtBootamountpri, txtPotLimitpri, txtNumberofBlindpri, txtMaximumbetvaluepri;
    RelativeLayout rltimageptofile;
    int version = 0;
    public static String profile_img = "";

    RelativeLayout rootView;

    public static String str_colr_pred = "";
    String base_64 = "";
    ProgressDialog progressDialog;
    Activity context = this;

    String REFERRAL_AMOUNT = "referral_amount";
    String JOINING_AMOUNT = "joining_amount";


    Random random = new Random();

    private void emitBubbles() {
        // It will create a thread and attach it to
        // the main thread
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                int size = random.nextInt(250);
//                bubbleEmitter.emitBubble(size);
//                bubbleEmitter.setColors(android.R.color.transparent,
//                        android.R.color.holo_blue_light,
//                        android.R.color.holo_blue_bright);
//                emitBubbles();
            }
        }, random.nextInt(500));
    }


//    BubbleEmitterView bubbleEmitter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        PhonePe.init(Homepage.this);
        String string_signature = PhonePe.getPackageSignature();
        Log.d("Rain Dice", "Signature:" + string_signature);

        LudoApiRepository.getInstance().init(this);

        soundMedia = new Sound();
        initGamesTabs();

        SharePref.getInstance().init(context);

        findViewById(R.id.rltDragonTiger).setVisibility(SharePref.getIsDragonTigerVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltTeenpatti).setVisibility(SharePref.getIsTeenpattiVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltPrivate).setVisibility(SharePref.getIsPrivateVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltCustom).setVisibility(SharePref.getIsCustomVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltAndharbhar).setVisibility(SharePref.getIsAndharBaharVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltRummy).setVisibility(SharePref.getIsRummyVisible()
                ? View.VISIBLE : View.GONE);

        findViewById(R.id.rltJackpot).setVisibility(Variables.JACKPOTGAME_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltRummyDeal).setVisibility(Variables.RUMMYDEAL_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltRummyPool).setVisibility(Variables.RUMMPOOL_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltSeveUp).setVisibility(Variables.SEVENUPSDOWN_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltCarRoullete).setVisibility(Variables.CARROULETE_SHOW ? View.VISIBLE : View.GONE);
        findViewById(R.id.rltAnimalRoullete).setVisibility(Variables.ANIMALROULETE_SHOW ? View.VISIBLE : View.GONE);

        tvUserCategory = findViewById(R.id.tvUserCategory);
        tvUserCategory.setOnClickListener(v -> openUserRanking());
        imgLogout = findViewById(R.id.imgLogout);
        initHomeScreenModel();

        lnrbuychips = findViewById(R.id.lnrbuychips);
        lnrmail = findViewById(R.id.lnrmail);
        lnrinvite = findViewById(R.id.lnrinvite);
        lnrsetting = findViewById(R.id.lnrsetting);


        imaprofile = findViewById(R.id.imaprofile);


        emitBubbles();

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");


        FrameLayout home_container = findViewById(R.id.home_container);
        home_container.setVisibility(View.VISIBLE);


        rootView = findViewById(R.id.rlt_animation_layout);
        RelativeLayout relativeLayout = findViewById(R.id.rlt_parent);
//        SetBackgroundImageAsDisplaySize(context,relativeLayout,R.drawable.splash);


//        BonusView();


        // Set fullscreen
        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        helvatikaboldround = Typeface.createFromAsset(getAssets(),
                "fonts/helvetica-rounded-bold-5871d05ead8de.otf");

        helvatikanormal = Typeface.createFromAsset(getAssets(),
                "fonts/Helvetica.ttf");

        rltimageptofile = findViewById(R.id.rltimageptofile);

        rltimageptofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserInformation_BT userInformation_bt = new UserInformation_BT(new Callback() {
                    @Override
                    public void Responce(String resp, String type, Bundle bundle) {
                        UserProfile();
                    }
                });
                userInformation_bt.setCancelable(false);
                userInformation_bt.show(getSupportFragmentManager(), userInformation_bt.getTag());
            }
        });

        imgpublicGame = findViewById(R.id.imgpublicGame);
        imgPrivategame = findViewById(R.id.imgPrivategame);
        ImgCustomePage = findViewById(R.id.ImgCustomePage);
        ImgVariationGane = findViewById(R.id.ImgVariationGane);
        iv_andher = findViewById(R.id.iv_andher);
        txtName = findViewById(R.id.txtName);
        txtName.setTypeface(helvatikaboldround);
        txtwallet = findViewById(R.id.txtwallet);
        txtwallet.setTypeface(helvatikanormal);
        txtproname = findViewById(R.id.txtproname);
        txtproname.setTypeface(helvatikaboldround);
        TextView txtMail = findViewById(R.id.txtMail);


        // load the animation
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);

        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        imgpublicGame.startAnimation(animBlink);
        imgpublicGame.startAnimation(animBounce);


        imgPrivategame.startAnimation(animBlink);
        imgPrivategame.startAnimation(animBounce);


        ImgCustomePage.startAnimation(animBlink);
        ImgCustomePage.startAnimation(animBounce);
        ImgCustomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomeTeenpatti(view);
            }
        });

        findViewById(R.id.money_transfer).setOnClickListener(view -> {
            dialog_send_money();
        });
        ImgVariationGane.startAnimation(animBlink);
        ImgVariationGane.startAnimation(animBounce);
        clickTask();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();

                        // Log and toast
                        // String msg = getString(R.string.msg_token_fmt, token);
                        // Log.d(TAG, msg);
                        // Funtions.showToast(context, token);
                        UserProfile();

                    }
                });


        //  rotation_animation(((ImageView) findViewById(R.id.imgsetting)), R.anim.rotationback_animation);


        findViewById(R.id.lnr_redeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, WithdrawalList.class));

//                LoadFragment(new WalletFragment());
            }
        });

        findViewById(R.id.lnr_statement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Statement.class));
            }
        });

        findViewById(R.id.lnr_spinner).setOnClickListener(v -> startActivity(new Intent(context, Spinner_Wheels.class)));
        findViewById(R.id.lnr_spin_win).setOnClickListener(v -> startActivity(new Intent(context, Spinner_Wheels_Reward.class)));

        findViewById(R.id.lnrhistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(context,MyWinningAcitivity.class));
                startActivity(new Intent(context, GameDetails_A.class));

//                showRedeemDailog();
            }
        });
        findViewById(R.id.lnr_mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDailyWinCoins();
            }
        });
        findViewById(R.id.lnr_first_reacharge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDailyWinCoins();
            }
        });


        findViewById(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BuyChipsList.class);
                intent.putExtra("homepage", "homepage");
                startActivity(intent);
//                rotation_animation(findViewById(R.id.iv_add), R.anim.rotationback_animation);
//                UserProfile();
            }
        });


    }

    String[] gamelist = {
            "All",
            "Skills",
            "Sports",
    };
    MyFlowLayout lnrGamesTabs;
    int tabsCount = 0;

    private void initGamesTabs() {
        tabsCount = 0;
        lnrGamesTabs = findViewById(R.id.lnrGamesTabs);
        for (String tabs : gamelist) {
            CreateTabsLayout(tabsCount, tabs);
            tabsCount++;
        }
    }

    private void CreateTabsLayout(final int position, String name) {
        final View view = Functions.CreateDynamicViews(R.layout.item_gamehistory_tabs, lnrGamesTabs, context);
        String strtitle = name;
        view.setTag("" + strtitle);

        TextView title = view.findViewById(R.id.tvGameRecord);

        title.setText(strtitle);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPagerPostion(strtitle);
            }
        });

        if (position == 0)
            setPagerPostion(strtitle);
    }

    String selectedTab = "";

    private void setPagerPostion(String name) {
        for (int i = 0; i < lnrGamesTabs.getChildCount(); i++) {

            View view = lnrGamesTabs.getChildAt(i);
            TextView title = view.findViewById(R.id.tvGameRecord);

            if (Functions.getStringFromTextView(title).equalsIgnoreCase(name)) {
                if (homegameListAdapter != null)
                    homegameListAdapter.getFilter().filter(name);
                title.setTextColor(context.getResources().getColor(R.color.black));
                title.setBackground(context.getResources().getDrawable(R.drawable.btn_yellow_discard));
            } else {
                title.setTextColor(context.getResources().getColor(R.color.white));
                title.setBackground(context.getResources().getDrawable(R.drawable.d_white_corner));
            }

        }
    }

    HomegameListAdapter homegameListAdapter;

    private void initHomeScreenModel() {
        RecyclerView recGamesList = findViewById(R.id.recGamesList);

        homegameListAdapter = new HomegameListAdapter(context);
        homegameListAdapter.setArrayList(getGameList());
        recGamesList.setLayoutManager(new GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false));
        homegameListAdapter.setCallback(new OnItemClickListener() {
            @Override
            public void Response(View v, int position, Object object) {
                Enum gametype = (Enum) object;
                if (TEENPATTI.equals(gametype)) {
                    openPublicTeenpatti(null);
                } else if (DRAGON_TIGER.equals(gametype)) {
                    openDragonTiger(null);
                } else if (ANDHAR_BAHAR.equals(gametype)) {
                    openAndharbahar(null);
                } else if (POINT_RUMMY.equals(gametype)) {
                    openRummyGame(null);
                } else if (PRIVATE_RUMMY.equals(gametype)) {
                    openPrivateRummyTable();
//                    DialogRummyCreateTable.getInstance(context).show();
                } else if (POOL_RUMMY.equals(gametype)) {
                    openRummyPullGame(null);
                } else if (DEAL_RUMMY.equals(gametype)) {
                    openRummyDealGame(null);
                } else if (PRIVATE_TABLE.equals(gametype)) {
                    openPrivateTeenpatti(null);
                } else if (CUSTOM_TABLE.equals(gametype)) {
                    openCustomeTeenpatti(null);
                } else if (SEVEN_UP_DOWN.equals(gametype)) {
                    openSevenup(null);
                } else if (CAR_ROULETTE.equals(gametype)) {
                    openCarRoulette(null);
                } else if (JACKPOT_3PATTI.equals(gametype)) {
                    openLuckJackpotActivity(null);
                } else if (ANIMAL_ROULETTE.equals(gametype)) {
                    openAnimalRoullete(null);
                } else if (COLOUR_PREDICTION.equals(gametype)) {
                    openColorPred(null);
                } else if (POKER_GAME.equals(gametype)) {
                    openPokerGame(null);
                } else if (HEAD_TAIL.equals(gametype)) {
                    openHeadTailGame(null);
                } else if (RED_BLACK.equals(gametype)) {
                    openRedBlackGame(null);
                } else if (LUDO_GAME_PLAY_LOCAL.equals(gametype)) {
                    openLudoGames_LOCAL(null);
                } else if (LUDO_GAME_COMPUTER.equals(gametype)) {
                    openLudoGames_COMPUTER(null);
                } else if (LUDO_GAME_ONLINE.equals(gametype)) {
                    openLudoGames_ONLINE(null);
                } else if (BACCARAT.equals(gametype)) {
                    openBaccarat(null);
                } else if (JHANDHI_MUNDA.equals(gametype)) {
                    openJhandhiMunda(null);
                } else if (ROULETTE.equals(gametype)) {
                    openRoulette(null);
                } else if (TOURNAMENT.equals(gametype)) {
                    openTournament();
                } else {
                    Functions.showToast(context, "Coming soon!");
                }
            }
        });
        recGamesList.setAdapter(homegameListAdapter);
    }

    private void openJhandhiMunda(Object o) {
        startActivity(new Intent(getApplicationContext(), JhandhiMunda_A.class));
    }

    private void openBaccarat(Object o) {
        startActivity(new Intent(getApplicationContext(), Baccarat_A.class));
    }

    private void openLudoGames(View view) {
        startActivity(new Intent(getApplicationContext(), Home_Activity.class));
    }

    private void openLudoGames_LOCAL(View view) {
        LocalGameMenu localgamemenu = new LocalGameMenu(this) {
            public void onGameStartAction(boolean[] players) {
                Intent intent = new Intent(Homepage.this, LocalGame.class);
                intent.putExtra("players", players);
                startActivity(intent);
            }
        };
        localgamemenu.showMenu();

    }

    private void openLudoGames_ONLINE(View view) {
        StartOnlineLudo();
    }

    private void StartOnlineLudo() {
        LudoActiveTables_BF ludoActiveTables_bf = new LudoActiveTables_BF();
        ludoActiveTables_bf.setCallback(new com.first_player_games.ClassCallback() {
            @Override
            public void Response(View v, int position, Object object) {
                if (object instanceof TableMaster.TableDatum) {
                    TableMaster.TableDatum tableDatum = (TableMaster.TableDatum) object;

                    if (!com.first_player_games.Others.Functions.checkStringisValid(tableDatum.getInvite_code()) && !tableDatum.getOnlineMembers().equals("1")) {
                        // Create Free Room
                        startActivity(new Intent(Homepage.this, RoomCreationActivity.class)
                                .putExtra("diamonds", 0).putExtra("boot_value", tableDatum.getBootValue()));
                    } else {
                        String roomid = tableDatum.getInvite_code();
                        Log.e("roomid", "Response: " + roomid);
                        Intent intent = new Intent(Homepage.this, RoomJoinActivity.class);
                        intent.putExtra("roomid", roomid);
                        startActivity(intent);
                    }

                }

            }
        });
        ludoActiveTables_bf.show(getSupportFragmentManager(), "lus");

    }

    private void openLudoGames_COMPUTER(View view) {
        computergamemenu = new ComputerGameMenu(Homepage.this) {
            public void onGameStartAction(int[] players) {
                Intent intent = new Intent(Homepage.this, VsComputer.class);
                intent.putExtra("players", players);
                startActivity(intent);
            }
        };
        computergamemenu.showMenu();
    }

    private void openRedBlackGame(View view) {
        startActivity(new Intent(getApplicationContext(), RedBlackPot_A.class));
    }

    private void openHeadTailGame(View view) {
        startActivity(new Intent(getApplicationContext(), HeadTail_A.class));
    }

    private void openPrivateRummyTable() {
        LoadTableFragments(ActiveTables_BF.RUMMY_PRIVATE_TABLE);
    }

    private List<HomescreenModel> getGameList() {
        List<HomescreenModel> arraylist = new ArrayList<>();
        if (SharePref.getIsTeenpattiVisible())
            arraylist.add(new HomescreenModel(1, TEENPATTI, R.drawable.teenpattiicon_animation, "Skills"));

        if (SharePref.getIsPrivateVisible())
            arraylist.add(new HomescreenModel(2, PRIVATE_TABLE, R.drawable.private_table_animation, "Skills"));

        if (SharePref.getIsCustomVisible())
            arraylist.add(new HomescreenModel(3, CUSTOM_TABLE, R.drawable.custom_boot_animation, "Skills"));

        if (SharePref.getIsHomeJackpotVisible())
            arraylist.add(new HomescreenModel(4, JACKPOT_3PATTI, R.drawable.jakpot_teenpatti_animation, "All"));

        if (SharePref.getIsPointRummyVisible())
            arraylist.add(new HomescreenModel(5, POINT_RUMMY, R.drawable.point_rummy_animation, "Skills"));

        if (SharePref.getIsPrivateRummyVisible())
            arraylist.add(new HomescreenModel(6, PRIVATE_RUMMY, R.drawable.private_rummy_animation, "Skills"));

        if (SharePref.getIsPoolRummyVisible())
            arraylist.add(new HomescreenModel(7, POOL_RUMMY, R.drawable.pool_rummy_animation, "Skills"));

        if (SharePref.getIsDealRummyVisible())
            arraylist.add(new HomescreenModel(8, DEAL_RUMMY, R.drawable.deal_rummy_animation, "Skills"));

        if (SharePref.getIsDragonTigerVisible())
            arraylist.add(new HomescreenModel(9, DRAGON_TIGER, R.drawable.dragon_tiger_animation, "All"));

        if (SharePref.getIsAndharBaharVisible())
            arraylist.add(new HomescreenModel(10, ANDHAR_BAHAR, R.drawable.ander_bhahr_ani, "All"));

        if (SharePref.getIsSevenUpVisible())
            arraylist.add(new HomescreenModel(11, SEVEN_UP_DOWN, R.drawable.seven_up_down_animation, "All"));

        if (SharePref.getIsCarRouletteVisible())
            arraylist.add(new HomescreenModel(12, CAR_ROULETTE, R.drawable.car_roulette_animation, "All"));

        if (SharePref.getIsAnimalRouletteVisible())
            arraylist.add(new HomescreenModel(13, ANIMAL_ROULETTE, R.drawable.animal_roulette_animation, "All"));

        if (SharePref.getIsColorPredictionVisible())
            arraylist.add(new HomescreenModel(14, COLOUR_PREDICTION, R.drawable.color_prediction_animation, "All"));

        if (SharePref.getIsPokerVisible())
            arraylist.add(new HomescreenModel(15, POKER_GAME, R.drawable.poker_animation, "Skills"));

        if (SharePref.getIsHeadTailVisible())
            arraylist.add(new HomescreenModel(16, HEAD_TAIL, R.drawable.head_tail_animation, "All"));

        if (SharePref.getIsRedBlackVisible())
            arraylist.add(new HomescreenModel(17, RED_BLACK, R.drawable.red_black_animation, "All"));

        if (SharePref.getIsLudoVisible())
            arraylist.add(new HomescreenModel(18, LUDO_GAME_PLAY_LOCAL, R.drawable.play_local_ludo_animation, "Skills"));

        if (SharePref.getIsLudoOnlineVisible())
            arraylist.add(new HomescreenModel(19, LUDO_GAME_ONLINE, R.drawable.ludo_play_animation, "Skills"));

        if (SharePref.getIsLudoComputerVisible())
            arraylist.add(new HomescreenModel(20, LUDO_GAME_COMPUTER, R.drawable.ludo_user_computer_animation, "Skills"));

        if (SharePref.getBacarateVisible())
            arraylist.add(new HomescreenModel(21, BACCARAT, R.drawable.baccarat_animation, "Skills"));

        if (SharePref.getJhandi_MundaVisible())
            arraylist.add(new HomescreenModel(22, JHANDHI_MUNDA, R.drawable.jhandi_munda_animation, "Skills"));

        if (SharePref.getRouletteVisible())
            arraylist.add(new HomescreenModel(23, ROULETTE, R.drawable.img_roulette_home, "Skills"));

        if (SharePref.getTournamentVisible())
            arraylist.add(new HomescreenModel(24, TOURNAMENT, R.drawable.tournament, "Skills"));


//        arraylist.add(new HomescreenModel(15,CRICKET_GAME,R.drawable.home_cricket,"Sports"));
//        arraylist.add(new HomescreenModel(16,BEST_OF_5,R.drawable.home_best_of_5,"All"));
        return arraylist;
    }

    private void BonusView() {

        if (SharePref.getInstance().getBoolean(SharePref.isBonusShow))
            lnrmail.setVisibility(View.VISIBLE);
        else
            lnrmail.setVisibility(View.GONE);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String datevalue = prefs.getString("cur_date4", "12/06/2020");


        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate1 = df1.format(c);
        int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd/MM/yyyy"), datevalue, formattedDate1);


        if (dateDifference > 0) {
            // catalog_outdated = 1;

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = df.format(c);

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("cur_date4", formattedDate);
            editor.apply();

           /* if (SharePref.getInstance().getBoolean(SharePref.isBonusShow))
                showDailyWinCoins();*/

        } else {

            System.out.println();


        }

        lnrmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(getApplicationContext(), MaiUserListingActivity.class);
//                startActivity(intent);
//                Funtions.showToast(context, "Coming Soon",
//                        Toast.LENGTH_LONG).show();
                showDailyWinCoins();

            }
        });
    }

    private void showDailyWinCoins() {
        DialogDailyBonus.getInstance(context).returnCallback(new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
                UserProfile();
            }
        }).show();
    }

    private void LoadFragment(Fragment fragment) {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.home_container, fragment).
                addToBackStack(null).
                commit();
    }

    private void BlinkAnimation(final View view) {
        view.setVisibility(View.VISIBLE);
        final Animation animationUtils = AnimationUtils.loadAnimation(context, R.anim.blink);
        animationUtils.setDuration(1000);
        animationUtils.setRepeatCount(1);
        animationUtils.setStartOffset(700);
        view.startAnimation(animationUtils);

        animationUtils.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void shineAnimation(final View view) {
        final Animation animationUtils = AnimationUtils.loadAnimation(context, R.anim.left_to_righ);
        animationUtils.setDuration(1500);
        view.startAnimation(animationUtils);

        animationUtils.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(animationUtils);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    LinearLayout lnr_2player, lnr_5player;
    TextView tv_2player, tv_5player;
    int selected_type = 5;

    public void Dialog_SelectPlayer() {
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle("");
        dialog.setContentView(R.layout.dialog_select_palyer);
        lnr_2player = dialog.findViewById(R.id.lnr_2player);
        lnr_5player = dialog.findViewById(R.id.lnr_5player);
        tv_2player = dialog.findViewById(R.id.tv_2player);
        tv_5player = dialog.findViewById(R.id.tv_5player);

        Button btn_player = dialog.findViewById(R.id.btn_play);

        ChangeTextviewColorChange(5);

        lnr_2player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTextviewColorChange(2);
            }
        });

        lnr_5player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTextviewColorChange(5);
            }
        });

        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (selected_type == 2) {
                    LoadTableFragments(ActiveTables_BF.RUMMY2);

                } else {
                    LoadTableFragments(ActiveTables_BF.RUMMY5);


                }


            }
        });

        dialog.setCancelable(true);
        dialog.show();
        Functions.setDialogParams(dialog);
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


    }

    public void Dialog_SelectPlayerPool() {
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle("");
        dialog.setContentView(R.layout.dialog_select_palyer);
        lnr_2player = dialog.findViewById(R.id.lnr_2player);
        lnr_5player = dialog.findViewById(R.id.lnr_5player);
        tv_2player = dialog.findViewById(R.id.tv_2player);
        tv_5player = dialog.findViewById(R.id.tv_5player);
        tv_5player.setText("6 \nPlayer");

        Button btn_player = dialog.findViewById(R.id.btn_play);

        ChangeTextviewColorChange(5);

        lnr_2player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTextviewColorChange(2);
            }
        });

        lnr_5player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeTextviewColorChange(5);
            }
        });

        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (selected_type == 2) {
                    LoadPullRummyActiveTable(ActiveTables_BF.RUMMY2);

                } else {
                    LoadPullRummyActiveTable(ActiveTables_BF.RUMMY5);
                }


            }
        });

        dialog.setCancelable(true);
        dialog.show();
        Functions.setDialogParams(dialog);
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


    }


    private void LoadTableFragments(String TAG) {
        ActiveTables_BF activeTables_bf = new ActiveTables_BF(TAG);
        activeTables_bf.show(getSupportFragmentManager(), activeTables_bf.getTag());
    }

    private void LoadPointRummyActiveTable(String TAG) {
        RummyActiveTables_BF rummyActiveTables_bf = new RummyActiveTables_BF(TAG);
        rummyActiveTables_bf.show(getSupportFragmentManager(), rummyActiveTables_bf.getTag());
    }

    private void ChangeTextviewColorChange(int colortype) {

        selected_type = colortype;

        if (colortype == 2) {
            tv_2player.setTextColor(getResources().getColor(R.color.white));
            lnr_2player.setBackgroundColor(getResources().getColor(R.color.new_colorPrimary));

            tv_5player.setTextColor(getResources().getColor(R.color.black));
            lnr_5player.setBackgroundColor(getResources().getColor(R.color.white));

        } else {
            tv_2player.setTextColor(getResources().getColor(R.color.black));
            lnr_2player.setBackgroundColor(getResources().getColor(R.color.white));

            tv_5player.setTextColor(getResources().getColor(R.color.white));
            lnr_5player.setBackgroundColor(getResources().getColor(R.color.new_colorPrimary));

        }


    }


    private void rotation_animation(View view, int animation) {

        Animation circle = Functions.AnimationListner(context, animation, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

            }
        });
        view.setAnimation(circle);
        circle.startNow();

    }

    int Counts = 100;
    int postion = -100;

    private void CenterAnimationView(String imagename, View imgcards, int Home_Page_Animation) {


        int AnimationSpeed = Counts + Home_Page_Animation;
        Counts += 300;

        final View fromView, toView;
        rootView.setVisibility(View.VISIBLE);
//        rootView.removeAllViews();
//        View ivpickcard = findViewById(R.id.view_center);

        fromView = rootView;
        toView = imgcards;


        int[] fromLoc = new int[2];
        fromView.getLocationOnScreen(fromLoc);
        float startX = fromLoc[0];
        float startY = fromLoc[1];

        int[] toLoc = new int[2];
        toView.getLocationOnScreen(toLoc);
        float destX = toLoc[0];
        float destY = toLoc[1];

        final ImageView sticker = new ImageView(context);

        int stickerId = Functions.GetResourcePath(imagename, context);

        int card_width = (int) getResources().getDimension(R.dimen.home_card_width);
        int card_hieght = (int) getResources().getDimension(R.dimen.home_card_height);

        Picasso.get().load(stickerId).into(sticker);

        sticker.setLayoutParams(new ViewGroup.LayoutParams(card_width, card_hieght));
        rootView.addView(sticker);


        Animations anim = new Animations();
        Animation a = anim.fromAtoB(0, 0, convertDpToPixel(postion, context), 0, null, AnimationSpeed, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
                fromView.setVisibility(View.VISIBLE);
                toView.setVisibility(View.VISIBLE);
                sticker.setVisibility(View.GONE);
            }
        });
        sticker.setAnimation(a);
        a.startNow();

        postion += 100;

    }

    @Override
    protected void onResume() {
        super.onResume();
        str_colr_pred = "";
//        EnableGPS();
        UserProfile();
        GameLeave();
        UserLudoLeaveTable();
    }

    private void UserLudoLeaveTable() {
        LudoApiRepository.getInstance().call_api_leaveTable();
    }


    public void clickTask() {

        imgPrivategame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialoag();
                openPrivateTeenpatti(null);

            }
        });

        lnrsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogSettingOption dialogSettingOption = new DialogSettingOption(context) {
                    public void playstopSound() {

                        playSound(R.raw.game_soundtrack, true);

                    }
                };

                dialogSettingOption.showDialogSetting();
            }
        });


        imgLogout.setOnClickListener(new View.OnClickListener() {       //contact us
            @Override
            public void onClick(View view) {

//                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Homepage.this);
////                builder.setTitle("Location")
//                builder.setMessage("Please connect us on raindicegamezone@gmail.com")
//                        .setCancelable(true)
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//
//                android.app.AlertDialog alert = builder.create();
//                alert.setTitle("Contact Us");
//                alert.show();
                try {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "raindicegamezone@gmail.com"});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                    emailIntent.setType("text/plain");
                    final PackageManager pm = Homepage.this.getPackageManager();
                    final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
                    ResolveInfo best = null;
                    for(final ResolveInfo info : matches)
                        if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
                            best = info;
                    if (best != null)
                        emailIntent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
                    startActivity(emailIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Homepage.this, "No Gmail App Found", Toast.LENGTH_SHORT).show();
                }

//                new SmartDialogBuilder(context)
//                        .setTitle("Do you want to Logout?")
//                        //.setSubTitle("context is the alert dialog to showing alert to user")
//                        .setCancalable(true)
//                        //.setTitleFont("Do you want to Logout?") //set title font
//                        // .setSubTitleFont(subTitleFont) //set sub title font
//                        .setNegativeButtonHide(true) //hide cancel button
//                        .setPositiveButton("Logout", new SmartDialogClickListener() {
//                            @Override
//                            public void onClick(SmartDialog smartDialog) {
//                                // Funtions.showToast(context,"Ok button Click",Toast.LENGTH_SHORT)
//                                // .show();
//                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                                editor.putString("user_id", "");
//                                editor.putString("name", "");
//                                editor.putString("mobile", "");
//                                editor.putString("token", "");
//                                editor.apply();
//                                Intent intent = new Intent(context, LoginScreen.class);
//                                startActivity(intent);
//                                finish();
//
//                                smartDialog.dismiss();
//                            }
//                        }).setNegativeButton("Cancel", new SmartDialogClickListener() {
//                    @Override
//                    public void onClick(SmartDialog smartDialog) {
//                        // Funtions.showToast(context,"Cancel button Click");
//                        smartDialog.dismiss();
//
//                    }
//                }).build().show();

            }
        });

        lnrinvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogReferandEarn.getInstance(context).show();
            }
        });
        findViewById(R.id.ivIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogReferandEarn.getInstance(context).show();
            }
        });
    }

    public void openBuyChipsActivity(View view) {
        Intent intent = new Intent(context, BuyChipsList.class);
        intent.putExtra("homepage", "homepage");
        startActivity(intent);
    }


    private void UserProfile() {
        CommonAPI.CALL_API_UserDetails(context, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
                if (resp != null) {
                    ParseResponse(resp);
                }
            }
        }, token);

    }

    private void ParseResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String code = jsonObject.getString("code");
            if (code.equalsIgnoreCase("200")) {
                JSONObject jsonObject0 = jsonObject.getJSONArray("user_data").getJSONObject(0);
                user_id = jsonObject0.getString("id");
                name = jsonObject0.optString("name");
                mobile = jsonObject0.optString("mobile");
                profile_pic = jsonObject0.optString("profile_pic");
                profile_img = jsonObject0.optString("profile_pic");
                referral_code = jsonObject0.optString("referral_code");
                wallet = jsonObject0.optString("wallet");
                String winning_wallet = jsonObject0.optString("winning_wallet");
                String spin_remaining = jsonObject0.getString("spin_remaining");
                Log.d("extra_spinner_", spin_remaining);
                SharePref.getInstance().setSpin_remaining(spin_remaining);
                game_played = jsonObject0.optString("game_played");
                bank_detail = jsonObject0.optString("bank_detail");
                upi = jsonObject0.optString("upi");
                adhar_card = jsonObject0.optString("adhar_card");


                // txtName.setText("Welcome Back "+name);

                if (Functions.isStringValid(wallet)) {
                    float f_wallet = Float.parseFloat(wallet);
//                    long numberamount =  Float.parseFloat(f_wallet);
//                    long numberamount = (long) f_wallet;
//                    txtwallet.setText("" + NumberFormat.getNumberInstance(Locale.US).format(f_wallet));
                    txtwallet.setText("" + f_wallet);
                }

                ((TextView) findViewById(R.id.txt_user_id)).setText("ID:" + user_id);
                txtproname.setText(name);
                Picasso.get().load(Const.IMGAE_PATH + profile_pic).into(imaprofile);

                String setting = jsonObject.optString("setting");
                JSONObject jsonObjectSetting = new JSONObject(setting);
                JSONArray avatar = jsonObject.getJSONArray("avatar");

                game_for_private = jsonObjectSetting.optString("game_for_private");
                app_version = jsonObjectSetting.optString("app_version");
                String referral_amount = jsonObjectSetting.optString("referral_amount");
                String referral_link = jsonObjectSetting.optString("referral_link");
                String joining_amount = jsonObjectSetting.optString("joining_amount");
                String whats_no = jsonObjectSetting.optString("whats_no");
                String mBan_states = jsonObjectSetting.optString("ban_states");
                String extra_spinner = jsonObjectSetting.optString("extra_spinner");

                int admin_commission = jsonObjectSetting.optInt("admin_commission", 2);

                ban_states = Arrays.asList(mBan_states.split(","));
//                checkForBanState();


                BonusView();

                ((ImageView) findViewById(R.id.imgicon)).setImageDrawable(
                        Functions.getDrawable(context, R.drawable.ic_coin));

                tvUserCategory.setText(SharePref.getInstance().getUSER_CATEGORY());

            } else if (code.equals("411")) {

                Intent intent = new Intent(context, LoginScreen.class);
                startActivity(intent);
                finishAffinity();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", "");
                editor.putString("referal_code", "");
                editor.putString("img_name", "");
                editor.putString("game_for_private", "");
                editor.putString("app_version", "");
                editor.apply();

                Functions.showToast(context, "You are Logged in from another " +
                        "device.");


            } else {

                if (jsonObject.has("message")) {
                    String message = jsonObject.getString("message");
                    Functions.showToast(context, message);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        findViewById(R.id.iv_add).clearAnimation();
        Functions.dismissLoader();

    }

    void openUserRanking() {
        DialogUserRanking.getInstance(context).setCallback(new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {
            }
        }).show();
    }

    public void PlaySaund(int sound) {

        if (!SharePref.getInstance().isSoundEnable())
            return;

        final MediaPlayer mp = MediaPlayer.create(context,
                sound);
        mp.start();
    }

    public void showDialoagPrivettable() {

        // custom dialog
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.setContentView(R.layout.custom_dialog_custon_boot);
        dialog.setTitle("Title...");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        sBarpri = dialog.findViewById(R.id.seekBar1);
        sBarpri.setProgress(0);
        sBarpri.incrementProgressBy(10);
        sBarpri.setMax(100);
        txtStartpri = dialog.findViewById(R.id.txtStart);
        txtLimitpri = dialog.findViewById(R.id.txtLimit);
        txtwalletchipspri = dialog.findViewById(R.id.txtwalletchips);
        float numberamount = Float.parseFloat(wallet);
        txtwalletchipspri.setText("" + NumberFormat.getNumberInstance(Locale.US).format(numberamount));

        // txtwalletchipspri.setText(wallet);
        txtBootamountpri = dialog.findViewById(R.id.txtBootamount);
        txtPotLimitpri = dialog.findViewById(R.id.txtPotLimit);
        txtNumberofBlindpri = dialog.findViewById(R.id.txtNumberofBlind);
        txtMaximumbetvaluepri = dialog.findViewById(R.id.txtMaximumbetvalue);
        imgclosetoppri = dialog.findViewById(R.id.imgclosetop);
        imgclosetoppri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        imgCreatetable = dialog.findViewById(R.id.imgCreatetable);
        imgCreatetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, PrivateTablev2.class);
                Intent intent = new Intent(context, PublicTable_New.class);
                intent.putExtra("gametype", PublicTable_New.PRIVATE_TABLE);
                intent.putExtra("bootvalue", pvalpri + "");
                startActivity(intent);
                dialog.dismiss();
            }
        });
        // tView.setText(sBar.getProgress() + "/" + sBar.getMax());
        sBarpri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 10;
                if (progress == 1) {

                    pvalpri = 50;

                } else if (progress == 2) {
                    pvalpri = 100;
                } else if (progress == 3) {

                    pvalpri = 500;
                } else if (progress == 4) {

                    pvalpri = 1000;

                } else if (progress == 5) {

                    pvalpri = 5000;

                } else if (progress == 6) {

                    pvalpri = 10000;
                } else if (progress == 7) {

                    pvalpri = 25000;
                } else if (progress == 8) {

                    pvalpri = 50000;
                } else if (progress == 9) {

                    pvalpri = 100000;
                } else if (progress == 10) {

                    pvalpri = 250000;
                }

                //progress = progress * 10;
                // pvalpri = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtBootamountpri.setText("Boot amount : " + kvalue(pvalpri) + "");

                int valueforpot = pvalpri * 1024;
                int valueformaxi = pvalpri * 128;

                //long valueforpotlong= valueforpot;

                txtPotLimitpri.setText("Pot limit : " + kvalue(valueforpot) + "");
                txtMaximumbetvaluepri.setText("Maximumbet balue : " + kvalue(valueformaxi) + "");
                txtNumberofBlindpri.setText("Number of Blinds : 4");
                //tView.setText(pval + "/" + seekBar.getMax());
            }
        });


        dialog.show();
        Functions.setDialogParams(dialog);
    }

    public void showDialoagonBack() {

        // custom dialog
        final Dialog dialog = Functions.DialogInstance(context);
        dialog.setContentView(R.layout.custom_dialog_custon_boot);
        dialog.setTitle("Title...");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        sBar = dialog.findViewById(R.id.seekBar1);
        sBar.setProgress(0);
        sBar.incrementProgressBy(10);
        sBar.setMax(100);
        txtStart = dialog.findViewById(R.id.txtStart);
        txtLimit = dialog.findViewById(R.id.txtLimit);
        txtwalletchips = dialog.findViewById(R.id.txtwalletchips);
        float numberamount = Float.parseFloat(wallet);
        txtwalletchips.setText("" + NumberFormat.getNumberInstance(Locale.US).format(numberamount));
        txtBootamount = dialog.findViewById(R.id.txtBootamount);
        txtPotLimit = dialog.findViewById(R.id.txtPotLimit);
        txtNumberofBlind = dialog.findViewById(R.id.txtNumberofBlind);
        txtMaximumbetvalue = dialog.findViewById(R.id.txtMaximumbetvalue);
        imgclosetop = dialog.findViewById(R.id.imgclosetop);
        imgclosetop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        imgCreatetablecustom = dialog.findViewById(R.id.imgCreatetable);
        imgCreatetablecustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, CustomisedTablev2.class);
                Intent intent = new Intent(context, PublicTable_New.class);
                intent.putExtra("gametype", PublicTable_New.CUSTOME_TABLE);
                intent.putExtra("bootvalue", pval + "");
                startActivity(intent);
                dialog.dismiss();
            }
        });
        // tView.setText(sBar.getProgress() + "/" + sBar.getMax());
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
                progress = progress / 10;
                if (progress == 1) {

                    pval = 50;

                } else if (progress == 2) {
                    pval = 100;
                } else if (progress == 3) {

                    pval = 500;
                } else if (progress == 4) {

                    pval = 1000;

                } else if (progress == 5) {

                    pval = 5000;

                } else if (progress == 6) {

                    pval = 10000;
                } else if (progress == 7) {

                    pval = 25000;
                } else if (progress == 8) {

                    pval = 50000;
                } else if (progress == 9) {

                    pval = 100000;
                } else if (progress == 10) {

                    pval = 250000;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtBootamount.setText("Boot amount : " + kvalue(pval) + "");

                int valueforpot = pval * 1024;
                int valueformaxi = pval * 128;
                txtPotLimit.setText("Pot limit : " + kvalue(valueforpot) + "");
                txtMaximumbetvalue.setText("Maximumbet balue : " + kvalue(valueformaxi) + "");
                txtNumberofBlind.setText("Number of Blinds : 4");
                //tView.setText(pval + "/" + seekBar.getMax());
            }
        });


        dialog.show();
        Functions.setDialogParams(dialog);
    }

    private void GameLeave() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Const.GAME_TABLE_LEAVE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        System.out.println("" + response);
                        // finish();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                params.put("user_id", prefs.getString("user_id", ""));
                params.put("token", prefs.getString("token", ""));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("token", Const.TOKEN);
                return headers;
            }
        };

        Volley.newRequestQueue(context).add(stringRequest);

    }

    public void openLuckJackpotActivity(View view) {
        startActivity(new Intent(context, LuckJackPot_A.class));
    }

    public void openSevenup(View view) {
        startActivity(new Intent(context, SevenUp_A.class));
    }

    public void openPublicTeenpatti(View view) {
        PlaySaund(R.raw.buttontouchsound);
        LoadTableFragments(ActiveTables_BF.TEENPATTI);
    }

    public void openPrivateTeenpatti(View view) {
        PlaySaund(R.raw.buttontouchsound);
        float gamecount = 0;
        if (game_played != null && !game_played.equals("")) {
            gamecount = Float.parseFloat(game_played);
        }
        float game_for_privatetemp = Float.parseFloat(game_for_private);
        if (gamecount > game_for_privatetemp) {

            showDialoagPrivettable();

        } else {

            Functions.showToast(context, "To Unblock Private Table you have to Play at least " + game_for_privatetemp +
                    " Games.");

        }
    }

    public void openCustomeTeenpatti(View view) {
        PlaySaund(R.raw.buttontouchsound);
        showDialoagonBack();
    }

    public void openRummyGame(View view) {
        Dialog_SelectPlayer();
//        LoadPointRummyActiveTable(ActiveTables_BF.RUMMY5);
    }

    public void openRummyPullGame(View view) {
        Dialog_SelectPlayerPool();
    }

    public void openPokerGame(View view) {
        LoadPokerGameActiveTable(ActiveTables_BF.RUMMY5);
    }


    public void openRummyDealGame(View view) {
//        LoadDealRummyActiveTable(ActiveTables_BF.RUMMY2);
        DialogDealType dialogDealType = new DialogDealType(this) {
            @Override
            protected void startGame(Bundle bundle) {
                openRummyGames(bundle);
            }
        };
        dialogDealType.show();
    }

    private void openRummyGames(Bundle bundle) {
        Intent intent = new Intent(context, RummyDealGame.class);
        if (bundle != null)
            intent.putExtras(bundle);

        if (context != null && !context.isFinishing())
            context.startActivity(intent);
    }


    public void openAndharbahar(View view) {
//        startActivity(new Intent(context, AndharBahar_Home_A.class));
        startActivity(new Intent(context, Andhar_Bahar_NewUI.class).putExtra("room_id", "1"));
    }

    public void openRoulette(View view) {
        startActivity(new Intent(context, RouleteGame_A.class).putExtra("room_id", "1"));
    }

    public void openTournament() {
        Intent intent = new Intent(context, TourList.class);
        context.startActivity(intent);
    }

    public void openColorPred(View view) {
//        str_colr_pred="1";
        Intent intent = new Intent(context, ColorPrediction.class);
//        intent.putExtra("room_id" ,gameModelArrayList.get(position).getId());
//        intent.putExtra("min_coin" ,gameModelArrayList.get(position).getMin_coin());
//        intent.putExtra("max_coin" ,gameModelArrayList.get(position).getMax_coin());
        context.startActivity(intent);
    }


    public void openDragonTiger(View view) {
        startActivity(new Intent(context, DragonTiger_A.class));
    }

    public void openCarRoulette(View view) {
        startActivity(new Intent(context, CarRoullete_A.class));
    }

    public void openAnimalRoullete(View view) {
        startActivity(new Intent(context, AnimalRoulette_A.class));
    }

    public interface itemClick {
        void OnDailyClick(String id);
    }

    TextView txtnotfound;

    private void LoadPullRummyActiveTable(String TAG) {
        RummyActiveTables_BF rummyActiveTables_bf = new RummyActiveTables_BF(TAG);
        rummyActiveTables_bf.show(getSupportFragmentManager(), rummyActiveTables_bf.getTag());
    }

    private void LoadPokerGameActiveTable(String TAG) {
        PokerActiveTables_BF pokerActiveTables_bf = new PokerActiveTables_BF(TAG);
        pokerActiveTables_bf.show(getSupportFragmentManager(), pokerActiveTables_bf.getTag());
    }

    private void LoadDealRummyActiveTable(String TAG) {
        RummyDealActiveTables_BF rummyDealActiveTables_bf = new RummyDealActiveTables_BF(TAG);
        rummyDealActiveTables_bf.show(getSupportFragmentManager(), rummyDealActiveTables_bf.getTag());
    }


    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public String kvalue(int number) {

        String numberString = "";
        if (Math.abs(number / 1000000) > 1) {
            numberString = (number / 1000000) + "m";

        } else if (Math.abs(number / 1000) > 1) {
            numberString = (number / 1000) + "k";

        } else {
            numberString = number + "";

        }
        return numberString;
    }

    @Override
    protected void onStart() {
        super.onStart();

        playSound(R.raw.game_soundtrack, true);
    }

    @Override
    protected void onDestroy() {
        stopPlaying();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlaying();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlaying();
    }

    Sound soundMedia;

    public void playSound(int sound, boolean isloop) {

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String value = prefs.getString("issoundon", "1");

        if (value.equals("1")) {
            soundMedia.PlaySong(sound, isloop, context);
        } else {
            stopPlaying();
        }


    }

    private void stopPlaying() {
        soundMedia.StopSong();
    }

    private void checkForBanState() {
        String user_state = "";
        if (Variables.latitude > 0 && Variables.longitude > 0) {
            Address address = getAddressFromLatLong(Variables.latitude, Variables.longitude, context);
            if (address != null)
                user_state = address.getAdminArea();
        }

        for (String state : ban_states) {
            if (state.trim().equalsIgnoreCase(user_state)) {
                DialogRestrictUser.getInstance(context).show();
                break;
            }
        }
    }

    public static Address getAddressFromLatLong(double lat, double long_temp, Context context) {

        Address address = null;

        if (lat <= 0 && long_temp <= 0)
            return address;

        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

            addresses = geocoder.getFromLocation(lat, long_temp, 1);
            address = addresses.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            // new GetClass().execute();
        }

        return address;
    }

    private boolean beforeClickPermissionRat;
    private boolean afterClickPermissionRat;

    public void requestLocationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            beforeClickPermissionRat = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissions(Functions.LOCATION_PERMISSIONS, Variables.REQUESTCODE_LOCATION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ENABLE_LOCATION_CODE) {
            if (resultCode == RESULT_OK) {

                storeLatlong();

            } else {
                finishAffinity();
            }
        }
    }

    // before after
// FALSE  FALSE  =  Was denied permanently, still denied permanently --> App Settings
// FALSE  TRUE   =  First time deny, not denied permanently yet --> Nothing
// TRUE   FALSE  =  Just been permanently denied --> Changing my caption to "Go to app settings to edit permissions"
// TRUE   TRUE   =  Wasn't denied permanently, still not denied permanently --> Nothing
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == Variables.REQUESTCODE_LOCATION) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    // user rejected the permission

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        afterClickPermissionRat = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
                    }
                    if ((!afterClickPermissionRat && !beforeClickPermissionRat)) {
                        // user also CHECKED "never ask again"
                        // you can either enable some fall back,
                        // disable features of your app
                        // or open another dialog explaining
                        // again the permission and directing to
                        // the app setting

//                            showUserClearAppDataDialog();

//                            openMapActivity();
                        finishAffinity();
                        break;
                    } else if ((afterClickPermissionRat && !beforeClickPermissionRat)) {

//                            if(!Functions.isAndroid11())
//                            {
//                                openMapActivity();
//                                break;
//                            }

                    } else {
                        showRationale(permission, R.string.permission_denied_contacts);
                        // user did NOT check "never ask again"
                        // context is a good place to explain the user
                        // why you need the permission and ask if he wants
                        // to accept it (the rationale)
                    }
                }
            }

            try {

                if (isPermissionGranted(grantResults)) {
                    enable_permission();
                } else {

                    if ((afterClickPermissionRat && !beforeClickPermissionRat)
                            || (afterClickPermissionRat && beforeClickPermissionRat)) {
                        EnableGPS();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

    }

    public void EnableGPS() {

        if (Functions.check_location_permissions(context)) {
            if (!GpsUtils.isGPSENABLE(context)) {
                requestLocationAccess();
//                showFragment();
            } else {
                enable_permission();
            }
        } else {
//            showFragment();
            requestLocationAccess();
        }
    }

    public void requestLocationAccess() {

        if (Functions.check_location_permissions(context)) {
            enable_permission();
        } else {
            requestLocationPermissions();
        }
    }


    private void showRationale(String permission, int permission_denied_contacts) {
    }


    private boolean isPermissionGranted(int[] grantResults) {
        boolean isGranted = true;

        for (int result : grantResults) {

            if (result != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                break;
            }

        }

        return isGranted;
    }


    private void storeLatlong() {
        LatLng latLng = getLatLong();
        Variables.latitude = latLng.latitude;
        Variables.longitude = latLng.longitude;

        checkForBanState();
    }

    public LatLng getLatLong() {
        if (getLocationlatlong != null)
            return getLocationlatlong.getLatlong();
        else {
            getLocationlatlong = new GetLocationlatlong(context);
        }

        return getLocationlatlong.getLatlong();
    }

    private void enable_permission() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!GpsStatus) {

            new GpsUtils(context).turnGPSOn(isGPSEnable -> {

                if (isGPSEnable)
                    enable_permission();

            });
        } else if (Functions.check_location_permissions(context)) {
//            dismissFragment();
            storeLatlong();
        }
    }

    GetLocationlatlong getLocationlatlong;

    private void initilizeLocation() {
        getLocationlatlong = new GetLocationlatlong(context);
    }
    Dialog dialog_send_money;
    public void dialog_send_money() {
        // custom dialog
        dialog_send_money = Functions.DialogInstance(context);
        dialog_send_money.setContentView(R.layout.dialog_send_money);
        dialog_send_money.setTitle("Send Money...");

        TextView tv_heading = dialog_send_money.findViewById(R.id.tv_heading);

        tv_heading.setText("Send Coin");

        final EditText edtForgetMobile = dialog_send_money.findViewById(R.id.edt_phone);
        final EditText edt_amount = dialog_send_money.findViewById(R.id.edt_amount);

        dialog_send_money.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Functions.checkisStringValid(Functions.getStringFromEdit(edtForgetMobile))) {
                    Functions.showToast(context, "Please enter Mobile Number.");
                    return;
                } else if (Functions.getStringFromEdit(edtForgetMobile).length() < 10) {
                    Functions.showToast(context, "Please Valid Mobile Number.");
                    return;
                } else if (!Functions.checkisStringValid(Functions.getStringFromEdit(edt_amount))) {
                    Functions.showToast(context, "Please Valid Mobile Number.");
                    return;
                }

                CALL_API_SendMoney(Functions.getStringFromEdit(edtForgetMobile), Functions.getStringFromEdit(edt_amount));


            }
        });

        dialog_send_money.findViewById(R.id.bt_no).setOnClickListener(v -> dialog_send_money.dismiss());

        dialog_send_money.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_send_money.dismiss();
            }
        });

        dialog_send_money.show();
        Functions.setDialogParams(dialog_send_money);
    }


    private void CALL_API_SendMoney(String edtForgetMobile, String edt_amount) {

        HashMap params = new HashMap();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        params.put("to_mobile", "" + edtForgetMobile);
        params.put("amount", "" + edt_amount);
        params.put("user_id", prefs.getString("user_id", ""));
        ApiRequest.Call_Api(context, Const.Transfer, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if (resp != null) {

                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.optString("code");
                        String message = jsonObject.optString("message");

                        if (code.equals("200")) {
                            dialog_send_money.dismiss();
                            Functions.showToast(context, "" + message);
                            UserProfile();
                        }else{
                            Functions.showToast(context, "" + message);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }

}



