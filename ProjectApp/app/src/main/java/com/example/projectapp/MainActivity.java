package com.example.projectapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_GADGET_REQUEST_CODE = 1;

    private RecyclerView recyclerView;
    private GadgetAdapter gadgetAdapter;
    private TextView NoData;
    private SearchView searchView;
    private List<Gadget> gadgetList;
    private Button addGadgetButton;
    private ActivityResultLauncher<Intent> addGadgetLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoData = findViewById(R.id.NoDataFoundText);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        addGadgetButton = findViewById(R.id.addGadgetButton);

        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        getAllGadgetList(this);
        gadgetAdapter = new GadgetAdapter(this, gadgetList);
        recyclerView.setAdapter(gadgetAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        addGadgetLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            handleNewGadgetResult(data);
                        }
                    }
                });

        addGadgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddGadgetActivity.class);
                addGadgetLauncher.launch(intent);
            }
        });


    }

    private void handleNewGadgetResult(Intent data) {
        String name = data.getStringExtra("name");
        String color = data.getStringExtra("color");
        String storage = data.getStringExtra("storage");
        String price = data.getStringExtra("price");
        String ram = data.getStringExtra("ram");
        String os = data.getStringExtra("os");
        String cpu = data.getStringExtra("cpu");
        String battery = data.getStringExtra("battery");
        String display = data.getStringExtra("display");
        String camera = data.getStringExtra("camera");
        String description = data.getStringExtra("description");
        String url = data.getStringExtra("url");
        String imageUriString = data.getStringExtra("imageUri");

        Uri imageUri = null;
        if (imageUriString != null && !imageUriString.isEmpty()) {
            imageUri = Uri.parse(imageUriString);
        }

        Gadget newGadget = new Gadget(
                name,
                "Color: " + color,
                "Storage: " + storage,
                "$ " + price,
                "RAM: " + ram,
                "OS: " + os,
                "CPU: " + cpu,
                "Battery: " + battery,
                "Display: " + display,
                "Camera: " + camera,
                imageUri,
                description,
                url
        );

        gadgetList.add(newGadget);
        gadgetAdapter.notifyItemInserted(gadgetList.size() - 1);
        Toast.makeText(this, "New gadget added", Toast.LENGTH_SHORT).show();
    }

    private void filterList(String text) {
        List<Gadget> filteredList = new ArrayList<>();
        for (Gadget item : gadgetList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (!filteredList.isEmpty()) {
            gadgetAdapter.setFilteredList(filteredList);
        }
        NoDataFound(filteredList);
    }

    private void NoDataFound(List<Gadget> gadgetList) {
        if (gadgetList.isEmpty()) {
            NoData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            NoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    public List<Gadget> getAllGadgetList(Context context) {
        gadgetList = new ArrayList<>();

        gadgetList.add(new Gadget(
                "Apple iPhone 14",
                "Color: Midnight",
                "Storage: 512GB",
                "$1,399",
                "RAM: 4GB",
                "iOS 17.4.",
                "CPU: Apple A15 Bionic",
                "Battery: 3279 mAh",
                "Display: 6.1-inch Super Retina XDR OLED",
                "Main Camera: Dual 12 MP (Main, Ultra Wide)",
                Uri.parse("android.resource://" + context.getPackageName() + "/" + R.drawable.iphone14),
                "The Apple iPhone 14 is now official and starts at P56,990 and is available for Pre-Order on September 9. Apple's newest Phone and it is the standard model among the 4 that was introduced by apple in 2022. The iPhone 14 is much improved in comparison to its iPhone 13 predecessor but not as much as a big jump in terms of design and specs unlike on its bigger siblings.",
                "https://www.apple.com/ph/shop/buy-iphone/iphone-14"));

        gadgetList.add(new Gadget(
                "Apple iPhone 13 Pro Max",
                "Colors: Green",
                "Storage: 128GB",
                "$1,199",
                "RAM: 6GB",
                "OS: iOS 15",
                "CPU: Apple A15 Bionic (5 nm)",
                "Battery: 4352 mAh",
                "Display: 6.7\" Super Retina XDR OLED",
                "Main Camera: Triple 12 MP (Telephoto, Ultra Wide)",
                Uri.parse("android.resource://" + context.getPackageName() + "/" +   R.drawable.iphone13promax),

                "Apple's iPhone 13 Pro Max is the \"Max\" version in the iPhone 13 series of phones. It sports a bigger, 6.7 in XDR OLED display, a bigger 4352 mAh of battery, and an overall better phone among the iPhone 13 series.",
                "https://www.apple.com/ph/shop/buy-iphone/iphone-13"));

        gadgetList.add(new Gadget(
                "Apple iPhone 13",
                "Color: Starlight",
                "Storage: 128GB",
                "$899.89",
                "RAM: 4GB",
                "OS: iOS 15",
                "CPU: Apple A15 Bionic",
                "Battery: 3240 mAh",
                "Display: 6.1\" Super Retina XDR OLED",
                "Main Camera: Dual 12 MP (Main, Ultra Wide)",
                Uri.parse("android.resource://" + context.getPackageName() + "/" + R.drawable.iphone13),
                "Apple's iPhone 13 is the base version among the iPhone 13 lineup. It sports a newer Apple A15 Bionic, a 6.1 in XDR OLED display, and 12MP cameras on the rear and front. iPhone 13 is available in 3 variants.",
                "https://www.apple.com/ph/shop/buy-iphone/iphone-13"));

        gadgetList.add(new Gadget(
                "Apple iPhone 12",
                "Color: Blue",
                "Storage:128GB",
                "$799.79",
                "RAM:  4GB",
                "OS: iOS 14.1",
                "CPU: Apple A14 Bionic",
                "Battery: 2815 mAh Li-Ion",
                "Display: 6.1\" Super Retina XDR OLED",
                "Main Camera: Dual 12 MP",
                Uri.parse("android.resource://" + context.getPackageName() + "/" + R.drawable.iphone12),
                "The iPhone 12 is Apple's flagship smartphone introduced in the fall of 2020. The iPhone 12 lineup consists of four devices ranging from 5.4-inches to 6.7-inches in screen size, with flagship features including 5G connectivity, OLED displays, and more.",
                "https://shopee.ph/Apple-iPhone-12-(64GB-128GB-256GB)-i.350609763.8002421072?sp_atk=abd76af9-095f-46ed-9fa9-514f7dfad4c8&xptdk=abd76af9-095f-46ed-9fa9-514f7dfad4c8"));

        gadgetList.add(new Gadget(
                "Apple iPhone 11 Pro",
                "Color: Gray",
                "Storage: 128GB",
                "$241.74",
                "RAM: 4GB",
                "iOS 13",
                "CPU: Apple A13 Bionic",
                "Battery: 3110 mAh",
                "Display: 6.1\" Liquid Retina IPS LCD",
                "Selfie Camera: 12 MP TrueDepth",
                Uri.parse("android.resource://" + context.getPackageName() + "/" + R.drawable.iphone11pro),
                "The iPhone 11 Pro features a textured matte glass back, polished stainless steel band, and comes in four stunning finishes. It’s powered by the A13 Bionic chip, has excellent battery life, and boasts a triple-camera system with advanced features like Portrait mode and Night mode. Additionally, it’s water and dust resistant (IP68-rated) and runs on iOS 13.",
                "https://shopee.ph/Apple-iPhone-11-(64GB-128GB-256GB)-i.350609763.9002420377?sp_atk=ef99f67e-0d4d-489c-9415-192eff7419d9&xptdk=ef99f67e-0d4d-489c-9415-192eff7419d9"));
        return gadgetList;
    }
}