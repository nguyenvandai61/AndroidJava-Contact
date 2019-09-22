package com.example.contactv1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class AddContactActivity extends AppCompatActivity {
    EditText edtName, edtPhone;
    ImageView imgVAvatar;
    Button btnLoadImage;
    private String mName, mPhone;
    private String mAvatar;

    static Intent intent;
    static Intent intentSendMainActivity;
    private static int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        setUpToolbar();

        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_mobile);
        imgVAvatar = findViewById(R.id.imgv_add_avatar);
        btnLoadImage = findViewById(R.id.btn_load_picture);

        intent = new Intent(this, MainActivity.class);
        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        pickImageGallery();
                    }
                } else {
                    pickImageGallery();
                }
            }
        });
    }

    public void pickImageGallery() {

        Intent intentGallery = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intentGallery, IMAGE_PICK_CODE);
        System.out.println("Vao result load image");
    }

    private void setUpToolbar() {
        Toolbar tb = findViewById(R.id.toolbarAdd);
        setSupportActionBar(tb);
        tb.setNavigationIcon(R.drawable.ic_close_black_24dp);
        intentSendMainActivity = new Intent(this, MainActivity.class);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentSendMainActivity);
            }
        });
    }

    boolean isValidated() {
        assignContact();
        return mName != "" && mPhone != "";
    }

    void assignContact() {
        mName = edtName.getText().toString();
        mPhone = edtPhone.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.act_check: {
                if (isValidated()) {
                    Contact newContact = new Contact(mName, mPhone, mAvatar);
                    intent.putExtra("contact", newContact);
                }
                break;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
        startActivity(intent);

        System.out.println(22222);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageGallery();
                } else {
                    Toast.makeText(this, "Không mở được ảnh", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imgVAvatar.setImageURI(data.getData());
            mAvatar = data.getData().toString();
        }
    }
}
