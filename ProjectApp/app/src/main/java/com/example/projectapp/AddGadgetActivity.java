package com.example.projectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class AddGadgetActivity extends AppCompatActivity {

    private EditText editTextName, editTextColor, editTextStorage, editTextPrice, editTextRAM,
            editTextOS, editTextCPU, editTextBattery, editTextDisplay, editTextCamera,
            editTextDescription, editTextUrl;
    private Button buttonSave, buttonSelectImage;
    private ImageView imageViewGadget;
    private Uri selectedImageUri;

    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gadget);

        editTextName = findViewById(R.id.editTextName);
        editTextColor = findViewById(R.id.editTextColor);
        editTextStorage = findViewById(R.id.editTextStorage);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextRAM = findViewById(R.id.editTextRAM);
        editTextOS = findViewById(R.id.editTextOS);
        editTextCPU = findViewById(R.id.editTextCPU);
        editTextBattery = findViewById(R.id.editTextBattery);
        editTextDisplay = findViewById(R.id.editTextDisplay);
        editTextCamera = findViewById(R.id.editTextCamera);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextUrl = findViewById(R.id.editTextUrl);
        buttonSave = findViewById(R.id.buttonSave);
        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        imageViewGadget = findViewById(R.id.imageViewGadget);

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            selectedImageUri = data.getData();
                            imageViewGadget.setImageURI(selectedImageUri);
                        }
                    }
                });

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGadget();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    private void saveGadget() {
        String name = editTextName.getText().toString().trim();
        String color = editTextColor.getText().toString().trim();
        String storage = editTextStorage.getText().toString().trim();
        String price = editTextPrice.getText().toString().trim();
        String ram = editTextRAM.getText().toString().trim();
        String os = editTextOS.getText().toString().trim();
        String cpu = editTextCPU.getText().toString().trim();
        String battery = editTextBattery.getText().toString().trim();
        String display = editTextDisplay.getText().toString().trim();
        String camera = editTextCamera.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String url = editTextUrl.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a gadget name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", name);
        resultIntent.putExtra("color", color);
        resultIntent.putExtra("storage", storage);
        resultIntent.putExtra("price", price);
        resultIntent.putExtra("ram", ram);
        resultIntent.putExtra("os", os);
        resultIntent.putExtra("cpu", cpu);
        resultIntent.putExtra("battery", battery);
        resultIntent.putExtra("display", display);
        resultIntent.putExtra("camera", camera);
        resultIntent.putExtra("description", description);
        resultIntent.putExtra("url", url);

        if (selectedImageUri != null) {
            resultIntent.putExtra("imageUri", selectedImageUri.toString());
        }

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}