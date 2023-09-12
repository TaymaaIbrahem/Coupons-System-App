package com.example.databases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    Button btnCompaniesManagment, btnCustomersManagment;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnCompaniesManagment = findViewById(R.id.admin_btnCompaniesMang);
        btnCustomersManagment = findViewById(R.id.admin_btnCustomersMang);
        btnBack = findViewById(R.id.admin_btnBack);

        ButtonsClick buttonsClick = new ButtonsClick();
        btnBack.setOnClickListener(buttonsClick);
        btnCustomersManagment.setOnClickListener(buttonsClick);
        btnCompaniesManagment.setOnClickListener(buttonsClick);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.admin_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AdminFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_home){
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new AdminFragment()).commit();
        }
       /* if(item.getItemId() == R.id.nav_settings){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SettingsFragment()).commit();
        }
        if(item.getItemId() == R.id.nav_share) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ShareFragment()).commit();
        }
        if (item.getItemId() == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AboutFragment()).commit();
        }*/
        if (item.getItemId() == R.id.nav_logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    class ButtonsClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(view.getId() == btnCompaniesManagment.getId()){
                Toast.makeText(AdminActivity.this, "Yalla", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this,ManageCompaniesActivity.class);
                startActivity(intent);
            }
            if(view.getId() == btnCustomersManagment.getId()){
                Intent intent = new Intent(AdminActivity.this,ManageCustomersActivity.class);
                startActivity(intent);
            }
            if(view.getId() == btnBack.getId()){
                finish();
            }
        }
    }
}