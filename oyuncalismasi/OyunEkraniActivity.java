package com.info.oyuncalismasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class OyunEkraniActivity extends AppCompatActivity {
    private ConstraintLayout cl;
    private TextView textViewSkor;
    private TextView textViewOyunaBasla;
    private ImageView saridaire;
    private ImageView anakarakter;
    private ImageView siyahkare;
    private ImageView kirmiziucgen;
    private ImageView siyahkare2;
    private ImageView siyahkare3;
    private ImageView saat;

    private int anakarakterX;
    private int anakarakterY;
    private int saridaireX;
    private int saridaireY;
    private int siyahkareX;
    private int siyahkareY;
    private int kirmiziucgenX;
    private int kirmiziucgenY;
    private int siyahkare2X;
    private int siyahkare2Y;
    private int siyahkare3X;
    private int siyahkare3Y;
    private int saatX;
    private int saatY;

    private int ekranGenisligi;
    private int ekranYuksekligi;
    private int anakarakterGenisligi;
    private int anakarakterYuksekligi;

    private int anakarakterHiz;
    private int saridaireHiz;
    private int kirmiziucgenHiz;
    private int siyahkareHiz;
    private int siyahkare2Hiz;
    private int siyahkare3Hiz;
    private int saatHiz;


    private int refreshtime=20;


    private boolean dokunmaKontrol= false;
    private boolean baslangicKontrol = false;

    private int skor=0;

    private Timer timer = new Timer();
    private Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);



        cl = findViewById(R.id.cl);
        textViewSkor = findViewById(R.id.textViewSkor);
        textViewOyunaBasla =findViewById(R.id.textViewOyunaBasla);
        saridaire = findViewById(R.id.saridaire);
        anakarakter = findViewById(R.id.anakarakter);
        siyahkare = findViewById(R.id.siyahkare);
        siyahkare2 = findViewById(R.id.siyahkare2);
        siyahkare3=findViewById(R.id.siyahkare3);
        kirmiziucgen = findViewById(R.id.kirmiziucgen);

        siyahkare.setY(-80);
        siyahkare.setX(-80);
        kirmiziucgen.setY(-80);
        kirmiziucgen.setX(-80);
        saridaire.setY(-80);
        saridaire.setX(-80);
        siyahkare2.setY(-800);
        siyahkare2.setX(-800);
        siyahkare3.setY(-800);
        siyahkare3.setX(-800);



        cl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(baslangicKontrol){
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        //Log.e("mesaj","ekrana dokunuldu");
                        dokunmaKontrol=true;
                    }
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                        //Log.e("mesaj","ekran bırakıldı");
                        dokunmaKontrol=false;


                    }

                }else{
                    baslangicKontrol=true;
                    textViewOyunaBasla.setVisibility(View.INVISIBLE);

                    anakarakterX=(int) anakarakter.getX();
                    anakarakterY=(int) anakarakter.getY();

                    anakarakterGenisligi=anakarakter.getWidth();
                    anakarakterYuksekligi=anakarakter.getHeight();
                    ekranGenisligi=cl.getWidth();
                    ekranYuksekligi=cl.getHeight();


                    saridaireHiz=Math.round(ekranGenisligi/60);
                    siyahkareHiz=Math.round(ekranGenisligi/60);
                    kirmiziucgenHiz=Math.round(ekranGenisligi/30);
                    siyahkare2Hiz=Math.round(ekranGenisligi/50);
                    siyahkare3Hiz=Math.round(ekranGenisligi/45);
                    Log.e("Hız mesaj","Normal Hız");

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                  if(skor>=1000){
                                      saridaireHiz=Math.round(ekranGenisligi/40);
                                      siyahkareHiz=Math.round(ekranGenisligi/40);
                                      kirmiziucgenHiz=Math.round(ekranGenisligi/20);
                                      siyahkare2Hiz=Math.round(ekranGenisligi/38);
                                      siyahkare3Hiz=Math.round(ekranGenisligi/36);
                                      Log.e("Hız mesaj","Yüksek Hız");

                                    }
                                    anakarakterHareketEttirme();
                                    cisimleriHareketEttir();
                                    carpismaKontrol();

                                }
                            });
                        }
                    },0,20);
                }
                return true;
            }
        });
    }

    public void anakarakterHareketEttirme(){
        anakarakterHiz=Math.round(ekranYuksekligi/60);

        if(dokunmaKontrol){
            anakarakterY-=anakarakterHiz;
        }else{
            anakarakterY+=anakarakterHiz;
        }

        if(anakarakterY<=0){
            anakarakterY=0;

        }
        if(anakarakterY>=ekranYuksekligi-anakarakterYuksekligi){
            anakarakterY=ekranYuksekligi-anakarakterYuksekligi;

        }

        anakarakter.setY(anakarakterY);

    }
    public void cisimleriHareketEttir(){
        /*saridaireHiz=Math.round(ekranGenisligi/60);
        siyahkareHiz=Math.round(ekranGenisligi/60);
        kirmiziucgenHiz=Math.round(ekranGenisligi/30);
        siyahkare2Hiz=Math.round(ekranGenisligi/50);
        siyahkare3Hiz=Math.round(ekranGenisligi/45);*/


        siyahkareX-=siyahkareHiz;

        if(siyahkareX<(-anakarakterGenisligi)){
            siyahkareX=ekranGenisligi+20;
            siyahkareY= (int) Math.floor(Math.random()*ekranYuksekligi);
        }
        siyahkare.setX(siyahkareX);
        siyahkare.setY(siyahkareY);

        saridaireX-=saridaireHiz;

        if(saridaireX<(-anakarakterGenisligi)){
            saridaireX=ekranGenisligi+20;
            saridaireY= (int) Math.floor(Math.random()*ekranYuksekligi);
        }
        saridaire.setX(saridaireX);
        saridaire.setY(saridaireY);

        kirmiziucgenX-=kirmiziucgenHiz;

        if(kirmiziucgenX<(-anakarakterGenisligi)){
            kirmiziucgenX=ekranGenisligi+20;
            kirmiziucgenY= (int) Math.floor(Math.random()*ekranYuksekligi);
        }
        kirmiziucgen.setX(kirmiziucgenX);
        kirmiziucgen.setY(kirmiziucgenY);

        if(skor>500) {
            siyahkare2X -= siyahkare2Hiz;

            if (siyahkare2X < (-anakarakterGenisligi)) {
                siyahkare2X = ekranGenisligi + 20;
                siyahkare2Y = (int) Math.floor(Math.random() * ekranYuksekligi);
            }
            siyahkare2.setX(siyahkare2X);
            siyahkare2.setY(siyahkare2Y);
        }

        if(skor>800) {
            siyahkare3X -= siyahkare3Hiz;

            if (siyahkare3X < (-anakarakterGenisligi)) {
                siyahkare3X = ekranGenisligi + 20;
                siyahkare3Y = (int) Math.floor(Math.random() * ekranYuksekligi);
            }
            siyahkare3.setX(siyahkare3X);
            siyahkare3.setY(siyahkare3Y);
        }




    }

    public void carpismaKontrol(){
        int saridaireMerkezX=saridaireX+saridaire.getWidth()/2;
        int saridaireMerkezY=saridaireY+saridaire.getHeight()/2;

        if(0<= saridaireMerkezX && anakarakterGenisligi >= saridaireMerkezX && saridaireMerkezY <= anakarakterY+anakarakterYuksekligi && saridaireMerkezY >= anakarakterY){
            skor+=80;
            saridaireX=-30;
        }

        int kirmiziucgenMerkezX=kirmiziucgenX+kirmiziucgen.getWidth()/2;
        int kirmiziucgenMerkezY=kirmiziucgenY+kirmiziucgen.getHeight()/2;

        if(0<= kirmiziucgenMerkezX && anakarakterGenisligi >= kirmiziucgenMerkezX && kirmiziucgenMerkezY <= anakarakterY+anakarakterYuksekligi && kirmiziucgenMerkezY >= anakarakterY){
            skor+=50;
            kirmiziucgenX=-30;
        }

        int siyahkareMerkezX=siyahkareX+siyahkare.getWidth()/2;
        int siyahkareMerkezY=siyahkareY+siyahkare.getHeight()/2;

        if(0<= siyahkareMerkezX && anakarakterGenisligi >= siyahkareMerkezX && siyahkareMerkezY <= anakarakterY+anakarakterYuksekligi && siyahkareMerkezY >= anakarakterY){
            Log.e("mesaj", "siyahkare1 değdi");
            siyahkareX=-30;
            timer.cancel();
            timer=null;

            Intent intent = new Intent(OyunEkraniActivity.this,SonucEkraniActivity.class);
            intent.putExtra("skor",skor);
            startActivity(intent);
        }

        int siyahkare2MerkezX=siyahkare2X+siyahkare2.getWidth()/2;
        int siyahkare2MerkezY=siyahkare2Y+siyahkare2.getHeight()/2;
        if(skor>500) {
            if (0 <= siyahkare2MerkezX && anakarakterGenisligi >= siyahkare2MerkezX && siyahkare2MerkezY <= anakarakterY + anakarakterYuksekligi && siyahkare2MerkezY >= anakarakterY) {
                Log.e("mesaj", "siyahkare2 değdi");
                siyahkare2X = -30;
                timer.cancel();
                timer = null;

                Intent intent = new Intent(OyunEkraniActivity.this, SonucEkraniActivity.class);
                intent.putExtra("skor", skor);
                startActivity(intent);
            }
        }

        int siyahkare3MerkezX=siyahkare3X+siyahkare3.getWidth()/2;
        int siyahkare3MerkezY=siyahkare3Y+siyahkare3.getHeight()/2;
        if(skor>800) {
            if (0 <= siyahkare3MerkezX && anakarakterGenisligi >= siyahkare3MerkezX && siyahkare3MerkezY <= anakarakterY + anakarakterYuksekligi && siyahkare3MerkezY >= anakarakterY) {
                Log.e("mesaj", "siyahkare3 değdi");
                siyahkare3X = -30;
                timer.cancel();
                timer = null;

                Intent intent = new Intent(OyunEkraniActivity.this, SonucEkraniActivity.class);
                intent.putExtra("skor", skor);
                startActivity(intent);
            }
        }





        textViewSkor.setText(String.valueOf(skor));




    }
    /*public void normalHız(){
        saridaireHiz=Math.round(ekranGenisligi/60);
        siyahkareHiz=Math.round(ekranGenisligi/60);
        kirmiziucgenHiz=Math.round(ekranGenisligi/30);
        siyahkare2Hiz=Math.round(ekranGenisligi/50);
        saatHiz=Math.round(ekranGenisligi/38);
    }*/




}