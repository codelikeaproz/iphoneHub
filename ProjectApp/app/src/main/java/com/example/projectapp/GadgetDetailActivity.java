package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GadgetDetailActivity extends AppCompatActivity {

    private String title;
    private String productDescription;
    private int productImg;
    private String imageUriString;
    private Button BuyHereBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadget_detail);

        title = getIntent().getStringExtra("GadgetName");
        productDescription = getIntent().getStringExtra("GadgetDescription");
        productImg = getIntent().getIntExtra("GadgetImgView", -1); // Use -1 as default value
        imageUriString = getIntent().getStringExtra("GadgetImgUri");
        String gadgetUrl = getIntent().getStringExtra("GadgetUrl");

        TextView titleNameTextView = findViewById(R.id.GadgetTitle);
        TextView description = findViewById(R.id.GadgetDescription);
        ImageView imageView = findViewById(R.id.GadgetImgView);
        BuyHereBtn = findViewById(R.id.webLink);
        Button backButton = findViewById(R.id.Back_button);
        titleNameTextView.setText(title);
        description.setText(productDescription);



        if (imageUriString != null && !imageUriString.isEmpty()) {
            Uri imageUri = Uri.parse(imageUriString);
            imageView.setImageURI(imageUri);
        } else if (productImg != -1) {
            imageView.setImageResource(productImg);
        }
        else {
            imageView.setImageResource(R.drawable.iphone11pro); // Default image
        }


        BuyHereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gadgetUrl != null && !gadgetUrl.isEmpty()) {
                    gotoUrl(gadgetUrl);
                } else {
                    Toast.makeText(GadgetDetailActivity.this, "No Website Linked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void gotoUrl(String url) {
        try {
            Uri uri = Uri.parse(url);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception e) {
            Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show();
        }
    }
}
