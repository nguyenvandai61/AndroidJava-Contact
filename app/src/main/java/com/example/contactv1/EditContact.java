package com.example.contactv1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EditContact extends AppCompatActivity {

    EditText edtName, edtMobile;
    ImageView civAvatar;
    static Intent intentSendMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        setUpToolbar();

        edtName = findViewById(R.id.edt_edit_name);
        edtMobile = findViewById(R.id.edt_edit_mobile);
        civAvatar = findViewById(R.id.civ_edit_avatar);

        // Nạp đối tượng cần chỉnh sửa
        Contact contact = (Contact) getIntent().getSerializableExtra("contact");

        edtName.setText(contact.getmName());
        edtMobile.setText(contact.getmMobile());

        if (contact.getmAvatar() != null)
            civAvatar.setImageURI(Uri.parse(contact.getmAvatar()));


        // TODO Chỉnh sửa đối tượng


        // TODO Nhấn Check Icon để
        // TODO Lưu đối tượng, thay thế đối tượng này với đối tượng đã chọn chỉnh sửa

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void setUpToolbar() {
        Toolbar tb = findViewById(R.id.toolbarEdit);
        setSupportActionBar(tb);

        // Nhấn Cancel Icon để trở về MainActivity
        tb.setNavigationIcon(R.drawable.ic_close_black_24dp);
        intentSendMainActivity = new Intent(this, MainActivity.class);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentSendMainActivity);
            }
        });
    }
}
