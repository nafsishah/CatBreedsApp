package com.example.catbreeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    String url;
    private EditText enterBreed;
    private TextView showBreed;
    private TextView showDescription;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBreed = findViewById(R.id.showBreed);
        enterBreed=findViewById(R.id.enterBreed);
        showDescription=findViewById(R.id.showDescription);

        Fragment fragment = new FavouriteRecyclerFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.favourite) {
                    Fragment fragment = new FavouriteRecyclerFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void swapFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }

    public void sendRequestOnClick(View v){
        BreedRequest dR = new BreedRequest(this, showBreed, showDescription);
        url= breedEntries();
        dR.execute(url);
    }

    private String breedEntries() {
        final String word = enterBreed.getText().toString();
        final String search = "search?q=";

        return "https://api.thecatapi.com/v1/breeds/" + search + word;

    }

}


