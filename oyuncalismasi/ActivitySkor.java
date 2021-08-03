package com.info.oyuncalismasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ActivitySkor extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<Skorlar> skorlarListe;
    private TextView textViewSkorListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("skor");
        textViewSkorListe=findViewById(R.id.textViewSkorListe);

        skorlarListe = new ArrayList<>();
        String skorliste="";
        tumSkorlar();
        for(Skorlar s: skorlarListe){
            skorliste = s.getName()+":"+s.getSkor();
        }
        textViewSkorListe.setText(skorliste);
    }
    public void tumSkorlar(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                skorlarListe.clear();

                for(DataSnapshot d: snapshot.getChildren()){
                    Skorlar skor = d.getValue(Skorlar.class);
                    skor.setSkor_id(d.getKey());

                    skorlarListe.add(skor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

