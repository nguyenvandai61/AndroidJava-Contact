package com.example.contactv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayAdapter adapter;
    static ArrayList<Contact> arrayList;
    static Intent intent;
    static ListView lvContact;
    static Intent intentAddContact;
    static boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
            arrayList = (ArrayList<Contact>) savedInstanceState.getSerializable("contacts");

        lvContact = findViewById(R.id.lv_contact);
        System.out.println("Trang thai onCreate");
        // Tạo 1 list view hiển thị nội dung trong mảng các Content
        if (isRunning) {
            arrayList = new ArrayList<Contact>();
            adapter = new ContactListAdapter(this, arrayList);
            isRunning = false;
        }
        lvContact.setAdapter(adapter);


        // Tạo 1 intent để chuyển sang activity add_contact
        intent = new Intent(this, AddContactActivity.class);

        FloatingActionButton btnAddContact = findViewById(R.id.fab_add_contact);
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


        // Nhận intent của addContact
        intentAddContact = getIntent();
        Contact newContact = (Contact) intentAddContact.getSerializableExtra("contact");
        if (newContact != null) {
            arrayList.add(newContact);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("contacts", arrayList);
    }

}
